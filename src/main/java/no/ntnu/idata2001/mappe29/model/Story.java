package no.ntnu.idata2001.mappe29.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import no.ntnu.idata2001.mappe29.model.exceptions.InvalidPassagesException;
import no.ntnu.idata2001.mappe29.model.exceptions.NoBrokenLinksException;

/**
 * Represents a story that is an interactive, non-linear narrative consisting of a collection of
 * passages.
 *
 * @author Tiago Brito
 * @version 2023.02.23
 * @see Passage
 * @see Link
 */
public class Story {
  private String title;
  private final Map<Link, Passage> passages;
  private Passage openingPassage;
  private Image image;
  private Image backgroundImage;
  private Image darkBackgroundImage;
  private String intro1Text;
  private String intro2Text;

  /**
   * Creates an instance of Story.
   *
   * @param title          the specified title of this story.
   * @param openingPassage the specified opening passage of this story.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public Story(String title, Passage openingPassage) {
    this.setTitle(title);
    this.passages = new HashMap<>();
    this.setOpeningPassage(openingPassage);
    this.image = null;
    this.backgroundImage = null;
    this.darkBackgroundImage = null;
    this.intro1Text = "";
    this.intro2Text = "";
    this.addPassage(this.getOpeningPassage());
  }

  /**
   * Gets the title of this story.
   *
   * @return the title of this story.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the title of this story to the specified title.
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
   * Gets the opening passage of this story.
   *
   * @return the opening passage of this story.
   */
  public Passage getOpeningPassage() {
    return this.openingPassage;
  }

  /**
   * Sets the opening passage of this story to the specified opening passage.
   *
   * @param openingPassage the specified opening passage.
   * @throws IllegalArgumentException if the specified opening passage is null or has no links.
   */
  private void setOpeningPassage(Passage openingPassage) {
    if (openingPassage == null) {
      throw new IllegalArgumentException("Opening passage cannot be null");
    } else if (!openingPassage.hasLinks()) {
      throw new IllegalArgumentException("Opening passage must have links");
    }
    this.openingPassage = openingPassage;
  }

  /**
   * Sets the image of this story to the specified image.
   *
   * @param image the specified image.
   * @throws IllegalArgumentException if the specified image is null.
   */
  public void setImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Background image cannot be null");
    }
    this.image = image;
  }

  /**
   * Gets the image of this story.
   *
   * @return the image of this story.
   */
  public Image getImage() {
    return this.image;
  }

  /**
   * Sets the background image of this story to the specified background image.
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
   * Gets the background image of this story.
   *
   * @return the background image of this story.
   */
  public Image getBackgroundImage() {
    return this.backgroundImage;
  }

  /**
   * Sets the dark background image of this story to the specified dark background image.
   *
   * @param darkBackgroundImage the specified dark background image.
   * @throws IllegalArgumentException if the specified dark background image is null.
   */
  public void setDarkBackgroundImage(Image darkBackgroundImage) {
    if (darkBackgroundImage == null) {
      throw new IllegalArgumentException("Dark background image cannot be null");
    }
    this.darkBackgroundImage = darkBackgroundImage;
  }

  /**
   * Gets the dark background image of this story.
   *
   * @return the dark background image of this story.
   */
  public Image getDarkBackgroundImage() {
    return this.darkBackgroundImage;
  }

  /**
   * Gets the intro 1 text of this story.
   *
   * @return the intro 1 text of this story.
   */
  public String getIntro1Text() {
    return this.intro1Text;
  }

  /**
   * Sets the intro 1 text of this story to the specified intro 1 text.
   *
   * @param intro1Text the specified intro 1 text.
   * @throws IllegalArgumentException if the specified intro 1 text is null or blank.
   */
  public void setIntro1Text(String intro1Text) {
    if (intro1Text == null) {
      throw new IllegalArgumentException("Intro 1 text cannot be null");
    } else if (intro1Text.isBlank()) {
      throw new IllegalArgumentException("Intro 1 text cannot be blank");
    }
    this.intro1Text = intro1Text;
  }

  /**
   * Gets the intro 2 text of this story.
   *
   * @return the intro 2 text of this story.
   */
  public String getIntro2Text() {
    return this.intro2Text;
  }

  /**
   * Sets the intro 2 text of this story to the specified intro 2 text.
   *
   * @param intro2Text the specified intro 2 text.
   * @throws IllegalArgumentException if the specified intro 2 text is null or blank.
   */
  public void setIntro2Text(String intro2Text) {
    if (intro2Text == null) {
      throw new IllegalArgumentException("Intro 2 text cannot be null");
    } else if (intro2Text.isBlank()) {
      throw new IllegalArgumentException("Intro 2 text cannot be blank");
    }
    this.intro2Text = intro2Text;
  }

  /**
   * Adds the specified passage to the passages of this story.
   *
   * @param passage the specified passage to be added.
   * @throws IllegalArgumentException if the specified passage to be added is null.
   */
  public void addPassage(Passage passage) {
    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null");
    }
    Link link = this.createLink(passage);
    this.passages.put(link, passage);
  }

  /**
   * Adds all specified passages to the passages of this story.
   *
   * @param passages the specified passages.
   * @throws IllegalArgumentException if the passages are null or less than 2 passages are provided.
   * @throws InvalidPassagesException if one of the passages is null.
   */
  public void addAllPassages(Passage... passages) throws InvalidPassagesException {
    if (passages == null) {
      throw new IllegalArgumentException("Passages cannot be null");
    } else if (passages.length < 2) {
      throw new IllegalArgumentException("Number of passages cannot be less than 2");
    }
    for (Passage passage : passages) {
      if (passage == null) {
        throw new InvalidPassagesException("One of the passages is null");
      }
    }
    for (Passage passage : passages) {
      this.addPassage(passage);
    }
  }

  /**
   * Gets the passage in this story that the specified link links to.
   *
   * @param link the specified link.
   * @return the passage that the specified link links to.
   * @throws IllegalArgumentException if the specified link is null or is not in the passages of
   *                                  this story.
   */
  public Passage getPassage(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("Link cannot be null");
    } else if (!this.doesLinkReferToPassageInPassages(link)) {
      throw new IllegalArgumentException("The specified link is not in the passages of this story");
    }
    return this.passages.get(link);
  }

  /**
   * Gets all the passages in this story.
   *
   * @return all the passages in this story.
   */
  public Collection<Passage> getPassages() {
    return this.passages.values();
  }

  /**
   * Creates a link to the specified passage.
   *
   * @param passage the specified passage.
   * @return a {@code Link} to the specified passage.
   */
  private Link createLink(Passage passage) {
    String linkTitle = passage.getTitle();
    String linkReference = passage.getTitle();
    return new Link(linkTitle, linkReference);
  }

  /**
   * Checks if the specified link refers to a passage in the passages of this story.
   *
   * @param link the specified link.
   * @return true if the link refers to a passage in the passages of this story, false otherwise.
   * @throws IllegalArgumentException if the specified link is null.
   */
  public boolean doesLinkReferToPassageInPassages(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("Link cannot be null");
    }
    return !this.passages.values().stream()
        .filter(passage -> passage.getTitle().equals(link.getReference())).toList().isEmpty();
  }

  /**
   * Checks if there are other passages that link to the specified passage.
   *
   * @param passage the specified passage.
   * @return true if the passage has other passages that link to it, false otherwise.
   * @throws IllegalArgumentException if the specified passage is null.
   */
  public boolean doOtherPassagesLinkToPassage(Passage passage) {
    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null");
    }
    return this.passages.values().stream().filter(otherPassage -> otherPassage.hasLinks())
        .flatMap(otherPassage -> otherPassage.getLinks().stream())
        .anyMatch(link -> link.getReference().equals(passage.getTitle()));
  }

  /**
   * Removes a passage in the passages of this story that the specified link links to.
   *
   * @param link the specified link.
   * @throws IllegalArgumentException if the specified link is null, is not in the passages of
   *                                  this story, or the passage that it links to has other
   *                                  passages linking to it.
   */
  public void removePassage(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("Link cannot be null");
    } else if (!this.doesLinkReferToPassageInPassages(link)) {
      throw new IllegalArgumentException("The specified link is not in the passages of this story");
    }
    Passage passage = this.getPassage(link);
    if (this.doOtherPassagesLinkToPassage(passage)) {
      throw new IllegalArgumentException("Cannot delete a passage when there are other passages "
          + "that link to it");
    } else {
      this.passages.remove(link);
    }
  }

  /**
   * Gets all the broken links in the passages of this story.
   *
   * @return a {@code List} of all the broken links.
   * @throws NoBrokenLinksException if there are no broken links in the passages of this story.
   */
  public List<Link> getBrokenLinks() throws NoBrokenLinksException {
    List<Link> brokenLinks = new ArrayList<>();
    for (Link link : this.passages.keySet()) {
      Passage passage = this.getPassage(link);
      passage.getLinks().stream()
          .filter(passageLink -> !this.doesLinkReferToPassageInPassages(passageLink))
          .forEach(passageLink -> brokenLinks.add(passageLink));
    }
    if (brokenLinks.isEmpty()) {
      throw new NoBrokenLinksException("No broken links were found");
    }

    return brokenLinks;
  }

  /**
   * Checks if this story has broken links.
   *
   * @return true if this story has broken links, false otherwise.
   */
  public boolean hasBrokenLinks() {
    boolean hasBrokenLinks = true;
    try {
      this.getBrokenLinks();
    } catch (NoBrokenLinksException exception) {
      hasBrokenLinks = false;
    }

    return hasBrokenLinks;
  }
}
