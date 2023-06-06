package no.ntnu.idata2001.mappe29.model.exceptions;

/**
 * Represents an exception that is caused when the model components initialization fails.
 */
public class ModelComponentsInitializationException extends Exception {
  /**
   * Creates an instance of ModelComponentsInitializationException.
   *
   * @param message the specified message of the exception.
   */
  public ModelComponentsInitializationException(String message) {
    super(message);
  }
}
