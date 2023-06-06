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
public class PlayScreenController extends Controller {

  /**
   * Creates an instance of PlayScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public PlayScreenController(PathsApp pathsApp) {
    super("play_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the button click of button 1 on the play screen of the Paths Application.
   */
  public void handleButton1Click() {
    if (this.pathsApp.getModelManager().getStoryCollection().size() > 0) {
      Story randomStory = this.pathsApp.getModelManager().getRandomStory();
      Screen storyIntroductionScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen(randomStory.getTitle() + "_story_introduction_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(storyIntroductionScreen.createScene(this.pathsApp));
      GoalsScreenController goalsScreenController = (GoalsScreenController)
          this.pathsApp.getUserInterfaceManager()
              .getController("goals_screen_controller");
      goalsScreenController.setStorySelected(randomStory);
    } else {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("No Stories", "There "
          + "are stories available to play");
    }

  }

  /**
   * Handles the button click of button 2 on the play screen of the Paths Application.
   */
  public void handleButton2Click() {
    if (this.pathsApp.getModelManager().getStoryCollection().size() > 0) {
      Screen chooseStoryScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen("choose_story_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(chooseStoryScreen.createScene(this.pathsApp));
    } else {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("No Stories", "There "
          + "are stories available to choose");
    }

  }

  /**
   * Handles the button click of button 3 on the play screen of the Paths Application.
   */
  public void handleButton3Click() {
    Screen homeScreen = this.pathsApp.getUserInterfaceManager()
        .getScreen("home_screen");
    this.pathsApp.getUserInterfaceManager()
        .setPrimaryStageScene(homeScreen.createScene(this.pathsApp));
  }

}
