package no.ntnu.idata2001.mappe29.userinterface.screens;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.userinterface.controllers.WelcomeScreenController;

/**
 * Represents the welcome screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.19.
 */
public class WelcomeScreen extends Screen {
  private String title;
  private String infoText;
  private Image backgroundImage;
  private String nameFieldText;
  private String genderFieldText;
  private String buttonText;

  /**
   * Creates an instance of WelcomeScreen.
   *
   * @param title           the title of this screen.
   * @param infoText        the info text of this screen.
   * @param backgroundImage the background image of this screen.
   * @param nameFieldText   the name field text of this screen.
   * @param genderFieldText the gender field text of this screen.
   * @param buttonText      the button text of this screen.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public WelcomeScreen(String title, String infoText, Image backgroundImage, String nameFieldText,
                       String genderFieldText, String buttonText) {
    super("welcome_screen");
    this.setTitle(title);
    this.setInfoText(infoText);
    this.setBackgroundImage(backgroundImage);
    this.setNameFieldText(nameFieldText);
    this.setGenderFieldText(genderFieldText);
    this.setButtonText(buttonText);
  }

  /**
   * Sets the title of this screen to the specified title.
   *
   * @param title the specified title.
   * @throws IllegalArgumentException if the specified title is null or blank.
   */
  private void setTitle(String title) {
    if (title == null) {
      throw new IllegalArgumentException("Title cannot be null");
    } else if (title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be blank");
    }
    this.title = title;
  }

  /**
   * Sets the info text of this welcome screen to the specified info text.
   *
   * @param infoText the specified info text.
   * @throws IllegalArgumentException if the specified info text is null or blank.
   */
  private void setInfoText(String infoText) {
    if (infoText == null) {
      throw new IllegalArgumentException("Info text cannot be null");
    } else if (infoText.isBlank()) {
      throw new IllegalArgumentException("Info text cannot be blank");
    }
    this.infoText = infoText;
  }

  /**
   * Sets the background image of this welcome screen to the specified background image.
   *
   * @param backgroundImage the specified background image.
   * @throws IllegalArgumentException if the specified background image is null.
   */
  private void setBackgroundImage(Image backgroundImage) {
    if (backgroundImage == null) {
      throw new IllegalArgumentException("Background image cannot be null");
    }
    this.backgroundImage = backgroundImage;
  }

  /**
   * Sets the name field text of this welcome screen to the specified name field text.
   *
   * @param nameFieldText if the specified name field text is null or blank.
   * @throws IllegalArgumentException if the specified name field text is null or blank.
   */
  private void setNameFieldText(String nameFieldText) {
    if (nameFieldText == null) {
      throw new IllegalArgumentException("Name field text cannot be null");
    } else if (nameFieldText.isBlank()) {
      throw new IllegalArgumentException("Name field text cannot be blank");
    }
    this.nameFieldText = nameFieldText;
  }

  /**
   * Sets the gender field text of this welcome screen to the specified gender field text.
   *
   * @param genderFieldText the specified gender field text.
   * @throws IllegalArgumentException if the specified gender field text is null or blank.
   */
  private void setGenderFieldText(String genderFieldText) {
    if (genderFieldText == null) {
      throw new IllegalArgumentException("Gender field text cannot be null");
    } else if (genderFieldText.isBlank()) {
      throw new IllegalArgumentException("Gender field text cannot be blank");
    }
    this.genderFieldText = genderFieldText;
  }

  /**
   * Sets the button text of this welcome screen to the specified button text.
   *
   * @param buttonText the specified button text.
   * @throws IllegalArgumentException if the specified button text is null or blank.
   */
  private void setButtonText(String buttonText) {
    if (buttonText == null) {
      throw new IllegalArgumentException("Button text cannot be null");
    } else if (buttonText.isBlank()) {
      throw new IllegalArgumentException("Button text cannot be blank");
    }
    this.buttonText = buttonText;
  }

  @Override
  public Scene createScene(PathsApp pathsApp) {
    BorderPane root = new BorderPane();

    Scene welcomeScreenScene = new Scene(root, 800, 600);
    welcomeScreenScene.getStylesheets().add(
        pathsApp.getClass().getResource("/css/welcome_screen.css").toExternalForm());
    root.getStyleClass().add("root-style");

    Label titleLabel = new Label(this.title);
    titleLabel.getStyleClass().add("title-label");

    VBox infoContainer = new VBox();
    infoContainer.getStyleClass().add("info-container");

    Label infoTextLabel = new Label(this.infoText);
    infoTextLabel.getStyleClass().add("info-text-label");
    VBox infoTextContainer = new VBox();
    infoTextContainer.getChildren().add(infoTextLabel);
    infoTextContainer.getStyleClass().add("info-text-container");

    Label nameFieldTextLabel = new Label(this.nameFieldText);
    nameFieldTextLabel.getStyleClass().add("name-field-text-label");
    TextArea nameFieldTextArea = new TextArea();
    nameFieldTextArea.getStyleClass().add("name-field-text-area");
    nameFieldTextArea.setOnMouseEntered(event -> nameFieldTextArea.setCursor(Cursor.HAND));

    Label genderFieldTextLabel = new Label(this.genderFieldText);
    genderFieldTextLabel.getStyleClass().add("gender-field-text-label");
    ComboBox<String> genderComboBox = new ComboBox<>();
    genderComboBox.getItems().addAll("Male", "Female");
    genderComboBox.getStyleClass().add("gender-combo-box");
    genderComboBox.setOnMouseEntered(event -> genderComboBox.setCursor(Cursor.HAND));

    GridPane inputGridPane = new GridPane();
    inputGridPane.getStyleClass().add("input-grid-pane");

    inputGridPane.add(nameFieldTextLabel, 0, 0);
    inputGridPane.add(nameFieldTextArea, 1, 0);
    inputGridPane.add(genderFieldTextLabel, 0, 1);
    inputGridPane.add(genderComboBox, 1, 1);

    infoContainer.getChildren().addAll(infoTextContainer, inputGridPane);

    Button button = new Button(this.buttonText);
    button.getStyleClass().add("button-style");
    button.setOnAction(event -> {
          WelcomeScreenController welcomeScreenController =
              (WelcomeScreenController) pathsApp.getUserInterfaceManager()
                  .getController("welcome_screen_controller");
          welcomeScreenController.handleButtonClick(nameFieldTextArea.getText(),
              genderComboBox.getValue());
        }
    );
    button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));

    VBox centerVbox = new VBox(titleLabel, infoContainer, button);
    centerVbox.getStyleClass().add("center-vbox");

    MenuBar menuBar = createMenuBar(pathsApp);
    root.setTop(menuBar);
    root.setCenter(centerVbox);

    BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true,
        false, false);
    BackgroundImage background =
        new BackgroundImage(this.backgroundImage, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER, backgroundSize);
    root.setBackground(new Background(background));

    return welcomeScreenScene;
  }

  /**
   * Creates the menu bar of this screen.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @return the menu bar of this screen.
   */
  private MenuBar createMenuBar(PathsApp pathsApp) {
    MenuItem howToPlayGuide = new MenuItem("How to play");
    howToPlayGuide.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("welcome_screen_controller").handleHowToPlayMenuItem());
    MenuItem howToImportStory = new MenuItem("How to import a story");
    howToImportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("welcome_screen_controller")
            .handleHowToImportStoryMenuItem());
    MenuItem howToExportStory = new MenuItem("How to export a story");
    howToExportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("welcome_screen_controller")
            .handleHowToExportStoryMenuItem());
    MenuItem whatAreGoals = new MenuItem("What are goals?");
    whatAreGoals.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("welcome_screen_controller")
            .handleWhatAreGoalsMenuItem());
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(howToPlayGuide, howToImportStory, howToExportStory, whatAreGoals);
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(helpMenu);
    return menuBar;
  }
}