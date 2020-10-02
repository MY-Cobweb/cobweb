package org.cobweb.core.exception;

/**
 * @author meijie
 * @since 0.0.1
 */
public class CobwebCodecException extends CobwebException {

  public CobwebCodecException() {
  }

  public CobwebCodecException(String message) {
    super(message);
  }

  public CobwebCodecException(String message, Throwable cause) {
    super(message, cause);
  }

  public CobwebCodecException(Throwable cause) {
    super(cause);
  }

  public CobwebCodecException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
