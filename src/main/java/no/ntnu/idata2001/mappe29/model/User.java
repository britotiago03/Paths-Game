package no.ntnu.idata2001.mappe29.model;

import javafx.scene.image.Image;

/**
 * Represents a user of the Paths Application.
 */
public class User {
  private String name;
  private Image image;

  /**
   * Creates an instance of User.
   */
  public User() {
    this.name = null;
    this.image = null;
  }

  /**
   * Creates an instance of user.
   *
   * @param name  the specified name of the user.
   * @param image the specified image of the user.
   */
  public User(String name, Image image) {
    this.setName(name);
    this.setImage(image);
  }

  /**
   * Sets the name of this user to the specified name.
   *
   * @param name the specified name.
   * @throws IllegalArgumentException if the specified name is null or blank.
   */
  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("User name cannot be null");
    } else if (name.isBlank()) {
      throw new IllegalArgumentException("User name cannot be blank");
    }
    this.name = name;
  }

  /**
   * Gets the name of this user.
   *
   * @return the name of this user.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the image of this user to the specified image.
   *
   * @param image the image of this player.
   */
  public void setImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Image view of the image of the user cannot be null");
    }
    this.image = image;
  }

  /**
   * Gets the image of this player.
   *
   * @return the image of this player.
   */
  public Image getImage() {
    return this.image;
  }
}
