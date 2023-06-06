package no.ntnu.idata2001.mappe29.model.actions;

import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents an action that removes an item from the player’s inventory.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class RemoveFromInventoryAction implements Action {
  private final String item;

  /**
   * Creates an instance of RemoveFromInventoryAction.
   *
   * @param item the item to be removed from the player's inventory.
   * @throws IllegalArgumentException if the item to be removed is null or blank.
   */
  public RemoveFromInventoryAction(String item) {
    if (item == null) {
      throw new IllegalArgumentException("Item to be removed be null!");
    } else if (item.isBlank()) {
      throw new IllegalArgumentException("Item to be removed be blank!");
    }
    this.item = item;
  }

  /**
   * Removes the item from the specified player's inventory.
   *
   * @param player the specified player.
   * @throws IllegalArgumentException if the item is not in the player's inventory.
   */
  @Override
  public void execute(Player player) {
    player.removeFromInventory(this.item);
  }

  /**
   * Gets the item of this action.
   *
   * @return the item of this action.
   */
  public String getItem() {
    return this.item;
  }
}
