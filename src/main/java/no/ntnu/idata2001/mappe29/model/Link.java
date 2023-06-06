package no.ntnu.idata2001.mappe29.model;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idata2001.mappe29.model.actions.Action;

/**
 * Represents a link that makes it possible to go from one passage to another. Links bind together
 * different parts of a story. Two links are considered equal if they refer to the same passage
 * (have the same reference).
 */
public class Link {
  private String text;
  private String reference;
  private List<Action> actions;

  /**
   * Creates an instance of Link.
   *
   * @param text      a descriptive text that indicates a choice or action in a story. This text is
   *                  the part of the link that will be visible to the player.
   * @param reference a string that uniquely identifies a passage (part of a story). In practice,
   *                  this will be the title of the passage you want to refer to.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public Link(String text, String reference) {
    this.setText(text);
    this.setReference(reference);
    this.actions = new ArrayList<>();
  }

  /**
   * Creates an instance of Link.
   *
   * @param text      a descriptive text that indicates a choice or action in a story. This text is
   *                  the part of the link that will be visible to the player.
   * @param reference a string that uniquely identifies a passage (part of a story). In practice,
   *                  this will be the title of the passage you want to refer to.
   * @param actions   the specified actions of this link.
   * @throws IllegalArgumentException if invalid parameters are provided.
   */
  public Link(String text, String reference, List<Action> actions) {
    this.setText(text);
    this.setReference(reference);
    this.setActions(actions);
  }

  /**
   * Returns a descriptive text that indicates a choice or action in a story.
   *
   * @return the descriptive text.
   */
  public String getText() {
    return this.text;
  }

  /**
   * Sets the text of the link to the specified text.
   *
   * @param text the specified text.
   * @throws IllegalArgumentException if text is invalid.
   */
  private void setText(String text) {
    if (text == null) {
      throw new IllegalArgumentException("Text cannot be null");
    } else if (text.isBlank()) {
      throw new IllegalArgumentException("Text cannot be blank");
    }
    this.text = text;
  }

  /**
   * Returns the reference string that uniquely identifies a passage (part of a story).
   *
   * @return the reference string that uniquely identifies a passage.
   */
  public String getReference() {
    return this.reference;
  }

  /**
   * Sets the reference of the link to the specified reference.
   *
   * @param reference the specified reference.
   * @throws IllegalArgumentException if reference is invalid.
   */
  private void setReference(String reference) {
    if (reference == null) {
      throw new IllegalArgumentException("Reference cannot be null");
    } else if (reference.isBlank()) {
      throw new IllegalArgumentException("Reference cannot be blank");
    }
    this.reference = reference;
  }

  /**
   * Adds an action to the actions list.
   *
   * @param action the action to be added.
   * @throws IllegalArgumentException if action is null.
   */
  public void addAction(Action action) {
    if (action == null) {
      throw new IllegalArgumentException("Action cannot be null");
    }
    this.actions.add(action);
  }

  /**
   * Returns the list of special objects that make it possible to influence the characteristics of a
   * player.
   *
   * @return the list of special objects that make it possible to influence the characteristics of a
   *     player.
   */
  public List<Action> getActions() {
    return this.actions;
  }

  /**
   * Sets the actions of this link to the specified actions.
   *
   * @param actions the specified actions.
   * @throws IllegalArgumentException if the specified actions are null or empty.
   */
  private void setActions(List<Action> actions) {
    if (actions == null) {
      throw new IllegalArgumentException("Actions cannot be null");
    } else if (actions.isEmpty()) {
      throw new IllegalArgumentException("Actions cannot be empty");
    }
    this.actions = actions;
  }

  @Override
  public String toString() {
    return "Link{text='" + this.text + "', reference='" + this.reference + "', "
        + "numberOfActions=" + this.actions.size() + "}";
  }

  /**
   * Checks if the specified object is the same link or is equal to this link. Two links are equal
   * if they have the same reference.
   *
   * @param object the specified object.
   * @return {@code true} if the specified object is the same link or is equal to this link,
   *     {@code false} otherwise.
   */
  @Override
  public boolean equals(Object object) {
    boolean equal = false;
    if (this == object) {
      equal = true;
    } else if (object instanceof Link) {
      Link link = (Link) object;
      if (link.getReference().equals(this.reference)) {
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
    result = (result * 37) + this.reference.hashCode();

    return result;
  }
}
