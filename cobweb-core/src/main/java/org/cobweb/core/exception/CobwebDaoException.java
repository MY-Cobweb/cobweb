package org.cobweb.core.exception;

/**
 * Exception for dao
 *
 * @author meijie
 * @since 0.0.1
 */
public class CobwebDaoException extends CobwebException {

  public CobwebDaoException() {
    super();
  }

  public CobwebDaoException(String message) {
    super(message);
  }

  public CobwebDaoException(String message, Throwable cause) {
    super(message, cause);
  }

  public CobwebDaoException(Throwable cause) {
    super(cause);
  }

  protected CobwebDaoException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
