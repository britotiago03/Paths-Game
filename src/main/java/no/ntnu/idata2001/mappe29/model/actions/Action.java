package no.ntnu.idata2001.mappe29.model.actions;

import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents a change in the state of a player.
 */
public interface Action {
  /**
   * Changes the specified player's state when called.
   *
   * @param player the specified player.
   */
  void execute(Player player);
}
