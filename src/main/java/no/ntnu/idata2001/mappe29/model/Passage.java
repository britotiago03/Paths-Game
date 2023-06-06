package no.ntnu.idata2001.mappe29.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import no.ntnu.idata2001.mappe29.model.exceptions.InvalidLinksException;

/**
 * Represents a smaller part of a story, a paragraph if you will. It is possible to go from one
 * passage to another using a link. Two passages are considered equal if they have the same content
 * and the same links.
 *
 * @author Tiago Brito.
 * @version 2023.02.23
 * @see Link
 */
public class Passage {
  private String title;
  private String content;
  private final List<Link> links;
  private boolean endingPassage;
  private Image backgroundImage;

  /**
   * Creates an instance of Passage.
   *
   * @param title   the specified title of this passage.
   * @param content the specified content of this passage.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public Passage(String title, String content) {
    this.setTitle(title);
    this.setContent(content);
    this.links = new ArrayList<>();
    this.endingPassage = false;
    this.backgroundImage = null;
  }

  /**
   * Gets the title of this passage.
   *
   * @return the title of this passage.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the title of this passage to the specified title.
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
   * Gets the content of this passage.
   *
   * @return the content of this passage.
   */
  public String getContent() {
    return this.content;
  }

  /**
   * Sets the content of this passage to the specified content.
   *
   * @param content the specified content.
   * @throws IllegalArgumentException if the specified content is null or blank.
   */
  private void setContent(String content) {
    if (content == null) {
      throw new IllegalArgumentException("Content cannot be null");
    } else if (content.isBlank()) {
      throw new IllegalArgumentException("Content cannot be blank");
    }
    this.content = content;

  }

  /**
   * Sets the background image of this passage to the specified background image.
   *
   * @param backgroundImage the specified background image.
   * @throws IllegalArgumentException if the specified background image is null.
   */
  public void setBackgroundImage(Image backgroundImage) {
    if (backgroundImage == null) {
      throw new IllegalArgumentException("Background image cannot be null");
    }
    this.backgroundImage = backgroundImage;
  }

  /**
   * Gets the background image of this passage.
   *
   * @return the background image of this passage.
   */
  public Image getBackgroundImage() {
    return this.backgroundImage;
  }

  /**
   * Adds a link to the links collection of this passage.
   *
   * @param link the link to be added.
   * @return true if the link was added successfully, false otherwise.
   * @throws IllegalArgumentException if the link to be added is {@code null}.
   */
  public boolean addLink(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("Link cannot be null");
    }
    return this.links.add(link);
  }

  /**
   * Adds multiple links to the links collection of this passage.
   *
   * @param links the links to be added.
   * @throws IllegalArgumentException if links are null or less than 2 links are provided.
   * @throws InvalidLinksException    if one of the links is null.
   */
  public void addAllLinks(Link... links) throws InvalidLinksException {
    if (links == null) {
      throw new IllegalArgumentException("Links cannot be null");
    } else if (links.length < 2) {
      throw new IllegalArgumentException("Number of links provided cannot be less than 2");
    }
    for (Link link : links) {
      if (link == null) {
        throw new InvalidLinksException("One of the links is null");
      }
    }
    for (Link link : links) {
      this.addLink(link);
    }
  }

  /**
   * Gets all the links of this passage.
   *
   * @return all the links of this passage as a list.
   */
  public List<Link> getLinks() {
    return this.links;
  }

  /**
   * Checks if this passage is an ending passage.
   *
   * @return true if this passage is an ending passage, false otherwise.
   */
  public boolean isEndingPassage() {
    return this.endingPassage;
  }

  /**
   * Sets the ending passage boolean of this passage to the specified ending passage boolean.
   *
   * @param endingPassage the specified ending passage boolean.
   */
  public void setEndingPassage(boolean endingPassage) {
    this.endingPassage = endingPassage;
  }

  /**
   * Checks if this passage has links.
   *
   * @return true if this passage has links, false otherwise.
   */
  public boolean hasLinks() {
    return !this.links.isEmpty();
  }

  /**
   * Removes the specified link from the links of this passage.
   *
   * @param link the specified link.
   * @throws IllegalArgumentException if the specified link is null or not in the links of this
   *                                  passage.
   */
  public void removeLink(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("The specified link cannot be null");
    } else if (!this.links.contains(link)) {
      throw new IllegalArgumentException("The specified link is not in the links of this passage");
    }
    this.links.remove(link);
  }

  @Override
  public String toString() {
    return "Passage{title='" + this.title + "', content='" + this.content + "', numberOfLinks="
        + this.links.size() + "}";
  }

  /**
   * Checks if the specified object is the same passage or is equal to this passage. Two passages
   * are equal if they have the same content and the same links.
   *
   * @param object the specified object.
   * @return {@code true} if the specified object is the same passage or is equal to this passage,
   *     {@code false} otherwise.
   */
  @Override
  public boolean equals(Object object) {
    boolean equal = false;
    if (this == object) {
      equal = true;
    } else if (object instanceof Passage) {
      Passage passage = (Passage) object;
      if (passage.getContent().equals(this.content) && passage.getLinks().equals(this.links)) {
        equal = true;
      }
    }
    return equal;
  }

  /**
   * Hashcode technique from Effective Java by Joshua Bloch.
   */
  @Override
  public int hashCode() {
    int result = 17;
    result = (result * 37) + this.content.hashCode();
    for (Object link : this.links) {
      result = (result * 37) + link.hashCode();
    }

    return result;
  }
}
