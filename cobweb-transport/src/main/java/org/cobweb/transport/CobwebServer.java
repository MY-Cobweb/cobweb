package org.cobweb.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.cobweb.core.LifeCycle;
import org.cobweb.transport.handler.CobwebRequestDispatcher;
import org.cobweb.transport.handler.ProtobufServiceServerCodec;
import org.cobweb.transport.handler.TransportFrameDecoder;
import org.cobweb.transport.handler.TransportFrameEncoder;

/**
 * Cobweb Server
 *
 * @author meijie
 * @since 0.0.1
 */
public class CobwebServer implements LifeCycle {

  private EventLoopGroup workGroup;
  private EventLoopGroup bossGroup;
  private ChannelFuture channelFuture;

  private Class serverSocketChannelClass;
  private final CobwebServerConfig serverConfig;

  public CobwebServer(CobwebServerConfig serverConfig) {
    this.serverConfig = serverConfig;
  }

  @Override
  public void init() {
    try {
      workGroup = new EpollEventLoopGroup();
      bossGroup = new EpollEventLoopGroup();
      serverSocketChannelClass = EpollServerSocketChannel.class;
    } catch (Exception e) {
      workGroup = new NioEventLoopGroup();
      bossGroup = new NioEventLoopGroup();
      serverSocketChannelClass = NioServerSocketChannel.class;
    }
  }

  @Override
  public void start() {
    channelFuture = new ServerBootstrap()
        .group(bossGroup, workGroup)
        .channel(serverSocketChannelClass)
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel channel) throws Exception {
            channel.pipeline().addLast(
                new TransportFrameDecoder(),
                new ProtobufServiceServerCodec(),
                new CobwebRequestDispatcher(),
                new TransportFrameEncoder()
            );
          }
        })
        // TODO ADJUST THE PARAMETERS FOR PERFORMANCE
        // more information https://github.com/EZLippi/awesome-netty
        .option(ChannelOption.SO_BACKLOG, 128)
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .bind(serverConfig.getPort()).syncUninterruptibly();
  }

  @Override
  public void stop() {
    if (channelFuture != null) {
      channelFuture.channel().close().awaitUninterruptibly();
    }
    if (bossGroup != null) {
      bossGroup.shutdownGracefully();
      bossGroup = null;
    }
    if (workGroup != null) {
      workGroup.shutdownGracefully();
      workGroup = null;
    }
  }

  @Override
  public void close() throws Exception {
    stop();
  }
}
