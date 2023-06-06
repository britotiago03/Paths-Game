package no.ntnu.idata2001.mappe29.userinterface.controllers;

import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Home Screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.21.
 */
public class HomeScreenController extends Controller {

  /**
   * Creates an instance of HomeScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public HomeScreenController(PathsApp pathsApp) {
    super("home_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the button click of button 1 on the home screen of the Paths Application.
   */
  public void handleButton1Click() {
    Screen playScreen = this.pathsApp.getUserInterfaceManager()
        .getScreen("play_screen");
    this.pathsApp.getUserInterfaceManager()
        .setPrimaryStageScene(playScreen.createScene(this.pathsApp));
  }

  /**
   * Handles the button click of button 3 on the home screen of the Paths Application.
   */
  public void handleButton3Click() {
    Screen optionsScreen = this.pathsApp.getUserInterfaceManager()
        .getScreen("options_screen");
    this.pathsApp.getUserInterfaceManager()
        .setPrimaryStageScene(optionsScreen.createScene(this.pathsApp));
  }

  /**
   * Handles the click on button 4 in the home screen.
   */
  public void handleButton4Click() {
    this.pathsApp.getUserInterfaceManager().terminateApp();
  }
}
