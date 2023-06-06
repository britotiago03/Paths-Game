package no.ntnu.idata2001.mappe29.userinterface.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.Passage;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Story Introduction Screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.21.
 */
public class StoryInformationScreenController extends Controller {

  /**
   * Creates an instance of StoryIntroductionScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public StoryInformationScreenController(PathsApp pathsApp) {
    super("story_information_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the button click of button 1 on the play screen of the Paths Application.
   */
  public void handleButton1Click(Story story) {
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(confirmationDialog);
    confirmationDialog.setTitle("Create Goals");
    confirmationDialog.setHeaderText("Do you want to make goals for this story?");
    confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

    ButtonType selectedOption = confirmationDialog.showAndWait().orElse(ButtonType.NO);
    if (selectedOption == ButtonType.YES) {
      Screen goalsScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen("goals_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(goalsScreen.createScene(this.pathsApp));
    } else if (selectedOption == ButtonType.NO) {
      this.pathsApp.getModelManager().getPlayer().resetStats();
      Passage openingPassage = story.getOpeningPassage();
      Screen openingPassageScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen(openingPassage.getTitle() + "_passage_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(openingPassageScreen.createScene(this.pathsApp));
    }
  }

  /**
   * Handles the button click of button 2 on the play screen of the Paths Application.
   */
  public void handleButton2Click(String button2Text) {
    if (button2Text.equals("Cancel")) {
      Screen playScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen("play_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(playScreen.createScene(this.pathsApp));
    } else {
      Screen homeScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen("home_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(homeScreen.createScene(this.pathsApp));
    }
  }
}
