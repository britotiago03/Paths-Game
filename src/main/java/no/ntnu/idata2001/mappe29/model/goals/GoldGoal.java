package no.ntnu.idata2001.mappe29.model.goals;

import javafx.scene.image.Image;
import no.ntnu.idata2001.mappe29.model.Player;

/**
 * Represents a target amount of gold for the player's gold attribute. Checks if the
 * player has achieved the target amount of gold.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class GoldGoal implements Goal {
  private final int minimumGold;
  private Image image;
  private String description;

  /**
   * Creates an instance of GoldGoal.
   *
   * @param minimumGold the specified minimum gold of this goal.
   * @throws IllegalArgumentException if the specified minimum gold of this goal is less than 1.
   */
  public GoldGoal(int minimumGold) {
    if (minimumGold < 1) {
      throw new IllegalArgumentException("Minimum gold of a gold goal cannot be less than 1");
    }
    this.minimumGold = minimumGold;
    this.image = null;
    this.description = "";
  }

  @Override
  public boolean isFulfilled(Player player) {
    return this.minimumGold <= player.getGold();
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
