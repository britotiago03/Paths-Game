package no.ntnu.idata2001.mappe29.actions;

import java.util.ArrayList;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.AddToInventoryAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the {@link AddToInventoryAction AddToInventoryAction} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of
 *   {@link AddToInventoryAction AddToInventoryAction} with valid parameters.</li>
 *   <li><b>Positive</b> test that executes the AddToInventoryAction on a player with an inventory
 *   that does not have the item that is added.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link AddToInventoryAction AddToInventoryAction} with invalid parameters.</li>
 *     <li><b>Negative</b> test that executes the AddToInventoryAction on a player with an
 *     inventory that already has the item that is added.</li>
 *  </ul>
 *  </p>
 */
public class AddToInventoryActionTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new AddToInventoryAction("pizza");
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new AddToInventoryAction("");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new AddToInventoryAction(null);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new AddToInventoryAction("    ");
    });
  }

  @Test
  public void testExecuteWithNonExistingItem() {
    Action action = new AddToInventoryAction("whiskey");
    Player player = new Player.PlayerBuilder().build();
    action.execute(player);
    assertEquals("whiskey", player.getInventory().get(0));
  }

  @Test
  public void testExecuteWithExistingItem() {
    Action action = new AddToInventoryAction("whiskey");
    Player player = new Player.PlayerBuilder().build();
    action.execute(player);
    assertThrows(IllegalArgumentException.class, () -> {
      action.execute(player);
    });
  }
}
