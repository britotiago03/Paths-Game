package no.ntnu.idata2001.mappe29.userinterface.controllers;

import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Play Screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.21.
 */
public class ChooseStoryScreenController extends Controller {

  /**
   * Creates an instance of ChooseStoryScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public ChooseStoryScreenController(PathsApp pathsApp) {
    super("choose_story_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the button click of button 1 on the choose story screen of the Paths Application.
   */
  public void handleButton1Click(Story story) {
    if (story == null) {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Story",
          "Please select a story");
    } else {
      Screen storyIntroductionScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen(story.getTitle() + "_story_introduction_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(storyIntroductionScreen.createScene(this.pathsApp));
      GoalsScreenController goalsScreenController = (GoalsScreenController)
          this.pathsApp.getUserInterfaceManager()
              .getController("goals_screen_controller");
      goalsScreenController.setStorySelected(story);
    }
  }

  /**
   * Handles the button click of button 2 on the choose story screen of the Paths Application.
   */
  public void handleButton2Click() {
    Screen playScreen = this.pathsApp.getUserInterfaceManager()
        .getScreen("play_screen");
    this.pathsApp.getUserInterfaceManager()
        .setPrimaryStageScene(playScreen.createScene(this.pathsApp));
  }

}
