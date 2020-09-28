package org.cobweb.core;

/**
 * Handler handle event from dispatcher
 *
 * @author meijie
 * @since 0.0.1
 */
public interface Handler<T> {

  void handle(T event);
}
