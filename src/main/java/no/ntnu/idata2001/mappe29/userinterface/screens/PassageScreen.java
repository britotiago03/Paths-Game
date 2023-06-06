package no.ntnu.idata2001.mappe29.userinterface.screens;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.InventoryTableViewRowData;
import no.ntnu.idata2001.mappe29.model.Link;
import no.ntnu.idata2001.mappe29.model.Passage;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.User;
import no.ntnu.idata2001.mappe29.model.goals.Goal;
import no.ntnu.idata2001.mappe29.userinterface.controllers.PassageScreenController;

/**
 * Represents the passage screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.19.
 */
public class PassageScreen extends Screen {
  private User user;
  private Image backgroundImage;
  private Player player;
  private Image scoreImage;
  private Image healthImage;
  private Image goldImage;
  private String button1Text;
  private Image button1Image;
  private String button2Text;
  private Image button2Image;
  private Passage passage;
  private Story story;
  private Label scoreLabel;
  private Label healthLabel;
  private Label goldLabel;

  /**
   * Creates an instance of PassageScreen.
   *
   * @param user         the specified user of this screen.
   * @param player       the specified player of this screen.
   * @param scoreImage   the specified score image of this screen.
   * @param healthImage  the specified health image of this screen.
   * @param goldImage    the specified gold image of this screen.
   * @param button1Text  the specified button 1 text of this screen.
   * @param button1Image the specified button 1 image of this screen.
   * @param button2Text  the specified button 2 text of this screen.
   * @param button2Image the specified button 2 image of this screen.
   * @param passage      the specified passage of this screen.
   */
  public PassageScreen(User user, Player player, Image scoreImage,
                       Image healthImage, Image goldImage, String button1Text,
                       Image button1Image, String button2Text, Image button2Image,
                       Passage passage, Story story) {
    super(passage.getTitle() + "_passage_screen");
    this.setUser(user);
    this.setBackgroundImage(passage.getBackgroundImage());
    this.setPlayer(player);
    this.setScoreImage(scoreImage);
    this.setHealthImage(healthImage);
    this.setGoldImage(goldImage);
    this.setButton1Text(button1Text);
    this.setButton1Image(button1Image);
    this.setButton2Text(button2Text);
    this.setButton2Image(button2Image);
    this.setPassage(passage);
    this.setStory(story);
    this.scoreLabel = new Label("");
    this.healthLabel = new Label("");
    this.goldLabel = new Label("");
  }

  /**
   * Sets the user of this screen to the specified user.
   *
   * @param user the specified user.
   * @throws IllegalArgumentException if the specified user is null.
   */
  private void setUser(User user) {
    if (user == null) {
      throw new IllegalArgumentException("Title cannot be null");
    }
    this.user = user;
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
   * Sets the player of this screen to the specified player.
   *
   * @param player the specified player.
   */
  private void setPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    this.player = player;
  }

  /**
   * Sets the score image of this screen to the specified score image.
   *
   * @param scoreImage the specified score image.
   * @throws IllegalArgumentException if the specified score image is null.
   */
  private void setScoreImage(Image scoreImage) {
    if (scoreImage == null) {
      throw new IllegalArgumentException("Score image cannot be null");
    }
    this.scoreImage = scoreImage;
  }

  /**
   * Sets the health image of this screen to the specified health image.
   *
   * @param healthImage the specified health image.
   * @throws IllegalArgumentException if the specified health image is null.
   */
  private void setHealthImage(Image healthImage) {
    if (healthImage == null) {
      throw new IllegalArgumentException("Health image cannot be null");
    }
    this.healthImage = healthImage;
  }

  /**
   * Sets the gold image of this screen to the specified gold image.
   *
   * @param goldImage the specified gold image.
   * @throws IllegalArgumentException if the specified gold image is null.
   */
  private void setGoldImage(Image goldImage) {
    if (goldImage == null) {
      throw new IllegalArgumentException("Gold image cannot be null");
    }
    this.goldImage = goldImage;
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
   * Sets the button 1 image of this screen to the specified button 1 image.
   *
   * @param button1Image the specified button 1 image.
   * @throws IllegalArgumentException if the specified button 1 image is null.
   */
  private void setButton1Image(Image button1Image) {
    if (button1Image == null) {
      throw new IllegalArgumentException("Button 1 image cannot be null");
    }
    this.button1Image = button1Image;
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
   * Sets the button 2 image of this screen to the specified button 2 image.
   *
   * @param button2Image the specified button 2 image.
   * @throws IllegalArgumentException if the specified button 2 image is null.
   */
  private void setButton2Image(Image button2Image) {
    if (button2Image == null) {
      throw new IllegalArgumentException("Button 2 image cannot be null");
    }
    this.button2Image = button2Image;
  }

  /**
   * Sets the passage of this screen to the specified passage.
   *
   * @param passage the specified passage.
   * @throws IllegalArgumentException if the specified passage is null.
   */
  private void setPassage(Passage passage) {
    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null");
    }
    this.passage = passage;
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
   * Sets the score label of this screen to the specified score label.
   *
   * @param scoreLabel the specified score label.
   */
  public void setScoreLabel(Label scoreLabel) {
    if (scoreLabel == null) {
      throw new IllegalArgumentException("Score label cannot be null");
    }
    this.scoreLabel = scoreLabel;
  }

  /**
   * Sets the health label of this screen to the specified health label.
   *
   * @param healthLabel the specified score label.
   */
  public void setHealthLabel(Label healthLabel) {
    if (healthLabel == null) {
      throw new IllegalArgumentException("Health label cannot be null");
    }
    this.healthLabel = healthLabel;
  }

  /**
   * Sets the gold label of this screen to the specified gold label.
   *
   * @param goldLabel the specified gold label.
   */
  public void setGoldLabel(Label goldLabel) {
    if (goldLabel == null) {
      throw new IllegalArgumentException("Gold label cannot be null");
    }
    this.goldLabel = goldLabel;
  }

  /**
   * Gets the score label of this screen.
   *
   * @return the score label of this screen.
   */
  public Label getScoreLabel() {
    return this.scoreLabel;
  }

  /**
   * Gets the health label of this screen.
   *
   * @return the health label of this screen.
   */
  public Label getHealthLabel() {
    return this.healthLabel;
  }

  /**
   * Gets the gold label of this screen.
   *
   * @return the gold label of this screen.
   */
  public Label getGoldLabel() {
    return this.goldLabel;
  }

  @Override
  public Scene createScene(PathsApp pathsApp) {
    BorderPane root = new BorderPane();

    Scene passageScreenScene = new Scene(root, 800, 600);
    passageScreenScene.getStylesheets().add(
        pathsApp.getClass().getResource("/css/passage_screen.css")
            .toExternalForm());
    root.getStyleClass().add("root-style");

    VBox userProfileContainer = new VBox();
    userProfileContainer.getStyleClass().add("user-profile-container");

    Label usernameLabel = new Label(this.user.getName());
    usernameLabel.getStyleClass().add("username-label");

    VBox userImageContainer = new VBox();
    userImageContainer.getStyleClass().add("user-image-container");
    ImageView userImageView = new ImageView(this.user.getImage());
    userImageContainer.getChildren().add(userImageView);

    userProfileContainer.getChildren().addAll(usernameLabel, userImageContainer);

    VBox playerStatsContainer = new VBox();
    playerStatsContainer.getStyleClass().add("player-stats-container");

    GridPane playerStatsGridPane = new GridPane();
    playerStatsGridPane.getStyleClass().add("player-stats-grid-pane");
    ImageView scoreImageView = new ImageView(this.scoreImage);
    this.setScoreLabel(new Label("" + this.player.getScore()));
    scoreLabel.getStyleClass().add("attribute-label");
    playerStatsGridPane.add(scoreImageView, 0, 0);
    playerStatsGridPane.add(this.scoreLabel, 1, 0);

    ImageView healthImageView = new ImageView(this.healthImage);
    this.setHealthLabel(new Label("" + this.player.getHealth()));
    healthLabel.getStyleClass().add("attribute-label");
    playerStatsGridPane.add(healthImageView, 0, 1);
    playerStatsGridPane.add(this.healthLabel, 1, 1);

    ImageView goldImageView = new ImageView(this.goldImage);
    this.setGoldLabel(new Label("" + this.player.getGold()));
    goldLabel.getStyleClass().add("attribute-label");
    playerStatsGridPane.add(goldImageView, 0, 2);
    playerStatsGridPane.add(this.goldLabel, 1, 2);

    Label button1Label = new Label(this.button1Text);
    button1Label.getStyleClass().add("player-stats-button-label");
    ImageView button1ImageView = new ImageView(this.button1Image);

    HBox button1 = new HBox(button1Label, button1ImageView);
    button1.getStyleClass().add("player-stats-button-style");
    button1.setOnMouseClicked(event -> {
      PassageScreenController passageScreenController =
          (PassageScreenController) pathsApp.getUserInterfaceManager()
              .getController("passage_screen_controller");
      passageScreenController.handleButton1Click(this.createGoalsTableView());
    });
    button1.setOnMouseEntered(event -> button1.setCursor(Cursor.HAND));

    Label button2Label = new Label(this.button2Text);
    button2Label.getStyleClass().add("player-stats-button-label");
    ImageView button2ImageView = new ImageView(this.button2Image);

    HBox button2 = new HBox(button2Label, button2ImageView);
    button2.getStyleClass().add("player-stats-button-style");
    button2.setOnMouseClicked(event -> {
      PassageScreenController passageScreenController =
          (PassageScreenController) pathsApp.getUserInterfaceManager()
              .getController("passage_screen_controller");
      passageScreenController.handleButton2Click(this.createInventoryTableView(pathsApp));
    });
    button2.setOnMouseEntered(event -> button2.setCursor(Cursor.HAND));

    playerStatsContainer.getChildren().addAll(playerStatsGridPane, button1, button2);

    Label passageContentLabel = new Label(this.passage.getContent());
    passageContentLabel.getStyleClass().add("passage-content-label");

    HBox buttonContainer = new HBox();
    buttonContainer.getStyleClass().add("button-container");

    for (Link link : this.passage.getLinks()) {
      Button button = new Button(link.getText());
      button.getStyleClass().add("button-style");
      button.setOnAction(event -> {
        PassageScreenController passageScreenController =
            (PassageScreenController) pathsApp.getUserInterfaceManager()
                .getController("passage_screen_controller");
        passageScreenController.handleButton3Click(link, this.story);
      });
      button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
      buttonContainer.getChildren().add(button);
    }

    VBox invisibleContainer = new VBox();
    invisibleContainer.getStyleClass().add("invisible-container");

    BorderPane topHalfContainer = new BorderPane();
    topHalfContainer.setLeft(userProfileContainer);
    topHalfContainer.setCenter(invisibleContainer);
    topHalfContainer.setRight(playerStatsContainer);
    topHalfContainer.getStyleClass().add("top-half-container");

    VBox bottomHalfContainer = new VBox(passageContentLabel, buttonContainer);
    bottomHalfContainer.getStyleClass().add("bottom-half-container");

    MenuBar menuBar = this.createMenuBar(pathsApp);

    root.setTop(menuBar);
    root.setCenter(topHalfContainer);
    root.setBottom(bottomHalfContainer);

    BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true,
        false, false);
    BackgroundImage background =
        new BackgroundImage(this.backgroundImage, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER, backgroundSize);
    root.setBackground(new Background(background));

    return passageScreenScene;
  }

  /**
   * Creates the goals tableview of this screen.
   *
   * @return the goals tableview of this screen.
   */
  private TableView<Goal> createGoalsTableView() {
    TableView<Goal> goalsTableView = new TableView<>();
    goalsTableView.setPlaceholder(new Label("You have no goals for this story"));
    TableColumn<Goal, Image> imageTableColumn = new TableColumn<>();
    imageTableColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
    imageTableColumn.setResizable(false);
    imageTableColumn.setReorderable(false);
    imageTableColumn.prefWidthProperty().bind(goalsTableView.widthProperty().multiply(0.2));

    TableColumn<Goal, String> descriptionTableColumn = new TableColumn<>();
    descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    descriptionTableColumn.setResizable(false);
    descriptionTableColumn.setReorderable(false);
    descriptionTableColumn.prefWidthProperty().bind(goalsTableView.widthProperty()
        .multiply(0.8));

    goalsTableView.getColumns().addAll(imageTableColumn, descriptionTableColumn);
    imageTableColumn.setCellFactory(goalImageTableColumn -> new TableCell<>() {
      private final ImageView imageView = new ImageView();

      @Override
      protected void updateItem(Image image, boolean empty) {
        super.updateItem(image, empty);
        if (empty || image == null) {
          setGraphic(null);
        } else {
          imageView.setImage(image);
          setGraphic(imageView);
        }
      }
    });
    descriptionTableColumn.setCellFactory(goalDescriptionTableColumn -> new TableCell<>() {
      @Override
      protected void updateItem(String description, boolean empty) {
        super.updateItem(description, empty);
        setText(empty || description == null ? "" : description);
      }
    });

    ObservableList<Goal> observableStories = FXCollections
        .observableArrayList(this.player.getGoals());
    goalsTableView.setItems(observableStories);
    goalsTableView.getSelectionModel().selectFirst();

    return goalsTableView;
  }

  /**
   * Creates the inventory tableview of this screen.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @return the inventory tableview of this screen.
   */
  private TableView<InventoryTableViewRowData> createInventoryTableView(PathsApp pathsApp) {
    List<InventoryTableViewRowData> tableViewData = new ArrayList<>();
    for (String item : this.player.getInventory()) {
      Image itemImage = new Image(pathsApp.getClass()
          .getResource("/images/icons/item_icon.png").toExternalForm());
      tableViewData.add(new InventoryTableViewRowData(itemImage, item));
    }

    TableView<InventoryTableViewRowData> inventoryTableView = new TableView<>();
    inventoryTableView.setPlaceholder(new Label("You have no items in your inventory"));
    TableColumn<InventoryTableViewRowData, Image> imageTableColumn = new TableColumn<>();
    imageTableColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
    imageTableColumn.setResizable(false);
    imageTableColumn.setReorderable(false);
    imageTableColumn.prefWidthProperty().bind(inventoryTableView.widthProperty().multiply(0.2));

    TableColumn<InventoryTableViewRowData, String> descriptionTableColumn = new TableColumn<>();
    descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    descriptionTableColumn.setResizable(false);
    descriptionTableColumn.setReorderable(false);
    descriptionTableColumn.prefWidthProperty().bind(inventoryTableView.widthProperty()
        .multiply(0.8));

    inventoryTableView.getColumns().addAll(imageTableColumn, descriptionTableColumn);
    imageTableColumn.setCellFactory(itemImageTableColumn -> new TableCell<>() {
      private final ImageView imageView = new ImageView();

      @Override
      protected void updateItem(Image image, boolean empty) {
        super.updateItem(image, empty);
        if (empty || image == null) {
          setGraphic(null);
        } else {
          imageView.setImage(image);
          getStyleClass().add("table-image-view");
          setGraphic(imageView);
        }
      }
    });
    descriptionTableColumn.setCellFactory(itemDescriptionTableColumn -> new TableCell<>() {
      @Override
      protected void updateItem(String description, boolean empty) {
        super.updateItem(description, empty);
        setText(empty || description == null ? "" : description);
        getStyleClass().add("table-description-label");
      }
    });

    ObservableList<InventoryTableViewRowData> observableTableViewData = FXCollections
        .observableArrayList(tableViewData);
    inventoryTableView.setItems(observableTableViewData);
    inventoryTableView.getSelectionModel().selectFirst();

    return inventoryTableView;
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
            .getController("passage_screen_controller").handleHowToPlayMenuItem());
    MenuItem howToImportStory = new MenuItem("How to import a story");
    howToImportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("passage_screen_controller")
            .handleHowToImportStoryMenuItem());
    MenuItem howToExportStory = new MenuItem("How to export a story");
    howToExportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("passage_screen_controller")
            .handleHowToExportStoryMenuItem());
    MenuItem whatAreGoals = new MenuItem("What are goals?");
    whatAreGoals.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("passage_screen_controller")
            .handleWhatAreGoalsMenuItem());
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(howToPlayGuide, howToImportStory, howToExportStory, whatAreGoals);
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(helpMenu);
    return menuBar;
  }
}
