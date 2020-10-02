package org.cobweb.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.cobweb.core.LifeCycle;

/**
 * @author meijie
 * @since 0.0.1
 */
public class CobwebClient implements LifeCycle {

  private EventLoopGroup workGroup;
  private ChannelFuture channelFuture;
  private Class socketChannelClass;

  @Override
  public void init() {
    try {
      workGroup = new EpollEventLoopGroup();
      socketChannelClass = EpollSocketChannel.class;
    } catch (Exception e) {
      workGroup = new NioEventLoopGroup();
      socketChannelClass = NioSocketChannel.class;
    }
  }

  @Override
  public void start() {
    channelFuture = new Bootstrap()
        .group(workGroup)
        .channel(socketChannelClass)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .handler(new ChannelInitializer<Channel>() {
          @Override
          protected void initChannel(Channel ch) throws Exception {

          }
        }).connect().syncUninterruptibly();
  }

  @Override
  public void stop() {

  }

  @Override
  public void close() throws Exception {

  }
}
