package no.ntnu.idata2001.mappe29.model.actions;

import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents an action that reduces health from the player’s health attribute.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class ReduceHealthAction implements Action {
  private final int health;

  /**
   * Creates an instance of ReduceHealthAction.
   *
   * @param health the health to reduce from the player's health attribute.
   * @throws IllegalArgumentException if the health to be reduced is less than 1.
   */
  public ReduceHealthAction(int health) {
    if (health < 1) {
      throw new IllegalArgumentException("Health to be reduced cannot be less than 1");
    }
    this.health = health;
  }

  /**
   * Reduces the health from the specified player's health attribute.
   *
   * @param player the specified player.
   */
  @Override
  public void execute(Player player) {
    player.reduceHealth(this.health);
  }

  /**
   * Gets the amount of health of this action.
   *
   * @return the amount of health of this action.
   */
  public int getHealth() {
    return this.health;
  }
}
