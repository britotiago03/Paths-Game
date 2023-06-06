package no.ntnu.idata2001.mappe29.model.exceptions;

/**
 * Represents an exception that is caused when a story has broken links.
 */
public class BrokenLinksException extends Exception {
  /**
   * Creates an instance of BrokenLinksException.
   *
   * @param message the specified message of the exception.
   */
  public BrokenLinksException(String message) {
    super(message);
  }
}
