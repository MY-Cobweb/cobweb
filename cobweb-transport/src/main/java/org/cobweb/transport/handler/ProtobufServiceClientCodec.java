package org.cobweb.transport.handler;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import java.util.List;
import org.cobweb.transport.message.CobwebRequest;
import org.cobweb.transport.message.MessageProtocol;
import org.cobweb.transport.message.Request;

/**
 * @author meijie
 * @since 0.0.1
 */
public class ProtobufServiceClientCodec extends ByteToMessageCodec<Request> {

  @Override
  protected void encode(ChannelHandlerContext ctx, Request msg, ByteBuf out)
      throws Exception {
    MessageProtocol.CobwebRequestHeader protoHeader = MessageProtocol.CobwebRequestHeader
        .newBuilder()
        .setType(msg.type().name())
        .build();
    Message payload = CobwebRequest.encode(msg);

    MessageProtocol.CobwebRequest protoRequest = MessageProtocol.CobwebRequest
        .newBuilder()
        .setHeader(protoHeader)
        .setPayload(Any.pack(payload))
        .build();

    out.writeBytes(protoRequest.toByteArray());
  }

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

  }
}
