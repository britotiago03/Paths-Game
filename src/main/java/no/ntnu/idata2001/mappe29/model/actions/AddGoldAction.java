package no.ntnu.idata2001.mappe29.model.actions;

import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents an action that adds gold to the player’s gold attribute.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class AddGoldAction implements Action {
  private final int gold;

  /**
   * Creates an instance of AddGoldAction.
   *
   * @param gold the gold to add to the player's gold attribute.
   * @throws IllegalArgumentException if the gold to be added is less than 1.
   */
  public AddGoldAction(int gold) {
    if (gold < 1) {
      throw new IllegalArgumentException("Gold to be added cannot be less than 1");
    }
    this.gold = gold;
  }

  /**
   * Adds the gold to the specified player's gold attribute.
   *
   * @param player the specified player.
   */
  @Override
  public void execute(Player player) {
    player.addGold(this.gold);
  }

  /**
   * Gets the amount of gold of this action.
   *
   * @return the amount of gold of this action.
   */
  public int getGold() {
    return this.gold;
  }
}
