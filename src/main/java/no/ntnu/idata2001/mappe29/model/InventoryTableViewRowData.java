package no.ntnu.idata2001.mappe29.model;

import javafx.scene.image.Image;

/**
 * Represents an inventory row data of the inventory table view in the Passage screen of the Paths
 * Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.22.
 */
public class InventoryTableViewRowData {
  private Image image;
  private String description;

  /**
   * Creates an instance of InventoryTableViewRowData.
   *
   * @param image       the specified image of this inventory tableview row data.
   * @param description the specified description of this inventory tableview row data.
   */
  public InventoryTableViewRowData(Image image, String description) {
    this.setImage(image);
    this.setDescription(description);
  }

  /**
   * Sets the image of this inventory tableview row data to the specified image.
   *
   * @param image the specified image.
   * @throws IllegalArgumentException if the specified image is null.
   */
  private void setImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.image = image;
  }

  /**
   * Gets the image of this inventory tableview row data.
   *
   * @return the image of this inventory tableview row data.
   */
  public Image getImage() {
    return this.image;
  }

  /**
   * Sets the description of this inventory tableview row data to the specified description.
   *
   * @param description the specified description.
   * @throws IllegalArgumentException if the specified description is null or blank.
   */
  private void setDescription(String description) {
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    } else if (description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be blank");
    }
    this.description = description;
  }

  /**
   * Gets the description of this inventory tableview row data.
   *
   * @return the description of this inventory tableview row data.
   */
  public String getDescription() {
    return this.description;
  }
}
