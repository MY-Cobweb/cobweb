package org.cobweb.transport.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Cobweb Response Frame Encoder
 *
 * @author meijie
 * @since 0.0.1
 */
public class TransportFrameEncoder extends MessageToByteEncoder {

  @Override
  protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

  }
}
