package no.ntnu.idata2001.mappe29.userinterface.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.Link;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.exceptions.BrokenLinksException;
import no.ntnu.idata2001.mappe29.model.exceptions.CorruptStoryException;
import no.ntnu.idata2001.mappe29.model.exceptions.NoBrokenLinksException;
import no.ntnu.idata2001.mappe29.userinterface.screens.ChooseStoryScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.DeleteStoryScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.ExportStoryScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;


/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Options Screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.22.
 */
public class OptionsScreenController extends Controller {
  /**
   * Creates an instance of OptionsScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public OptionsScreenController(PathsApp pathsApp) {
    super("options_screen_controller");
    super.setPathsApp(pathsApp);
  }

  /**
   * Handles the button click of button 1 on the options screen of the Paths Application.
   */
  public void handleButton1Click() {
    Story importedStory = null;
    try {
      importedStory = this.pathsApp.getModelManager().getStoryFileManager()
          .openStoryFromFile();
      if (importedStory.hasBrokenLinks()) {
        throw new BrokenLinksException("Imported story has broken links");
      }
      Map<String, Story> storyCollection = this.pathsApp.getModelManager().getStoryCollection();
      if (storyCollection.containsKey(importedStory.getTitle())) {
        this.pathsApp.getUserInterfaceManager().showErrorDialog("Story Already Exists",
            "The story you are trying to import already exists in the\n"
                + "stories collection");
      } else {
        storyCollection.put(importedStory.getTitle(), importedStory);
        List<Story> newStories = storyCollection.values().stream().toList();
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

        Alert successDialog = new Alert(Alert.AlertType.INFORMATION);
        this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(successDialog);
        successDialog.setTitle("Successfully Imported Story");
        successDialog.setHeaderText("Successfully Imported The " + importedStory.getTitle()
            + " Story");
        successDialog.getButtonTypes().setAll(ButtonType.OK);
        successDialog.showAndWait();
        this.pathsApp.getUserInterfaceManager().initializeStoryScreensForStory(importedStory);
      }
    } catch (IOException exception) {
      Alert successCancelDialog = new Alert(Alert.AlertType.INFORMATION);
      this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(successCancelDialog);
      successCancelDialog.setTitle("Cancel Import Story");
      successCancelDialog.setHeaderText("Canceled the import story request");
      successCancelDialog.getButtonTypes().setAll(ButtonType.OK);
      successCancelDialog.showAndWait();
    } catch (CorruptStoryException exception) {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("Corrupt Story Error",
          "The story you tried to open is corrupted.\nYou have to import stable stories");
    } catch (BrokenLinksException exception) {
      StringBuilder brokenLinksStringBuilder = new StringBuilder();
      try {
        if (importedStory != null) {
          List<Link> brokenLinks = importedStory.getBrokenLinks();
          brokenLinksStringBuilder.append("The story you tried to open is has the following"
              + " broken links:");
          for (Link link : brokenLinks) {
            brokenLinksStringBuilder.append("\nLink: " + link.getText());
          }
          brokenLinksStringBuilder.append("\n\nPlease fix these broken links before importing "
              + "the story");
          this.pathsApp.getUserInterfaceManager().showErrorDialog("Broken Links Detected",
              brokenLinksStringBuilder.toString());
        }
      } catch (NoBrokenLinksException noBrokenLinksException) {
        this.pathsApp.getUserInterfaceManager().showErrorDialog("No Broken Link Error",
            "This error should not occur");
      }
    }

  }

  /**
   * Handles the button click of button 2 on the options screen of the Paths Application.
   */
  public void handleButton2Click() {
    if (this.pathsApp.getModelManager().getStoryCollection().size() > 0) {
      Screen exportStoryScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen("export_story_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(exportStoryScreen.createScene(this.pathsApp));
    } else {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("No Stories", "There "
          + "are no stories to export");
    }
  }

  /**
   * Handles the button click of button 3 on the options screen of the Paths Application.
   */
  public void handleButton3Click() {
    if (this.pathsApp.getModelManager().getStoryCollection().size() > 0) {
      Screen deleteStoryScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen("delete_story_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(deleteStoryScreen.createScene(this.pathsApp));
    } else {
      this.pathsApp.getUserInterfaceManager().showErrorDialog("No Stories", "There "
          + "are stories to delete");
    }
  }

  /**
   * Handles the click on button 4 in the options screen of the Paths Application.
   */
  public void handleButton4Click() {
    Screen welcomeScreen = this.pathsApp.getUserInterfaceManager()
        .getScreen("welcome_screen");
    this.pathsApp.getUserInterfaceManager()
        .setPrimaryStageScene(welcomeScreen.createScene(this.pathsApp));
  }

  /**
   * Handles the click on button 5 in the options screen of the Paths Application.
   */
  public void handleButton5Click() {
    Screen homeScreen = this.pathsApp.getUserInterfaceManager()
        .getScreen("home_screen");
    this.pathsApp.getUserInterfaceManager()
        .setPrimaryStageScene(homeScreen.createScene(this.pathsApp));
  }
}
