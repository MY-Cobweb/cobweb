package org.cobweb.core.exception;

/**
 * Cobweb Base Exception
 *
 * @author meijie
 */
public class CobwebException extends Exception {

  public CobwebException() {
    super();
  }

  public CobwebException(String message) {
    super(message);
  }

  public CobwebException(String message, Throwable cause) {
    super(message, cause);
  }

  public CobwebException(Throwable cause) {
    super(cause);
  }

  protected CobwebException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
