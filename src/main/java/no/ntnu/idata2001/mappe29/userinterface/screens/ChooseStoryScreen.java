package no.ntnu.idata2001.mappe29.userinterface.screens;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.userinterface.controllers.ChooseStoryScreenController;

/**
 * Represents the choose story screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.21.
 */
public class ChooseStoryScreen extends Screen {
  private String title;
  private Image backgroundImage;
  private String subTitle;
  private List<Story> stories;
  private String button1Text;
  private String button2Text;

  /**
   * Creates an instance of ChooseStoryScreen.
   *
   * @param title           the specified title of this screen.
   * @param backgroundImage the specified background image of this screen.
   * @param subTitle        the specified subtitle of this screen.
   * @param stories         the specified stories of this screen.
   * @param button1Text     the specified button 1 text of this screen.
   * @param button2Text     the specified button 2 text of this screen.
   */
  public ChooseStoryScreen(PathsApp pathsApp, String title, Image backgroundImage, String subTitle,
                           List<Story> stories, String button1Text, String button2Text) {
    super("choose_story_screen");
    pathsApp.getModelManager().getStoryCollectionSubscribers().add(this);
    this.setTitle(title);
    this.setBackgroundImage(backgroundImage);
    this.setSubTitle(subTitle);
    this.setStories(stories);
    this.setButton1Text(button1Text);
    this.setButton2Text(button2Text);
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
   * Sets the stories of this screen to the specified stories.
   *
   * @param stories the specified stories.
   * @throws IllegalArgumentException if the specified stories are null.
   */
  public void setStories(List<Story> stories) {
    if (stories == null) {
      throw new IllegalArgumentException("Stories cannot be null");
    }
    this.stories = stories;
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

    Scene chooseStoryScreenScene = new Scene(root, 800, 600);
    chooseStoryScreenScene.getStylesheets().add(
        pathsApp.getClass().getResource("/css/choose_story_screen.css").toExternalForm());
    root.getStyleClass().add("root-style");

    Label titleLabel = new Label(this.title);
    titleLabel.getStyleClass().add("title-label");

    VBox infoContainer = new VBox();
    infoContainer.getStyleClass().add("info-container");

    Label subTitleLabel = new Label(this.subTitle);
    subTitleLabel.getStyleClass().add("subtitle-label");

    Label testLabel = new Label(pathsApp.getModelManager().getRandomStory().getTitle());
    testLabel.getStyleClass().add("subtitle-label");

    TableView<Story> storiesTableView = this.createStoriesTableView();
    storiesTableView.getStyleClass().add("stories-table-view");
    storiesTableView.setOnMouseClicked(event -> {
      if (event.getClickCount() == 2) {
        Story selectedStory = storiesTableView.getSelectionModel().getSelectedItem();

        ChooseStoryScreenController chooseStoryScreenController = (ChooseStoryScreenController)
            pathsApp.getUserInterfaceManager()
                .getController("choose_story_screen_controller");
        chooseStoryScreenController.handleButton1Click(selectedStory);
      }
    });

    infoContainer.getChildren().addAll(subTitleLabel, storiesTableView);

    HBox buttonContainer = new HBox();
    buttonContainer.getStyleClass().add("button-container");

    Button button1 = new Button(this.button1Text);
    button1.getStyleClass().add("button-style");
    button1.setOnAction(event -> {
      Story selectedStory = storiesTableView.getSelectionModel().getSelectedItem();

      ChooseStoryScreenController chooseStoryScreenController = (ChooseStoryScreenController)
          pathsApp.getUserInterfaceManager()
              .getController("choose_story_screen_controller");
      chooseStoryScreenController.handleButton1Click(selectedStory);
    });
    button1.setOnMouseEntered(event -> button1.setCursor(Cursor.HAND));

    Button button2 = new Button(this.button2Text);
    button2.getStyleClass().add("button-style");
    button2.setOnAction(event -> {
      ChooseStoryScreenController chooseStoryScreenController = (ChooseStoryScreenController)
          pathsApp.getUserInterfaceManager()
              .getController("choose_story_screen_controller");
      chooseStoryScreenController.handleButton2Click();
    });
    button2.setOnMouseEntered(event -> button2.setCursor(Cursor.HAND));

    buttonContainer.getChildren().addAll(button1, button2);

    VBox centerVbox = new VBox(titleLabel, infoContainer, buttonContainer);
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

    return chooseStoryScreenScene;
  }

  /**
   * Creates the stories table view of this screen.
   *
   * @return the stories table view of this screen.
   */
  private TableView<Story> createStoriesTableView() {
    TableView<Story> storiesTableView = new TableView<>();
    storiesTableView.setPlaceholder(new Label("There are no stories"));
    TableColumn<Story, Image> imageTableColumn = new TableColumn<>();
    imageTableColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
    imageTableColumn.setResizable(false);
    imageTableColumn.setReorderable(false);
    imageTableColumn.prefWidthProperty().bind(storiesTableView.widthProperty().multiply(0.2));

    TableColumn<Story, String> storyNameTableColumn = new TableColumn<>();
    storyNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    storyNameTableColumn.setResizable(false);
    storyNameTableColumn.setReorderable(false);
    storyNameTableColumn.prefWidthProperty().bind(storiesTableView.widthProperty()
        .multiply(0.8));

    storiesTableView.getColumns().addAll(imageTableColumn, storyNameTableColumn);
    imageTableColumn.setCellFactory(storyImageTableColumn -> new TableCell<>() {
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
    storyNameTableColumn.setCellFactory(storyStringTableColumn -> new TableCell<>() {
      @Override
      protected void updateItem(String storyName, boolean empty) {
        super.updateItem(storyName, empty);
        setText(empty || storyName == null ? "" : storyName);
        getStyleClass().add("table-story-label");
      }
    });

    ObservableList<Story> observableStories = FXCollections.observableArrayList(this.stories);
    storiesTableView.setItems(observableStories);
    storiesTableView.getSelectionModel().selectFirst();

    return storiesTableView;
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
            .getController("choose_story_screen_controller").handleHowToPlayMenuItem());
    MenuItem howToImportStory = new MenuItem("How to import a story");
    howToImportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("choose_story_screen_controller")
            .handleHowToImportStoryMenuItem());
    MenuItem howToExportStory = new MenuItem("How to export a story");
    howToExportStory.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("choose_story_screen_controller")
            .handleHowToExportStoryMenuItem());
    MenuItem whatAreGoals = new MenuItem("What are goals?");
    whatAreGoals.setOnAction(event ->
        pathsApp.getUserInterfaceManager()
            .getController("choose_story_screen_controller")
            .handleWhatAreGoalsMenuItem());
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(howToPlayGuide, howToImportStory, howToExportStory, whatAreGoals);
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(helpMenu);
    return menuBar;
  }
}
