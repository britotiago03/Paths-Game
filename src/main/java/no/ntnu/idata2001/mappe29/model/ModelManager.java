package no.ntnu.idata2001.mappe29.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.scene.image.Image;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.actions.AddGoldAction;
import no.ntnu.idata2001.mappe29.model.actions.AddScoreAction;
import no.ntnu.idata2001.mappe29.model.actions.AddToInventoryAction;
import no.ntnu.idata2001.mappe29.model.actions.ReduceHealthAction;
import no.ntnu.idata2001.mappe29.model.exceptions.CorruptStoryException;
import no.ntnu.idata2001.mappe29.model.exceptions.InvalidLinksException;
import no.ntnu.idata2001.mappe29.model.exceptions.InvalidPassagesException;
import no.ntnu.idata2001.mappe29.model.exceptions.ModelComponentsInitializationException;
import no.ntnu.idata2001.mappe29.model.filehandling.StoryFileFormatter;
import no.ntnu.idata2001.mappe29.model.filehandling.StoryFileManager;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a Model Manager that is responsible for managing the business logic part of the
 * application.
 *
 * @author Tiago Brito.
 * @version 2023.05.19.
 */
public class ModelManager {
  private final Map<String, Story> storyCollection;
  private final Random randomGenerator;
  private final StoryFileManager storyFileManager;
  private User user;
  private Player player;
  private final PathsApp pathsApp;
  private final List<Screen> storyCollectionSubscribers;

  /**
   * Creates an instance of ModelManager.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws ModelComponentsInitializationException if the initialization of the model components
   *                                                fails.
   * @throws IllegalArgumentException               if PathsApp is null.
   */
  public ModelManager(PathsApp pathsApp) throws ModelComponentsInitializationException,
      CorruptStoryException {
    if (pathsApp == null) {
      throw new IllegalArgumentException("PathsApp cannot be null");
    }
    this.pathsApp = pathsApp;
    this.storyCollection = new HashMap<>();
    this.randomGenerator = new Random();
    this.storyFileManager = new StoryFileManager(new StoryFileFormatter());
    this.initializeModelComponents();
    this.user = new User();
    this.storyCollectionSubscribers = new ArrayList<>();
  }

  /**
   * Gets the story collection of this model manager.
   *
   * @return the story collection of this model manager.
   */
  public Map<String, Story> getStoryCollection() {
    return this.storyCollection;
  }

  /**
   * Gets a random story from the story collection.
   *
   * @return a random story from the story collection.
   */
  public Story getRandomStory() {
    int randomStoryIndex = this.randomGenerator.nextInt(this.storyCollection.size());
    return this.storyCollection.values().stream().toList().get(randomStoryIndex);
  }

  /**
   * Gets the story file manager.
   *
   * @return the story file manager.
   */
  public StoryFileManager getStoryFileManager() {
    return this.storyFileManager;
  }

  /**
   * Sets the user of this ModelManager to the specified user.
   *
   * @param user the specified user.
   * @throws IllegalArgumentException if the user is null.
   */
  public void setUser(User user) {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null");
    }
    this.user = user;
  }

  /**
   * Gets the user of this Model Manager.
   *
   * @return the user of this Model Manager.
   */
  public User getUser() {
    return this.user;
  }

  /**
   * Gets the story collection subscribers of this model manager.
   *
   * @return the story collection subscribers of this model manager.
   */
  public List<Screen> getStoryCollectionSubscribers() {
    return this.storyCollectionSubscribers;
  }

  /**
   * Sets the player of this Model Manager to the specified player.
   *
   * @param player the specified player.
   * @throws IllegalArgumentException if the specified player is null.
   */
  public void setPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    this.player = player;
  }

  /**
   * Gets the player of this Model Manager.
   *
   * @return the player of this Model Manager.
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Initializes the model components of the Paths application.
   *
   * @throws ModelComponentsInitializationException if the initialization of the model components
   *                                                fails.
   */
  private void initializeModelComponents()
      throws ModelComponentsInitializationException, CorruptStoryException {
    Story trollAdventureStory;
    try {
      trollAdventureStory = this.createTrollAdventureStory();
      Image storyImage = new Image(this.pathsApp.getClass()
          .getResource("/images/icons/story_icon.png").toExternalForm());
      trollAdventureStory.setImage(storyImage);
      Image storyBackgroundImage = new Image(this.getClass()
          .getResource("/images/backgrounds/forest_image.png").toExternalForm());
      trollAdventureStory.setBackgroundImage(storyBackgroundImage);
      Image storyDarkBackgroundImage = new Image(this.getClass()
          .getResource("/images/backgrounds/forest_image_dark.png").toExternalForm());
      trollAdventureStory.setDarkBackgroundImage(storyDarkBackgroundImage);
      trollAdventureStory.setIntro1Text(
          "Embark on an extraordinary quest through the realm of trolls. In this immersive"
              + " adventure, you'll navigate treacherous landscapes and face the enigmatic"
              + " creatures that dwell within.");
      trollAdventureStory.setIntro2Text(
          "Your choices will shape your path as you navigate through"
              + " cunning puzzles and daunting obstacles.");
    } catch (InvalidLinksException | InvalidPassagesException exception) {
      throw new ModelComponentsInitializationException(exception.getMessage());
    }
    this.storyCollection.put(trollAdventureStory.getTitle(), trollAdventureStory);
  }

  /**
   * Creates the Troll Adventure story.
   *
   * @return the Troll Adventure story.
   * @throws InvalidLinksException    if passages of the story are created with invalid links.
   * @throws InvalidPassagesException if there is no opening passage in the passages of the
   *                                  story.
   */
  public Story createTrollAdventureStory() throws InvalidLinksException, InvalidPassagesException,
      CorruptStoryException {
    List<Passage> trollAdventureStoryPassages = this.createTrollAdventureStoryPassages();
    Passage openingPassage = this.getOpeningPassage(trollAdventureStoryPassages);

    Story story = new Story("Troll Adventure", openingPassage);

    trollAdventureStoryPassages.remove(openingPassage);
    for (Passage passage : trollAdventureStoryPassages) {
      story.addPassage(passage);
    }
    if (story.hasBrokenLinks()) {
      throw new CorruptStoryException("Story created during application "
          + "initialization is corrupt. Broken links have been detected");
    }

    return story;
  }

  /**
   * Gets the opening passage in the specified list of passages.
   *
   * @param passages the specified list of passages.
   * @return the opening passage
   * @throws InvalidPassagesException if the specified list of passages does not have an opening
   *                                  passage.
   */
  private Passage getOpeningPassage(List<Passage> passages) throws InvalidPassagesException {
    Passage openingPassage = null;
    boolean openingPassageFound = false;

    Iterator<Passage> passagesIterator = passages.iterator();
    while (!openingPassageFound && passagesIterator.hasNext()) {
      Passage passage = passagesIterator.next();
      if (passage.getTitle().contains("opening_passage")) {
        openingPassage = passage;
        openingPassageFound = true;
      } else if (openingPassage == null && !passagesIterator.hasNext()) {
        throw new InvalidPassagesException(
            "Troll Adventure Story Passages does not have an opening "
                + "passage");
      }
    }

    return openingPassage;
  }

  /**
   * Creates the passages for the Troll Adventure story.
   *
   * @return passages the passages for the Troll Adventure story.
   * @throws InvalidLinksException if passages are created with invalid links.
   */
  private List<Passage> createTrollAdventureStoryPassages() throws InvalidLinksException {
    Passage openingPassage = new Passage("troll_adventure_opening_passage",
        "You start your quest by walking through a bridge, when suddenly...");
    Link continueLink = new Link("Continue",
        "troll_adventure_you_see_troll_passage");
    continueLink.addAction(new AddToInventoryAction("Sword"));
    continueLink.addAction(new AddToInventoryAction("Magic_Grimoire"));
    openingPassage.addLink(continueLink);
    Image openingPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/opening_image.png").toExternalForm());
    openingPassage.setBackgroundImage(openingPassageImage);

    List<Passage> passages = new ArrayList<>();
    passages.add(openingPassage);

    Passage youSeeTrollPassage = new Passage("troll_adventure_you_see_troll_passage",
        "You come face to face with a fearsome troll. You have your sword and your magic "
            + "grimoire in your inventory");
    Link continueLink2 = new Link("Continue", "troll_adventure_what_to_do_passage1");
    youSeeTrollPassage.addLink(continueLink2);
    Image youSeeTrollPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/you_see_troll_image.png")
        .toExternalForm());
    youSeeTrollPassage.setBackgroundImage(youSeeTrollPassageImage);
    passages.add(youSeeTrollPassage);


    Link talkToTrollLink = new Link("Talk to the troll",
        "troll_adventure_talk_to_troll_passage");
    talkToTrollLink.addAction(new AddScoreAction(50));
    Link attackTheTrollLink = new Link("Attack the troll",
        "troll_adventure_attack_troll_passage");
    attackTheTrollLink.addAction(new AddScoreAction(100));
    Link runAwayLink = new Link("Run away", "troll_adventure_run_away_passage");
    runAwayLink.addAction(new ReduceHealthAction(100));
    Passage whatToDoPassage1 = new Passage("troll_adventure_what_to_do_passage1",
        "What will you do?");
    whatToDoPassage1.addAllLinks(talkToTrollLink, attackTheTrollLink, runAwayLink);
    whatToDoPassage1.setBackgroundImage(youSeeTrollPassageImage);
    passages.add(whatToDoPassage1);

    Passage talkToTrollPassage = new Passage("troll_adventure_talk_to_troll_passage",
        "How do you want to talk to the troll?");
    Link sayHelloLink = new Link("Say hello", "troll_adventure_say_hello_passage");
    sayHelloLink.addAction(new ReduceHealthAction(100));
    Link blowKissLink = new Link("Blow a kiss", "troll_adventure_blow_kiss_passage");
    blowKissLink.addAction(new AddScoreAction(100));
    talkToTrollPassage.addAllLinks(sayHelloLink, blowKissLink);
    Image talkToTrollPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/talk_to_troll_image.png")
        .toExternalForm());
    talkToTrollPassage.setBackgroundImage(talkToTrollPassageImage);
    passages.add(talkToTrollPassage);

    Passage sayHelloPassage = new Passage("troll_adventure_say_hello_passage",
        "The troll doesn't understand what you say and kills you.");
    Link failedContinueLink = new Link("Continue", "troll_adventure_story_failed"
        + "_passage");
    sayHelloPassage.addLink(failedContinueLink);
    Image sayHelloPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/say_hello_image.png")
        .toExternalForm());
    sayHelloPassage.setBackgroundImage(sayHelloPassageImage);
    passages.add(sayHelloPassage);

    Passage blowKissPassage = new Passage("troll_adventure_blow_kiss_passage",
        "The troll is surprised and decides to move closer to you.");
    Link kissTrollLink = new Link("Kiss the troll",
        "troll_adventure_kiss_troll_passage");
    kissTrollLink.addAction(new AddScoreAction(100));
    blowKissPassage.addAllLinks(kissTrollLink, attackTheTrollLink);
    Image blowKissPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/blow_kiss_image.png")
        .toExternalForm());
    blowKissPassage.setBackgroundImage(blowKissPassageImage);
    passages.add(blowKissPassage);

    Passage kissTrollPassage = new Passage("troll_adventure_kiss_troll_passage",
        "The troll kisses you back and takes you to her house.");
    Link tryToMakeLoveLink = new Link("Try to make love",
        "troll_adventure_try_to_make_love_passage");
    tryToMakeLoveLink.addAction(new AddGoldAction(1000));
    tryToMakeLoveLink.addAction(new AddScoreAction(300));
    kissTrollPassage.addAllLinks(tryToMakeLoveLink, runAwayLink);
    Image kissTrollPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/kiss_troll_image.png")
        .toExternalForm());
    kissTrollPassage.setBackgroundImage(kissTrollPassageImage);
    passages.add(kissTrollPassage);

    Passage tryToMakeLovePassage = new Passage("troll_adventure_try_to_make_love_passage",
        "In a moment of passionate connection, you and the troll share a romantic love "
            + "session. As a token of appreciation, the troll rewards you with 1000 gold coins. "
            + "You receive 300 score points.");
    Link successContinueLink = new Link("Continue", "troll_adventure_story_success"
        + "_passage");
    tryToMakeLovePassage.addLink(successContinueLink);
    Image tryToMakeLovePassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/try_to_make_love_image.png")
        .toExternalForm());
    tryToMakeLovePassage.setBackgroundImage(tryToMakeLovePassageImage);
    passages.add(tryToMakeLovePassage);


    Link useSwordLink = new Link("Use your sword",
        "troll_adventure_use_sword_passage");
    useSwordLink.addAction(new ReduceHealthAction(100));
    useSwordLink.addAction(new AddScoreAction(100));
    Link castMagicSpellLink = new Link("Cast a magic spell",
        "troll_adventure_cast_magic_spell_passage");
    castMagicSpellLink.addAction(new AddGoldAction(100));
    castMagicSpellLink.addAction(new AddScoreAction(50));
    Passage attackTrollPassage = new Passage("troll_adventure_attack_troll_passage",
        "How do you want to attack the troll?");
    attackTrollPassage.addAllLinks(useSwordLink, castMagicSpellLink);
    Image attackTrollPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/attack_troll_image.png")
        .toExternalForm());
    attackTrollPassage.setBackgroundImage(attackTrollPassageImage);
    passages.add(attackTrollPassage);

    Passage useSwordPassage = new Passage("troll_adventure_use_sword_passage",
        "As you swing your sword, the troll notices you. With a skillful maneuver, the "
            + "troll redirects your sword, and it pierces your chest. You die.");
    useSwordPassage.addLink(failedContinueLink);
    Image useSwordPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/use_sword_image.png")
        .toExternalForm());
    useSwordPassage.setBackgroundImage(useSwordPassageImage);
    passages.add(useSwordPassage);

    Passage castMagicSpellPassage = new Passage("troll_adventure_cast_magic_spell_passage",
        "Harnessing the powers of magic, you cast a devastating spell that turns the "
            + "troll to stone. As the troll solidifies, it shatters into a pile of rubble, "
            + "revealing 100 gold coins. You gain the gold coins and 50 score points.");
    castMagicSpellPassage.addLink(successContinueLink);
    Image castMagicSpellPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/cast_magic_spell_image.png")
        .toExternalForm());
    castMagicSpellPassage.setBackgroundImage(castMagicSpellPassageImage);
    passages.add(castMagicSpellPassage);


    Link keepRunningLink = new Link("Keep running",
        "troll_adventure_keep_running_passage");
    keepRunningLink.addAction(new ReduceHealthAction(100));
    Link stopAndCastMagicSpell = new Link("Stop and cast a magic spell",
        "troll_adventure_cast_magic_spell_passage");
    stopAndCastMagicSpell.addAction(new AddGoldAction(100));
    stopAndCastMagicSpell.addAction(new AddScoreAction(50));
    Image runAwayPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/run_away_image.png")
        .toExternalForm());
    Passage runAwayPassage = new Passage("troll_adventure_run_away_passage",
        "The troll sees you and runs after you.");
    runAwayPassage.setBackgroundImage(runAwayPassageImage);
    runAwayPassage.addAllLinks(keepRunningLink, stopAndCastMagicSpell);
    passages.add(runAwayPassage);

    Passage keepRunningPassage = new Passage("troll_adventure_keep_running_passage",
        "Despite your best efforts, the troll catches up to you, delivering a powerful "
            + "blow. Your health diminishes, and with your last breath, you realize the futility "
            + "of your escape attempt.");
    keepRunningPassage.addLink(failedContinueLink);
    Image keepRunningPassageImage = new Image(this.pathsApp.getClass()
        .getResource("/images/stories/troll_adventure/keep_running_image.png")
        .toExternalForm());
    keepRunningPassage.setBackgroundImage(keepRunningPassageImage);
    passages.add(keepRunningPassage);

    Passage trollAdventureStorySuccessPassage = new Passage(
        "troll_adventure_story_success_passage",
        "As you swing your sword, the troll notices you. With a skillful maneuver, the "
            + "troll redirects your sword, and it pierces your chest. You die.");
    trollAdventureStorySuccessPassage.setBackgroundImage(useSwordPassageImage);
    passages.add(trollAdventureStorySuccessPassage);

    Passage trollAdventureStoryFailedPassage = new Passage(
        "troll_adventure_story_failed_passage",
        "As you swing your sword, the troll notices you. With a skillful maneuver, the "
            + "troll redirects your sword, and it pierces your chest. You die.");
    trollAdventureStoryFailedPassage.setBackgroundImage(useSwordPassageImage);
    passages.add(trollAdventureStoryFailedPassage);

    return passages;
  }
}
