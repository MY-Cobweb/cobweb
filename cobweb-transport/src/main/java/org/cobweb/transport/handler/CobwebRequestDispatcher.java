package org.cobweb.transport.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.cobweb.core.Dispatcher;
import org.cobweb.core.Handler;
import org.cobweb.transport.message.Request;
import org.cobweb.transport.message.RequestType;

/**
 * @author meijie
 * @since 0.0.1
 */
public class CobwebRequestDispatcher extends SimpleChannelInboundHandler<Request> implements
    Dispatcher<RequestType, Request> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
    System.out.println(msg.type().name());
  }

  @Override
  public void dispatch(Request request) {

  }

  @Override
  public void registry(RequestType type, Handler handler) {

  }

  @Override
  public void unRegistry(RequestType type, Handler handler) {

  }
}
