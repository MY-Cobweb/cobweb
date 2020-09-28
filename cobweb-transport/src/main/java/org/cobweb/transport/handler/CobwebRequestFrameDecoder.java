package org.cobweb.transport.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Decoder Request Frame
 *
 * <pre>
 * +----------------------------------------------------------------------------------
 * | FRAME LENGTH(4) | MAGIC NUMBER(4) | VERSION(2) | MESSAGE TYPE(1) | MESSAGE BODY |
 * +---------------------------------------------------------------------------------
 * </pre>
 *
 * @author meijie
 * @since 0.0.1
 */
public class CobwebRequestFrameDecoder extends LengthFieldBasedFrameDecoder {

  private static final int MAGIC_NUMBER = 0xCABD;
  private static final short VERSION = 1;

  public CobwebRequestFrameDecoder(int maxFrameLength, int lengthFieldOffset,
      int lengthFieldLength) {
    super(Integer.MAX_VALUE, 0, 4, -4, 4);
  }

  @Override
  protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    ByteBuf msg = (ByteBuf) super.decode(ctx, in);
    if (msg == null) {
      return null;
    }
    checkMagicNumber(msg);
    checkVersion(msg);
    return decodeMsg(msg);
  }

  private Object decodeMsg(ByteBuf msg) {
    return null;
  }

  private void checkMagicNumber(ByteBuf msg) {
    int magicNumber = msg.readInt();
    if (magicNumber != MAGIC_NUMBER) {
      throw new IllegalStateException(
          "Network stream corrupted: received incorrect magic number.");
    }
  }

  private void checkVersion(ByteBuf msg) {
    short version = msg.readShort();
    if (version != VERSION) {
      throw new IllegalStateException("Network stream corrupted: received incorrect version");
    }
  }
}
