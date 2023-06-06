package no.ntnu.idata2001.mappe29.model.filehandling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.image.Image;
import no.ntnu.idata2001.mappe29.model.Link;
import no.ntnu.idata2001.mappe29.model.Passage;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.actions.Action;
import no.ntnu.idata2001.mappe29.model.actions.AddGoldAction;
import no.ntnu.idata2001.mappe29.model.actions.AddHealthAction;
import no.ntnu.idata2001.mappe29.model.actions.AddScoreAction;
import no.ntnu.idata2001.mappe29.model.actions.AddToInventoryAction;
import no.ntnu.idata2001.mappe29.model.actions.ReduceGoldAction;
import no.ntnu.idata2001.mappe29.model.actions.ReduceHealthAction;
import no.ntnu.idata2001.mappe29.model.actions.ReduceScoreAction;
import no.ntnu.idata2001.mappe29.model.actions.RemoveFromInventoryAction;
import no.ntnu.idata2001.mappe29.model.exceptions.CorruptStoryException;
import no.ntnu.idata2001.mappe29.model.exceptions.StringHasNoLinksException;
import no.ntnu.idata2001.mappe29.model.exceptions.UnknownActionException;

/**
 * Represents a Story File Formatter that is responsible for formatting Story instances into
 * a list of strings, and for parsing a list of strings into Story instances.
 *
 * @author Tiago Brito.
 * @version 2023.05.15.
 */
public class StoryFileFormatter {
  /**
   * Formats the specified story into a list of strings of the contents of the story file.
   *
   * @param story the specified story.
   * @return a list of strings of the contents of the story file.
   * @throws IllegalArgumentException if the specified story is null.
   * @throws UnknownActionException   if the action of a link is an unknown action.
   */
  public List<String> formatStory(Story story) throws UnknownActionException {
    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    }
    List<String> storyFileContents = new ArrayList<>();
    String storyTitle = story.getTitle();
    Passage openingPassage = story.getOpeningPassage();
    storyFileContents.add(storyTitle);
    storyFileContents.add("");
    this.addPassageToStoryFileContents(openingPassage, storyFileContents);
    for (Passage passage : story.getPassages()) {
      if (passage != openingPassage) {
        storyFileContents.add("");
        this.addPassageToStoryFileContents(passage, storyFileContents);
      }
    }

    return storyFileContents;
  }

  /**
   * Parses the specified contents of the story file into an instance of Story.
   *
   * @param storyFileContents the specified contents of the story file.
   * @return an instance of Story of the story file.
   * @throws IllegalArgumentException  if the specified contents of the story file are null
   *                                   or empty.
   * @throws CorruptStoryException if the story file provided was corrupt.
   */
  public Story parseStory(List<String> storyFileContents) throws CorruptStoryException {
    if (storyFileContents == null) {
      throw new IllegalArgumentException("Story file contents cannot be null");
    } else if (storyFileContents.isEmpty()) {
      throw new IllegalArgumentException("Story file contents cannot be empty");
    }
    String firstLine = storyFileContents.get(0);
    if (!firstLine.matches("[a-zA-Z ]+")) {
      throw new CorruptStoryException("Corrupt story file was provided");
    }

    Iterator<String> stringIterator = storyFileContents.iterator();
    String storyTitle = stringIterator.next();
    List<Passage> storyPassages = this.createPassagesFromIterator(stringIterator);

    Image passageBackgroundImage = new Image(this.getClass()
        .getResource("/images/backgrounds/default_background.png").toExternalForm());

    Passage openingPassage = storyPassages.get(0);
    openingPassage.setBackgroundImage(passageBackgroundImage);
    Story story = new Story(storyTitle, openingPassage);
    Image storyImage = new Image(this.getClass()
        .getResource("/images/icons/story_icon.png").toExternalForm());
    story.setImage(storyImage);
    Image storyBackgroundImage = new Image(this.getClass()
        .getResource("/images/backgrounds/sunset_adventure.png").toExternalForm());
    story.setBackgroundImage(storyBackgroundImage);
    Image storyDarkBackgroundImage = new Image(this.getClass()
        .getResource("/images/backgrounds/sunset_adventure_dark.png").toExternalForm());
    story.setDarkBackgroundImage(storyDarkBackgroundImage);
    story.setIntro1Text(
        "Embark on an extraordinary quest through mystical realms, shaping your path with choices "
            + "as you navigate puzzles and challenges. ");
    story.setIntro2Text("Encounter mythical creatures, uncover hidden treasures, and"
        + " unlock ancient" + " mysteries in this immersive adventure across"
        + " mesmerizing landscapes.");
    storyPassages.stream().filter(passage -> passage != openingPassage)
        .forEach(passage -> {
          passage.setBackgroundImage(passageBackgroundImage);
          story.addPassage(passage);
        });

    return story;
  }

  /**
   * Creates a list of passages from the specified string iterator.
   *
   * @param stringIterator the specified string iterator.
   * @return the list of passages from the specified string iterator.
   * @throws CorruptStoryException if the story file provided was corrupt.
   */
  private List<Passage> createPassagesFromIterator(Iterator<String> stringIterator)
      throws CorruptStoryException {
    List<Passage> storyPassages = new ArrayList<>();
    while (stringIterator.hasNext()) {
      String line = stringIterator.next();
      if (line.startsWith("::")) {
        String passageTitle = line.substring(2);
        String passageContent = stringIterator.next();
        List<Link> passageLinks = this.createLinksFromIterator(stringIterator);
        Passage passage = new Passage(passageTitle, passageContent);
        if (!passageLinks.isEmpty()) {
          for (Link link : passageLinks) {
            passage.addLink(link);
          }
        }
        storyPassages.add(passage);
      } else if (!line.isBlank()) {
        throw new CorruptStoryException("Corrupt story file was provided");
      }
    }
    return storyPassages;
  }

  /**
   * Creates a list of links from the specified string iterator.
   *
   * @param stringIterator the specified string iterator.
   * @return a list of links from the specified string iterator.
   * @throws CorruptStoryException if the story file provided was corrupt.
   */
  private List<Link> createLinksFromIterator(Iterator<String> stringIterator)
      throws CorruptStoryException {
    boolean linksRead = false;
    List<Link> passageLinks = new ArrayList<>();
    while (stringIterator.hasNext() && !linksRead) {
      String linkLine = stringIterator.next();
      if (linkLine.startsWith("[")) {
        Link link;
        try {
          link = this.parseLinkFromString(linkLine);
          passageLinks.add(link);
        } catch (StringHasNoLinksException exception) {
          linksRead = true;
        }
      } else if (linkLine.isBlank()) {
        linksRead = true;
      } else {
        throw new CorruptStoryException("Corrupt story file was provided");
      }
    }

    return passageLinks;
  }

  /**
   * Parses the specified link string into a Link.
   *
   * @param linkString the specified link string.
   * @return the Link of the specified link string.
   * @throws IllegalArgumentException  if the specified link string is null or blank.
   * @throws StringHasNoLinksException if the specified link string has no links.
   */
  public Link parseLinkFromString(String linkString) throws StringHasNoLinksException,
      CorruptStoryException {
    if (linkString == null) {
      throw new IllegalArgumentException("Link string cannot be null");
    } else if (linkString.isBlank()) {
      throw new IllegalArgumentException("Link string cannot be blank");
    }

    String linkText =
        linkString.substring(linkString.indexOf("[") + 1, linkString.indexOf("]"));
    if (linkText.equals("no_links")) {
      throw new StringHasNoLinksException("String has no links");
    }
    String linkReference =
        linkString.substring(linkString.indexOf("(") + 1, linkString.indexOf(")"));
    List<Action> linkActions = new ArrayList<>();

    int actionsStartIndex = linkString.indexOf("{");
    int actionsEndIndex = linkString.indexOf("}");
    if (actionsStartIndex != -1 && actionsEndIndex != -1
        && actionsStartIndex < actionsEndIndex - 1) {
      String actionsString = linkString.substring(actionsStartIndex + 1, actionsEndIndex);
      String[] actionsArray = actionsString.split(",\\s*");
      for (String actionString : actionsArray) {
        if (!actionString.equals("no_actions")) {
          Action action = this.parseActionFromString(actionString.trim());
          linkActions.add(action);
        }
      }
    }

    Link link;
    if (linkActions.isEmpty()) {
      link = new Link(linkText, linkReference);
    } else {
      link = new Link(linkText, linkReference, linkActions);
    }
    return link;
  }

  /**
   * Parses the specified action string into an Action.
   *
   * @param actionString the action string.
   * @return the Action of the specified action string.
   * @throws IllegalArgumentException  if the specified action string is null or empty.
   * @throws CorruptStoryException if the story file provided is corrupt.
   */
  public Action parseActionFromString(String actionString) throws CorruptStoryException {
    if (actionString == null) {
      throw new IllegalArgumentException("Action string cannot be null");
    } else if (actionString.isBlank()) {
      throw new IllegalArgumentException("Action string cannot be blank");
    }
    Action action;
    String[] actionComponents = actionString.split(" ");
    int value;
    String item;
    switch (actionComponents[0]) {
      case "Add":
        try {
          value = Integer.parseInt(actionComponents[1]);
          switch (actionComponents[2]) {
            case "gold":
              action = new AddGoldAction(value);
              break;

            case "health":
              action = new AddHealthAction(value);
              break;

            case "points":
              action = new AddScoreAction(value);
              break;

            default:
              throw new CorruptStoryException("The story file provided is corrupt");
          }

        } catch (NumberFormatException exception) {
          item = actionComponents[1];
          action = new AddToInventoryAction(item);
        }
        break;

      case "Reduce":
        value = Integer.parseInt(actionComponents[1]);
        switch (actionComponents[2]) {
          case "gold":
            action = new ReduceGoldAction(value);
            break;

          case "health":
            action = new ReduceHealthAction(value);
            break;

          case "points":
            action = new ReduceScoreAction(value);
            break;

          default:
            throw new CorruptStoryException("The story file provided is corrupt");
        }
        break;

      case "Remove":
        action = new RemoveFromInventoryAction(actionComponents[1]);
        break;

      default:
        throw new CorruptStoryException("The story file provided is corrupt");
    }

    return action;
  }

  /**
   * Adds the specified passage to the list of strings of the contents of the story file.
   *
   * @param passage           the specified passage.
   * @param storyFileContents the list of strings of the contents of the story file.
   * @throws UnknownActionException   if the action of a link is an unknown action.
   * @throws IllegalArgumentException if the specified passage or story file contents are null, or
   *                                  story file contents are empty.
   */
  public void addPassageToStoryFileContents(Passage passage, List<String> storyFileContents)
      throws UnknownActionException {
    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null");
    } else if (storyFileContents == null) {
      throw new IllegalArgumentException("Story file contents cannot be null");
    } else if (storyFileContents.isEmpty()) {
      throw new IllegalArgumentException("Story file contents cannot be empty");
    }

    storyFileContents.add("::" + passage.getTitle());
    storyFileContents.add(passage.getContent());
    if (!passage.getLinks().isEmpty()) {
      for (Link link : passage.getLinks()) {
        storyFileContents.add(this.createLinkString(link));
      }
    } else {
      storyFileContents.add("[no_links]");
    }

  }

  /**
   * Creates a link string of the specified link.
   *
   * @param link the specified link.
   * @return the link string of the specified link.
   * @throws UnknownActionException if an action of the specified link is an unknown action.
   */
  private String createLinkString(Link link) throws UnknownActionException {
    StringBuilder linkLine = new StringBuilder();
    linkLine.append("[" + link.getText() + "] (" + link.getReference() + ") {");

    if (!link.getActions().isEmpty()) {
      for (int index = 0; index < link.getActions().size(); index++) {
        Action action = link.getActions().get(index);
        this.addActionToStringBuilder(action, linkLine);

        if (index + 1 < link.getActions().size()) {
          linkLine.append(", ");
        }
      }
    } else {
      linkLine.append("no_actions");
    }

    linkLine.append("}");
    return linkLine.toString();
  }

  /**
   * Adds the specified action to the specified string builder.
   *
   * @param action        the specified action.
   * @param stringBuilder the specified string builder.
   * @throws UnknownActionException   if the specified action is an unknown action.
   * @throws IllegalArgumentException if the specified action or string builder are null.
   */
  public void addActionToStringBuilder(Action action, StringBuilder stringBuilder)
      throws UnknownActionException {
    if (action == null) {
      throw new IllegalArgumentException("Action cannot be null");
    }
    if (stringBuilder == null) {
      throw new IllegalArgumentException("String builder cannot be null");
    }

    if (action instanceof AddGoldAction) {
      AddGoldAction addGoldAction = (AddGoldAction) action;
      stringBuilder.append("Add " + addGoldAction.getGold() + " gold");
    } else if (action instanceof AddHealthAction) {
      AddHealthAction addHealthAction = (AddHealthAction) action;
      stringBuilder.append("Add " + addHealthAction.getHealth() + " health");
    } else if (action instanceof AddScoreAction) {
      AddScoreAction addScoreAction = (AddScoreAction) action;
      stringBuilder.append("Add " + addScoreAction.getPoints() + " points to score");
    } else if (action instanceof AddToInventoryAction) {
      AddToInventoryAction addToInventoryAction = (AddToInventoryAction) action;
      stringBuilder.append("Add " + addToInventoryAction.getItem() + " to inventory");
    } else if (action instanceof ReduceGoldAction) {
      ReduceGoldAction reduceGoldAction = (ReduceGoldAction) action;
      stringBuilder.append("Reduce " + reduceGoldAction.getGold() + " gold");
    } else if (action instanceof ReduceHealthAction) {
      ReduceHealthAction reduceHealthAction = (ReduceHealthAction) action;
      stringBuilder.append("Reduce " + reduceHealthAction.getHealth() + " health");
    } else if (action instanceof ReduceScoreAction) {
      ReduceScoreAction reduceScoreAction = (ReduceScoreAction) action;
      stringBuilder.append("Reduce " + reduceScoreAction.getPoints() + " points from score");
    } else if (action instanceof RemoveFromInventoryAction) {
      RemoveFromInventoryAction removeFromInventoryAction = (RemoveFromInventoryAction) action;
      stringBuilder.append("Remove " + removeFromInventoryAction.getItem() + " from inventory");
    } else {
      throw new UnknownActionException("Unknown action has been specified in a link");
    }

  }
}
