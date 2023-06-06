package no.ntnu.idata2001.mappe29.model.goals;

import javafx.scene.image.Image;
import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents a target value or a desired result related to the player’s state. Checks if the
 * player has achieved the expected result.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public interface Goal {
  /**
   * Checks if the specified player has achieved the goal.
   *
   * @param player the specified player.
   * @return true if the specified player has achieved the goal, false otherwise.
   */
  boolean isFulfilled(Player player);

  /**
   * Gets the image of this goal.
   *
   * @return the image of this goal.
   */
  Image getImage();

  /**
   * Gets the description of this goal.
   *
   * @return the description of this goal.
   */
  String getDescription();
}
