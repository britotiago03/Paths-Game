package no.ntnu.idata2001.mappe29.userinterface.controllers;

import java.io.IOException;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.exceptions.UnknownActionException;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Export Story Screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.22.
 */
public class ExportStoryScreenController extends Controller {
  /**
   * Creates an instance of ExportStoryScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public ExportStoryScreenController(PathsApp pathsApp) {
    super("export_story_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the button click of button 1 on the export story screen of the Paths Application.
   */
  public void handleButton1Click(Story story) {
    if (story == null) {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Story",
          "Please select a story");
    } else {
      try {
        this.pathsApp.getModelManager().getStoryFileManager().saveStoryToFile(story);
      } catch (UnknownActionException exception) {
        this.pathsApp.getUserInterfaceManager().showErrorDialog("Unknown Action Error",
            "Cannot export the story because a link in the passages of the story"
                + " contains an unknown action");
      } catch (IOException exception) {
        this.pathsApp.getUserInterfaceManager().showErrorDialog("Input/Output Error",
            "An input/output error has occurred");
      }
    }
  }

  /**
   * Handles the button click of button 2 on the export story screen of the Paths Application.
   */
  public void handleButton2Click() {
    Screen optionsScreen = this.pathsApp.getUserInterfaceManager()
        .getScreen("options_screen");
    this.pathsApp.getUserInterfaceManager()
        .setPrimaryStageScene(optionsScreen.createScene(this.pathsApp));
  }
}
