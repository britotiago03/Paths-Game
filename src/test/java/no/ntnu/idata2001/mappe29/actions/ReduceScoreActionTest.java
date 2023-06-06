package no.ntnu.idata2001.mappe29.actions;

import java.util.ArrayList;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.ReduceHealthAction;
import no.ntnu.idata2001.mappe29.model.actions.ReduceScoreAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the {@link ReduceScoreAction ReduceScoreAction} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of
 *   {@link ReduceScoreAction ReduceScoreAction} with valid parameters.</li>
 *   <li><b>Positive</b> test that executes the ReduceScoreAction on a player without score
 *   overflow.</li>
 *   <li><b>Positive</b> test that executes the ReduceScoreAction on a player with score
 *   overflow.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link ReduceScoreAction ReduceScoreAction} with invalid parameters.</li>
 *  </ul>
 *  </p>
 */
public class ReduceScoreActionTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new ReduceScoreAction(100);
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new ReduceScoreAction(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new ReduceScoreAction(-100);
    });
  }

  @Test
  public void testExecuteWithoutOverflow() {
    Player player = new Player.PlayerBuilder().withScore(1000).build();
    Action action = new ReduceScoreAction(300);
    action.execute(player);
    assertEquals(700, player.getScore());
  }

  @Test
  public void testExecuteWithOverflow() {
    Player player = new Player.PlayerBuilder().withScore(1000).build();
    Action action = new ReduceScoreAction(3000);
    action.execute(player);
    assertEquals(0, player.getScore());
  }
}
