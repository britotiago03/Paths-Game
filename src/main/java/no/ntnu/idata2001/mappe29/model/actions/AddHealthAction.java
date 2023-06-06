package no.ntnu.idata2001.mappe29.model.actions;

import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents an action that adds health to the player’s health attribute.
 *
 * @author Tiago Brito
 * @version 2023.02.23
 */
public class AddHealthAction implements Action {
  private final int health;

  /**
   * Creates an instance of AddHealthAction.
   *
   * @param health the health to be added to the player's health attribute.
   * @throws IllegalArgumentException if the health to be added is less than 1.
   */
  public AddHealthAction(int health) {
    if (health < 1) {
      throw new IllegalArgumentException("Health to be added cannot be less than 1");
    }
    this.health = health;
  }

  /**
   * Adds the health to the specified player's health attribute.
   *
   * @param player the specified player.
   */
  @Override
  public void execute(Player player) {
    player.addHealth(this.health);
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
