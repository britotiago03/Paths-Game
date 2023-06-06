package no.ntnu.idata2001.mappe29.model.filehandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;
import no.ntnu.idata2001.mappe29.model.Story;
import no.ntnu.idata2001.mappe29.model.exceptions.CorruptStoryException;
import no.ntnu.idata2001.mappe29.model.exceptions.UnknownActionException;

/**
 * Represents a Story File Manager that is responsible for saving stories to .path files and for
 * opening stories from .path files.
 *
 * @author Tiago Brito.
 * @version 2023.05.14.
 */
public class StoryFileManager {
  private final StoryFileFormatter storyFileFormatter;

  public StoryFileManager(StoryFileFormatter storyFileFormatter) {
    this.storyFileFormatter = storyFileFormatter;
  }

  /**
   * Saves the specified story as a .paths file.
   *
   * @param story the specified story.
   * @throws UnknownActionException if a link in the passages of the story contains an unknown
   *                                action.
   * @throws IOException            if an input or output error occurs.
   */
  public void saveStoryToFile(Story story) throws
      UnknownActionException, IOException {
    List<String> storyFileContents = this.storyFileFormatter.formatStory(story);
    String fileName = story.getTitle() + ".paths";

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Story to File");
    fileChooser.setInitialFileName(fileName);
    File file = fileChooser.showSaveDialog(null);

    if (file != null) {
      try (OutputStreamWriter
               writer = new OutputStreamWriter(new FileOutputStream(file),
          StandardCharsets.UTF_8)) {
        for (String line : storyFileContents) {
          writer.write(line + "\n");
        }
      } catch (IOException exception) {
        throw new IOException("Failed to save story to file");
      }
    }
  }

  /**
   * Opens the specified story from a .paths file.
   *
   * @return the specified story.
   * @throws IOException               if an input or output error occurs.
   * @throws CorruptStoryException if a corrupt story file was provided.
   */
  public Story openStoryFromFile() throws IOException, CorruptStoryException {
    Story story = null;

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Story File");
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Story Files", "*.paths"));
    File file = fileChooser.showOpenDialog(null);
    if (file != null) {
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        List<String> fileContents = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
          fileContents.add(line);
        }
        story = this.storyFileFormatter.parseStory(fileContents);
      } catch (IOException exception) {
        throw new IOException("Failed to open story file");
      } catch (CorruptStoryException exception) {
        throw new CorruptStoryException("Corrupt story file was provided");
      }
    }
    if (story == null) {
      throw new IOException("Failed to open story file");
    }
    return story;
  }
}
