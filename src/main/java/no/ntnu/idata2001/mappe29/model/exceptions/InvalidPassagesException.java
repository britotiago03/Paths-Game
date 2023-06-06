package no.ntnu.idata2001.mappe29.model.exceptions;

/**
 * Represents an exception caused when invalid passages are provided.
 *
 * @author Tiago Brito.
 * @version 2023.05.14.
 */
public class InvalidPassagesException extends Exception {
  /**
   * Creates an instance of InvalidPassagesException.
   *
   * @param message the specified message of the exception.
   */
  public InvalidPassagesException(String message) {
    super(message);
  }
}
