package no.ntnu.idata2001.mappe29.model.exceptions;

/**
 * Represents an exception that is caused when the link string to be parsed has no links.
 *
 * @author Tiago Brito.
 * @version 2023.05.15.
 */
public class StringHasNoLinksException extends Exception {
  /**
   * Creates an instance of StringHasNoLinksException.
   *
   * @param message the specified message of the exception.
   */
  public StringHasNoLinksException(String message) {
    super(message);
  }
}
