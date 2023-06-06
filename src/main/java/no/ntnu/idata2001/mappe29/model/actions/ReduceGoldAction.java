package no.ntnu.idata2001.mappe29.model.actions;

import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents an action that reduces gold from the player’s gold attribute.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class ReduceGoldAction implements Action {

  private final int gold;

  /**
   * Creates an instance of ReduceGoldAction.
   *
   * @param gold the gold to reduce from the player's gold attribute.
   * @throws IllegalArgumentException if the gold to be reduced is less than 1.
   */
  public ReduceGoldAction(int gold) {
    if (gold < 1) {
      throw new IllegalArgumentException("Gold to be reduced cannot be less than 1");
    }
    this.gold = gold;
  }

  /**
   * Reduces the gold from the specified player's gold attribute.
   *
   * @param player the specified player.
   */
  @Override
  public void execute(Player player) {
    player.reduceGold(this.gold);
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
