package no.ntnu.idata2001.mappe29.userinterface.screens;

import javafx.scene.Scene;
import no.ntnu.idata2001.mappe29.PathsApp;

/**
 * Represents a screen in the user interface of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.19.
 */
public abstract class Screen {
  private String name;

  protected Screen(String name) {
    this.setName(name);
  }

  /**
   * Sets the name of this screen to the specified name.
   *
   * @param name the specified name.
   * @throws IllegalArgumentException if the specified name is null or blank.
   */
  private void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Screen name cannot be null");
    } else if (name.isBlank()) {
      throw new IllegalArgumentException("Screen name cannot be blank");
    }
    this.name = name;
  }

  /**
   * Gets the name of this screen.
   *
   * @return the name of this screen.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Creates a scene of this screen.
   *
   * @return the scene of this screen.
   */
  public abstract Scene createScene(PathsApp pathsApp);
}
