package no.ntnu.idata2001.mappe29;

import javafx.application.Application;
import javafx.stage.Stage;
import no.ntnu.idata2001.mappe29.model.ModelManager;
import no.ntnu.idata2001.mappe29.userinterface.UserInterfaceManager;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * The PathsApp class is the main class for the Paths Game Application. It extends the JavaFX
 * {@link javafx.application.Application Application} class and provides the main method for
 * launching the application.
 *
 * @author Tiago Brito.
 * @version 2023.05.15
 */
public class PathsApp extends Application {
  private ModelManager modelManager;
  private UserInterfaceManager userInterfaceManager;

  @Override
  public void start(Stage stage) throws Exception {
    this.modelManager = new ModelManager(this);
    this.userInterfaceManager = new UserInterfaceManager(this, stage);
    Screen welcomeScreen = this.userInterfaceManager.getScreen("welcome_screen");
    this.userInterfaceManager.setPrimaryStageScene(welcomeScreen.createScene(this));
  }

  /**
   * Returns the model manager of the Paths Application.
   *
   * @return the model manager of the Paths Application.
   */
  public ModelManager getModelManager() {
    return this.modelManager;
  }

  /**
   * Returns the user interface manager of the Paths Application.
   *
   * @return the user interface manager of the Paths Application.
   */
  public UserInterfaceManager getUserInterfaceManager() {
    return this.userInterfaceManager;
  }

  /**
   * The primary method that launches the application.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }

}
