package no.ntnu.idata2001.mappe29.model.exceptions;

/**
 * Represents an exception that is caused when there are no broken links.
 *
 * @author Tiago Brito.
 * @version 2023.05.12.
 */
public class NoBrokenLinksException extends Exception {
  /**
   * Creates an instance of NoBrokenLinksException.
   *
   * @param message the specified message of the exception.
   */
  public NoBrokenLinksException(String message) {
    super(message);
  }
}
