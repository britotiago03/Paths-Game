package no.ntnu.idata2001.mappe29;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.ntnu.idata2001.mappe29.model.Link;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.AddGoldAction;
import no.ntnu.idata2001.mappe29.model.actions.AddScoreAction;
import no.ntnu.idata2001.mappe29.model.actions.ReduceHealthAction;
import org.junit.jupiter.api.Test;

/**
 * Tests the class {@link Link Link}.
 * <p>The following positive tests are performed:</p>
 *  <ul>
 *      <li><b>Positive</b> test that creates an instance of {@link Link Link} with valid
 *      parameters.</li>
 *      <li><b>Positive</b> test that gets the text of the link.</li>
 *      <li><b>Positive</b> test that gets the reference of the link.</li>
 *      <li><b>Positive</b> test that adds an action to the link with valid parameters.</li>
 *      <li><b>Positive</b> test that gets the actions of the link.</li>
 *      <li><b>Positive</b> test that checks the toString method of the link.</li>
 *      <li><b>Positive</b> test that checks that two links with same reference are equal.</li>
 *      <li><b>Positive</b> test that checks that two equal links have the same hash code.</li>
 *  </ul>
 * <p>The following negative tests are performed:
 * <ul>
 *    <li><b>Negative</b> test that tries to create instances of {@link Link Link} with invalid
 *    parameters.</li>
 *    <li><b>Negative</b> test that tries to add an action to the link with invalid parameters.</li>
 *    <li><b>Negative</b> test that checks that two links with different references are
 *    not equal.</li>
 *    <li><b>Negative</b> test that checks that two links that are not equal do not have the same
 *    hash code.</li>
 * </ul>
 * </p>
 */
public class LinkTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new Link("Run away", "run_passage");
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Link(null, "passage");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Link("", "passage");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Link("  ", "passage");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Link("run", null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Link("run", "");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Link("run", "  ");
    });
  }

  @Test
  public void testGetText() {
    Link link = new Link("fight", "fight_passage");
    assertEquals("fight", link.getText());
  }

  @Test
  public void testGetReference() {
    Link link = new Link("fight", "fight_passage");
    assertEquals("fight_passage", link.getReference());
  }

  @Test
  public void testAddActionWithValidParameters() {
    Link link = new Link("fight", "fight_passage");
    link.addAction(new AddScoreAction(100));
    assertFalse(link.getActions().isEmpty());
  }

  @Test
  public void testAddActionWithInvalidParameters() {
    Link link = new Link("fight", "fight_passage");
    assertThrows(IllegalArgumentException.class, () -> {
      link.addAction(null);
    });
  }

  @Test
  public void testGetActions() {
    Link link = new Link("fight", "fight_passage");
    Action increaseScore = new AddScoreAction(100);
    Action increaseGold = new AddGoldAction(200);
    link.addAction(increaseScore);
    link.addAction(increaseGold);
    assertEquals(increaseScore, link.getActions().get(0));
    assertEquals(increaseGold, link.getActions().get(1));
  }

  @Test
  public void testToString() {
    Link link = new Link("fight", "fight_passage");
    link.addAction(new ReduceHealthAction(40));
    assertEquals("Link{text='fight', reference='fight_passage', numberOfActions=1}",
        link.toString());
  }

  @Test
  public void testEqualsTrue() {
    Link link1 = new Link("fight", "fight_passage");
    Link link2 = new Link("grab sword", "fight_passage");
    Link link3 = new Link("fight", "fight_passage");
    assertEquals(true, link1.equals(link2));
    assertEquals(true, link1.equals(link3));
  }

  @Test
  public void testEqualsFalse() {
    Link link1 = new Link("fight", "fight_troll_passage");
    Link link2 = new Link("fight", "fight_monster_passage");
    Link link3 = new Link("run", "run_passage");
    assertEquals(false, link1.equals(link2));
    assertEquals(false, link1.equals(link3));
  }

  @Test
  public void testHashCodeTrue() {
    Link link1 = new Link("fight", "fight_passage");
    Link link2 = new Link("grab sword", "fight_passage");
    Link link3 = new Link("fight", "fight_passage");
    assertEquals(link1.hashCode(), link2.hashCode());
    assertEquals(link1.hashCode(), link3.hashCode());
  }

  @Test
  public void testHashCodeFalse() {
    Link link1 = new Link("fight", "fight_troll_passage");
    Link link2 = new Link("fight", "fight_monster_passage");
    Link link3 = new Link("run", "run_passage");
    assertNotEquals(link1.hashCode(), link2.hashCode());
    assertNotEquals(link1.hashCode(), link3.hashCode());
  }



}
