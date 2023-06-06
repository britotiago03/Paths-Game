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
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.userinterface.controllers.StoryInformationScreenController;

/**
 * Represents the story information screens of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.19.
 */
public class StoryInformationScreen extends Screen {
  private String title;
  private Story story;
  private Image backgroundImage;
  private String intro1Text;
  private String intro2Text;
  private String button1Text;
  private String button2Text;

  /**
   * Creates an instance of StoryInformationScreen.
   *
   * @param name            the specified name of this screen.
   * @param title           the specified title of this screen.
   * @param backgroundImage the specified background image of this screen.
   * @param intro1Text      the specified intro 1 text of this screen.
   * @param intro2Text      the specified intro 2 text of this screen.
   * @param button1Text     the specified button 1 text of this screen.
   * @param button2Text     the specified button 2 text of this screen.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public StoryInformationScreen(String name, Story story, String title, Image backgroundImage,
                                String intro1Text, String intro2Text, String button1Text,
                                String button2Text) {
    super(name);
    this.setStory(story);
    this.setTitle(title);
    this.setBackgroundImage(backgroundImage);
    this.setIntro1Text(intro1Text);
    this.setIntro2Text(intro2Text);
    this.setButton1Text(button1Text);
    this.setButton2Text(button2Text);
  }

  /**
   * Sets the story of this screen to the specified story.
   *
   * @param story the specified story.
   * @throws IllegalArgumentException if the specified story is null.
   */
  private void setStory(Story story) {
    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    }
    this.story = story;
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
   * Sets the intro 1 text of this screen to the specified intro 1 text.
   *
   * @param intro1Text the specified intro 1 text.
   * @throws IllegalArgumentException if the specified intro 1 text is null or blank.
   */
  private void setIntro1Text(String intro1Text) {
    if (intro1Text == null) {
      throw new IllegalArgumentException("Intro 1 text cannot be null");
    } else if (intro1Text.isBlank()) {
      throw new IllegalArgumentException("Intro 1 text cannot be blank");
    }
    this.intro1Text = intro1Text;
  }

  /**
   * Sets the intro 2 text of this screen to the specified intro 2 text.
   *
   * @param intro2Text the specified intro 2 text.
   * @throws IllegalArgumentException if the specified intro 2 text is null or blank.
   */
  private void setIntro2Text(String intro2Text) {
    if (intro2Text == null) {
      throw new IllegalArgumentException("Intro 2 text cannot be null");
    } else if (intro2Text.isBlank()) {
      throw new IllegalArgumentException("Intro 2 text cannot be blank");
    }
    this.intro2Text = intro2Text;
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


  @Override
  public Scene createScene(PathsApp pathsApp) {
    BorderPane root = new BorderPane();

    Scene storyIntroductionScreenScene = new Scene(root, 800, 600);
    storyIntroductionScreenScene.getStylesheets().add(
        pathsApp.getClass().getResource("/css/story_information_screen.css")
            .toExternalForm());
    root.getStyleClass().add("root-style");

    Label titleLabel = new Label(this.title);
    titleLabel.getStyleClass().add("title-label");

    VBox introLabelContainer = new VBox();
    introLabelContainer.getStyleClass().add("intro-label-container");

    VBox intro1TextLabelContainer = new VBox();

    Label intro1TextLabel = new Label(this.intro1Text);
    intro1TextLabel.getStyleClass().add("intro-text-label");
    intro1TextLabelContainer.getChildren().add(intro1TextLabel);

    VBox intro2TextLabelContainer = new VBox();

    Label intro2TextLabel = new Label(this.intro2Text);
    intro2TextLabel.getStyleClass().add("intro-text-label");
    intro2TextLabelContainer.getChildren().add(intro2TextLabel);

    introLabelContainer.getChildren().addAll(intro1TextLabelContainer, intro2TextLabelContainer);

    HBox buttonContainer = new HBox();
    buttonContainer.getStyleClass().add("button-container");

    Button button1 = new Button(this.button1Text);
    button1.getStyleClass().add("button-style");
    button1.setOnAction(event -> {
      StoryInformationScreenController storyInformationScreenController =
          (StoryInformationScreenController) pathsApp.getUserInterfaceManager()
              .getController("story_information_screen_controller");
      storyInformationScreenController.handleButton1Click(this.story);
    });
    button1.setOnMouseEntered(event -> button1.setCursor(Cursor.HAND));

    Button button2 = new Button(this.button2Text);
    button2.getStyleClass().add("button-style");
    button2.setOnAction(event -> {
      StoryInformationScreenController storyInformationScreenController =
          (StoryInformationScreenController) pathsApp.getUserInterfaceManager()
              .getController("story_information_screen_controller");
      storyInformationScreenController.handleButton2Click(this.button2Text);
    });
    button2.setOnMouseEntered(event -> button2.setCursor(Cursor.HAND));

    buttonContainer.getChildren().addAll(button1, button2);

    VBox centerVbox = new VBox(titleLabel, introLabelContainer, buttonContainer);
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

    return storyIntroductionScreenScene;
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
            .getController("story_information_screen_controller")
            .handleHowToPlayMenuItem());
    MenuItem howToImportStory = new MenuItem("How to import a story");
    howToImportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("story_information_screen_controller")
            .handleHowToImportStoryMenuItem());
    MenuItem howToExportStory = new MenuItem("How to export a story");
    howToExportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("story_information_screen_controller")
            .handleHowToExportStoryMenuItem());
    MenuItem whatAreGoals = new MenuItem("What are goals?");
    whatAreGoals.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("story_information_screen_controller")
            .handleWhatAreGoalsMenuItem());
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(howToPlayGuide, howToImportStory, howToExportStory, whatAreGoals);
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(helpMenu);
    return menuBar;
  }
}
