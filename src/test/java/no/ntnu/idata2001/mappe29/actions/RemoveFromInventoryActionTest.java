package no.ntnu.idata2001.mappe29.actions;

import java.util.ArrayList;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.ReduceScoreAction;
import no.ntnu.idata2001.mappe29.model.actions.RemoveFromInventoryAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the {@link RemoveFromInventoryAction RemoveFromInventoryAction} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of
 *   {@link RemoveFromInventoryAction RemoveFromInventoryAction} with valid parameters.</li>
 *   <li><b>Positive</b> test that executes the RemoveFromInventoryAction with an item that the
 *   player has in his inventory.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link RemoveFromInventoryAction RemoveFromInventoryAction} with invalid parameters.</li>
 *     <li><b>Negative</b> test that executes the RemoveFromInventoryAction with an item that the
 *     player does not have in his inventory.</li>
 *  </ul>
 *  </p>
 */
public class RemoveFromInventoryActionTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new RemoveFromInventoryAction("sword");
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new RemoveFromInventoryAction("");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new RemoveFromInventoryAction("   ");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new RemoveFromInventoryAction(null);
    });
  }

  @Test
  public void testExecuteWithExistingItem() {
    Player player = new Player.PlayerBuilder().build();
    player.addToInventory("champagne");
    Action action = new RemoveFromInventoryAction("champagne");
    action.execute(player);
    assertFalse(player.hasItem("champagne"));
  }

  @Test
  public void testExecuteWithNonExistingItem() {
    Player player = new Player.PlayerBuilder().build();
    Action action = new RemoveFromInventoryAction("champagne");
    assertThrows(IllegalArgumentException.class, () -> {
      action.execute(player);
    });
  }
}
