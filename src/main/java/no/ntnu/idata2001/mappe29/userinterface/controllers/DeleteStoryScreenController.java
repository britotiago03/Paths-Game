package no.ntnu.idata2001.mappe29.userinterface.controllers;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.userinterface.screens.ChooseStoryScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.DeleteStoryScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.ExportStoryScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Delete Story Screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.22.
 */
public class DeleteStoryScreenController extends Controller {
  /**
   * Creates an instance of DeleteStoryScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public DeleteStoryScreenController(PathsApp pathsApp) {
    super("delete_story_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the button click of button 1 on the delete story screen of the Paths Application.
   */
  public void handleButton1Click(Story story, TableView<Story> storiesTableView) {
    if (story == null) {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Story",
          "Please select a story");
    } else {
      this.pathsApp.getModelManager().getStoryCollection().remove(story.getTitle());
      List<Story> newStories = this.pathsApp.getModelManager().getStoryCollection()
          .values().stream().toList();
      for (Screen screen : this.pathsApp.getModelManager()
          .getStoryCollectionSubscribers()) {
        if (screen instanceof ExportStoryScreen) {
          ExportStoryScreen exportStoryScreen = (ExportStoryScreen) screen;
          exportStoryScreen.setStories(newStories);
        } else if (screen instanceof ChooseStoryScreen) {
          ChooseStoryScreen chooseStoryScreen = (ChooseStoryScreen) screen;
          chooseStoryScreen.setStories(newStories);
        } else if (screen instanceof DeleteStoryScreen) {
          DeleteStoryScreen deleteStoryScreen = (DeleteStoryScreen) screen;
          deleteStoryScreen.setStories(newStories);
        }
      }
      ObservableList<Story> observableStories = FXCollections.observableArrayList(newStories);
      storiesTableView.setItems(observableStories);
    }
  }

  /**
   * Handles the button click of button 2 on the delete story screen of the Paths Application.
   */
  public void handleButton2Click() {
    Screen optionsScreen = this.pathsApp.getUserInterfaceManager()
        .getScreen("options_screen");
    this.pathsApp.getUserInterfaceManager()
        .setPrimaryStageScene(optionsScreen.createScene(this.pathsApp));
  }
}
