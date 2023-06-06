package no.ntnu.idata2001.mappe29.model.exceptions;

/**
 * Represents an exception that is caused when an action is unknown.
 *
 * @author Tiago Brito.
 * @version 2023.05.15.
 */
public class UnknownActionException extends Exception {

  /**
   * Creates an instance of UnknownActionException.
   *
   * @param message the specified message of the exception.
   */
  public UnknownActionException(String message) {
    super(message);
  }
}
