package org.cobweb.server.handler;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Decoder Request Frame
 *
 * @author meijie
 * @since 0.0.1
 */
public class CobwebRequestFrameDecoder extends LengthFieldBasedFrameDecoder {

  public CobwebRequestFrameDecoder(int maxFrameLength, int lengthFieldOffset,
      int lengthFieldLength) {
    super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
  }
}
