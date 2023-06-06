package no.ntnu.idata2001.mappe29;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import no.ntnu.idata2001.mappe29.model.Link;
import no.ntnu.idata2001.mappe29.model.Passage;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.exceptions.InvalidLinksException;
import no.ntnu.idata2001.mappe29.model.exceptions.InvalidPassagesException;
import no.ntnu.idata2001.mappe29.model.exceptions.NoBrokenLinksException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the class {@link Story Story}.
 * <p>The following positive tests are performed:</p>
 *  <ul>
 *      <li><b>Positive</b> test that creates an instance of {@link Story Story} with valid
 *      parameters.</li>
 *      <li><b>Positive</b> test that gets the title of the story.</li>
 *      <li><b>Positive</b> test that gets the opening passage of the story.</li>
 *      <li><b>Positive</b> test that adds a passage to the story with, valid parameters.</li>
 *      <li><b>Positive</b> test that adds multiple passages to the story, with valid
 *      parameters.</li>
 *      <li><b>Positive</b> test that gets a passage in the story.</li>
 *      <li><b>Positive</b> test that gets all passages in the story.</li>
 *      <li><b>Positive</b> test that checks if a link refers to a passage in the passage
 *      of the story, with valid parameters.</li>
 *      <li><b>Positive</b> test that checks if other passages in the story link to a passage,
 *      with valid parameters.</li>
 *      <li><b>Positive</b> test that removes a passage from the story, with valid parameters.</li>
 *      <li><b>Positive</b> test that gets the broken links of a story with broken links a
 *      passage</li>
 *  </ul>
 * <p>The following negative tests are performed:
 * <ul>
 *    <li><b>Negative</b> test that tries to create instances of {@link Story Story} with
 *    invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to add a passage to the story with invalid
 *    parameters.</li>
 *    <li><b>Negative</b> test that tries to add multiple passages to the story with invalid
 *    parameters.</li>
 *    <li><b>Negative</b> test that tries to check if a link refers to a passage in the passage of
 *    the story with invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to check if other passages in the story link to a passage,
 *    with invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to remove a passage from the story, with invalid
 *    parameters.</li>
 *    <li><b>Negative</b> test that tries to get the broken links of a story without broken
 *    links</li>
 * </ul>
 * </p>
 */
public class StoryTest {

  /**
   * Creates the opening passage of the story.
   *
   * @return the opening passage of the story.
   */
  public Passage createOpeningPassage() {
    Passage openingPassage = new Passage("opening_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    openingPassage.addLink(attackTroll);
    openingPassage.addLink(runAway);
    return openingPassage;
  }

  /**
   * Creates the attack troll passage of the story.
   *
   * @return the attack troll passage of the story.
   */
  public Passage createAttackTrollPassage() {
    Passage attackTrollPassage = new Passage("attack_troll_passage",
        "How do you want to attack?");
    Link useSword = new Link("Use sword", "use_sword_passage");
    Link castMagicSpell = new Link("Cast magic spell", "cast_magic_spell_passage");
    try {
      attackTrollPassage.addAllLinks(useSword, castMagicSpell);
    } catch (InvalidLinksException exception) {
      throw new RuntimeException(exception.getMessage());
    }
    return attackTrollPassage;
  }

  /**
   * Creates the runaway passage of the story.
   *
   * @return the runaway passage of the story.
   */
  public Passage createRunAwayPassage() {
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

    return runAwayPassage;
  }

  @Test
  public void testCreationWithValidParameters() {
    Passage openingPassage = this.createOpeningPassage();
    assertDoesNotThrow(() -> {
      new Story("Troll Adventure", openingPassage);
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    Passage openingPassage = this.createOpeningPassage();

    Passage openingPassageWithNoLinks = new Passage("introduction",
        "You see a big ugly troll");
    assertThrows(IllegalArgumentException.class, () -> {
      new Story("", openingPassage);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Story("    ", openingPassage);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Story(null, openingPassage);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Story("Troll Adventure", null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Story("Troll Adventure", openingPassageWithNoLinks);
    });
  }

  @Test
  public void testGetTitle() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);
    assertEquals("Troll Adventure", story.getTitle());
  }

  @Test
  public void testGetOpeningPassage() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);
    assertEquals(openingPassage, story.getOpeningPassage());
  }

  @Test
  public void testAddPassageWithValidParameters() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = this.createAttackTrollPassage();
    assertDoesNotThrow(() -> {
      story.addPassage(attackTrollPassage);
    });
    assertEquals(2, story.getPassages().size());
  }

  @Test
  public void testAddPassageWithInvalidParameters() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    assertThrows(IllegalArgumentException.class, () -> {
      story.addPassage(null);
    });
    assertEquals(1, story.getPassages().size());
  }

  @Test
  public void testAddAllPassagesWithValidParameters() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = this.createAttackTrollPassage();
    Passage runAwayPassage = this.createRunAwayPassage();

    assertDoesNotThrow(() -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage);
    });
    assertEquals(3, story.getPassages().size());
  }

  @Test
  public void testAddAllPassagesWithInvalidParameters() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = this.createAttackTrollPassage();
    Passage runAwayPassage = this.createRunAwayPassage();

    assertThrows(IllegalArgumentException.class, () -> {
      story.addAllPassages();
    });
    assertThrows(IllegalArgumentException.class, () -> {
      story.addAllPassages(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      story.addAllPassages(attackTrollPassage);
    });
    assertThrows(InvalidPassagesException.class, () -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage, null);
    });
    assertEquals(1, story.getPassages().size());
  }

  @Test
  public void testGetPassage() {
    Passage openingPassage = new Passage("opening_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    assertDoesNotThrow(() -> {
      openingPassage.addAllLinks(attackTroll, runAway);
    });
    Story story = new Story("Troll Adventure", openingPassage);
    Passage attackTrollPassage = this.createAttackTrollPassage();

    story.addPassage(attackTrollPassage);
    assertEquals(attackTrollPassage, story.getPassage(attackTroll));
  }

  @Test
  public void testGetPassages() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = this.createAttackTrollPassage();
    Passage runAwayPassage = this.createRunAwayPassage();

    assertDoesNotThrow(() -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage);
    });
    assertTrue(story.getPassages().stream().toList().contains(attackTrollPassage));
    assertTrue(story.getPassages().stream().toList().contains(runAwayPassage));
  }

  @Test
  public void testdoesLinkReferToAPassageInPassagesWithValidParameters() {
    Passage openingPassage = new Passage("opening_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    assertDoesNotThrow(() -> {
      openingPassage.addAllLinks(attackTroll, runAway);
    });
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = new Passage("attack_troll_passage",
        "How do you want to attack?");
    Link useSword = new Link("Use sword", "use_sword_passage");
    Link castMagicSpell = new Link("Cast magic spell", "cast_magic_spell_passage");
    assertDoesNotThrow(() -> {
      attackTrollPassage.addAllLinks(useSword, castMagicSpell);
    });

    Passage runAwayPassage = new Passage("run_away_passage",
        "The troll sees you and runs after you.");
    Link stopAndCastMagicSpell = new Link("Stop and cast magic spell",
        "cast_magic_spell_passage");
    Link keepRunning = new Link("Keep running", "keep_running_passage");
    assertDoesNotThrow(() -> {
      runAwayPassage.addAllLinks(stopAndCastMagicSpell, keepRunning);
    });

    assertDoesNotThrow(() -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage);
    });

    assertTrue(story.doesLinkReferToPassageInPassages(attackTroll));
    assertTrue(story.doesLinkReferToPassageInPassages(runAway));
    assertFalse(story.doesLinkReferToPassageInPassages(useSword));
    assertFalse(story.doesLinkReferToPassageInPassages(castMagicSpell));
    assertFalse(story.doesLinkReferToPassageInPassages(stopAndCastMagicSpell));
    assertFalse(story.doesLinkReferToPassageInPassages(keepRunning));
  }

  @Test
  public void testdoesLinkReferToAPassageInPassagesWithInvalidParameters() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    assertThrows(IllegalArgumentException.class, () -> {
      story.doesLinkReferToPassageInPassages(null);
    });
  }

  @Test
  public void testDoOtherPassagesLinkToPassageWithValidParameters() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = this.createAttackTrollPassage();
    Passage runAwayPassage = this.createRunAwayPassage();


    assertDoesNotThrow(() -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage);
    });

    assertTrue(story.doOtherPassagesLinkToPassage(attackTrollPassage));
    assertTrue(story.doOtherPassagesLinkToPassage(runAwayPassage));
    assertFalse(story.doOtherPassagesLinkToPassage(openingPassage));
  }

  @Test
  public void testDoOtherPassagesLinkToPassageWithInvalidParameters() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    assertThrows(IllegalArgumentException.class, () -> {
      story.doOtherPassagesLinkToPassage(null);
    });
  }

  @Test
  public void testRemovePassageWithValidParameters() {
    Passage openingPassage = new Passage("opening_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    assertDoesNotThrow(() -> {
      openingPassage.addAllLinks(attackTroll, runAway);
    });
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = this.createAttackTrollPassage();
    Passage runAwayPassage = this.createRunAwayPassage();

    assertDoesNotThrow(() -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage);
    });

    story.getOpeningPassage().removeLink(attackTroll);
    assertDoesNotThrow(() -> {
      story.removePassage(attackTroll);
    });
    assertEquals(2, story.getPassages().size());

  }

  @Test
  public void testRemovePassageWithInvalidParameters() {
    Passage openingPassage = new Passage("opening_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    assertDoesNotThrow(() -> {
      openingPassage.addAllLinks(attackTroll, runAway);
    });
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = new Passage("attack_troll_passage",
        "How do you want to attack?");
    Link useSword = new Link("Use sword", "use_sword_passage");
    Link castMagicSpell = new Link("Cast magic spell", "cast_magic_spell_passage");
    assertDoesNotThrow(() -> {
      attackTrollPassage.addAllLinks(useSword, castMagicSpell);
    });

    Passage runAwayPassage = this.createRunAwayPassage();

    assertDoesNotThrow(() -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      story.removePassage(attackTroll);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      story.removePassage(useSword);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      story.removePassage(null);
    });
  }

  @Test
  public void testGetBrokenLinksTrue() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = this.createAttackTrollPassage();
    Passage runAwayPassage = this.createRunAwayPassage();

    assertDoesNotThrow(() -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage);
    });

    AtomicReference<List<Link>> brokenLinks = new AtomicReference<>(new ArrayList<>());
    assertDoesNotThrow(() -> {
      brokenLinks.set(story.getBrokenLinks());
    });
    assertEquals(4, brokenLinks.get().size());

  }

  @Test
  public void testGetBrokenLinksFalse() {
    Passage openingPassage = this.createOpeningPassage();
    Story story = new Story("Troll Adventure", openingPassage);

    Passage attackTrollPassage = new Passage("attack_troll_passage",
        "How do you want to attack?");
    Passage runAwayPassage = new Passage("run_away_passage",
        "The troll sees you and runs after you.");

    assertDoesNotThrow(() -> {
      story.addAllPassages(attackTrollPassage, runAwayPassage);
    });

    assertThrows(NoBrokenLinksException.class, () -> {
      story.getBrokenLinks();
    });
  }

}
