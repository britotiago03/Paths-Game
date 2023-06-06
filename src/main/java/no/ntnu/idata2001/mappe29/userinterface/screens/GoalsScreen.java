package no.ntnu.idata2001.mappe29.userinterface.screens;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.userinterface.controllers.GoalsScreenController;

/**
 * Represents the goals screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.22.
 */
public class GoalsScreen extends Screen {
  private String title;
  private Image backgroundImage;
  private String subTitle;
  private Image image1;
  private String image1Text;
  private Image image2;
  private String image2Text;
  private Image image3;
  private String image3Text;
  private Image image4;
  private String image4Text;
  private String buttonText;

  /**
   * Creates an instance of GoalsScreen.
   *
   * @param title           the specified title of this screen.
   * @param backgroundImage the specified background image of this screen.
   * @param subTitle        the specified subtitle of this screen.
   * @param image1          the specified image 1 of this screen.
   * @param image1Text      the specified image 1 text of this screen.
   * @param image2          the specified image 2 of this screen.
   * @param image2Text      the specified image 2 text of this screen.
   * @param image3          the specified image 3 of this screen.
   * @param image3Text      the specified image 3 text of this screen.
   * @param image4          the specified image 4 of this screen.
   * @param image4Text      the specified image 4 text of this screen.
   * @param buttonText      the specified button text of this screen.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public GoalsScreen(String title, Image backgroundImage, String subTitle, Image image1,
                     String image1Text, Image image2, String image2Text, Image image3,
                     String image3Text, Image image4, String image4Text, String buttonText) {
    super("goals_screen");
    this.setTitle(title);
    this.setSubTitle(subTitle);
    this.setImages(backgroundImage, image1, image2, image3, image4);
    this.setImageTexts(image1Text, image2Text, image3Text, image4Text);
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
   * Sets the subtitle of this screen to the specified subtitle.
   *
   * @param subTitle the specified subtitle.
   * @throws IllegalArgumentException if the specified subtitle is null or blank.
   */
  private void setSubTitle(String subTitle) {
    if (subTitle == null) {
      throw new IllegalArgumentException("Subtitle cannot be null");
    } else if (subTitle.isBlank()) {
      throw new IllegalArgumentException("Subtitle cannot be blank");
    }
    this.subTitle = subTitle;
  }

  /**
   * Sets the background image and the images of this screen to the specified background
   * image and images.
   *
   * @param backgroundImage the specified background image.
   * @param image1          the specified image 1.
   * @param image3          the specified image 2.
   * @param image2          the specified image 3.
   * @param image4          the specified image 4.
   * @throws IllegalArgumentException if the specified images are null.
   */
  private void setImages(Image backgroundImage, Image image1, Image image2, Image image3,
                         Image image4) {
    if (backgroundImage == null) {
      throw new IllegalArgumentException("Background image cannot be null");
    } else if (image1 == null) {
      throw new IllegalArgumentException("Image 1 cannot be null");
    } else if (image2 == null) {
      throw new IllegalArgumentException("Image 2 cannot be null");
    } else if (image3 == null) {
      throw new IllegalArgumentException("Image 3 cannot be null");
    } else if (image4 == null) {
      throw new IllegalArgumentException("Image 4 cannot be null");
    }
    this.backgroundImage = backgroundImage;
    this.image1 = image1;
    this.image2 = image2;
    this.image3 = image3;
    this.image4 = image4;
  }

  /**
   * Sets the image texts of this screen to the specified image texts.
   *
   * @param image1Text the specified image 1 text.
   * @param image2Text the specified image 2 text.
   * @param image3Text the specified image 3 text.
   * @param image4Text the specified image 4 text.
   * @throws IllegalArgumentException if the specified image texts are null or blank.
   */
  private void setImageTexts(String image1Text, String image2Text, String image3Text,
                             String image4Text) {
    if (image1Text == null) {
      throw new IllegalArgumentException("Image 1 text cannot be null");
    } else if (image1Text.isBlank()) {
      throw new IllegalArgumentException("Image 1 text cannot be blank");
    } else if (image2Text == null) {
      throw new IllegalArgumentException("Image 2 text cannot be null");
    } else if (image2Text.isBlank()) {
      throw new IllegalArgumentException("Image 2 text cannot be blank");
    } else if (image3Text == null) {
      throw new IllegalArgumentException("Image 3 text cannot be null");
    } else if (image3Text.isBlank()) {
      throw new IllegalArgumentException("Image 3 text cannot be blank");
    } else if (image4Text == null) {
      throw new IllegalArgumentException("Image 4 text cannot be null");
    } else if (image4Text.isBlank()) {
      throw new IllegalArgumentException("Image 4 text cannot be blank");
    }
    this.image1Text = image1Text;
    this.image2Text = image2Text;
    this.image3Text = image3Text;
    this.image4Text = image4Text;
  }

  /**
   * Sets the button text of this screen to the specified button text.
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

    Scene chooseAvatarMaleScreen = new Scene(root, 800, 600);
    chooseAvatarMaleScreen.getStylesheets().add(
        pathsApp.getClass().getResource("/css/goals_screen.css")
            .toExternalForm());
    root.getStyleClass().add("root-style");

    Label titleLabel = new Label(this.title);
    titleLabel.getStyleClass().add("title-label");

    Label subtitleLabel = new Label(this.subTitle);
    subtitleLabel.getStyleClass().add("subtitle-label");

    GridPane imageGridPane = new GridPane();
    imageGridPane.getStyleClass().add("image-grid-pane");

    VBox image1Container = new VBox();
    Label image1TextLabel = new Label(this.image1Text);
    image1TextLabel.getStyleClass().add("image-label");
    ImageView imageView1 = new ImageView(this.image1);

    image1Container.setOnMouseClicked(
        event -> {
          GoalsScreenController goalsScreenController = (GoalsScreenController)
              pathsApp.getUserInterfaceManager()
                  .getController("goals_screen_controller");
          goalsScreenController.handleImage1Click();
        });
    image1Container.setOnMouseEntered(event -> image1Container.setCursor(Cursor.HAND));
    image1Container.getChildren().addAll(imageView1, image1TextLabel);
    image1Container.getStyleClass().add("image-container");

    VBox image2Container = new VBox();
    ImageView imageView2 = new ImageView(this.image2);
    Label image2TextLabel = new Label(this.image2Text);
    image2TextLabel.getStyleClass().add("image-label");

    image2Container.getChildren().addAll(imageView2, image2TextLabel);
    image2Container.getStyleClass().add("image-container");
    image2Container.setOnMouseClicked(
        event -> {
          GoalsScreenController goalsScreenController = (GoalsScreenController)
              pathsApp.getUserInterfaceManager()
                  .getController("goals_screen_controller");
          goalsScreenController.handleImage2Click();
        });
    image2Container.setOnMouseEntered(event -> image2Container.setCursor(Cursor.HAND));

    VBox image3Container = new VBox();
    ImageView imageView3 = new ImageView(this.image3);
    Label image3TextLabel = new Label(this.image3Text);
    image3TextLabel.getStyleClass().add("image-label");
    image3Container.getChildren().addAll(imageView3, image3TextLabel);
    image3Container.getStyleClass().add("image-container");
    image3Container.setOnMouseClicked(
        event -> {
          GoalsScreenController goalsScreenController = (GoalsScreenController)
              pathsApp.getUserInterfaceManager()
                  .getController("goals_screen_controller");
          goalsScreenController.handleImage3Click();
        });
    image3Container.setOnMouseEntered(event -> image3Container.setCursor(Cursor.HAND));

    VBox image4Container = new VBox();
    ImageView avatarImageView4 = new ImageView(this.image4);
    Label image4TextLabel = new Label(this.image4Text);
    image4TextLabel.getStyleClass().add("image-label");
    image4Container.getChildren().addAll(avatarImageView4, image4TextLabel);
    image4Container.getStyleClass().add("image-container");
    image4Container.setOnMouseClicked(
        event -> {
          GoalsScreenController goalsScreenController = (GoalsScreenController)
              pathsApp.getUserInterfaceManager()
                  .getController("goals_screen_controller");
          goalsScreenController.handleImage4Click();
        });
    image4Container.setOnMouseEntered(event -> image4Container.setCursor(Cursor.HAND));

    imageGridPane.add(image1Container, 0, 0);
    imageGridPane.add(image2Container, 1, 0);
    imageGridPane.add(image4Container, 2, 0);

    Button button = new Button(this.buttonText);
    button.getStyleClass().add("button-style");
    button.setOnAction(event -> {
      GoalsScreenController goalsScreenController =
          (GoalsScreenController) pathsApp.getUserInterfaceManager()
              .getController("goals_screen_controller");
      goalsScreenController.handleButtonClick();
    });
    button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));

    VBox centerVbox = new VBox(titleLabel, subtitleLabel, imageGridPane, button);
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

    return chooseAvatarMaleScreen;
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
            .getController("goals_screen_controller").handleHowToPlayMenuItem());
    MenuItem howToImportStory = new MenuItem("How to import a story");
    howToImportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("goals_screen_controller")
            .handleHowToImportStoryMenuItem());
    MenuItem howToExportStory = new MenuItem("How to export a story");
    howToExportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("goals_screen_controller")
            .handleHowToExportStoryMenuItem());
    MenuItem whatAreGoals = new MenuItem("What are goals?");
    whatAreGoals.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("goals_screen_controller")
            .handleWhatAreGoalsMenuItem());
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(howToPlayGuide, howToImportStory, howToExportStory, whatAreGoals);
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(helpMenu);
    return menuBar;
  }
}
