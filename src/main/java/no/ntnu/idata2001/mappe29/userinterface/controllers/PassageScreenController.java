package no.ntnu.idata2001.mappe29.userinterface.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.InventoryTableViewRowData;
import no.ntnu.idata2001.mappe29.model.Link;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.goals.Goal;
import no.ntnu.idata2001.mappe29.userinterface.screens.PassageScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Passage Screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.21.
 */
public class PassageScreenController extends Controller {

  /**
   * Creates an instance of PassageScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   */
  public PassageScreenController(PathsApp pathsApp) {
    super("passage_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the button click of button 1 on the passage screen of the Paths Application.
   */
  public void handleButton1Click(TableView<Goal> goalsTableView) {
    Alert goalsAlert = new Alert(Alert.AlertType.INFORMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(goalsAlert);
    goalsAlert.setTitle("Goals");
    goalsAlert.setHeaderText("Your goals for this story");

    VBox content = new VBox(goalsTableView);

    goalsAlert.getDialogPane().setContent(content);
    goalsAlert.getDialogPane().setPrefWidth(500);
    goalsAlert.showAndWait();
  }

  /**
   * Handles the button click of button 2 on the passage screen of the Paths Application.
   */
  public void handleButton2Click(TableView<InventoryTableViewRowData> inventoryTableView) {
    Alert inventoryAlert = new Alert(Alert.AlertType.INFORMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(inventoryAlert);
    inventoryAlert.setTitle("Inventory");
    inventoryAlert.setHeaderText("The items in your inventory");

    VBox content = new VBox(inventoryTableView);

    inventoryAlert.getDialogPane().setContent(content);
    inventoryAlert.getDialogPane().setPrefWidth(500);
    inventoryAlert.showAndWait();
  }

  /**
   * Handles the button click of button 3 on the passage screen of the Paths Application.
   */
  public void handleButton3Click(Link link, Story story) {
    String nextPassageName = link.getReference();
    if (nextPassageName.contains("story_failed")) {
      Screen storyFinishedFailedScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen(story.getTitle() + "_story_failed_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(storyFinishedFailedScreen.createScene(this.pathsApp));
      this.pathsApp.getModelManager().getPlayer().resetGoals();
    } else if (nextPassageName.contains("story_success")) {
      Screen storyFinishedSuccessScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen(story.getTitle() + "_story_success_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(storyFinishedSuccessScreen.createScene(this.pathsApp));
    } else {
      if (!link.getActions().isEmpty()) {
        Player player = this.pathsApp.getModelManager().getPlayer();
        for (Action action : link.getActions()) {
          action.execute(player);
        }
        for (Screen screen : this.pathsApp.getUserInterfaceManager().getScreens()) {
          if (screen instanceof PassageScreen) {
            PassageScreen passageScreen = (PassageScreen) screen;
            passageScreen.setScoreLabel(new Label("" + player.getScore()));
            passageScreen.setHealthLabel(new Label("" + player.getHealth()));
            passageScreen.setGoldLabel(new Label("" + player.getGold()));
          }
        }

      }
      Screen nextPassageScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen(nextPassageName + "_passage_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(nextPassageScreen.createScene(this.pathsApp));
    }
  }
}
