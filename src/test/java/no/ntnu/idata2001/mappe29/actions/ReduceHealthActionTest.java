package no.ntnu.idata2001.mappe29.actions;

import java.util.ArrayList;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.ReduceHealthAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the {@link ReduceHealthAction ReduceHealthAction} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of
 *   {@link ReduceHealthAction ReduceHealthAction} with valid parameters.</li>
 *   <li><b>Positive</b> test that executes the ReduceHealthAction on a player without health
 *   overflow.</li>
 *   <li><b>Positive</b> test that executes the ReduceHealthAction on a player with health
 *   overflow.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link ReduceHealthAction ReduceHealthAction} with invalid parameters.</li>
 *  </ul>
 *  </p>
 */
public class ReduceHealthActionTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new ReduceHealthAction(70);
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new ReduceHealthAction(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new ReduceHealthAction(-10);
    });
  }

  @Test
  public void testExecuteWithoutOverflow() {
    Player player = new Player.PlayerBuilder().build();
    Action action = new ReduceHealthAction(30);
    action.execute(player);
    assertEquals(70, player.getHealth());
  }

  @Test
  public void testExecuteWithOverflow() {
    Player player = new Player.PlayerBuilder().build();
    Action action = new ReduceHealthAction(130);
    action.execute(player);
    assertEquals(0, player.getHealth());
  }
}
