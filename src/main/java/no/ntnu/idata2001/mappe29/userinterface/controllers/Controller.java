package no.ntnu.idata2001.mappe29.userinterface.controllers;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import no.ntnu.idata2001.mappe29.PathsApp;

/**
 * Represents a controller for a screen in the user interface of the Paths Application.
 */
public abstract class Controller {
  private String name;
  protected PathsApp pathsApp;

  protected Controller(String name) {
    this.setName(name);
  }

  /**
   * Sets the name of this controller to the specified name.
   *
   * @param name the specified name.
   * @throws IllegalArgumentException if the specified name is null or blank.
   */
  private void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Controller name cannot be null");
    } else if (name.isBlank()) {
      throw new IllegalArgumentException("Controller name cannot be blank");
    }
    this.name = name;
  }

  /**
   * Gets the name of this controller.
   *
   * @return the name of this controller.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the PathsApp instance of this controller to the specified PathsApp instance.
   *
   * @param pathsApp the specified PathsApp instance.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  protected void setPathsApp(PathsApp pathsApp) {
    if (pathsApp == null) {
      throw new IllegalArgumentException("PathsApp cannot be null");
    }
    this.pathsApp = pathsApp;
  }

  /**
   * Handles the how to play menu item.
   */
  public void handleHowToPlayMenuItem() {
    Alert howToPlayDialog = new Alert(Alert.AlertType.INFORMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(howToPlayDialog);
    howToPlayDialog.setTitle("How To Play Paths");
    StringBuilder headerTextStringBuilder = new StringBuilder();
    headerTextStringBuilder.append("To play Paths, you need to do the following:\n\n");
    headerTextStringBuilder.append("1. Create an avatar by writing your name and selecting "
        + "an avatar.\n\n");
    headerTextStringBuilder.append("2. Click on the Play button of the Home screen.\n\n");
    headerTextStringBuilder.append("3. Check if there are stories by clicking on Random Story "
        + "or Choose Story. If there are no stories, you need to import stories.\n\n");
    headerTextStringBuilder.append("4. Click on the Start button after clicking on Random Story "
        + "or Selecting a story in the Choose Story screen.\n\n");
    headerTextStringBuilder.append("5. After the goals pop-up window comes up, choose yes if you "
        + "want to make goals to achieve during the story, or choose no if you don't.\n\n");
    headerTextStringBuilder.append("6. Enjoy playing Paths.");

    howToPlayDialog.setHeaderText(headerTextStringBuilder.toString());
    howToPlayDialog.getButtonTypes().setAll(ButtonType.OK);
    howToPlayDialog.showAndWait();
  }

  /**
   * Handles the how to import story menu item.
   */
  public void handleHowToImportStoryMenuItem() {
    Alert howToImportStoryDialog = new Alert(Alert.AlertType.INFORMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(howToImportStoryDialog);
    howToImportStoryDialog.setTitle("How To Import A Story");
    StringBuilder headerTextStringBuilder = new StringBuilder();
    headerTextStringBuilder.append("To import a story in Paths, you need to do the following:\n\n");
    headerTextStringBuilder.append("1. Create an avatar by writing your name and selecting "
        + "an avatar.\n\n");
    headerTextStringBuilder.append("2. Make sure you have downloaded valid stories that have the "
        + ".paths extension.\n\n");
    headerTextStringBuilder.append("3. Click on the Options button of the Home screen, then click"
        + "on the Import Story button.\n\n");
    headerTextStringBuilder.append("4. Your file explorer will show up and you need to select the "
        + ".paths file of the story you want to import.\n\n");
    headerTextStringBuilder.append("5. After selecting the file, you need to double click on it "
        + "to import it to Paths.\n\n");
    headerTextStringBuilder.append("6. If the story was successfully imported, is corrupt, or has "
        + "broken links, you will notified about what happened.");

    howToImportStoryDialog.setHeaderText(headerTextStringBuilder.toString());
    howToImportStoryDialog.getButtonTypes().setAll(ButtonType.OK);
    howToImportStoryDialog.showAndWait();
  }

  /**
   * Handles the how to export story menu item.
   */
  public void handleHowToExportStoryMenuItem() {
    Alert howToExportStoryDialog = new Alert(Alert.AlertType.INFORMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(howToExportStoryDialog);
    howToExportStoryDialog.setTitle("How To Export A Story");
    StringBuilder headerTextStringBuilder = new StringBuilder();
    headerTextStringBuilder.append("To export a story in Paths, you need to do the following:\n\n");
    headerTextStringBuilder.append("1. Create an avatar by writing your name and selecting "
        + "an avatar.\n\n");
    headerTextStringBuilder.append("2. Click on the Options button of the Home screen, then click"
        + "on the Export Story button.\n\n");
    headerTextStringBuilder.append("3. If there are stories available to export, you will see a "
        + "table with all the stories currently in Paths, if there are no stories available you "
        + "will be notified.\n\n");
    headerTextStringBuilder.append("4. Double click on the story you want to export or click once "
        + "and click the export button. Your file explorer will show up and you can choose where "
        + "you want to save the .paths story file.\n\n");

    howToExportStoryDialog.setHeaderText(headerTextStringBuilder.toString());
    howToExportStoryDialog.getButtonTypes().setAll(ButtonType.OK);
    howToExportStoryDialog.showAndWait();
  }

  /**
   * Handles the what are goals story menu item.
   */
  public void handleWhatAreGoalsMenuItem() {
    Alert whatAreGoalsDialog = new Alert(Alert.AlertType.INFORMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(whatAreGoalsDialog);
    whatAreGoalsDialog.setTitle("What Are Goals?");
    StringBuilder headerTextStringBuilder = new StringBuilder();
    headerTextStringBuilder.append("In Paths, goals are desired outcomes that you would like "
        + "to achieve\nduring the story you are playing.\n\n");
    headerTextStringBuilder.append("You have the following types of goals in Paths:\n\n");
    headerTextStringBuilder.append("Gold Goals: The amount of gold you would like to gain during "
        + "the story.\n\n");
    headerTextStringBuilder.append("Health Goals: The amount of health you would like to end the "
        + "story with.\n\n");
    headerTextStringBuilder.append("Score Goals: The amount of score points you would like to gain "
        + "during the story.\n\n");

    whatAreGoalsDialog.setHeaderText(headerTextStringBuilder.toString());
    whatAreGoalsDialog.getButtonTypes().setAll(ButtonType.OK);
    whatAreGoalsDialog.showAndWait();
  }
}
