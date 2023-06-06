package no.ntnu.idata2001.mappe29.model;

import java.util.List;
import no.ntnu.idata2001.mappe29.model.exceptions.NoBrokenLinksException;
import no.ntnu.idata2001.mappe29.model.goals.Goal;

/**
 * Represents a facade for a Paths-game. The class connects a player to a story, and has methods
 * for starting and maneuvering in the game.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class Game {
  private Player player;
  private Story story;
  private List<Goal> goals;

  /**
   * Creates an instance of Game.
   *
   * @param player the player of this game.
   * @param story  the story of this game.
   * @param goals  the goals of this game.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public Game(Player player, Story story, List<Goal> goals) {
    this.setPlayer(player);
    this.setStory(story);
    this.setGoals(goals);
  }

  /**
   * Gets the player of this game.
   *
   * @return the player of this game.
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Sets the player of this game to the specified player.
   *
   * @param player the specified player.
   * @throws IllegalArgumentException if the specified player is null.
   */
  private void setPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    this.player = player;
  }

  /**
   * Gets the story of this game.
   *
   * @return the story of this game.
   */
  public Story getStory() {
    return this.story;
  }

  /**
   * Sets the story of this game to the specified story.
   *
   * @param story the specified story.
   * @throws IllegalArgumentException if the specified story is null or contains broken links.
   */
  private void setStory(Story story) {
    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    }
    try {
      story.getBrokenLinks();
      throw new IllegalArgumentException("Story cannot contain broken links");
    } catch (NoBrokenLinksException exception) {
      this.story = story;
    }
  }

  /**
   * Gets the goals of this game.
   *
   * @return the goals of this game.
   */
  public List<Goal> getGoals() {
    return this.goals;
  }

  /**
   * Sets the goals of this game to the specified goals.
   *
   * @param goals the specified goals.
   * @throws IllegalArgumentException if the specified goals are null or empty.
   */
  private void setGoals(List<Goal> goals) {
    if (goals == null) {
      throw new IllegalArgumentException("Goals cannot be null");
    } else if (goals.isEmpty()) {
      throw new IllegalArgumentException("Goals cannot be empty");
    }
    this.goals = goals;
  }

  /**
   * Starts the game by getting the opening passage of the story of this game.
   *
   * @return the opening passage of the story of this game.
   */
  public Passage begin() {
    return this.story.getOpeningPassage();
  }

  /**
   * Goes to the passage that the specified link links to.
   *
   * @param link the specified link.
   * @return the passage that the specified link links to.
   * @throws IllegalArgumentException if the specified link is null or is not in the passages of
   *                                  this story.
   */
  public Passage go(Link link) {
    return this.story.getPassage(link);
  }

}
