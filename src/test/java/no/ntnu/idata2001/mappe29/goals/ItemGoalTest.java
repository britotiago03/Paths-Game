package no.ntnu.idata2001.mappe29.goals;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.goals.Goal;
import no.ntnu.idata2001.mappe29.model.goals.ItemGoal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the {@link ItemGoal InventoryGoal} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of {@link ItemGoal InventoryGoal}
 *      with valid parameters.</li>
 *   <li><b>Positive</b> test that checks that the InventoryGoal of a player is fulfilled.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link ItemGoal InventoryGoal} with invalid parameters.</li>
 *     <li><b>Negative</b> test that checks that the InventoryGoal of a player is not
 *     fulfilled.</li>
 *  </ul>
 *  </p>
 */
public class ItemGoalTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new ItemGoal("whiskey");
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new ItemGoal("");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new ItemGoal("    ");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new ItemGoal(null);
    });
  }

  @Test
  public void testIsFulfilledTrue() {
    Player player = new Player.PlayerBuilder().build();
    Goal goal = new ItemGoal("whiskey");
    player.addToInventory("whiskey");
    player.addToInventory("sword");
    player.addToInventory("shield");
    player.addToInventory("hat");
    assertTrue(goal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledFalse() {
    Player player = new Player.PlayerBuilder().build();
    Goal goal = new ItemGoal("beer");
    player.addToInventory("whiskey");
    player.addToInventory("sword");
    player.addToInventory("hat");
    assertFalse(goal.isFulfilled(player));
  }
}
