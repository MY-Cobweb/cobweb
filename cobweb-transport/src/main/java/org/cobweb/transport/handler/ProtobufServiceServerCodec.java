package org.cobweb.transport.handler;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import java.util.List;
import org.cobweb.transport.message.CobwebRequest;
import org.cobweb.transport.message.Request;
import org.cobweb.transport.message.Response;

/**
 * @author meijie
 * @since 0.0.1
 */
public class ProtobufServiceServerCodec extends ByteToMessageCodec<Response> {

  private static final byte REQUEST_MESSAGE = 1;
  private static final byte RESPONSE_MESSAGE = 2;

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    byte messageType = in.readByte();
    CodedInputStream cis = CodedInputStream.newInstance(in.array());
    if (messageType == REQUEST_MESSAGE) {
      Request request = CobwebRequest.decode(cis);
      out.add(request);
    } else if (messageType == RESPONSE_MESSAGE) {
      // TODO
    }
  }

  @Override
  protected void encode(ChannelHandlerContext ctx, Response msg, ByteBuf out)
      throws Exception {
    Message message = msg.encode();
    out.writeBytes(message.toByteArray());
  }

}
