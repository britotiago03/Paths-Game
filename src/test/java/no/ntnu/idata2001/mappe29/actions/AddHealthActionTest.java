package no.ntnu.idata2001.mappe29.actions;

import java.util.ArrayList;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.AddHealthAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the {@link AddHealthAction AddHealthAction} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of {@link AddHealthAction AddHealthAction}
 *      with valid parameters.</li>
 *   <li><b>Positive</b> test that executes the AddHealthAction on a player without overflow of
 *   health.</li>
 *   <li><b>Positive</b> test that executes the AddHealthAction on a player with overflow of
 *   health.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link AddHealthAction AddHealthAction} with invalid parameters.</li>
 *  </ul>
 *  </p>
 */
public class AddHealthActionTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new AddHealthAction(30);
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new AddHealthAction(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new AddHealthAction(-100);
    });
  }

  @Test
  public void testExecuteWithoutOverflow() {
    Action action = new AddHealthAction(30);
    Player player = new Player.PlayerBuilder().withHealth(60).build();
    action.execute(player);
    assertEquals(90, player.getHealth());
  }

  @Test
  public void testExecuteWithOverflow() {
    Action action = new AddHealthAction(100);
    Player player = new Player.PlayerBuilder().withHealth(60).build();
    action.execute(player);
    assertEquals(100, player.getHealth());
  }

}
