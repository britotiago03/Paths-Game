package no.ntnu.idata2001.mappe29.actions;

import java.util.ArrayList;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.AddGoldAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the {@link AddGoldAction AddGoldAction} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of {@link AddGoldAction AddGoldAction}
 *      with valid parameters.</li>
 *   <li><b>Positive</b> test that executes the AddGoldAction on a player.</li>
 *   <li><b>Positive</b> test that executes the AddGoldAction on a player with debt in order to
 *   remove his debt.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link AddGoldAction AddGoldAction} with invalid parameters.</li>
 *  </ul>
 *  </p>
 */
public class AddGoldActionTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new AddGoldAction(50);
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new AddGoldAction(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new AddGoldAction(-100);
    });
  }

  @Test
  public void testExecute() {
    Action action = new AddGoldAction(230);
    Player player = new Player.PlayerBuilder().build();
    action.execute(player);
    assertEquals(230, player.getGold());
  }

  @Test
  public void testExecuteLoseDebt() {
    Action action = new AddGoldAction(230);
    Player player = new Player.PlayerBuilder().withGold(-100).build();
    action.execute(player);
    assertEquals(130, player.getGold());
  }
}
