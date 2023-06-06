package no.ntnu.idata2001.mappe29.userinterface.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.User;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Choose Avatar Screens of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.19.
 */
public class ChooseAvatarScreenController extends Controller {

  /**
   * Creates an instance of ChooseAvatarScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public ChooseAvatarScreenController(PathsApp pathsApp) {
    super("choose_avatar_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the click on an image of an avatar in the choose avatar screens.
   *
   * @param image the specified image clicked.
   */
  public void handleImageClick(Image image) {
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(confirmationDialog);
    confirmationDialog.setTitle("Confirm Image Selection");
    confirmationDialog.setHeaderText("Are you sure you want to select this image?");
    ImageView dialogImageView = new ImageView(image);
    confirmationDialog.setGraphic(dialogImageView);

    confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

    ButtonType selectedOption = confirmationDialog.showAndWait().orElse(ButtonType.NO);
    if (selectedOption == ButtonType.YES) {
      this.pathsApp.getModelManager().getUser().setImage(image);
      User user = this.pathsApp.getModelManager().getUser();
      Player player = new Player.PlayerBuilder().withName(user.getName()).build();
      this.pathsApp.getModelManager().setPlayer(player);
      this.pathsApp.getUserInterfaceManager().initializeStoryScreens();
      Screen homeScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen("home_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(homeScreen.createScene(this.pathsApp));
    }
  }
}
