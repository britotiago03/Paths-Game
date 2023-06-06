package no.ntnu.idata2001.mappe29.actions;

import java.util.ArrayList;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.ReduceGoldAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the {@link ReduceGoldAction ReduceGoldAction} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of {@link ReduceGoldAction ReduceGoldAction}
 *      with valid parameters.</li>
 *   <li><b>Positive</b> test that executes the ReduceGoldAction on a player with the player
 *   not getting debt as a result.</li>
 *   <li><b>Positive</b> test that executes the ReduceGoldAction on a player with the player
 *   getting debt as a result.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link ReduceGoldAction ReduceGoldAction} with invalid parameters.</li>
 *  </ul>
 *  </p>
 */
public class ReduceGoldActionTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new ReduceGoldAction(70);
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new ReduceGoldAction(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new ReduceGoldAction(-10);
    });
  }

  @Test
  public void testExecuteWithoutDebtResult() {
    Action action = new ReduceGoldAction(100);
    Player player = new Player.PlayerBuilder().withGold(250).build();
    action.execute(player);
    assertEquals(150, player.getGold());
  }

  @Test
  public void testExecuteWithDebtResult() {
    Action action = new ReduceGoldAction(400);
    Player player = new Player.PlayerBuilder().withGold(250).build();
    action.execute(player);
    assertEquals(-150, player.getGold());
  }

}
