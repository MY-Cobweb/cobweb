package org.cobweb.transport.handler;

import com.google.protobuf.CodedInputStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import java.util.List;
import org.cobweb.transport.message.CobwebRequest;
import org.cobweb.transport.message.CobwebResponse;
import org.cobweb.transport.message.Request;

/**
 * @author meijie
 * @since 0.0.1
 */
public class ProtobufServiceServerCodec extends ByteToMessageCodec<CobwebResponse> {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    CodedInputStream cis = CodedInputStream.newInstance(in.array());
    Request request = CobwebRequest.buildFrom(cis);
    out.add(request);
  }

  @Override
  protected void encode(ChannelHandlerContext ctx, CobwebResponse msg, ByteBuf out)
      throws Exception {

  }

}
