package no.ntnu.idata2001.mappe29;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idata2001.mappe29.model.Game;
import no.ntnu.idata2001.mappe29.model.Link;
import no.ntnu.idata2001.mappe29.model.Passage;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.exceptions.InvalidLinksException;
import no.ntnu.idata2001.mappe29.model.goals.Goal;
import no.ntnu.idata2001.mappe29.model.goals.HealthGoal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the class {@link Game Game}.
 * <p>The following positive tests are performed:</p>
 *  <ul>
 *      <li><b>Positive</b> test that creates an instance of {@link Game Game} with valid
 *      parameters.</li>
 *      <li><b>Positive</b> test that gets the player of the game.</li>
 *      <li><b>Positive</b> test that gets the story of the game.</li>
 *      <li><b>Positive</b> test that gets the goals of the game.</li>
 *      <li><b>Positive</b> test that begins the game.</li>
 *      <li><b>Positive</b> test that goes to the next passage, with valid parameters.</li>
 *  </ul>
 * <p>The following negative tests are performed:
 * <ul>
 *    <li><b>Negative</b> test that tries to create instances of {@link Game Game} with
 *    invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to go to the next passage, with invalid parameters.</li>
 * </ul>
 * </p>
 */
public class GameTest {

  /**
   * Creates the story of the game.
   *
   * @return the story of the game.
   */
  public Story createStory() {
    Passage openingPassage = new Passage("opening_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    try {
      openingPassage.addAllLinks(attackTroll, runAway);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }

    return new Story("Troll Adventure", openingPassage);
  }

  /**
   * Creates the passages of the story of the game.
   *
   * @return the passages of the story of the game.
   */
  public List<Passage> createPassages() {
    List<Passage> passages = new ArrayList<>();
    Passage attackTrollPassage = new Passage("attack_troll_passage",
        "How do you want to attack?");
    Link useSword = new Link("Use sword", "use_sword_passage");
    Link castMagicSpell = new Link("Cast magic spell", "cast_magic_spell_passage");
    try {
      attackTrollPassage.addAllLinks(useSword, castMagicSpell);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }
    passages.add(attackTrollPassage);

    Passage runAwayPassage = new Passage("run_away_passage",
        "The troll sees you and runs after you.");
    Link stopAndCastMagicSpell = new Link("Stop and cast magic spell",
        "cast_magic_spell_passage");
    Link keepRunning = new Link("Keep running", "keep_running_passage");
    try {
      runAwayPassage.addAllLinks(stopAndCastMagicSpell, keepRunning);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }
    passages.add(runAwayPassage);

    Passage useSwordPassage = new Passage("use_sword_passage",
        "The sword only tickles the troll. It rips the sword from your hand and stabs" +
            " you to death.");
    useSwordPassage.setEndingPassage(true);
    passages.add(useSwordPassage);

    Passage castMagicSpellPassage = new Passage("cast_magic_spell_passage", "The troll" +
        " turns to stone. 50 gold coins drop to the ground.");
    castMagicSpellPassage.setEndingPassage(true);
    passages.add(castMagicSpellPassage);

    Passage keepRunningPassage = new Passage("keep_running_passage", "You are too " +
        "slow. The troll catches up to you, and hits you in the head. It is a fatal blow");
    keepRunningPassage.setEndingPassage(true);
    passages.add(keepRunningPassage);

    return passages;
  }

  /**
   * Creates the goals of the game.
   *
   * @return the goals of the game.
   */
  public List<Goal> createGoals() {
    Goal healthGoal = new HealthGoal(100);
    List<Goal> goals = new ArrayList<>();
    goals.add(healthGoal);

    return goals;
  }

  @Test
  public void testCreationWithValidParameters() {
    Player player = new Player.PlayerBuilder().withName("Rafael").withHealth(100).withGold(0)
            .withScore(0).withInventory(new ArrayList<>()).withGoals(new ArrayList<>()).build();
    Story story = this.createStory();
    for (Passage passage : this.createPassages()) {
      story.addPassage(passage);
    }
    List<Goal> goals = this.createGoals();
    assertDoesNotThrow(() -> {
      new Game(player, story, goals);
    });

  }

  @Test
  public void testCreationWithInvalidParameters() {
    Player player = new Player.PlayerBuilder().withName("Rafael").withHealth(100).withGold(0)
        .withScore(0).withInventory(new ArrayList<>()).withGoals(new ArrayList<>()).build();
    Story story = this.createStory();
    for (Passage passage : this.createPassages()) {
      story.addPassage(passage);
    }
    List<Goal> goals = this.createGoals();
    assertThrows(IllegalArgumentException.class, () -> {
      new Game(null, story, goals);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Game(player, null, goals);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Game(player, story, null);
    });
    List<Goal> emptyGoalList = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> {
      new Game(player, story, emptyGoalList);
    });
    story.getOpeningPassage().addLink(new Link("Seduce the troll",
        "seduce_troll_passage"));
    assertThrows(IllegalArgumentException.class, () -> {
      new Game(player, story, goals);
    });
  }

  @Test
  public void testGetPlayer() {
    Player player = new Player.PlayerBuilder().withName("Rafael").withHealth(100).withGold(0)
        .withScore(0).withInventory(new ArrayList<>()).withGoals(new ArrayList<>()).build();
    Story story = this.createStory();
    for (Passage passage : this.createPassages()) {
      story.addPassage(passage);
    }
    List<Goal> goals = this.createGoals();
    Game game = new Game(player, story, goals);
    assertEquals(player, game.getPlayer());
  }

  @Test
  public void testGetStory() {
    Player player = new Player.PlayerBuilder().withName("Rafael").withHealth(100).withGold(0)
        .withScore(0).withInventory(new ArrayList<>()).withGoals(new ArrayList<>()).build();
    Story story = this.createStory();
    for (Passage passage : this.createPassages()) {
      story.addPassage(passage);
    }
    List<Goal> goals = this.createGoals();
    Game game = new Game(player, story, goals);
    assertEquals(story, game.getStory());
  }

  @Test
  public void testGetGoals() {
    Player player = new Player.PlayerBuilder().withName("Rafael").withHealth(100).withGold(0)
        .withScore(0).withInventory(new ArrayList<>()).withGoals(new ArrayList<>()).build();
    Story story = this.createStory();
    for (Passage passage : this.createPassages()) {
      story.addPassage(passage);
    }
    List<Goal> goals = this.createGoals();
    Game game = new Game(player, story, goals);
    assertEquals(goals, game.getGoals());
  }

  @Test
  public void testBegin() {
    Player player = new Player.PlayerBuilder().withName("Rafael").withHealth(100).withGold(0)
        .withScore(0).withInventory(new ArrayList<>()).withGoals(new ArrayList<>()).build();
    Story story = this.createStory();
    for (Passage passage : this.createPassages()) {
      story.addPassage(passage);
    }
    List<Goal> goals = this.createGoals();
    Game game = new Game(player, story, goals);
    assertEquals(story.getOpeningPassage(), game.begin());
  }

  @Test
  public void testGoWithValidParameters() {
    Player player = new Player.PlayerBuilder().withName("Rafael").withHealth(100).withGold(0)
        .withScore(0).withInventory(new ArrayList<>()).withGoals(new ArrayList<>()).build();

    Passage openingPassage = new Passage("opening_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    try {
      openingPassage.addAllLinks(attackTroll, runAway);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }

    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = new Passage("attack_troll_passage",
        "How do you want to attack?");
    Link useSword = new Link("Use sword", "use_sword_passage");
    Link castMagicSpell = new Link("Cast magic spell", "cast_magic_spell_passage");
    try {
      attackTrollPassage.addAllLinks(useSword, castMagicSpell);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }

    Passage runAwayPassage = new Passage("run_away_passage",
        "The troll sees you and runs after you.");
    Link stopAndCastMagicSpell = new Link("Stop and cast magic spell",
        "cast_magic_spell_passage");
    Link keepRunning = new Link("Keep running", "keep_running_passage");
    try {
      runAwayPassage.addAllLinks(stopAndCastMagicSpell, keepRunning);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }

    Passage useSwordPassage = new Passage("use_sword_passage",
        "The sword only tickles the troll. It rips the sword from your hand and stabs" +
            " you to death.");
    useSwordPassage.setEndingPassage(true);

    Passage castMagicSpellPassage = new Passage("cast_magic_spell_passage", "The troll" +
        " turns to stone. 50 gold coins drop to the ground.");
    castMagicSpellPassage.setEndingPassage(true);

    Passage keepRunningPassage = new Passage("keep_running_passage", "You are too " +
        "slow. The troll catches up to you, and hits you in the head. It is a fatal blow");
    keepRunningPassage.setEndingPassage(true);

    assertDoesNotThrow(() -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage, useSwordPassage,
          castMagicSpellPassage,
          keepRunningPassage);
    });

    List<Goal> goals = this.createGoals();
    Game game = new Game(player, story, goals);
    Passage secondPassage = game.go(attackTroll);
    Passage thirdPassage = game.go(runAway);
    assertEquals(attackTrollPassage, secondPassage);
    assertEquals(runAwayPassage, thirdPassage);
  }

  @Test
  public void testGoWithInvalidParameters() {
    Player player = new Player.PlayerBuilder().withName("Rafael").withHealth(100).withGold(0)
        .withScore(0).withInventory(new ArrayList<>()).withGoals(new ArrayList<>()).build();
    Story story = this.createStory();
    for (Passage passage : this.createPassages()) {
      story.addPassage(passage);
    }
    List<Goal> goals = this.createGoals();
    Game game = new Game(player, story, goals);
    assertThrows(IllegalArgumentException.class, () -> {
      game.go(null);
    });

    Link hastaLaVistaLink = new Link("Hasta la vista", "hasta_la_vista_passage");
    assertThrows(IllegalArgumentException.class, () -> {
      game.go(hastaLaVistaLink);
    });
  }
}
