package no.ntnu.idata2001.mappe29.userinterface.screens;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.userinterface.controllers.PlayScreenController;

/**
 * Represents the play screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.21.
 */
public class PlayScreen extends Screen {
  private String title;
  private Image backgroundImage;
  private String subTitle;
  private String line1Text;
  private String line2Text;
  private String button1Text;
  private String button2Text;
  private String button3Text;

  /**
   * Creates an instance of PlayScreen.
   *
   * @param title           the specified title of this screen.
   * @param backgroundImage the specified background image of this screen.
   * @param subTitle        the specified subtitle of this screen.
   * @param line1Text       the specified line 1 text of this screen.
   * @param line2Text       the specified line 2 text of this screen.
   * @param button1Text     the specified button 1 text of this screen.
   * @param button2Text     the specified button 2 text of this screen.
   * @param button3Text     the specified button 3 text of this screen.
   */
  public PlayScreen(String title, Image backgroundImage, String subTitle, String line1Text,
                    String line2Text, String button1Text, String button2Text, String button3Text) {
    super("play_screen");
    this.setTitle(title);
    this.setBackgroundImage(backgroundImage);
    this.setSubTitle(subTitle);
    this.setLine1Text(line1Text);
    this.setLine2Text(line2Text);
    this.setButton1Text(button1Text);
    this.setButton2Text(button2Text);
    this.setButton3Text(button3Text);
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
   * Sets the background image of this screen to the specified background image.
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
   * Sets the subtitle of this screen to the specified subtitle.
   *
   * @param subTitle the specified subtitle.
   * @throws IllegalArgumentException if the specified subtitle is null or blank.
   */
  private void setSubTitle(String subTitle) {
    if (subTitle == null) {
      throw new IllegalArgumentException("Sub title cannot be null");
    } else if (subTitle.isBlank()) {
      throw new IllegalArgumentException("Sub title cannot be blank");
    }
    this.subTitle = subTitle;
  }

  /**
   * Sets the line 1 text of this screen to the specified line 1 text.
   *
   * @param line1Text the specified line 1 text.
   * @throws IllegalArgumentException if the specified line 1 text is null or blank.
   */
  private void setLine1Text(String line1Text) {
    if (line1Text == null) {
      throw new IllegalArgumentException("Line 1 text cannot be null");
    } else if (line1Text.isBlank()) {
      throw new IllegalArgumentException("Line 1 text cannot be blank");
    }
    this.line1Text = line1Text;
  }

  /**
   * Sets the line 2 text of this screen to the specified line 2 text.
   *
   * @param line2Text the specified line 2 text.
   * @throws IllegalArgumentException if the specified line 2 text is null or blank.
   */
  private void setLine2Text(String line2Text) {
    if (line2Text == null) {
      throw new IllegalArgumentException("Line 2 text cannot be null");
    } else if (line2Text.isBlank()) {
      throw new IllegalArgumentException("Line 2 text cannot be blank");
    }
    this.line2Text = line2Text;
  }

  /**
   * Sets the button 1 text of this screen to the specified button 1 text.
   *
   * @param button1Text the specified button 1 text.
   * @throws IllegalArgumentException if the specified button 1 text is null or blank.
   */
  private void setButton1Text(String button1Text) {
    if (button1Text == null) {
      throw new IllegalArgumentException("Button 1 text cannot be null");
    } else if (button1Text.isBlank()) {
      throw new IllegalArgumentException("Button 1 text cannot be blank");
    }
    this.button1Text = button1Text;
  }

  /**
   * Sets the button 2 text of this screen to the specified button 2 text.
   *
   * @param button2Text the specified button 2 text.
   * @throws IllegalArgumentException if the specified button 2 text is null or blank.
   */
  private void setButton2Text(String button2Text) {
    if (button2Text == null) {
      throw new IllegalArgumentException("Button 2 text cannot be null");
    } else if (button2Text.isBlank()) {
      throw new IllegalArgumentException("Button 2 text cannot be blank");
    }
    this.button2Text = button2Text;
  }

  /**
   * Sets the button 3 text of this screen to the specified button 3 text.
   *
   * @param button3Text the specified button 3 text.
   * @throws IllegalArgumentException if the specified button 3 text is null or blank.
   */
  private void setButton3Text(String button3Text) {
    if (button3Text == null) {
      throw new IllegalArgumentException("Button 3 text cannot be null");
    } else if (button3Text.isBlank()) {
      throw new IllegalArgumentException("Button 3 text cannot be blank");
    }
    this.button3Text = button3Text;
  }


  @Override
  public Scene createScene(PathsApp pathsApp) {
    BorderPane root = new BorderPane();

    Scene playScreenScene = new Scene(root, 800, 600);
    playScreenScene.getStylesheets().add(
        pathsApp.getClass().getResource("/css/play_screen.css").toExternalForm());
    root.getStyleClass().add("root-style");

    Label titleLabel = new Label(this.title);
    titleLabel.getStyleClass().add("title-label");

    Label subTitleLabel = new Label(this.subTitle);
    subTitleLabel.getStyleClass().add("subtitle-label");

    VBox line1TextLabelContainer = new VBox();
    line1TextLabelContainer.getStyleClass().add("line1-text-label-container");

    Label line1TextLabel = new Label(this.line1Text);
    line1TextLabel.getStyleClass().add("line1-text-label");
    line1TextLabelContainer.getChildren().add(line1TextLabel);

    VBox line2TextLabelContainer = new VBox();
    line2TextLabelContainer.getStyleClass().add("line2-text-label-container");

    Label line2TextLabel = new Label(this.line2Text);
    line2TextLabel.getStyleClass().add("line2-text-label");
    line2TextLabelContainer.getChildren().add(line2TextLabel);

    VBox buttonContainer = new VBox();
    buttonContainer.getStyleClass().add("button-container");

    Button button1 = new Button(this.button1Text);
    button1.getStyleClass().add("button-style");
    button1.setOnAction(event -> {
      PlayScreenController playScreenController = (PlayScreenController)
          pathsApp.getUserInterfaceManager()
              .getController("play_screen_controller");
      playScreenController.handleButton1Click();
    });
    button1.setOnMouseEntered(event -> button1.setCursor(Cursor.HAND));

    Button button2 = new Button(this.button2Text);
    button2.getStyleClass().add("button-style");
    button2.setOnAction(event -> {
      PlayScreenController playScreenController = (PlayScreenController)
          pathsApp.getUserInterfaceManager()
              .getController("play_screen_controller");
      playScreenController.handleButton2Click();
    });
    button2.setOnMouseEntered(event -> button2.setCursor(Cursor.HAND));

    Button button3 = new Button(this.button3Text);
    button3.getStyleClass().add("button-style");
    button3.setOnAction(event -> {
      PlayScreenController playScreenController = (PlayScreenController)
          pathsApp.getUserInterfaceManager()
              .getController("play_screen_controller");
      playScreenController.handleButton3Click();
    });
    button3.setOnMouseEntered(event -> button3.setCursor(Cursor.HAND));

    HBox topButtonsContainer = new HBox();
    topButtonsContainer.getStyleClass().add("top-buttons-container");
    topButtonsContainer.getChildren().addAll(button1, button2);

    buttonContainer.getChildren().addAll(topButtonsContainer, button3);

    VBox centerVbox = new VBox(titleLabel, subTitleLabel, line1TextLabelContainer,
        line2TextLabelContainer, buttonContainer);
    centerVbox.getStyleClass().add("center-vbox");

    MenuBar menuBar = this.createMenuBar(pathsApp);
    root.setTop(menuBar);
    root.setCenter(centerVbox);

    BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true,
        false, false);
    BackgroundImage background =
        new BackgroundImage(this.backgroundImage, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER, backgroundSize);
    root.setBackground(new Background(background));

    return playScreenScene;
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
            .getController("play_screen_controller").handleHowToPlayMenuItem());
    MenuItem howToImportStory = new MenuItem("How to import a story");
    howToImportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("play_screen_controller")
            .handleHowToImportStoryMenuItem());
    MenuItem howToExportStory = new MenuItem("How to export a story");
    howToExportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("play_screen_controller")
            .handleHowToExportStoryMenuItem());
    MenuItem whatAreGoals = new MenuItem("What are goals?");
    whatAreGoals.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("play_screen_controller")
            .handleWhatAreGoalsMenuItem());
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(howToPlayGuide, howToImportStory, howToExportStory, whatAreGoals);
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(helpMenu);
    return menuBar;
  }
}
