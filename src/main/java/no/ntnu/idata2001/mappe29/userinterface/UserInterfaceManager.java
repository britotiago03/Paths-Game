package no.ntnu.idata2001.mappe29.userinterface;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.Passage;
import no.ntnu.idata2001.mappe29.model.Player;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.User;
import no.ntnu.idata2001.mappe29.userinterface.controllers.ChooseAvatarScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.ChooseStoryScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.Controller;
import no.ntnu.idata2001.mappe29.userinterface.controllers.DeleteStoryScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.ExportStoryScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.GoalsScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.HomeScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.OptionsScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.PassageScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.PlayScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.StoryInformationScreenController;
import no.ntnu.idata2001.mappe29.userinterface.controllers.WelcomeScreenController;
import no.ntnu.idata2001.mappe29.userinterface.screens.ChooseAvatarScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.ChooseStoryScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.DeleteStoryScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.ExportStoryScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.GoalsScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.HomeScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.OptionsScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.PassageScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.PlayScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;
import no.ntnu.idata2001.mappe29.userinterface.screens.StoryInformationScreen;
import no.ntnu.idata2001.mappe29.userinterface.screens.WelcomeScreen;


/**
 * Represents a User Interface Manager that is responsible for managing the user interface part of
 * the application.
 *
 * @author Tiago Brito.
 * @version 2023.05.19.
 */
public class UserInterfaceManager {
  private final PathsApp pathsApp;
  private final Stage primaryStage;
  private final Map<String, Screen> screenCollectionMap;
  private final Map<String, Controller> controllerCollectionMap;

  /**
   * Creates an instance of UserInterfaceManager.
   *
   * @param pathsApp     the PathsApp instance of this application.
   * @param primaryStage the primary stage of the graphical user interface.
   * @throws IllegalArgumentException if the specified primary stage is null or PathsApp is null.
   */
  public UserInterfaceManager(PathsApp pathsApp, Stage primaryStage) {
    if (pathsApp == null) {
      throw new IllegalArgumentException("Paths App cannot be null");
    } else if (primaryStage == null) {
      throw new IllegalArgumentException("Primary stage cannot be null");
    }
    this.pathsApp = pathsApp;
    this.primaryStage = primaryStage;
    this.initializePrimaryStage();
    this.screenCollectionMap = new HashMap<>();
    this.controllerCollectionMap = new HashMap<>();
    this.initializeScreens();
    this.initializeControllers();
  }

  /**
   * Gets the screens of this user interface manager.
   *
   * @return the screens of this user interface manager.
   */
  public Collection<Screen> getScreens() {
    return this.screenCollectionMap.values();
  }

  /**
   * Checks if the specified screen name refers to a screen in the screen collection map of this
   * user interface manager.
   *
   * @param screenName the specified screen name.
   * @return true if the screen name refers to a screen in the screen collection map of this
   *     user interface manager, false otherwise.
   * @throws IllegalArgumentException if the specified screen name is null or blank.
   */
  private boolean doesScreenNameReferToScreenInScreenCollectionMap(String screenName) {
    if (screenName == null) {
      throw new IllegalArgumentException("Screen name cannot be null");
    } else if (screenName.isBlank()) {
      throw new IllegalArgumentException("Screen name cannot be blank");
    }
    return !this.screenCollectionMap.values().stream()
        .filter(screen -> screen.getName().equals(screenName)).toList().isEmpty();
  }

  /**
   * Gets the screen that the specified screen name refers to.
   *
   * @param screenName the specified screen name.
   * @return the screen that the specified screen name refers to.
   * @throws IllegalArgumentException if the specified screen name is null, blank, or does not
   *                                  refer to a screen in the screen collection map of this
   *                                  user interface.
   */
  public Screen getScreen(String screenName) {
    if (screenName == null) {
      throw new IllegalArgumentException("Screen name cannot be null");
    } else if (screenName.isBlank()) {
      throw new IllegalArgumentException("Screen name cannot be blank");
    } else if (!this.doesScreenNameReferToScreenInScreenCollectionMap(screenName)) {
      throw new IllegalArgumentException("The specified screen name does not refer to a screen"
          + " in the screen collection map of this user interface");
    }
    return this.screenCollectionMap.get(screenName);
  }

  /**
   * Checks if the specified controller name refers to a controller in the controller collection
   * map of this user interface manager.
   *
   * @param controllerName the specified controller name.
   * @return true if the controller name refers to a controller in the controller collection map
   *     of this user interface manager, false otherwise.
   * @throws IllegalArgumentException if the specified controller name is null or blank.
   */
  private boolean doesControllerNameReferToControllerInControllerCollectionMap(
      String controllerName) {
    if (controllerName == null) {
      throw new IllegalArgumentException("Controller name cannot be null");
    } else if (controllerName.isBlank()) {
      throw new IllegalArgumentException("Controller name cannot be blank");
    }
    return !this.controllerCollectionMap.values().stream()
        .filter(screen -> screen.getName().equals(controllerName)).toList().isEmpty();
  }

  /**
   * Gets the controller that the specified controller name refers to.
   *
   * @param controllerName the specified controller name.
   * @return the controller that the specified controller name refers to.
   * @throws IllegalArgumentException if the specified controller name is null, blank, or does not
   *                                  refer to a controller in the controller collection map of
   *                                  this user interface.
   */
  public Controller getController(String controllerName) {
    if (controllerName == null) {
      throw new IllegalArgumentException("Controller name cannot be null");
    } else if (controllerName.isBlank()) {
      throw new IllegalArgumentException("Controller name cannot be blank");
    } else if (!this.doesControllerNameReferToControllerInControllerCollectionMap(controllerName)) {
      throw new IllegalArgumentException("The specified controller name does not refer to a"
          + " controller in the controller collection map of this user interface");
    }
    return this.controllerCollectionMap.get(controllerName);
  }

  /**
   * Initializes the primary stage.
   */
  private void initializePrimaryStage() {
    this.primaryStage.setOnCloseRequest(event -> {
      event.consume();
      this.terminateApp();
    });
  }

  /**
   * Terminates the Paths Application.
   */
  public void terminateApp() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    this.addPathsIconToDialog(alert);
    alert.setTitle("Confirm Exit");
    alert.setHeaderText("Are you sure you want to exit?");
    alert.setContentText("Any unsaved changes will be lost.");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      System.exit(0);
      Platform.exit();
    }
  }

  /**
   * Adds the icon of the Paths logo to the specified dialog.
   *
   * @param dialog the specified dialog.
   */
  public void addPathsIconToDialog(Dialog<?> dialog) {
    Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
    dialogStage.getIcons().add(
        new Image(this.getClass().getResource("/images/icons/paths_logo.png").toExternalForm()));

  }

  /**
   * Shows an error dialog with the specified title and message.
   *
   * @param title   the specified title.
   * @param message the specified message.
   * @throws IllegalArgumentException if the title or message are null or blank.
   */
  public void showErrorDialog(String title, String message) {
    if (title == null) {
      throw new IllegalArgumentException("Title cannot be null");
    } else if (title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be blank");
    } else if (message == null) {
      throw new IllegalArgumentException("Message cannot be null");
    } else if (message.isBlank()) {
      throw new IllegalArgumentException("Message cannot be blank");
    }
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(title);
    alert.setContentText(message);
    this.addPathsIconToDialog(alert);
    alert.showAndWait();
  }

  /**
   * Creates the welcome screen of the user interface of the Paths Application.
   *
   * @return the welcome screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen.
   */
  private Screen createWelcomeScreen() {
    String title = "Welcome to Paths";
    String infoText = "PLEASE ENTER INFORMATION ABOUT YOURSELF:";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library.png").toExternalForm());
    String nameFieldText = "Name:";
    String genderFieldText = "Gender:";
    String buttonText = "Continue";

    return new WelcomeScreen(title, infoText, backgroundImage, nameFieldText,
        genderFieldText, buttonText);
  }

  /**
   * Creates the choose avatar male screen of the user interface of the Paths Application.
   *
   * @return the choose avatar male screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen.
   */
  private Screen createChooseAvatarMaleScreen() {
    String title = "Choose your Avatar";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library.png")
            .toExternalForm());
    Image avatarImage1 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/male/male_arab_avatar_face.png")
        .toExternalForm());
    Image avatarImage2 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/male/male_asian_avatar_face.png")
        .toExternalForm());
    Image avatarImage3 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/male/male_black_avatar_face.png")
        .toExternalForm());
    Image avatarImage4 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/male/male_geek_avatar_face.png")
        .toExternalForm());
    Image avatarImage5 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/male/male_latino_avatar_face.png")
        .toExternalForm());
    Image avatarImage6 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/male/male_norwegian_avatar_face.png")
        .toExternalForm());

    return new ChooseAvatarScreen("choose_avatar_male_screen", title, backgroundImage,
        avatarImage1, avatarImage2, avatarImage3, avatarImage4, avatarImage5, avatarImage6);
  }

  /**
   * Creates the choose avatar female screen of the user interface of the Paths Application.
   *
   * @return the choose avatar female screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen.
   */
  private Screen createChooseAvatarFemaleScreen() {
    String title = "Choose your Avatar";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library.png")
            .toExternalForm());
    Image avatarImage1 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/female/female_arab_avatar_face.png")
        .toExternalForm());
    Image avatarImage2 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/female/female_asian_avatar_face.png")
        .toExternalForm());
    Image avatarImage3 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/female/female_black_avatar_face.png")
        .toExternalForm());
    Image avatarImage4 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/female/female_geek_avatar_face.png")
        .toExternalForm());
    Image avatarImage5 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/female/female_latina_avatar_face.png")
        .toExternalForm());
    Image avatarImage6 = new Image(this.pathsApp.getClass()
        .getResource("/images/avatars/female/female_norwegian_avatar_face.png")
        .toExternalForm());

    return new ChooseAvatarScreen("choose_avatar_female_screen", title, backgroundImage,
        avatarImage1, avatarImage2, avatarImage3, avatarImage4, avatarImage5, avatarImage6);
  }

  /**
   * Creates the home screen of the user interface of the Paths Application.
   *
   * @return the home screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen.
   */
  private Screen createHomeScreen() {
    String title = "PATHS";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library.png").toExternalForm());
    String button1Text = "Play";
    String button2Text = "History";
    String button3Text = "Options";
    String button4Text = "Quit";

    return new HomeScreen(title, backgroundImage, this.pathsApp.getModelManager().getUser(),
        button1Text, button2Text, button3Text, button4Text);
  }

  /**
   * Creates the play screen of the user interface of the Paths Application.
   *
   * @return the play screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen.
   */
  private Screen createPlayScreen() {
    String title = "Play";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library_dark.png").toExternalForm());
    String subTitle = "CHOOSE AN OPTION TO BEGIN";
    String line1Text = "Random Story: Embark on a surprising and unpredictable journey.";
    String line2Text = "Choose Story: Explore specific narratives tailored to your preference.";
    String button1Text = "Random Story";
    String button2Text = "Choose Story";
    String button3Text = "Back";

    return new PlayScreen(title, backgroundImage, subTitle, line1Text, line2Text, button1Text,
        button2Text, button3Text);
  }

  /**
   * Creates the choose story screen of the user interface of the Paths Application.
   *
   * @return the choose story screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen.
   */
  private Screen createChooseStoryScreen() {
    String title = "Choose a Story";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library_dark.png").toExternalForm());
    String subTitle = "Stories";
    List<Story> stories = this.pathsApp.getModelManager().getStoryCollection().values().stream()
        .toList();
    String button1Text = "Select";
    String button2Text = "Cancel";

    return new ChooseStoryScreen(this.pathsApp, title, backgroundImage, subTitle, stories,
        button1Text, button2Text);
  }

  /**
   * Creates the story introduction screen of the user interface of the Paths Application.
   *
   * @param story the specified story to create the story introduction screen for.
   * @return the story introduction screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen
   *                                  or the specified story is null.
   */
  private Screen createStoryIntroductionScreen(Story story) {
    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    }
    String screenName = story.getTitle() + "_story_introduction_screen";
    String title = story.getTitle();
    Image backgroundImage = story.getBackgroundImage();
    String intro1Text = story.getIntro1Text();
    String intro2Text = story.getIntro2Text();
    String button1Text = "Start";
    String button2Text = "Cancel";

    return new StoryInformationScreen(screenName, story, title, backgroundImage, intro1Text,
        intro2Text,
        button1Text, button2Text);
  }

  /**
   * Creates the passage screen of the user interface of the Paths Application.
   *
   * @param passage the specified passage to create the passage screen for.
   * @return the passage screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen
   *                                  or the specified passage is null.
   */
  private Screen createPassageScreen(Passage passage, Story story) {
    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null");
    }
    User user = this.pathsApp.getModelManager().getUser();
    Player player = this.pathsApp.getModelManager().getPlayer();
    Image scoreImage = new Image(this.pathsApp.getClass()
        .getResource("/images/icons/score_icon.png").toExternalForm());
    Image healthImage = new Image(this.pathsApp.getClass()
        .getResource("/images/icons/health_icon.png").toExternalForm());
    Image goldImage = new Image(this.pathsApp.getClass()
        .getResource("/images/icons/gold_icon.png").toExternalForm());
    String button1Text = "Goals";
    Image button1Image = new Image(this.pathsApp.getClass()
        .getResource("/images/icons/goal_icon.png").toExternalForm());
    String button2Text = "Inventory";
    Image button2Image = new Image(this.pathsApp.getClass()
        .getResource("/images/icons/inventory_icon.png").toExternalForm());

    return new PassageScreen(user, player, scoreImage, healthImage, goldImage,
        button1Text, button1Image, button2Text, button2Image, passage, story);
  }

  /**
   * Creates the story finished success screen of the user interface of the Paths Application.
   *
   * @param story the specified story to create the story finished success screen for.
   * @return the story finished success screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen
   *                                  or the specified story is null.
   */
  private Screen createStoryFinishedSuccessScreen(Story story) {
    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    }
    String screenName = story.getTitle() + "_story_success_screen";
    String title = "Story Success";
    Image backgroundImage = story.getBackgroundImage();
    String intro1Text = "Congratulations, valiant adventurer! Through your unwavering resolve and "
        + "astute decision-making, you have emerged triumphant in this enthralling tale.";
    String intro2Text = "But remember dear adventurer, this is just one chapter in an epic saga. "
        + "Many more stories lie ahead, each brimming with untold adventures and thrilling twists.";
    String button1Text = "Play again";
    String button2Text = "Return Home";

    return new StoryInformationScreen(screenName, story, title, backgroundImage, intro1Text,
        intro2Text,
        button1Text, button2Text);
  }

  /**
   * Creates the story finished failed screen of the user interface of the Paths Application.
   *
   * @param story the specified story to create the story finished failed screen for.
   * @return the story finished failed screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen
   *                                  or the specified story is null.
   */
  private Screen createStoryFinishedFailedScreen(Story story) {
    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    }
    String screenName = story.getTitle() + "_story_failed_screen";
    String title = "Story Failed";
    Image backgroundImage = story.getBackgroundImage();
    String intro1Text = "As the story unfolds, the path you have taken leads to an unexpected "
        + "outcome. Alas, the winds of fate have not favored your journey, and you find yourself "
        + "facing failure in this particular tale.";
    String intro2Text = "Learn from the choices that led you here and let them guide you "
        + "towards a different path, one filled with renewed determination and fresh "
        + "possibilities.";
    String button1Text = "Try again";
    String button2Text = "Return Home";

    return new StoryInformationScreen(screenName, story, title, backgroundImage, intro1Text,
        intro2Text,
        button1Text, button2Text);
  }

  /**
   * Creates the goals screen of the user interface of the Paths Application.
   *
   * @return the story introduction screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen
   *                                  or the specified story is null.
   */
  private Screen createGoalsScreen() {
    String title = "Goals";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library_dark.png").toExternalForm());
    String subtitle = "What type of goal do you want to make?";
    Image image1 = new Image(this.pathsApp.getClass()
        .getResource("/images/icons/gold_icon_big.png").toExternalForm());
    String image1Text = "Gold Goal";
    Image image2 = new Image(this.pathsApp.getClass()
        .getResource("/images/icons/health_icon_big.png").toExternalForm());
    String image2Text = "Health Goal";
    Image image3 = new Image(this.pathsApp.getClass()
        .getResource("/images/icons/inventory_icon_big.png").toExternalForm());
    String image3Text = "Item Goal";
    Image image4 = new Image(this.pathsApp.getClass()
        .getResource("/images/icons/score_icon_big.png").toExternalForm());
    String image4Text = "Score Goal";
    String buttonText = "Cancel";

    return new GoalsScreen(title, backgroundImage, subtitle, image1, image1Text, image2,
        image2Text, image3, image3Text, image4, image4Text, buttonText);
  }

  /**
   * Creates the options screen of the user interface of the Paths Application.
   *
   * @return the options screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen.
   */
  private Screen createOptionsScreen() {
    String title = "Options";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library_dark.png").toExternalForm());
    String button1Text = "Import a Story";
    String button2Text = "Export a Story";
    String button3Text = "Delete a Story";
    String button4Text = "Reset Player";
    String button5Text = "Back";

    return new OptionsScreen(title, backgroundImage, button1Text, button2Text,
        button3Text, button4Text, button5Text);
  }

  /**
   * Creates the export story screen of the user interface of the Paths Application.
   *
   * @return the export story screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen.
   */
  private Screen createExportStoryScreen() {
    String title = "Export a Story";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library_dark.png").toExternalForm());
    String subTitle = "Stories";
    List<Story> stories = this.pathsApp.getModelManager().getStoryCollection().values().stream()
        .toList();
    String button1Text = "Export";
    String button2Text = "Cancel";

    return new ExportStoryScreen(this.pathsApp, title, backgroundImage, subTitle, stories,
        button1Text, button2Text);
  }

  /**
   * Creates the delete story screen of the user interface of the Paths Application.
   *
   * @return the delete story screen of the user interface of the Paths Application.
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the screen.
   */
  private Screen createDeleteStoryScreen() {
    String title = "Delete a Story";
    Image backgroundImage =
        new Image(this.pathsApp.getClass()
            .getResource("/images/backgrounds/grand_library_dark.png").toExternalForm());
    String subTitle = "Stories";
    List<Story> stories = this.pathsApp.getModelManager().getStoryCollection().values().stream()
        .toList();
    String button1Text = "Delete";
    String button2Text = "Cancel";

    return new DeleteStoryScreen(this.pathsApp, title, backgroundImage, subTitle, stories,
        button1Text, button2Text);
  }

  /**
   * Initializes the screens of the user interface of the Paths Application.
   *
   * @throws IllegalArgumentException if invalid parameters are used in the creation of the
   *                                  welcome screen.
   */
  private void initializeScreens() {
    Screen welcomeScreen = this.createWelcomeScreen();
    this.screenCollectionMap.put(welcomeScreen.getName(), welcomeScreen);
    Screen chooseAvatarMaleScreen = this.createChooseAvatarMaleScreen();
    this.screenCollectionMap.put(chooseAvatarMaleScreen.getName(), chooseAvatarMaleScreen);
    Screen chooseAvatarFemaleScreen = this.createChooseAvatarFemaleScreen();
    this.screenCollectionMap.put(chooseAvatarFemaleScreen.getName(), chooseAvatarFemaleScreen);
    Screen homeScreen = this.createHomeScreen();
    this.screenCollectionMap.put(homeScreen.getName(), homeScreen);
    Screen playScreen = this.createPlayScreen();
    this.screenCollectionMap.put(playScreen.getName(), playScreen);
    Screen chooseStoryScreen = this.createChooseStoryScreen();
    this.screenCollectionMap.put(chooseStoryScreen.getName(), chooseStoryScreen);
    Screen goalsScreen = this.createGoalsScreen();
    this.screenCollectionMap.put(goalsScreen.getName(), goalsScreen);
    Screen optionsScreen = this.createOptionsScreen();
    this.screenCollectionMap.put(optionsScreen.getName(), optionsScreen);
    Screen exportStoryScreen = this.createExportStoryScreen();
    this.screenCollectionMap.put(exportStoryScreen.getName(), exportStoryScreen);
    Screen deleteStoryScreen = this.createDeleteStoryScreen();
    this.screenCollectionMap.put(deleteStoryScreen.getName(), deleteStoryScreen);
  }

  /**
   * Initializes all the story screens of the Paths Application.
   */
  public void initializeStoryScreens() {
    for (Story story : this.pathsApp.getModelManager().getStoryCollection().values()
        .stream().toList()) {
      this.initializeStoryScreensForStory(story);
    }
  }

  /**
   * Initializes the story screens of the Paths Application for a specific story.
   */
  public void initializeStoryScreensForStory(Story story) {
    Screen storyIntroductionScreen = this.createStoryIntroductionScreen(story);
    this.screenCollectionMap.put(storyIntroductionScreen.getName(), storyIntroductionScreen);
    for (Passage passage : story.getPassages()) {
      Screen passageScreen = this.createPassageScreen(passage, story);
      this.screenCollectionMap.put(passageScreen.getName(), passageScreen);
    }
    Screen storyFinishedSuccessScreen = this.createStoryFinishedSuccessScreen(story);
    this.screenCollectionMap.put(storyFinishedSuccessScreen.getName(),
        storyFinishedSuccessScreen);
    Screen storyFinishedFailedScreen = this.createStoryFinishedFailedScreen(story);
    this.screenCollectionMap.put(storyFinishedFailedScreen.getName(),
        storyFinishedFailedScreen);
  }

  /**
   * Creates the controller for the welcome screen of the Paths Application.
   *
   * @return the controller for the welcome screen of the Paths Application.
   */
  private Controller createWelcomeScreenController() {
    return new WelcomeScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the choose avatar screens of the Paths Application.
   *
   * @return the controller for the choose avatar screens of the Paths Application.
   */
  private Controller createChooseAvatarScreenController() {
    return new ChooseAvatarScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the home screen of the Paths Application.
   *
   * @return the controller for the home screen of the Paths Application.
   */
  private Controller createHomeScreenController() {
    return new HomeScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the play screen of the Paths Application.
   *
   * @return the controller for the play screen of the Paths Application.
   */
  private Controller createPlayScreenController() {
    return new PlayScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the choose story screen of the Paths Application.
   *
   * @return the controller for the choose story screen of the Paths Application.
   */
  private Controller createChooseStoryScreenController() {
    return new ChooseStoryScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the story introduction screen of the Paths Application.
   *
   * @return the controller for the story introduction screen of the Paths Application.
   */
  private Controller createStoryIntroductionScreenController() {
    return new StoryInformationScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the passage screen of the Paths Application.
   *
   * @return the controller for the passage screen of the Paths Application.
   */
  private Controller createPassageScreenController() {
    return new PassageScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the goals screen of the Paths Application.
   *
   * @return the controller for the goals screen of the Paths Application.
   */
  private Controller createGoalsScreenController() {
    return new GoalsScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the options screen of the Paths Application.
   *
   * @return the controller for the options screen of the Paths Application.
   */
  private Controller createOptionsScreenController() {
    return new OptionsScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the export story screen of the Paths Application.
   *
   * @return the controller for the export story screen of the Paths Application.
   */
  private Controller createExportStoryScreenController() {
    return new ExportStoryScreenController(this.pathsApp);
  }

  /**
   * Creates the controller for the delete story screen of the Paths Application.
   *
   * @return the controller for the delete story screen of the Paths Application.
   */
  private Controller createDeleteStoryScreenController() {
    return new DeleteStoryScreenController(this.pathsApp);
  }

  /**
   * Initializes the controllers of screens of the user interface of the Paths Application.
   */
  private void initializeControllers() {
    Controller welcomeScreenController = this.createWelcomeScreenController();
    this.controllerCollectionMap.put(welcomeScreenController.getName(), welcomeScreenController);
    Controller chooseAvatarScreenController = this.createChooseAvatarScreenController();
    this.controllerCollectionMap.put(chooseAvatarScreenController.getName(),
        chooseAvatarScreenController);
    Controller homeScreenController = this.createHomeScreenController();
    this.controllerCollectionMap.put(homeScreenController.getName(), homeScreenController);
    Controller playScreenController = this.createPlayScreenController();
    this.controllerCollectionMap.put(playScreenController.getName(), playScreenController);
    Controller chooseStoryScreenController = this.createChooseStoryScreenController();
    this.controllerCollectionMap.put(chooseStoryScreenController.getName(),
        chooseStoryScreenController);
    Controller storyIntroductionScreenController = this.createStoryIntroductionScreenController();
    this.controllerCollectionMap.put(storyIntroductionScreenController.getName(),
        storyIntroductionScreenController);
    Controller passageScreenController = this.createPassageScreenController();
    this.controllerCollectionMap.put(passageScreenController.getName(), passageScreenController);
    Controller goalsScreenController = this.createGoalsScreenController();
    this.controllerCollectionMap.put(goalsScreenController.getName(), goalsScreenController);
    Controller optionsScreenController = this.createOptionsScreenController();
    this.controllerCollectionMap.put(optionsScreenController.getName(), optionsScreenController);
    Controller exportStoryScreenController = this.createExportStoryScreenController();
    this.controllerCollectionMap.put(exportStoryScreenController.getName(),
        exportStoryScreenController);
    Controller deleteStoryScreenController = this.createDeleteStoryScreenController();
    this.controllerCollectionMap.put(deleteStoryScreenController.getName(),
        deleteStoryScreenController);
  }

  /**
   * Sets the scene of the primary stage to the specified scene.
   *
   * @param scene the specified scene.
   */
  public void setPrimaryStageScene(Scene scene) {
    Platform.runLater(() -> {
      this.primaryStage.hide();
      Image icon = new Image(
          this.getClass().getResource("/images/icons/paths_logo.png").toExternalForm());
      this.primaryStage.getIcons().add(icon);

      this.primaryStage.setScene(scene);
      this.primaryStage.setTitle("Paths Game");
      this.primaryStage.setMinHeight(650);
      this.primaryStage.setMinWidth(800);
      this.primaryStage.show();
    });
  }
}
