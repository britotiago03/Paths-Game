package no.ntnu.idata2001.mappe29.userinterface.controllers;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Welcome Screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.19.
 */
public class WelcomeScreenController extends Controller {

  /**
   * Creates an instance of WelcomeScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public WelcomeScreenController(PathsApp pathsApp) {
    super("welcome_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the button click of the button on the welcome screen of the Paths Application.
   *
   * @param userName   the username data entered by the user.
   * @param userGender the gender data selected by the user.
   */
  public void handleButtonClick(String userName, String userGender) {
    if (userName == null) {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Name",
          "Please enter a name");
    } else if (userName.isBlank()) {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Name",
          "Name cannot be blank");
    } else if (userGender == null) {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Gender",
          "Please select a gender");
    } else if (userGender.isBlank()) {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Gender",
          "Please select a gender");
    } else {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(alert);
      alert.setTitle("Confirm Information");
      alert.setHeaderText("Are you sure you want to have the name " + userName.trim() + " and "
          + userGender + " gender?");
      Optional<ButtonType> result = alert.showAndWait();

      if (result.isPresent() && result.get() == ButtonType.OK) {
        if (userGender.equals("Male")) {
          Screen chooseAvatarMaleScreen = this.pathsApp.getUserInterfaceManager()
              .getScreen("choose_avatar_male_screen");
          this.pathsApp.getUserInterfaceManager()
              .setPrimaryStageScene(chooseAvatarMaleScreen.createScene(this.pathsApp));
        } else {
          Screen chooseAvatarFemaleScreen = this.pathsApp.getUserInterfaceManager()
              .getScreen("choose_avatar_female_screen");
          this.pathsApp.getUserInterfaceManager()
              .setPrimaryStageScene(chooseAvatarFemaleScreen.createScene(this.pathsApp));
        }
        this.pathsApp.getModelManager().getUser().setName(userName.trim());
      }
    }
  }
}
