package no.ntnu.idata2001.mappe29.model;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idata2001.mappe29.model.goals.Goal;

/**
 * Represents a player with unique attributes that can be affected during a story. A player has the
 * following attributes:
 * <ul>
 *   <li>health</li>
 *   <li>score</li>
 *   <li>gold</li>
 *   <li>inventory</li>
 *   <li>goals</li>
 * </ul>
 *
 * <p>Important notes on the player's attributes:
 * <ol>
 *   <li>The health attribute cannot be less than 0 or greater than 100.</li>
 *   <li>The score attribute cannot be less than 0.</li>
 *   <li>The gold attribute has no restrictions. It can be negative, which means the player is in
 *   debt.</li>
 * </ol>
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 */
public class Player {
  private String name;
  private int health;
  private int score;
  private int gold;
  private List<String> inventory;
  private List<Goal> goals;

  /**
   * Private constructor to force use of PlayerBuilder.
   */
  private Player() {

  }

  /**
   * Gets the name of this player.
   *
   * @return the name of this player.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the goals of this player.
   *
   * @return the goals of this player.
   */
  public List<Goal> getGoals() {
    return this.goals;
  }

  /**
   * Sets the goals of this player to the specified goals.
   *
   * @param goals the specified goals.
   * @throws IllegalArgumentException if the specified goals are null or empty.
   */
  public void setGoals(List<Goal> goals) {
    if (goals == null) {
      throw new IllegalArgumentException("Goals cannot be null");
    }
    this.goals = goals;
  }

  /**
   * Adds the specified health to this player's health attribute. If the new health of the player
   * becomes over 100, the new health of the player becomes 100.
   *
   * @param health the specified health to be added.
   * @throws IllegalArgumentException if the specified health to be added is less than 1.
   */
  public void addHealth(int health) {
    if (health < 1) {
      throw new IllegalArgumentException("Health to be added cannot be less than 1");
    }
    int newHealth = this.getHealth() + health;
    if (newHealth > 100) {
      this.setHealth(100);
    } else {
      this.setHealth(newHealth);
    }
  }

  /**
   * Reduces the specified health from this player's health attribute. If the new health of the
   * player becomes less than 0, the new health of the player becomes 0.
   *
   * @param health the specified health to be reduced.
   * @throws IllegalArgumentException if the specified health to be reduced is less than 1.
   */
  public void reduceHealth(int health) {
    if (health < 1) {
      throw new IllegalArgumentException("Health to be reduced cannot be less than 1");
    }
    int newHealth = this.getHealth() - health;
    if (newHealth < 0) {
      this.setHealth(0);
    } else {
      this.setHealth(newHealth);
    }
  }

  /**
   * Gets the health of this player.
   *
   * @return the health of this player.
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Sets the health of this player to the specified health.
   *
   * @param health the specified health.
   * @throws IllegalArgumentException if the specified health is less than 0 or greater than 100.
   */
  private void setHealth(int health) {
    if (health < 0) {
      throw new IllegalArgumentException("Health attribute cannot be less than 0");
    } else if (health > 100) {
      throw new IllegalArgumentException("Health attribute cannot be greater than 100");
    }
    this.health = health;
  }

  /**
   * Adds the specified points to this player's score attribute.
   *
   * @param points the specified points to be added.
   * @throws IllegalArgumentException if the specified points to be added are less than 1.
   */
  public void addScore(int points) {
    if (points < 1) {
      throw new IllegalArgumentException("Points to be added cannot be less than 1");
    }
    int newScore = this.getScore() + points;
    this.setScore(newScore);
  }

  /**
   * Reduces the specified points from this player's score attribute. If the new score of the
   * player becomes less than 0, the new score of the player becomes 0.
   *
   * @param points the specified points to be reduced.
   * @throws IllegalArgumentException if the specified points to be reduced are less than 1.
   */
  public void reduceScore(int points) {
    if (points < 1) {
      throw new IllegalArgumentException("Points to be reduced cannot be less than 1");
    }
    int newScore = this.getScore() - points;
    if (newScore < 0) {
      this.setScore(0);
    } else {
      this.setScore(newScore);
    }
  }

  /**
   * Gets the score of this player.
   *
   * @return the score of this player.
   */
  public int getScore() {
    return this.score;
  }

  /**
   * Sets the score of this player to the specified score.
   *
   * @param score the specified score.
   * @throws IllegalArgumentException if the specified score is less than 0.
   */
  private void setScore(int score) {
    if (score < 0) {
      throw new IllegalArgumentException("Score attribute cannot be less than 0");
    }
    this.score = score;
  }

  /**
   * Adds the specified gold to this player's gold attribute.
   *
   * @param gold the specified gold to be added.
   * @throws IllegalArgumentException if the specified gold to be added is less than 1.
   */
  public void addGold(int gold) {
    if (gold < 1) {
      throw new IllegalArgumentException("Gold to be added cannot be less than 1");
    }
    int newGold = this.getGold() + gold;
    this.setGold(newGold);
  }

  /**
   * Reduces the specified gold from this player's gold attribute.
   *
   * @param gold the specified gold to be reduced.
   * @throws IllegalArgumentException if the specified gold to be reduced is less than 1.
   */
  public void reduceGold(int gold) {
    if (gold < 1) {
      throw new IllegalArgumentException("Gold to be reduced cannot be less than 1");
    }
    int newGold = this.getGold() - gold;
    this.setGold(newGold);
  }

  /**
   * Gets the gold of this player.
   *
   * @return the gold of this player.
   */
  public int getGold() {
    return this.gold;
  }

  /**
   * Sets the gold of this player to the specified gold.
   *
   * @param gold the specified gold.
   */
  private void setGold(int gold) {
    this.gold = gold;
  }

  /**
   * Adds the specified item to this player's inventory.
   *
   * @param item the specified item to be added.
   * @throws IllegalArgumentException if the specified item is null, blank, or is already in the
   *                                  inventory.
   */
  public void addToInventory(String item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    } else if (item.isBlank()) {
      throw new IllegalArgumentException("Item cannot be blank");
    } else if (this.inventory.contains(item)) {
      throw new IllegalArgumentException("Item is already in the inventory");
    }
    this.inventory.add(item);
  }

  /**
   * Removes the specified item from this player's inventory.
   *
   * @param item the specified item to be removed.
   * @throws IllegalArgumentException if the specified item is null, blank, or not in the inventory.
   */
  public void removeFromInventory(String item) {
    if (item == null) {
      throw new IllegalArgumentException("Item to be removed be null");
    } else if (item.isBlank()) {
      throw new IllegalArgumentException("Item to be removed be blank");
    } else if (!this.inventory.contains(item)) {
      throw new IllegalArgumentException("The inventory does not have the specified item");
    }
    this.inventory.remove(item);
  }

  /**
   * Gets this player's inventory.
   *
   * @return this player's inventory.
   */
  public List<String> getInventory() {
    return this.inventory;
  }

  /**
   * Sets the inventory of this player to the specified inventory.
   *
   * @param inventory the specified inventory.
   * @throws IllegalArgumentException if the specified inventory is null.
   */
  private void setInventory(List<String> inventory) {
    if (inventory == null) {
      throw new IllegalArgumentException("Inventory cannot be null");
    }
    this.inventory = inventory;
  }

  /**
   * Checks if this player is dead, i.g. his health is 0.
   *
   * @return true if the player is dead, false otherwise.
   */
  public boolean isDead() {
    boolean isDead = false;
    if (this.health == 0) {
      isDead = true;
    }
    return isDead;
  }

  /**
   * Checks if this player has the specified item.
   *
   * @param item the specified item.
   * @return true if the player has the specified item, false otherwise.
   */
  public boolean hasItem(String item) {
    return this.inventory.contains(item);
  }

  /**
   * Resets all the stats of this player.
   */
  public void resetStats() {
    this.setScore(0);
    this.setGold(0);
    this.setInventory(new ArrayList<>());
    this.setHealth(100);
  }

  /**
   * Resets the goals of the player.
   */
  public void resetGoals() {
    this.setGoals(new ArrayList<>());
  }

  /**
   * Represents a builder that is responsible for creating different representations of the Player
   * class.
   */
  public static class PlayerBuilder {
    private String name;
    private int health;
    private int score;
    private int gold;
    private List<String> inventory;
    private List<Goal> goals;

    /**
     * Creates an instance of PlayerBuilder.
     */
    public PlayerBuilder() {
      this.name = "Rogelio";
      this.health = 100;
      this.score = 0;
      this.gold = 0;
      this.inventory = new ArrayList<>();
      this.goals = new ArrayList<>();
    }

    /**
     * Sets the name of this player builder to the specified name.
     *
     * @param name the specified name.
     * @throws IllegalArgumentException if the specified name is null or blank.
     */
    private void setName(String name) {
      if (name == null) {
        throw new IllegalArgumentException("Name cannot be null");
      } else if (name.isBlank()) {
        throw new IllegalArgumentException("Name cannot be blank");
      }
      this.name = name;
    }

    /**
     * Sets the name of this player builder to the specified name and gets the player builder.
     *
     * @param name the specified name.
     * @return the player builder.
     * @throws IllegalArgumentException if the specified name is null or blank.
     */
    public PlayerBuilder withName(String name) {
      this.setName(name);
      return this;
    }

    /**
     * Sets the health of this player builder to the specified health.
     *
     * @param health the specified health.
     * @throws IllegalArgumentException if the specified health is less than 0 or greater than 100.
     */
    private void setHealth(int health) {
      if (health < 0) {
        throw new IllegalArgumentException("Health attribute cannot be less than 0");
      } else if (health > 100) {
        throw new IllegalArgumentException("Health attribute cannot be greater than 100");
      }
      this.health = health;
    }

    /**
     * Sets the health of this player builder to the specified health and gets the player builder.
     *
     * @param health the specified health.
     * @return the player builder.
     * @throws IllegalArgumentException if the specified health is less than 0 or greater than 100.
     */
    public PlayerBuilder withHealth(int health) {
      this.setHealth(health);
      return this;
    }

    /**
     * Sets the gold of this player builder to the specified gold and gets this player builder.
     *
     * @param gold the specified gold.
     * @return the player builder.
     */
    public PlayerBuilder withGold(int gold) {
      this.gold = gold;
      return this;
    }

    /**
     * Sets the score of this player builder to the specified score.
     *
     * @param score the specified score.
     * @throws IllegalArgumentException if the specified score is less than 0.
     */
    private void setScore(int score) {
      if (score < 0) {
        throw new IllegalArgumentException("Score attribute cannot be less than 0");
      }
      this.score = score;
    }

    /**
     * Sets the score of this player builder to the specified score and gets this player builder.
     *
     * @param score the specified score.
     * @return the player builder.
     * @throws IllegalArgumentException if the specified score is less than 0.
     */
    public PlayerBuilder withScore(int score) {
      this.setScore(score);
      return this;
    }

    /**
     * Sets the inventory of this player builder to the specified inventory.
     *
     * @param inventory the specified inventory.
     * @throws IllegalArgumentException if the specified inventory is null.
     */
    private void setInventory(List<String> inventory) {
      if (inventory == null) {
        throw new IllegalArgumentException("Inventory cannot be null");
      }
      this.inventory = inventory;
    }

    /**
     * Sets the inventory of this player builder to the specified inventory and gets this
     * player builder.
     *
     * @param inventory the specified inventory.
     * @return this player builder.
     * @throws IllegalArgumentException if the specified inventory is null.
     */
    public PlayerBuilder withInventory(List<String> inventory) {
      this.setInventory(inventory);
      return this;
    }

    /**
     * Sets the goals of this player builder to the specified goals.
     *
     * @param goals the specified goals.
     * @throws IllegalArgumentException if the specified goals are null.
     */
    public void setGoals(List<Goal> goals) {
      if (goals == null) {
        throw new IllegalArgumentException("Goals cannot be null");
      }
      this.goals = goals;
    }

    /**
     * Sets the goals of this player builder to the specified goals and gets this player builder.
     *
     * @param goals the specified goals.
     * @return the player builder.
     * @throws IllegalArgumentException if the specified goals are null.
     */
    public PlayerBuilder withGoals(List<Goal> goals) {
      this.setGoals(goals);
      return this;
    }

    /**
     * Builds a player object with the current fields of the player builder.
     *
     * @return a player object with the current fields of the player builder.
     */
    public Player build() {
      Player player = new Player();
      player.name = this.name;
      player.health = this.health;
      player.gold = this.gold;
      player.score = this.score;
      player.inventory = this.inventory;
      player.goals = this.goals;

      return player;
    }
  }

}
