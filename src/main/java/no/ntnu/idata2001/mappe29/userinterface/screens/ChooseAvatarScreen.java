package no.ntnu.idata2001.mappe29.userinterface.screens;

import javafx.scene.Cursor;
import javafx.scene.Scene;
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
import no.ntnu.idata2001.mappe29.userinterface.controllers.ChooseAvatarScreenController;

/**
 * Represents the ChooseAvatarScreen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.21.
 */
public class ChooseAvatarScreen extends Screen {
  private String title;
  private Image backgroundImage;
  private Image avatarImage1;
  private Image avatarImage2;
  private Image avatarImage3;
  private Image avatarImage4;
  private Image avatarImage5;
  private Image avatarImage6;

  /**
   * Creates an instance of ChooseAvatarScreen.
   *
   * @param title           the title of this screen.
   * @param backgroundImage the background image of this screen.
   * @param avatarImage1    the avatar 1 image of this screen.
   * @param avatarImage2    the avatar 2 image of this screen.
   * @param avatarImage3    the avatar 3 image of this screen.
   * @param avatarImage4    the avatar 4 image of this screen.
   * @param avatarImage5    the avatar 5 image of this screen.
   * @param avatarImage6    the avatar 6 image of this screen.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public ChooseAvatarScreen(String name, String title, Image backgroundImage, Image avatarImage1,
                            Image avatarImage2, Image avatarImage3, Image avatarImage4,
                            Image avatarImage5, Image avatarImage6) {
    super(name);
    this.setTitle(title);
    this.setImages(backgroundImage, avatarImage1, avatarImage2, avatarImage3, avatarImage4,
        avatarImage5, avatarImage6);
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
   * Sets the background image and the avatar images of this screen to the specified background
   * image and avatar images.
   *
   * @param backgroundImage the specified background image.
   * @param avatar1         the specified avatar 1 image.
   * @param avatar2         the specified avatar 2 image.
   * @param avatar3         the specified avatar 3 image.
   * @param avatar4         the specified avatar 4 image.
   * @param avatar5         the specified avatar 5 image.
   * @param avatar6         the specified avatar 6 image.
   * @throws IllegalArgumentException if the specified background image is null.
   */
  private void setImages(Image backgroundImage, Image avatar1, Image avatar2, Image avatar3,
                         Image avatar4, Image avatar5, Image avatar6) {
    if (backgroundImage == null) {
      throw new IllegalArgumentException("Background image cannot be null");
    } else if (avatar1 == null) {
      throw new IllegalArgumentException("Avatar image cannot be null");
    } else if (avatar2 == null) {
      throw new IllegalArgumentException("Avatar image cannot be null");
    } else if (avatar3 == null) {
      throw new IllegalArgumentException("Avatar image cannot be null");
    } else if (avatar4 == null) {
      throw new IllegalArgumentException("Avatar image cannot be null");
    } else if (avatar5 == null) {
      throw new IllegalArgumentException("Avatar image cannot be null");
    } else if (avatar6 == null) {
      throw new IllegalArgumentException("Avatar image cannot be null");
    }
    this.backgroundImage = backgroundImage;
    this.avatarImage1 = avatar1;
    this.avatarImage2 = avatar2;
    this.avatarImage3 = avatar3;
    this.avatarImage4 = avatar4;
    this.avatarImage5 = avatar5;
    this.avatarImage6 = avatar6;
  }


  @Override
  public Scene createScene(PathsApp pathsApp) {
    BorderPane root = new BorderPane();

    Scene chooseAvatarMaleScreen = new Scene(root, 800, 600);
    chooseAvatarMaleScreen.getStylesheets().add(
        pathsApp.getClass().getResource("/css/choose_avatar_screen.css")
            .toExternalForm());
    root.getStyleClass().add("root-style");

    Label titleLabel = new Label(this.title);
    titleLabel.getStyleClass().add("title-label");

    GridPane avatarGridPane = new GridPane();
    avatarGridPane.getStyleClass().add("avatar-grid-pane");
    VBox avatarContainer1 = new VBox();
    ImageView avatarImageView1 = new ImageView(this.avatarImage1);
    avatarContainer1.setOnMouseClicked(
        event -> this.callControllerHandleImageClick(pathsApp, this.avatarImage1));
    avatarContainer1.setOnMouseEntered(event -> avatarContainer1.setCursor(Cursor.HAND));
    avatarContainer1.getChildren().add(avatarImageView1);
    avatarContainer1.getStyleClass().add("avatar-container");

    VBox avatarContainer2 = new VBox();
    ImageView avatarImageView2 = new ImageView(this.avatarImage2);
    avatarContainer2.getChildren().add(avatarImageView2);
    avatarContainer2.getStyleClass().add("avatar-container");
    avatarContainer2.setOnMouseClicked(
        event -> this.callControllerHandleImageClick(pathsApp, this.avatarImage2));
    avatarContainer2.setOnMouseEntered(event -> avatarContainer2.setCursor(Cursor.HAND));

    VBox avatarContainer3 = new VBox();
    ImageView avatarImageView3 = new ImageView(this.avatarImage3);
    avatarContainer3.getChildren().add(avatarImageView3);
    avatarContainer3.getStyleClass().add("avatar-container");
    avatarContainer3.setOnMouseClicked(
        event -> this.callControllerHandleImageClick(pathsApp, this.avatarImage3));
    avatarContainer3.setOnMouseEntered(event -> avatarContainer3.setCursor(Cursor.HAND));

    VBox avatarContainer4 = new VBox();
    ImageView avatarImageView4 = new ImageView(this.avatarImage4);
    avatarContainer4.getChildren().add(avatarImageView4);
    avatarContainer4.getStyleClass().add("avatar-container");
    avatarContainer4.setOnMouseClicked(
        event -> this.callControllerHandleImageClick(pathsApp, this.avatarImage4));
    avatarContainer4.setOnMouseEntered(event -> avatarContainer4.setCursor(Cursor.HAND));

    VBox avatarContainer5 = new VBox();
    ImageView avatarImageView5 = new ImageView(this.avatarImage5);
    avatarContainer5.getChildren().add(avatarImageView5);
    avatarContainer5.getStyleClass().add("avatar-container");
    avatarContainer5.setOnMouseClicked(
        event -> this.callControllerHandleImageClick(pathsApp, this.avatarImage5));
    avatarContainer5.setOnMouseEntered(event -> avatarContainer5.setCursor(Cursor.HAND));

    VBox avatarContainer6 = new VBox();
    ImageView avatarImageView6 = new ImageView(this.avatarImage6);
    avatarContainer6.getChildren().add(avatarImageView6);
    avatarContainer6.getStyleClass().add("avatar-container");
    avatarContainer6.setOnMouseClicked(
        event -> this.callControllerHandleImageClick(pathsApp, this.avatarImage6));
    avatarContainer6.setOnMouseEntered(event -> avatarContainer6.setCursor(Cursor.HAND));

    avatarGridPane.add(avatarContainer1, 0, 0);
    avatarGridPane.add(avatarContainer2, 1, 0);
    avatarGridPane.add(avatarContainer3, 2, 0);
    avatarGridPane.add(avatarContainer4, 0, 1);
    avatarGridPane.add(avatarContainer5, 1, 1);
    avatarGridPane.add(avatarContainer6, 2, 1);

    VBox centerVbox = new VBox(titleLabel, avatarGridPane);
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
   * Calls the controller handle image click method.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @param image    the image selected by the user.
   */
  private void callControllerHandleImageClick(PathsApp pathsApp, Image image) {
    ChooseAvatarScreenController chooseAvatarScreenController = (ChooseAvatarScreenController)
        pathsApp.getUserInterfaceManager()
            .getController("choose_avatar_screen_controller");
    chooseAvatarScreenController.handleImageClick(image);
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
            .getController("choose_avatar_screen_controller")
            .handleHowToPlayMenuItem());
    MenuItem howToImportStory = new MenuItem("How to import a story");
    howToImportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("choose_avatar_screen_controller")
            .handleHowToImportStoryMenuItem());
    MenuItem howToExportStory = new MenuItem("How to export a story");
    howToExportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("choose_avatar_screen_controller")
            .handleHowToExportStoryMenuItem());
    MenuItem whatAreGoals = new MenuItem("What are goals?");
    whatAreGoals.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("choose_avatar_screen_controller")
            .handleWhatAreGoalsMenuItem());
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(howToPlayGuide, howToImportStory, howToExportStory, whatAreGoals);
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(helpMenu);
    return menuBar;
  }
}
