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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.User;
import no.ntnu.idata2001.mappe29.userinterface.controllers.HomeScreenController;

/**
 * Represents the home screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.21.
 */
public class HomeScreen extends Screen {
  private String title;
  private Image backgroundImage;
  private User user;
  private String button1Text;
  private String button2Text;
  private String button3Text;
  private String button4Text;

  /**
   * Creates an instance of HomeScreen.
   *
   * @param title           the specified title of this screen.
   * @param backgroundImage the specified background image of this screen.
   * @param user            the specified user of this screen.
   * @param button1Text     the specified button 1 text of this screen.
   * @param button2Text     the specified button 2 text of this screen.
   * @param button3Text     the specified button 3 text of this screen.
   * @param button4Text     the specified button 4 text of this screen.
   */
  public HomeScreen(String title, Image backgroundImage, User user, String button1Text,
                    String button2Text, String button3Text, String button4Text) {
    super("home_screen");
    this.setTitle(title);
    this.setBackgroundImage(backgroundImage);
    this.setUser(user);
    this.setButton1Text(button1Text);
    this.setButton2Text(button2Text);
    this.setButton3Text(button3Text);
    this.setButton4Text(button4Text);
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
   * Sets the user of this screen to the specified user.
   *
   * @param user the specified user.
   * @throws IllegalArgumentException if the user is null.
   */
  private void setUser(User user) {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null");
    }
    this.user = user;
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

  /**
   * Sets the button 4 text of this screen to the specified button 4 text.
   *
   * @param button4Text the specified button 4 text.
   * @throws IllegalArgumentException if the specified button 4 text is null or blank.
   */
  private void setButton4Text(String button4Text) {
    if (button4Text == null) {
      throw new IllegalArgumentException("Button 4 text cannot be null");
    } else if (button4Text.isBlank()) {
      throw new IllegalArgumentException("Button 4 text cannot be blank");
    }
    this.button4Text = button4Text;
  }

  @Override
  public Scene createScene(PathsApp pathsApp) {
    BorderPane root = new BorderPane();

    Scene homeScreenScene = new Scene(root, 800, 600);
    homeScreenScene.getStylesheets().add(
        pathsApp.getClass().getResource("/css/home_screen.css").toExternalForm());
    root.getStyleClass().add("root-style");

    Label titleLabel = new Label(this.title);
    titleLabel.getStyleClass().add("title-label");

    VBox userProfileContainer = new VBox();
    userProfileContainer.getStyleClass().add("user-profile-container");

    Label usernameLabel = new Label(this.user.getName());
    usernameLabel.getStyleClass().add("username-label");

    VBox userImageContainer = new VBox();
    userImageContainer.getStyleClass().add("user-image-container");
    ImageView userImageView = new ImageView(this.user.getImage());
    userImageContainer.getChildren().add(userImageView);

    userProfileContainer.getChildren().addAll(usernameLabel, userImageContainer);

    Button button1 = new Button(this.button1Text);
    button1.getStyleClass().add("button-style");
    button1.setOnAction(event -> {
      HomeScreenController homeScreenController = (HomeScreenController)
          pathsApp.getUserInterfaceManager()
              .getController("home_screen_controller");
      homeScreenController.handleButton1Click();
    });
    button1.setOnMouseEntered(event -> button1.setCursor(Cursor.HAND));

    Button button2 = new Button(this.button2Text);
    button2.getStyleClass().add("button-style");
    button2.setOnMouseEntered(event -> button2.setCursor(Cursor.HAND));

    Button button3 = new Button(this.button3Text);
    button3.getStyleClass().add("button-style");
    button3.setOnAction(event -> {
      HomeScreenController homeScreenController = (HomeScreenController)
          pathsApp.getUserInterfaceManager()
              .getController("home_screen_controller");
      homeScreenController.handleButton3Click();
    });
    button3.setOnMouseEntered(event -> button3.setCursor(Cursor.HAND));

    Button button4 = new Button(this.button4Text);
    button4.getStyleClass().add("button-style");
    button4.setOnAction(event -> {
      HomeScreenController homeScreenController = (HomeScreenController)
          pathsApp.getUserInterfaceManager()
              .getController("home_screen_controller");
      homeScreenController.handleButton4Click();
    });
    button4.setOnMouseEntered(event -> button4.setCursor(Cursor.HAND));

    VBox centerVbox = new VBox(titleLabel, button1, button3, button4);
    centerVbox.getStyleClass().add("center-vbox");

    VBox invisibleContainer = new VBox();
    invisibleContainer.getStyleClass().add("user-profile-container");
    Label usernameLabel2 = new Label(this.user.getName());
    usernameLabel2.getStyleClass().add("username-label");
    VBox userImageContainer2 = new VBox();
    userImageContainer2.getStyleClass().add("user-image-container");
    ImageView userImageView2 = new ImageView(this.user.getImage());
    userImageContainer2.getChildren().add(userImageView2);
    invisibleContainer.getChildren().addAll(usernameLabel2, userImageContainer2);
    invisibleContainer.setVisible(false);

    VBox userProfileContainer2 = new VBox(userProfileContainer);
    userProfileContainer2.getStyleClass().add("user-profile-container2");

    HBox contentsHbox = new HBox(userProfileContainer2, centerVbox, invisibleContainer);
    contentsHbox.getStyleClass().add("contents-hbox");

    MenuBar menuBar = this.createMenuBar(pathsApp);
    root.setTop(menuBar);
    root.setCenter(contentsHbox);

    BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true,
        false, false);
    BackgroundImage background =
        new BackgroundImage(this.backgroundImage, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER, backgroundSize);
    root.setBackground(new Background(background));

    return homeScreenScene;
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
            .getController("home_screen_controller").handleHowToPlayMenuItem());
    MenuItem howToImportStory = new MenuItem("How to import a story");
    howToImportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("home_screen_controller")
            .handleHowToImportStoryMenuItem());
    MenuItem howToExportStory = new MenuItem("How to export a story");
    howToExportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("home_screen_controller")
            .handleHowToExportStoryMenuItem());
    MenuItem whatAreGoals = new MenuItem("What are goals?");
    whatAreGoals.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("home_screen_controller")
            .handleWhatAreGoalsMenuItem());
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(howToPlayGuide, howToImportStory, howToExportStory, whatAreGoals);
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(helpMenu);
    return menuBar;
  }
}
