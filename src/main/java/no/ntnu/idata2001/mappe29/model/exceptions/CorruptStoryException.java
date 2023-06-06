package no.ntnu.idata2001.mappe29.model.exceptions;

/**
 * Represents an exception that is caused when a story is corrupt.
 *
 * @author Tiago Brito.
 * @version 2023.05.16.
 */
public class CorruptStoryException extends Exception {
  /**
   * Creates an instance of CorruptStoryException.
   *
   * @param message the specified message of the exception.
   */
  public CorruptStoryException(String message) {
    super(message);
  }
}
