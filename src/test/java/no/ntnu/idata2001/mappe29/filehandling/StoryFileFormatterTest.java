package no.ntnu.idata2001.mappe29.filehandling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import no.ntnu.idata2001.mappe29.model.Link;
import no.ntnu.idata2001.mappe29.model.Passage;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.AddGoldAction;
import no.ntnu.idata2001.mappe29.model.actions.AddHealthAction;
import no.ntnu.idata2001.mappe29.model.actions.AddScoreAction;
import no.ntnu.idata2001.mappe29.model.actions.AddToInventoryAction;
import no.ntnu.idata2001.mappe29.model.actions.ReduceGoldAction;
import no.ntnu.idata2001.mappe29.model.actions.ReduceHealthAction;
import no.ntnu.idata2001.mappe29.model.actions.ReduceScoreAction;
import no.ntnu.idata2001.mappe29.model.actions.RemoveFromInventoryAction;
import no.ntnu.idata2001.mappe29.model.exceptions.CorruptStoryException;
import no.ntnu.idata2001.mappe29.model.exceptions.InvalidLinksException;
import no.ntnu.idata2001.mappe29.model.exceptions.StringHasNoLinksException;
import no.ntnu.idata2001.mappe29.model.exceptions.UnknownActionException;
import no.ntnu.idata2001.mappe29.model.filehandling.StoryFileFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the class {@link StoryFileFormatter StoryFileFormatter}.
 * <p>The following positive tests are performed:</p>
 *  <ul>
 *      <li><b>Positive</b> test that formats the specified story into a list of strings of the
 *      contents of the story file, using valid parameters.</li>
 *      <li><b>Positive</b> test that parses the specified list of strings of the contents of
 *      the story file into a Story, using valid parameters.</li>
 *      <li><b>Positive</b> test that parses the specified link string into a Link, using
 *      valid parameters.</li>
 *      <li><b>Positive</b> test that parses the specified action string into an Action, using
 *      valid parameters.</li>
 *      <li><b>Positive</b> test that adds the specified passage to the list of strings of the
 *      contents of the story file, using valid parameters.</li>
 *      <li><b>Positive</b> test that adds the specified action to the specified string builder,
 *      using valid parameters.</li>
 *  </ul>
 * <p>The following negative tests are performed:
 * <ul>
 *    <li><b>Negative</b> test that tries to format the specified story into a list of strings
 *    of the contents of the story file, using invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to parse the specified list of strings of the contents
 *    of the story file into a Story, using invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to parse the specified Link string into a link, using
 *    invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to parse the specified action string into an Action,
 *    using invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to add the specified passage to the list of strings
 *    of the contents of the story file, using invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to add the specified action to the specified string
 *    builder, using invalid parameters.</li>
 * </ul>
 * </p>
 */
public class StoryFileFormatterTest {
  /**
   * Creates a story that will be used for testing.
   *
   * @return a story that will be used for testing.
   */
  public Story createStory() {
    Passage openingPassage = new Passage("opening_passage",
        "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    try {
      openingPassage.addAllLinks(attackTroll, runAway);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }

    Story story = new Story("Troll Adventure", openingPassage);

    for (Passage passage : this.createPassages()) {
      story.addPassage(passage);
    }

    return story;
  }

  /**
   * Creates passages that will be used for testing.
   *
   * @return passages that will be used for testing.
   */
  public List<Passage> createPassages() {
    List<Passage> passages = new ArrayList<>();
    Passage attackTrollPassage = new Passage("attack_troll_passage",
        "How do you want to attack?");
    Link useSword = new Link("Use sword", "use_sword_passage");
    Link castMagicSpell = new Link("Cast magic spell", "cast_magic_spell_passage");
    Link seduceTroll = new Link("Seduce troll", "seduce_troll_passage");
    seduceTroll.addAction(new AddGoldAction(300));
    seduceTroll.addAction(new AddScoreAction(500));
    try {
      attackTrollPassage.addAllLinks(useSword, castMagicSpell, seduceTroll);
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
   * Creates a list of all the links of the passages of the specified story.
   *
   * @param story              the specified story.
   * @param storyFileFormatter the specified story file formatter.
   * @return a list of all the links of the passages of the specified story.
   */
  public List<String> createPassageLinks(Story story, StoryFileFormatter storyFileFormatter) {
    List<String> passageLinks = new ArrayList<>();
    for (Passage passage : story.getPassages()) {
      for (Link link : passage.getLinks()) {
        StringBuilder linkContent = new StringBuilder("[" + link.getText() + "] (" +
            link.getReference() + ") {");
        if (!link.getActions().isEmpty()) {
          for (int index = 0; index < link.getActions().size(); index++) {
            Action action = link.getActions().get(index);
            try {
              storyFileFormatter.addActionToStringBuilder(action, linkContent);
            } catch (UnknownActionException exception) {
              throw new RuntimeException(exception.getMessage());
            }
            if (index + 1 < link.getActions().size()) {
              linkContent.append(", ");
            }
          }
        } else {
          linkContent.append("no_actions");
        }

        linkContent.append("}");
        passageLinks.add(linkContent.toString());
      }
    }
    passageLinks.add("[no_links]");

    return passageLinks;
  }


  @Test
  public void testFormatStoryWithValidParameters() {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    Story story = this.createStory();

    List<String> passageTitles = new ArrayList<>();
    for (Passage passage : story.getPassages()) {
      String passageTitle = "::" + passage.getTitle();
      passageTitles.add(passageTitle);
    }
    List<String> passageContents = new ArrayList<>();
    for (Passage passage : story.getPassages()) {
      passageContents.add(passage.getContent());
    }
    List<String> passageLinks = this.createPassageLinks(story, storyFileFormatter);

    Iterator<String> storyFileContentsIterator = null;
    try {
      storyFileContentsIterator = storyFileFormatter.formatStory(story).iterator();
    } catch (UnknownActionException exception) {
      throw new RuntimeException(exception.getMessage());
    }

    assertEquals(story.getTitle(), storyFileContentsIterator.next());
    while (storyFileContentsIterator.hasNext()) {
      String line = storyFileContentsIterator.next();
      if (!line.isBlank()) {
        if (line.startsWith("::")) {
          assertTrue(passageTitles.contains(line));
          line = storyFileContentsIterator.next();
          assertTrue(passageContents.contains(line));
        } else if (line.startsWith("[")) {
          assertTrue(passageLinks.contains(line));
        }
      }
    }
  }

  @Test
  public void testFormatStoryWithInvalidParameters() {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.formatStory(null);
    });
  }


  public void testParseStoryWithValidParameters() {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    Story story = this.createStory();
    List<String> storyFileContents;
    try {
      storyFileContents = storyFileFormatter.formatStory(story);
    } catch (UnknownActionException exception) {
      throw new RuntimeException(exception.getMessage());
    }
    Story parsedStory;
    try {
      parsedStory = storyFileFormatter.parseStory(storyFileContents);
    } catch (CorruptStoryException exception) {
      throw new RuntimeException(exception.getMessage());
    }

    assertEquals(story.getTitle(), parsedStory.getTitle());
    assertEquals(story.getOpeningPassage(), parsedStory.getOpeningPassage());
    assertEquals(story.getPassages().size(), parsedStory.getPassages().size());
  }

  public void testParseStoryWithInvalidParameters() {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    List<String> emptyStoryFileContents = new ArrayList<>();
    List<String> invalidStoryFileContents = new ArrayList<>();
    invalidStoryFileContents.add("suh dude 123 sda");

    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.parseStory(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.parseStory(emptyStoryFileContents);
    });
    assertThrows(CorruptStoryException.class, () -> {
      storyFileFormatter.parseStory(invalidStoryFileContents);
    });

    List<String> corruptStoryFileContents1 = new ArrayList<>();
    corruptStoryFileContents1.add("Troll Adventure");
    corruptStoryFileContents1.add("");
    corruptStoryFileContents1.add("assdas");
    assertThrows(CorruptStoryException.class, () -> {
      storyFileFormatter.parseStory(corruptStoryFileContents1);
    });

    List<String> corruptStoryFileContents2 = new ArrayList<>();
    corruptStoryFileContents2.add("Troll Adventure");
    corruptStoryFileContents2.add("");
    corruptStoryFileContents2.add("::passage");
    corruptStoryFileContents2.add("12sdas");
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.parseStory(corruptStoryFileContents2);
    });

    List<String> corruptStoryFileContents3 = new ArrayList<>();
    corruptStoryFileContents3.add("Troll Adventure");
    corruptStoryFileContents3.add("");
    corruptStoryFileContents3.add("::passage");
    corruptStoryFileContents3.add("12sdas");
    corruptStoryFileContents3.add("gfss12");
    assertThrows(CorruptStoryException.class, () -> {
      storyFileFormatter.parseStory(corruptStoryFileContents3);
    });

    List<String> corruptStoryFileContents4 = new ArrayList<>();
    corruptStoryFileContents4.add("Troll Adventure");
    corruptStoryFileContents4.add("");
    corruptStoryFileContents4.add("::passage");
    corruptStoryFileContents4.add("You see a big troll");
    corruptStoryFileContents4.add("[fight] (fight_passage) {no_actions}");
    corruptStoryFileContents4.add("");
    corruptStoryFileContents4.add("23l34lkj");
    assertThrows(CorruptStoryException.class, () -> {
      storyFileFormatter.parseStory(corruptStoryFileContents4);
    });
  }

  @Test
  public void testParseLinkFromStringWithValidParameters() throws CorruptStoryException {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    String linkLine1 =
        "[Seduce troll] (seduce_troll_passage) {Add 300 gold}";
    String linkLine2 =
        "[Fight troll] (fight_troll_passage) {Add 30 health, Add shield to inventory}";
    String linkLine3 =
        "[Confuse troll] (confuse_troll_passage) {}";
    String linkLine4 =
        "[Distract troll] (distract_troll_passage) {Reduce 1000 points from score," +
            " Remove sword from inventory}";
    Link link1;
    try {
      link1 = storyFileFormatter.parseLinkFromString(linkLine1);
    } catch (StringHasNoLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }
    assertEquals("Seduce troll", link1.getText());
    assertEquals("seduce_troll_passage", link1.getReference());
    assertEquals(1, link1.getActions().size());

    Link link2;
    try {
      link2 = storyFileFormatter.parseLinkFromString(linkLine2);
    } catch (StringHasNoLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }
    assertEquals("Fight troll", link2.getText());
    assertEquals("fight_troll_passage", link2.getReference());
    assertEquals(2, link2.getActions().size());

    Link link3;
    try {
      link3 = storyFileFormatter.parseLinkFromString(linkLine3);
    } catch (StringHasNoLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }
    assertEquals("Confuse troll", link3.getText());
    assertEquals("confuse_troll_passage", link3.getReference());
    assertTrue(link3.getActions().isEmpty());

    Link link4;
    try {
      link4 = storyFileFormatter.parseLinkFromString(linkLine4);
    } catch (StringHasNoLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }
    assertEquals("Distract troll", link4.getText());
    assertEquals("distract_troll_passage", link4.getReference());
    assertEquals(2, link4.getActions().size());

  }

  @Test
  public void testParseLinkFromStringWithInvalidParameters() {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.parseLinkFromString(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.parseLinkFromString("");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.parseLinkFromString("     ");
    });
  }

  @Test
  public void testParseActionFromStringWithValidParameters() throws CorruptStoryException {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    String linkLine1 =
        "[Seduce troll] (seduce_troll_passage) {Add 300 gold, Add 500 points to score}";
    linkLine1 = linkLine1.substring(linkLine1.indexOf("{") + 1, linkLine1.indexOf("}"));
    String[] link1Actions = linkLine1.split(",\\s*");

    String addGoldActionString = link1Actions[0].trim();
    Action action1 = storyFileFormatter.parseActionFromString(addGoldActionString);
    assertTrue(action1 instanceof AddGoldAction);
    AddGoldAction addGoldAction = (AddGoldAction) action1;
    assertEquals(300, addGoldAction.getGold());

    String addScoreActionString = link1Actions[1].trim();
    Action action2 = storyFileFormatter.parseActionFromString(addScoreActionString);
    assertTrue(action2 instanceof AddScoreAction);
    AddScoreAction addScoreAction = (AddScoreAction) action2;
    assertEquals(500, addScoreAction.getPoints());

    String linkLine2 =
        "[Seduce troll] (seduce_troll_passage) {Add 30 health, Add shield to inventory}";
    linkLine2 = linkLine2.substring(linkLine2.indexOf("{") + 1, linkLine2.indexOf("}"));
    String[] link2Actions = linkLine2.split(",\\s*");

    String addHealthActionString = link2Actions[0].trim();
    Action action3 = storyFileFormatter.parseActionFromString(addHealthActionString);
    assertTrue(action3 instanceof AddHealthAction);
    AddHealthAction addHealthAction = (AddHealthAction) action3;
    assertEquals(30, addHealthAction.getHealth());

    String addToInventoryActionString = link2Actions[1].trim();
    Action action4 = storyFileFormatter.parseActionFromString(addToInventoryActionString);
    assertTrue(action4 instanceof AddToInventoryAction);
    AddToInventoryAction addToInventoryAction = (AddToInventoryAction) action4;
    assertEquals("shield", addToInventoryAction.getItem());

    String linkLine3 =
        "[Seduce troll] (seduce_troll_passage) {Reduce 100 gold, Reduce 100 health}";
    linkLine3 = linkLine3.substring(linkLine3.indexOf("{") + 1, linkLine3.indexOf("}"));
    String[] link3Actions = linkLine3.split(",\\s*");

    String reduceGoldActionString = link3Actions[0].trim();
    Action action5 = storyFileFormatter.parseActionFromString(reduceGoldActionString);
    assertTrue(action5 instanceof ReduceGoldAction);
    ReduceGoldAction reduceGoldAction = (ReduceGoldAction) action5;
    assertEquals(100, reduceGoldAction.getGold());

    String reduceHealthActionString = link3Actions[1].trim();
    Action action6 = storyFileFormatter.parseActionFromString(reduceHealthActionString);
    assertTrue(action6 instanceof ReduceHealthAction);
    ReduceHealthAction reduceHealthAction = (ReduceHealthAction) action6;
    assertEquals(100, reduceHealthAction.getHealth());

    String linkLine4 =
        "[Seduce troll] (seduce_troll_passage) {Reduce 1000 points from score," +
            " Remove sword from inventory}";
    linkLine4 = linkLine4.substring(linkLine4.indexOf("{") + 1, linkLine4.indexOf("}"));
    String[] link4Actions = linkLine4.split(",\\s*");

    String reduceScoreActionString = link4Actions[0].trim();
    Action action7 = storyFileFormatter.parseActionFromString(reduceScoreActionString);
    assertTrue(action7 instanceof ReduceScoreAction);
    ReduceScoreAction reduceScoreAction = (ReduceScoreAction) action7;
    assertEquals(1000, reduceScoreAction.getPoints());

    String removeFromInventoryActionString = link4Actions[1].trim();
    Action action8 = storyFileFormatter.parseActionFromString(removeFromInventoryActionString);
    assertTrue(action8 instanceof RemoveFromInventoryAction);
    RemoveFromInventoryAction removeFromInventoryAction = (RemoveFromInventoryAction) action8;
    assertEquals("sword", removeFromInventoryAction.getItem());
  }

  @Test
  public void testParseActionFromStringWithInvalidParameters() {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.parseActionFromString(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.parseActionFromString("");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.parseActionFromString("    ");
    });
  }

  @Test
  public void testAddPassageToStoryFileContentsWithValidParameters() {
    Passage attackTrollPassage = new Passage("attack_troll_passage",
        "How do you want to attack?");
    Link useSword = new Link("Use sword", "use_sword_passage");
    Link castMagicSpell = new Link("Cast magic spell", "cast_magic_spell_passage");
    Link seduceTroll = new Link("Seduce troll", "seduce_troll_passage");
    seduceTroll.addAction(new AddGoldAction(300));
    seduceTroll.addAction(new AddScoreAction(500));
    try {
      attackTrollPassage.addAllLinks(useSword, castMagicSpell, seduceTroll);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }
    List<String> storyFileContents = new ArrayList<>();
    storyFileContents.add("");
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();

    assertDoesNotThrow(() -> {
      storyFileFormatter.addPassageToStoryFileContents(attackTrollPassage, storyFileContents);
    });
    assertEquals(6, storyFileContents.size());
    assertEquals("::attack_troll_passage", storyFileContents.get(1));
    assertEquals("How do you want to attack?", storyFileContents.get(2));
    assertEquals("[Use sword] (use_sword_passage) {no_actions}", storyFileContents.get(3));
    assertEquals("[Cast magic spell] (cast_magic_spell_passage) {no_actions}",
        storyFileContents.get(4));
    assertEquals("[Seduce troll] (seduce_troll_passage) " +
        "{Add 300 gold, Add 500 points to score}", storyFileContents.get(5));
  }

  @Test
  public void testAddPassageToStoryFileContentsWithInvalidParameters() {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    Passage attackTrollPassage = new Passage("attack_troll_passage",
        "How do you want to attack?");
    Link useSword = new Link("Use sword", "use_sword_passage");
    Link castMagicSpell = new Link("Cast magic spell", "cast_magic_spell_passage");
    Link seduceTroll = new Link("Seduce troll", "seduce_troll_passage");
    seduceTroll.addAction(new AddGoldAction(300));
    seduceTroll.addAction(new AddScoreAction(500));
    try {
      attackTrollPassage.addAllLinks(useSword, castMagicSpell, seduceTroll);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }

    List<String> storyFileContents = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.addPassageToStoryFileContents(attackTrollPassage, storyFileContents);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.addPassageToStoryFileContents(attackTrollPassage, null);
    });
    storyFileContents.add("");
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.addPassageToStoryFileContents(null, storyFileContents);
    });
  }

  @Test
  public void testAddActionToStringBuilderWithValidParameters() {
    StringBuilder stringBuilder1 = new StringBuilder();
    StringBuilder stringBuilder2 = new StringBuilder();
    StringBuilder stringBuilder3 = new StringBuilder();
    StringBuilder stringBuilder4 = new StringBuilder();
    StringBuilder stringBuilder5 = new StringBuilder();
    StringBuilder stringBuilder6 = new StringBuilder();
    StringBuilder stringBuilder7 = new StringBuilder();
    StringBuilder stringBuilder8 = new StringBuilder();

    AddGoldAction addGoldAction = new AddGoldAction(100);
    AddHealthAction addHealthAction = new AddHealthAction(50);
    AddScoreAction addScoreAction = new AddScoreAction(300);
    AddToInventoryAction addToInventoryAction = new AddToInventoryAction("sword");
    ReduceGoldAction reduceGoldAction = new ReduceGoldAction(30);
    ReduceHealthAction reduceHealthAction = new ReduceHealthAction(100);
    ReduceScoreAction reduceScoreAction = new ReduceScoreAction(200);
    RemoveFromInventoryAction removeFromInventoryAction = new RemoveFromInventoryAction(
        "sword");

    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    assertDoesNotThrow(() -> {
      storyFileFormatter.addActionToStringBuilder(addGoldAction, stringBuilder1);
    });
    assertEquals("Add 100 gold", stringBuilder1.toString());

    assertDoesNotThrow(() -> {
      storyFileFormatter.addActionToStringBuilder(addHealthAction, stringBuilder2);
    });
    assertEquals("Add 50 health", stringBuilder2.toString());

    assertDoesNotThrow(() -> {
      storyFileFormatter.addActionToStringBuilder(addScoreAction, stringBuilder3);
    });
    assertEquals("Add 300 points to score", stringBuilder3.toString());

    assertDoesNotThrow(() -> {
      storyFileFormatter.addActionToStringBuilder(addToInventoryAction, stringBuilder4);
    });
    assertEquals("Add sword to inventory", stringBuilder4.toString());

    assertDoesNotThrow(() -> {
      storyFileFormatter.addActionToStringBuilder(reduceGoldAction, stringBuilder5);
    });
    assertEquals("Reduce 30 gold", stringBuilder5.toString());

    assertDoesNotThrow(() -> {
      storyFileFormatter.addActionToStringBuilder(reduceHealthAction, stringBuilder6);
    });
    assertEquals("Reduce 100 health", stringBuilder6.toString());

    assertDoesNotThrow(() -> {
      storyFileFormatter.addActionToStringBuilder(reduceScoreAction, stringBuilder7);
    });
    assertEquals("Reduce 200 points from score", stringBuilder7.toString());

    assertDoesNotThrow(() -> {
      storyFileFormatter.addActionToStringBuilder(removeFromInventoryAction, stringBuilder8);
    });
    assertEquals("Remove sword from inventory", stringBuilder8.toString());
  }

  @Test
  public void testAddActionToStringBuilderWithInvalidParameters() {
    StoryFileFormatter storyFileFormatter = new StoryFileFormatter();
    StringBuilder stringBuilder = new StringBuilder();

    class MakeRichAction implements Action {
      @Override
      public void execute(Player player) {
        player.addGold(100000);
      }
    }
    Action makeRichAction = new MakeRichAction();
    assertThrows(UnknownActionException.class, () -> {
      storyFileFormatter.addActionToStringBuilder(makeRichAction, stringBuilder);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.addActionToStringBuilder(null, stringBuilder);
    });
    Action addGoldAction = new AddGoldAction(50);
    assertThrows(IllegalArgumentException.class, () -> {
      storyFileFormatter.addActionToStringBuilder(addGoldAction, null);
    });
  }
}
