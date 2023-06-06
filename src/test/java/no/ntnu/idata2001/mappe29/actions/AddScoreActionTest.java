package no.ntnu.idata2001.mappe29.actions;

import java.util.ArrayList;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.AddScoreAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the {@link AddScoreAction AddScoreAction} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of {@link AddScoreAction AddScoreAction}
 *      with valid parameters.</li>
 *   <li><b>Positive</b> test that executes the AddScoreAction on a player.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link AddScoreAction AddScoreAction} with invalid parameters.</li>
 *  </ul>
 *  </p>
 */
public class AddScoreActionTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new AddScoreAction(300);
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new AddScoreAction(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new AddScoreAction(-10);
    });
  }

  @Test
  public void testExecute() {
    Action action = new AddScoreAction(200);
    Player player = new Player.PlayerBuilder().build();
    action.execute(player);
    assertEquals(200, player.getScore());
  }

}
