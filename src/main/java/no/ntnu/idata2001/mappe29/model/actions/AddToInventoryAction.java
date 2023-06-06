package no.ntnu.idata2001.mappe29.model.actions;

import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents an action that adds an item to the player’s inventory.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class AddToInventoryAction implements Action {
  private final String item;

  /**
   * Creates an instance of AddToInventoryAction.
   *
   * @param item the item to be added to the player's inventory.
   * @throws IllegalArgumentException if the item to be added is null or blank.
   */
  public AddToInventoryAction(String item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null!");
    } else if (item.isBlank()) {
      throw new IllegalArgumentException("Item cannot be blank!");
    }
    this.item = item;
  }

  /**
   * Adds the item to the specified player's inventory.
   *
   * @param player the specified player.
   * @throws IllegalArgumentException if the item is already in the player's inventory.
   */
  @Override
  public void execute(Player player) {
    player.addToInventory(this.item);
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
