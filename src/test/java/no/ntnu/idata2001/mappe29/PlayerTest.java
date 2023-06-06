package no.ntnu.idata2001.mappe29;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.goals.Goal;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Player Player} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of {@link Player Player} with valid
 *   parameters.</li>
 *   <li><b>Positive</b> test that gets the name of the player.</li>
 *   <li><b>Positive</b> test that gets the health of the player.</li>
 *   <li><b>Positive</b> test that gets the score of the player.</li>
 *   <li><b>Positive</b> test that gets the gold of the player.</li>
 *   <li><b>Positive</b> test that gets the inventory of the player.</li>
 *   <li><b>Positive</b> test that adds health to the player without overflow of health.</li>
 *   <li><b>Positive</b> test that adds health to the player with overflow of health.</li>
 *   <li><b>Positive</b> test that reduces health from the player without overflow of health.</li>
 *   <li><b>Positive</b> test that reduces health from the player with overflow of health.</li>
 *   <li><b>Positive</b> test that adds points to the player's score.</li>
 *   <li><b>Positive</b> test that reduces points from the player's score
 *   without overflow of score.</li>
 *   <li><b>Positive</b> test that reduces points from the player's score with overflow of
 *   score.</li>
 *   <li><b>Positive</b> test that adds gold to the player.</li>
 *   <li><b>Positive</b> test that reduces gold from the player.</li>
 *   <li><b>Positive</b> test that adds an item to the player's inventory.</li>
 *   <li><b>Positive</b> test that removes an item from the player's inventory.</li>
 *   <li><b>Positive</b> test that checks that the player is dead.</li>
 *   <li><b>Positive</b> test that checks that the player has a specified item.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of {@link Player Player} with
 *     invalid parameters.</li>
 *     <li><b>Negative</b> test that tries to add health with invalid parameters.</li>
 *     <li><b>Negative</b> test that tries to reduce health with invalid parameters.</li>
 *     <li><b>Negative</b> test that tries to add score with invalid parameters.</li>
 *     <li><b>Negative</b> test that tries to reduce score with invalid parameters.</li>
 *     <li><b>Negative</b> test that tries to add gold with invalid parameters.</li>
 *     <li><b>Negative</b> test that tries to reduce gold with invalid parameters.</li>
 *     <li><b>Negative</b> test that tries to add an existing item to the player's inventory.</li>
 *     <li><b>Negative</b> test that tries to add an invalid items to the player's inventory.</li>
 *     <li><b>Negative</b> test that tries to remove a non-existing item from the player's
 *     inventory.</li>
 *     <li><b>Negative</b> test that tries to remove invalid items from the player's
 *     inventory.</li>
 *     <li><b>Negative</b> test that checks that the player is not dead.</li>
 *     <li><b>Negative</b> test that checks that the player does not have a specified item.</li>
 *  </ul>
 *  </p>
 */
public class PlayerTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new Player.PlayerBuilder().withName("James").withHealth(100).withGold(1000).build();
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Player.PlayerBuilder().withName("").build();
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Player.PlayerBuilder().withName("    ").build();
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Player.PlayerBuilder().withHealth(-1).build();
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Player.PlayerBuilder().withScore(-10).build();
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Player.PlayerBuilder().withInventory(null).build();
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Player.PlayerBuilder().withGoals(null).build();
    });
  }

  @Test
  public void testGetName() {
    Player player = new Player.PlayerBuilder().withName("James").build();
    assertEquals("James", player.getName());
  }

  @Test
  public void testGetHealth() {
    Player player = new Player.PlayerBuilder().build();
    assertEquals(100, player.getHealth());
  }

  @Test
  public void testGetScore() {
    Player player = new Player.PlayerBuilder().withScore(20).build();
    assertEquals(20, player.getScore());
  }

  @Test
  public void testGetGold() {
    Player player = new Player.PlayerBuilder().withGold(50).build();
    assertEquals(50, player.getGold());
  }

  @Test
  public void testGetInventory() {
    List<String> inventory = new ArrayList<>();
    inventory.add("spoon");
    Player player = new Player.PlayerBuilder().withInventory(inventory).build();
    assertEquals("spoon", player.getInventory().get(0));
  }

  @Test
  public void testAddHealthWithoutOverflow() {
    Player player = new Player.PlayerBuilder().withHealth(75).build();
    player.addHealth(20);
    assertEquals(95, player.getHealth());
  }

  @Test
  public void testAddHealthWithOverflow() {
    Player player = new Player.PlayerBuilder().withHealth(80).build();
    player.addHealth(60);
    assertEquals(100, player.getHealth());
  }

  @Test
  public void testAddHealthWithInvalidParameters() {
    Player player = new Player.PlayerBuilder().build();
    assertThrows(IllegalArgumentException.class, () -> {
      player.addHealth(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.addHealth(-100);
    });
  }

  @Test
  public void testReduceHealthWithoutOverflow() {
    Player player = new Player.PlayerBuilder().withHealth(75).build();
    player.reduceHealth(30);
    assertEquals(45, player.getHealth());
  }

  @Test
  public void testReduceHealthWithOverflow() {
    Player player = new Player.PlayerBuilder().build();
    player.reduceHealth(300);
    assertEquals(0, player.getHealth());
  }

  @Test
  public void testReduceHealthWithInvalidParameters() {
    Player player = new Player.PlayerBuilder().build();
    assertThrows(IllegalArgumentException.class, () -> {
      player.reduceHealth(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.reduceHealth(-100);
    });
  }

  @Test
  public void testAddScore() {
    Player player = new Player.PlayerBuilder().withScore(20).build();
    player.addScore(100);
    assertEquals(120, player.getScore());
  }

  @Test
  public void testAddScoreWithInvalidParameters() {
    Player player = new Player.PlayerBuilder().build();
    assertThrows(IllegalArgumentException.class, () -> {
      player.addScore(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.addScore(-100);
    });
  }

  @Test
  public void testReduceScoreWithoutOverflow() {
    Player player = new Player.PlayerBuilder().withScore(20).build();
    player.reduceScore(10);
    assertEquals(10, player.getScore());
  }

  @Test
  public void testReduceScoreWithOverflow() {
    Player player = new Player.PlayerBuilder().withScore(200).build();
    player.reduceScore(200);
    assertEquals(0, player.getScore());
  }

  @Test
  public void testReduceScoreWithInvalidParameters() {
    Player player = new Player.PlayerBuilder().build();
    assertThrows(IllegalArgumentException.class, () -> {
      player.reduceScore(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.reduceScore(-100);
    });
  }

  @Test
  public void testAddGold() {
    Player player = new Player.PlayerBuilder().withGold(50).build();
    player.addGold(120);
    assertEquals(170, player.getGold());
  }

  @Test
  public void testAddGoldWithInvalidParameters() {
    Player player = new Player.PlayerBuilder().build();
    assertThrows(IllegalArgumentException.class, () -> {
      player.addGold(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.addGold(-100);
    });
  }

  @Test
  public void testReduceGold() {
    Player player = new Player.PlayerBuilder().withGold(50).build();
    player.reduceGold(150);
    assertEquals(-100, player.getGold());
  }

  @Test
  public void testReduceGoldWithInvalidParameters() {
    Player player = new Player.PlayerBuilder().build();
    assertThrows(IllegalArgumentException.class, () -> {
      player.reduceGold(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.reduceGold(-100);
    });
  }

  @Test
  public void testAddToInventory() {
    Player player = new Player.PlayerBuilder().build();
    player.addToInventory("socks");
    player.addToInventory("hat");
    assertEquals("socks", player.getInventory().get(0));
    assertEquals(2, player.getInventory().size());
  }

  @Test
  public void testAddToInventoryExistingItem() {
    Player player = new Player.PlayerBuilder().build();
    player.addToInventory("socks");
    player.addToInventory("hat");
    assertThrows(IllegalArgumentException.class, () -> {
      player.addToInventory("hat");
    });
  }

  @Test
  public void testAddToInventoryInvalidItems() {
    Player player = new Player.PlayerBuilder().build();
    assertThrows(IllegalArgumentException.class, () -> {
      player.addToInventory("");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.addToInventory(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.addToInventory("  ");
    });
  }

  @Test
  public void testRemoveFromInventory() {
    Player player = new Player.PlayerBuilder().build();
    player.addToInventory("socks");
    player.addToInventory("hat");
    player.removeFromInventory("socks");
    assertEquals(1, player.getInventory().size());
  }

  @Test
  public void testRemoveFromInventoryNonExistingItem() {
    Player player = new Player.PlayerBuilder().build();
    player.addToInventory("hat");
    assertThrows(IllegalArgumentException.class, () -> {
      player.removeFromInventory("socks");
    });
  }

  @Test
  public void testRemoveFromInventoryInvalidItems() {
    Player player = new Player.PlayerBuilder().build();
    assertThrows(IllegalArgumentException.class, () -> {
      player.removeFromInventory("");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.removeFromInventory(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      player.removeFromInventory("   ");
    });
  }

  @Test
  public void testIsDeadTrue() {
    Player player = new Player.PlayerBuilder().build();
    player.reduceHealth(100);
    assertTrue(player.isDead());
  }

  @Test
  public void testIsDeadFalse() {
    Player player = new Player.PlayerBuilder().build();
    assertFalse(player.isDead());
  }

  @Test
  public void testHasItemTrue() {
    Player player = new Player.PlayerBuilder().build();
    player.addToInventory("beer");
    assertTrue(player.hasItem("beer"));
  }

  @Test
  public void testHasItemFalse() {
    Player player = new Player.PlayerBuilder().build();
    assertFalse(player.hasItem("beer"));
  }
}
