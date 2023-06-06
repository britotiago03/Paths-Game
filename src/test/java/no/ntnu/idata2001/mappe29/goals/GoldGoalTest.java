package no.ntnu.idata2001.mappe29.goals;

import java.util.ArrayList;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.actions.ReduceGoldAction;
import no.ntnu.idata2001.mappe29.model.goals.Goal;
import no.ntnu.idata2001.mappe29.model.goals.GoldGoal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the {@link GoldGoal GoldGoal} class.
 * <p>The following positive tests are performed:</p>
 * <ul>
 *   <li><b>Positive</b> test that creates an instance of {@link GoldGoal GoldGoal}
 *      with valid parameters.</li>
 *   <li><b>Positive</b> test that checks that the GoldGoal of a player is fulfilled.</li>
 * </ul>
 * The following negative tests are performed:
 *  <ul>
 *     <li><b>Negative</b> test that tries to create instances of
 *     {@link GoldGoal GoldGoal} with invalid parameters.</li>
 *     <li><b>Negative</b> test that checks that the GoldGoal of a player is not fulfilled.</li>
 *  </ul>
 *  </p>
 */
public class GoldGoalTest {
  @Test
  public void testCreationWithValidParameters() {
    assertDoesNotThrow(() -> {
      new GoldGoal(100);
    });
  }

  @Test
  public void testCreationWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new GoldGoal(0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new GoldGoal(-200);
    });
  }

  @Test
  public void testIsFulfilledTrue() {
    Player player = new Player.PlayerBuilder().build();
    Goal goal = new GoldGoal(100);
    player.addGold(1000);
    assertTrue(goal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledFalse() {
    Player player = new Player.PlayerBuilder().build();
    Goal goal = new GoldGoal(100);
    assertFalse(goal.isFulfilled(player));
  }
}
