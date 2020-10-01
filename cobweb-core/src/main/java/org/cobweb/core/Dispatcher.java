package org.cobweb.core;


/**
 * Dispatcher dispatch event to target handler.
 *
 * @author meijie
 * @since 0.0.1
 */
public interface Dispatcher<T, E> {

  /**
   * Dispatcher the event to target handler
   *
   * @param e
   */
  void dispatch(E e);

  /**
   * Registry the handler to process target event.
   *
   * @param type
   * @param handler
   */
  void registry(T type, Handler handler);

  /**
   * UnRegistry the handler.
   *
   * @param type
   * @param handler
   */
  void unRegistry(T type, Handler handler);
}
