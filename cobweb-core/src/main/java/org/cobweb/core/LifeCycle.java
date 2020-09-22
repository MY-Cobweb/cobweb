package org.cobweb.core;

/**
 * Base LifeCycle for a service or a server
 *
 * @author meijie
 * @since 0.0.1
 */
public interface LifeCycle extends AutoCloseable {

  /**
   * init step
   */
  void init();

  /**
   * start the service and entering running status
   */
  void start();

  /**
   * stop the service or the server and entering the stopped status
   */
  void stop();

}
