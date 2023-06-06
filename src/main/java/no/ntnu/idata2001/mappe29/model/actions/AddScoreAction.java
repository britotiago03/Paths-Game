package no.ntnu.idata2001.mappe29.model.actions;

import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents an action that adds score to the player’s score attribute.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class AddScoreAction implements Action {
  private final int points;

  /**
   * Creates an instance of AddScoreAction.
   *
   * @param points the points to be added to the player's score attribute.
   * @throws IllegalArgumentException if the points to be added are less than 1.
   */
  public AddScoreAction(int points) {
    if (points < 1) {
      throw new IllegalArgumentException("Points to be added cannot be less than 1");
    }
    this.points = points;
  }

  /**
   * Adds the points to the specified player's score attribute.
   *
   * @param player the specified player.
   */
  @Override
  public void execute(Player player) {
    player.addScore(this.points);
  }

  /**
   * Gets the amount of points of this action.
   *
   * @return the amount of points of this action.
   */
  public int getPoints() {
    return this.points;
  }
}
