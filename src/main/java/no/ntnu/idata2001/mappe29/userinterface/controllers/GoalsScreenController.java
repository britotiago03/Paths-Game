package no.ntnu.idata2001.mappe29.userinterface.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import no.ntnu.idata2001.mappe29.PathsApp;
import no.ntnu.idata2001.mappe29.model.Passage;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.goals.GoldGoal;
import no.ntnu.idata2001.mappe29.model.goals.HealthGoal;
import no.ntnu.idata2001.mappe29.model.goals.ItemGoal;
import no.ntnu.idata2001.mappe29.model.goals.ScoreGoal;
import no.ntnu.idata2001.mappe29.userinterface.screens.Screen;

/**
 * Represents a controller that is responsible for managing the flow of data with user interaction
 * in the Goals Screen of the Paths Application.
 *
 * @author Tiago Brito.
 * @version 2023.05.19.
 */
public class GoalsScreenController extends Controller {

  private Story storySelected;

  /**
   * Creates an instance of GoalsScreenController.
   *
   * @param pathsApp the PathsApp instance of this application.
   * @throws IllegalArgumentException if PathsApp is null.
   */
  public GoalsScreenController(PathsApp pathsApp) {
    super("goals_screen_controller");
    super.setPathsApp(pathsApp);
    this.storySelected = null;
  }

  /**
   * Sets the story selected of this controller to the specified story selected.
   *
   * @param storySelected the specified selected story.
   * @throws IllegalArgumentException if the specified selected story is null.
   */
  public void setStorySelected(Story storySelected) {
    if (storySelected == null) {
      throw new IllegalArgumentException("Selected story cannot be null");
    }
    this.storySelected = storySelected;
  }

  /**
   * Handles the click on the image 1 in the goals screen.
   */
  public void handleImage1Click() {
    Alert inputGoldDialog = new Alert(Alert.AlertType.NONE);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(inputGoldDialog);
    inputGoldDialog.setTitle("Amount of gold to gain during the story");
    inputGoldDialog.setHeaderText("Amount of gold must be between 1 and 1000");
    inputGoldDialog.setContentText("Amount of gold:");

    TextField textField = new TextField();
    inputGoldDialog.getDialogPane().setContent(textField);

    inputGoldDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
    ButtonType selectedOption = inputGoldDialog.showAndWait().orElse(ButtonType.CANCEL);
    if (selectedOption == ButtonType.OK) {
      String inputData = textField.getText();
      int number;
      try {
        number = Integer.parseInt(inputData);
        if (number < 1 || number > 1000) {
          throw new NumberFormatException();
        }
        GoldGoal goldGoal = new GoldGoal(number);
        Image goldImage = new Image(this.pathsApp.getClass()
            .getResource("/images/icons/gold_icon.png").toExternalForm());
        goldGoal.setImage(goldImage);
        goldGoal.setDescription("Finish the story with " + number + " gold");
        this.pathsApp.getModelManager().getPlayer().getGoals().add(goldGoal);
        this.showSuccessDialog();
        this.showMoreGoalsDialog();
      } catch (NumberFormatException exception) {
        this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Input",
            "Please enter a valid number between 1 and 1000");
      }
    }
  }

  /**
   * Handles the click on the image 2 in the goals screen.
   */
  public void handleImage2Click() {
    Alert inputHealthDialog = new Alert(Alert.AlertType.NONE);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(inputHealthDialog);
    inputHealthDialog.setTitle("Amount of health to finish the story with");
    inputHealthDialog.setHeaderText("Amount of health must be between 1 and 100");
    inputHealthDialog.setContentText("Amount of health:");

    TextField textField = new TextField();
    inputHealthDialog.getDialogPane().setContent(textField);

    inputHealthDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
    ButtonType selectedOption = inputHealthDialog.showAndWait().orElse(ButtonType.CANCEL);
    if (selectedOption == ButtonType.OK) {
      String inputData = textField.getText();
      int number;
      try {
        number = Integer.parseInt(inputData);
        if (number < 1 || number > 100) {
          throw new NumberFormatException();
        }
        HealthGoal healthGoal = new HealthGoal(number);
        Image healthImage = new Image(this.pathsApp.getClass()
            .getResource("/images/icons/health_icon.png").toExternalForm());
        healthGoal.setImage(healthImage);
        healthGoal.setDescription("Finish the story with " + number + " health");
        this.pathsApp.getModelManager().getPlayer().getGoals().add(healthGoal);
        this.showSuccessDialog();
        this.showMoreGoalsDialog();
      } catch (NumberFormatException exception) {
        this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Input",
            "Please enter a valid number between 1 and 100");
      }
    }
  }

  /**
   * Handles the click on the image 3 in the goals screen.
   */
  public void handleImage3Click() {
    Alert inputItemDialog = new Alert(Alert.AlertType.NONE);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(inputItemDialog);
    inputItemDialog.setTitle("Item to get during the story");
    inputItemDialog.setHeaderText("Item to get cannot be empty");
    inputItemDialog.setContentText("Item to get:");

    TextField textField = new TextField();
    inputItemDialog.getDialogPane().setContent(textField);

    inputItemDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
    ButtonType selectedOption = inputItemDialog.showAndWait().orElse(ButtonType.CANCEL);
    if (selectedOption == ButtonType.OK) {
      String inputData = textField.getText();
      try {
        if (inputData == null || inputData.isBlank()) {
          throw new IllegalArgumentException();
        }
        ItemGoal itemGoal = new ItemGoal(inputData);
        Image inventoryImage = new Image(this.pathsApp.getClass()
            .getResource("/images/icons/inventory_icon.png").toExternalForm());
        itemGoal.setImage(inventoryImage);
        itemGoal.setDescription("Finish the story with " + inputData);
        this.pathsApp.getModelManager().getPlayer().getGoals().add(itemGoal);
        this.showSuccessDialog();
        this.showMoreGoalsDialog();
      } catch (IllegalArgumentException exception) {
        this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Input",
            "Please enter an item to get during the story");
      }
    }
  }

  /**
   * Handles the click on the image 4 in the goals screen.
   */
  public void handleImage4Click() {
    Alert inputScoreDialog = new Alert(Alert.AlertType.NONE);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(inputScoreDialog);
    inputScoreDialog.setTitle("Amount of score to finish the story with");
    inputScoreDialog.setHeaderText("Amount of score must be between 1 and 500");
    inputScoreDialog.setContentText("Amount of score:");

    TextField textField = new TextField();
    inputScoreDialog.getDialogPane().setContent(textField);

    inputScoreDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
    ButtonType selectedOption = inputScoreDialog.showAndWait().orElse(ButtonType.CANCEL);
    if (selectedOption == ButtonType.OK) {
      String inputData = textField.getText();
      int number;
      try {
        number = Integer.parseInt(inputData);
        if (number < 1 || number > 500) {
          throw new NumberFormatException();
        }
        ScoreGoal scoreGoal = new ScoreGoal(number);
        Image scoreImage = new Image(this.pathsApp.getClass()
            .getResource("/images/icons/score_icon.png").toExternalForm());
        scoreGoal.setImage(scoreImage);
        scoreGoal.setDescription("Finish this story with " + number + " score points");
        this.pathsApp.getModelManager().getPlayer().getGoals().add(scoreGoal);
        this.showSuccessDialog();
        this.showMoreGoalsDialog();
      } catch (NumberFormatException exception) {
        this.pathsApp.getUserInterfaceManager().showErrorDialog("Invalid Input",
            "Please enter a valid number between 1 and 500");
      }
    }
  }

  /**
   * Handles the click on the button in the goals screen.
   */
  public void handleButtonClick() {
    Screen storyIntroductionScreen = this.pathsApp.getUserInterfaceManager()
        .getScreen(this.storySelected.getTitle() + "_story_introduction_screen");
    this.pathsApp.getUserInterfaceManager()
        .setPrimaryStageScene(storyIntroductionScreen.createScene(this.pathsApp));
  }

  /**
   * Show a dialog that tells the user the goal was created successfully.
   */
  private void showSuccessDialog() {
    Alert successDialog = new Alert(Alert.AlertType.INFORMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(successDialog);
    successDialog.setTitle("Goal Created");
    successDialog.setHeaderText("The goal was created successfully!");
    successDialog.getButtonTypes().setAll(ButtonType.OK);
    successDialog.showAndWait();
  }

  /**
   * Show a dialog that asks the user if he wants to make more goals.
   */
  private void showMoreGoalsDialog() {
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    this.pathsApp.getUserInterfaceManager().addPathsIconToDialog(confirmationDialog);
    confirmationDialog.setTitle("More Goals");
    confirmationDialog.setHeaderText("Do you want to make more goals for this story?");

    confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

    ButtonType selectedOption = confirmationDialog.showAndWait().orElse(ButtonType.YES);
    if (selectedOption == ButtonType.NO) {
      this.pathsApp.getModelManager().getPlayer().resetStats();
      Passage openingPassage = this.storySelected.getOpeningPassage();
      Screen openingPassageScreen = this.pathsApp.getUserInterfaceManager()
          .getScreen(openingPassage.getTitle() + "_passage_screen");
      this.pathsApp.getUserInterfaceManager()
          .setPrimaryStageScene(openingPassageScreen.createScene(this.pathsApp));
    }
  }


}
