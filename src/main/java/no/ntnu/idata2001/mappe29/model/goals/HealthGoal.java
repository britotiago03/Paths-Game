package no.ntnu.idata2001.mappe29.model.goals;

import javafx.scene.image.Image;
import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents a target amount of health for the player's health attribute. Checks if the
 * player has achieved the target amount of health.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class HealthGoal implements Goal {
  private final int minimumHealth;
  private Image image;
  private String description;

  /**
   * Creates an instance of HealthGoal.
   *
   * @param minimumHealth the specified minimum health of this goal.
   * @throws IllegalArgumentException if the specified minimum health is less than 1.
   */
  public HealthGoal(int minimumHealth) {
    if (minimumHealth < 1) {
      throw new IllegalArgumentException("Minimum health cannot be less than 1");
    }
    this.minimumHealth = minimumHealth;
    this.image = null;
    this.description = "";
  }

  @Override
  public boolean isFulfilled(Player player) {
    return this.minimumHealth <= player.getHealth();
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
