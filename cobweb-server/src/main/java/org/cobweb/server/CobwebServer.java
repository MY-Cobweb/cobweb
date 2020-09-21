package org.cobweb.server;

import io.netty.channel.EventLoopGroup;

/**
 * Cobweb Server
 *
 * @author meijie
 * @since 0.0.1
 */
public class CobwebServer implements LifeCycle {

  private EventLoopGroup workGroup;
  private EventLoopGroup bossGroup;

  @Override
  public void close() throws Exception {
    stop();
  }

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }
}
