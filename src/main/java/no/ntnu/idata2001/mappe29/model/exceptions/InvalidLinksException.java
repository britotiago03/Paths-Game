package no.ntnu.idata2001.mappe29.model.exceptions;

/**
 * Represents an exception that is caused when invalid links are provided.
 *
 * @author Tiago Brito.
 * @version 2023.05.14
 */
public class InvalidLinksException extends Exception {
  /**
   * Creates an instance of InvalidLinksException.
   *
   * @param message the specified message of the exception.
   */
  public InvalidLinksException(String message) {
    super(message);
  }
}
