package no.ntnu.idata2001.mappe29.model.goals;

import javafx.scene.image.Image;
import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents a target list of items for the player's inventory. Checks if the player has
 * achieved the target list of items.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class ItemGoal implements Goal {
  private String mandatoryItem;
  private Image image;
  private String description;

  /**
   * Creates an instance of InventoryGoal.
   *
   * @param mandatoryItem the specified mandatory item of this goal.
   * @throws IllegalArgumentException if the specified mandatory item is null or blank.
   */
  public ItemGoal(String mandatoryItem) {
    this.setMandatoryItem(mandatoryItem);
    this.image = null;
    this.description = "";
  }

  /**
   * Gets the mandatory item of this goal.
   *
   * @return the mandatory item of this goal.
   */
  public String getMandatoryItem() {
    return this.mandatoryItem;
  }

  /**
   * Sets the mandatory item of this item goal to the specified mandatory item.
   *
   * @param mandatoryItem the specified mandatory item.
   * @throws IllegalArgumentException if the specified Mandatory item is null or blank
   */
  public void setMandatoryItem(String mandatoryItem) {
    if (mandatoryItem == null) {
      throw new IllegalArgumentException("Mandatory item cannot be null");
    } else if (mandatoryItem.isBlank()) {
      throw new IllegalArgumentException("Mandatory item cannot be blank");
    }
    this.mandatoryItem = mandatoryItem;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return player.hasItem(this.mandatoryItem);
  }

  @Override
  public Image getImage() {
    return this.image;
  }

  /**
   * Sets the image of this goal to the specified image.
   *
   * @param image the specified image.
   */
  public void setImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.image = image;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Sets the description of this goal to the specified description.
   *
   * @param description the specified description.
   */
  public void setDescription(String description) {
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    } else if (description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be blank");
    }
    this.description = description;
  }
}
