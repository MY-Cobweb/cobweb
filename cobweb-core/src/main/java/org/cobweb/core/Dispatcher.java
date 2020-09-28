package org.cobweb.core;

import org.cobweb.core.constant.RequestType;

/**
 * Dispatcher dispatch event to target handler.
 *
 * @author meijie
 * @since 0.0.1
 */
public interface Dispatcher<T> {

  /**
   * Dispatcher the event to target handler
   *
   * @param t
   */
  void dispatch(T t);

  /**
   * Registry the handler to process target event.
   *
   * @param type
   * @param handler
   */
  void registry(RequestType type, Handler handler);

  /**
   * UnRegistry the handler.
   *
   * @param type
   * @param handler
   */
  void unRegistry(RequestType type, Handler handler);
}
