package no.ntnu.idata2001.mappe29;


import no.ntnu.idata2001.mappe29.model.Link;
import no.ntnu.idata2001.mappe29.model.Passage;

import no.ntnu.idata2001.mappe29.model.exceptions.InvalidLinksException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the class {@link Passage Passage}.
 * <p>The following positive tests are performed:</p>
 *  <ul>
 *      <li><b>Positive</b> test that creates an instance of {@link Passage Passage} with valid
 *      parameters.</li>
 *      <li><b>Positive</b> test that gets the title of the passage.</li>
 *      <li><b>Positive</b> test that gets the content of the passage.</li>
 *      <li><b>Positive</b> test that adds a link to the passage with valid parameters.</li>
 *      <li><b>Positive</b> test that gets the links of the passage.</li>
 *      <li><b>Positive</b> test that checks that the passage has links.</li>
 *      <li><b>Positive</b> test that checks the toString method of the passage.</li>
 *      <li><b>Positive</b> test that checks that two passages with same content are equal.</li>
 *      <li><b>Positive</b> test that checks that two equal passages have the same hash code.</li>
 *      <li><b>Positive</b> test that adds multiple links to the passage with valid
 *      parameters.</li>
 *      <li><b>Positive</b> test that removes a link from the passage with valid parameters.</li>
 *      <li><b>Positive</b> test that checks if a passage is an ending passage</li>
 *      <li><b>Positive</b> test that turns a passage into an ending passage</li>
 *  </ul>
 * <p>The following negative tests are performed:
 * <ul>
 *    <li><b>Negative</b> test that tries to create instances of {@link Passage Passage} with
 *    invalid parameters.</li>
 *    <li><b>Negative</b> test that tries to add a link to the passage with invalid
 *    parameters.</li>
 *    <li><b>Negative</b> test that checks that the passage does not have links.</li>
 *    <li><b>Negative</b> test that checks that two passages with different content and links are
 *    not equal.</li>
 *    <li><b>Negative</b> test that checks that two passages that are not equal do not have the same
 *    hash code.</li>
 *    <li><b>Negative</b> test that tries to add multiple links to the passage with invalid
 *    parameters. Checks that no links are added if invalid links are provided.</li>
 *    <li><b>Negative</b> test that tries to remove a link from the passage with invalid
 *    parameters.</li>
 * </ul>
 * </p>
 */
public class PassageTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new Passage("run_passage", "You ran from the boss, but it catches you and eats" +
          "you!");
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Passage("", "You ran from the boss, but it catches you and eats you!");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Passage("    ", "You ran from the boss, but it catches you and eats you!");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Passage(null, "You ran from the boss, but it catches you and eats you!");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Passage("run_passage", "");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Passage("run_passage", "   ");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Passage("run_passage", null);
    });
  }

  @Test
  public void testGetTitle() {
    String title = "run_passage";
    String content = "You ran from the boss, but it catches you and eats you!";
    Passage passage = new Passage(title, content);
    assertEquals(title, passage.getTitle());
  }

  @Test
  public void testGetContent() {
    String title = "run_passage";
    String content = "You ran from the boss, but it catches you and eats you!";
    Passage passage = new Passage(title, content);
    assertEquals(content, passage.getContent());
  }

  @Test
  public void testAddLinkWithValidParameters() {
    String title = "run_passage";
    String content = "You ran from the boss, but it catches you and eats you!";
    Passage passage = new Passage(title, content);
    Link link = new Link("run", "run_away_passage");
    assertDoesNotThrow(() -> {
      passage.addLink(link);
    });
    assertFalse(passage.getLinks().isEmpty());
  }

  @Test
  public void testAddLinkWithInvalidParameters() {
    String title = "run_passage";
    String content = "You ran from the boss, but it catches you and eats you!";
    Passage passage = new Passage(title, content);
    assertThrows(IllegalArgumentException.class, () -> {
      passage.addLink(null);
    });
    assertTrue(passage.getLinks().isEmpty());
  }

  @Test
  public void testGetLinks() {
    String title = "run_passage";
    String content = "You ran from the boss, but it catches you and eats you!";
    Passage passage = new Passage(title, content);
    Link link1 = new Link("run", "run_away_passage");
    Link link2 = new Link("fight", "fight_passage");
    passage.addLink(link1);
    passage.addLink(link2);
    assertEquals(link1, passage.getLinks().get(0));
    assertEquals(link2, passage.getLinks().get(1));
  }

  @Test
  public void testHasLinksTrue() {
    String title = "run_passage";
    String content = "You ran from the boss, but it catches you and eats you!";
    Passage passage = new Passage(title, content);
    Link link1 = new Link("run", "run_away_passage");
    Link link2 = new Link("fight", "fight_passage");
    passage.addLink(link1);
    passage.addLink(link2);
    assertTrue(passage.hasLinks());
  }

  @Test
  public void testHasLinksFalse() {
    String title = "run_passage";
    String content = "You ran from the boss, but it catches you and eats you!";
    Passage passage = new Passage(title, content);
    assertFalse(passage.hasLinks());
  }

  @Test
  public void testToString() {
    String title = "run_passage";
    String content = "You ran from the boss, but it catches you and eats you!";
    Passage passage = new Passage(title, content);
    Link link1 = new Link("run", "run_away_passage");
    Link link2 = new Link("fight", "fight_passage");
    passage.addLink(link1);
    passage.addLink(link2);
    assertEquals("Passage{title='run_passage', content='You ran from the boss, but it " +
        "catches you and eats you!', numberOfLinks=2}", passage.toString());
  }

  @Test
  public void testEqualsTrue() {
    Passage passage1 = new Passage("title", "content");
    Passage passage2 = new Passage("different title", "content");
    Passage passage3 = new Passage("title", "content");
    assertEquals(true, passage1.equals(passage2));
    assertEquals(true, passage1.equals(passage3));
  }

  @Test
  public void testEqualsFalse() {
    Passage passage1 = new Passage("title", "content");
    passage1.addLink(new Link("text", "reference"));
    Passage passage2 = new Passage("title", "content");
    Passage passage3 = new Passage("title", "content");
    passage3.addLink(new Link("text", "different reference"));

    assertEquals(false, passage1.equals(passage2));
    assertEquals(false, passage1.equals(passage3));
  }

  @Test
  public void testHashCodeTrue() {
    Passage passage1 = new Passage("title", "content");
    Passage passage2 = new Passage("different title", "content");
    Passage passage3 = new Passage("title", "content");
    assertEquals(passage1.hashCode(), passage2.hashCode());
    assertEquals(passage1.hashCode(), passage3.hashCode());
  }

  @Test
  public void testHashCodeFalse() {
    Passage passage1 = new Passage("title", "content");
    Passage passage2 = new Passage("title", "different content");
    Passage passage3 = new Passage("title", "content");
    passage3.addLink(new Link("text", "reference"));
    assertNotEquals(passage1.hashCode(), passage2.hashCode());
    assertNotEquals(passage1.hashCode(), passage3.hashCode());
  }

  @Test
  public void testAddAllLinksWithValidParameters() {
    Passage passage = new Passage("introduction_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    assertDoesNotThrow(() -> {
      passage.addAllLinks(attackTroll, runAway);
    });
    assertEquals(2, passage.getLinks().size());
  }

  @Test
  public void testAddAllLinksWithInvalidParameters() {
    Passage passage = new Passage("introduction_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    assertThrows(InvalidLinksException.class, () -> {
      passage.addAllLinks(attackTroll, runAway, null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      passage.addAllLinks(attackTroll);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      passage.addAllLinks();
    });
    assertThrows(IllegalArgumentException.class, () -> {
      passage.addAllLinks(null);
    });
    assertEquals(0, passage.getLinks().size());
  }

  @Test
  public void testRemoveLinkWithValidParameters() {
    Passage passage = new Passage("introduction_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    assertDoesNotThrow(() -> {
      passage.addAllLinks(attackTroll, runAway);
    });

    assertDoesNotThrow(() -> {
      passage.removeLink(attackTroll);
    });
    assertEquals(1, passage.getLinks().size());
  }

  @Test
  public void testRemoveLinkWithInvalidParameters() {
    Passage passage = new Passage("introduction_passage", "You see a big ugly troll");
    Link attackTroll = new Link("Attack the troll", "attack_troll_passage");
    Link runAway = new Link("Run away", "run_away_passage");
    passage.addLink(attackTroll);
    assertThrows(IllegalArgumentException.class, () -> {
      passage.removeLink(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      passage.removeLink(runAway);
    });
  }

  @Test
  public void testIsEndingPassage() {
    Passage openingPassage = new Passage("opening_passage", "You see a big ugly troll");
    Passage endingPassage = new Passage("use_sword_passage",
        "The sword only tickles the troll. It rips the sword from your hand and stabs" +
            " you to death.");
    endingPassage.setEndingPassage(true);

    assertFalse(openingPassage.isEndingPassage());
    assertTrue(endingPassage.isEndingPassage());
  }

  @Test
  public void testSetEndingPassage() {
    Passage endingPassage = new Passage("use_sword_passage",
        "The sword only tickles the troll. It rips the sword from your hand and stabs" +
            " you to death.");
    endingPassage.setEndingPassage(true);
    assertTrue(endingPassage.isEndingPassage());
  }

}
