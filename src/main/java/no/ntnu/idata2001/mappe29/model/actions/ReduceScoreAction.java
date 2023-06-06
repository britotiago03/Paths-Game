package no.ntnu.idata2001.mappe29.model.actions;

import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents an action that reduces score from the player’s score attribute.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class ReduceScoreAction implements Action {
  private final int points;

  /**
   * Creates an instance of ReduceScoreAction.
   *
   * @param points the points to be reduced from the player's score attribute.
   * @throws IllegalArgumentException if the points to be reduced are less than 1.
   */
  public ReduceScoreAction(int points) {
    if (points < 1) {
      throw new IllegalArgumentException("Points to be reduced cannot be less than 1");
    }
    this.points = points;
  }

  /**
   * Reduces the points from the specified player's score attribute.
   *
   * @param player the specified player.
   */
  @Override
  public void execute(Player player) {
    player.reduceScore(this.points);
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
