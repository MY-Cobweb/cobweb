package org.cobweb.transport.message;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import org.cobweb.core.exception.CobwebCodecException;

/**
 * @author meijie
 * @since 0.0.1
 */
public class DimAggCountResponse implements Response {

  private int cnt;

  @Override
  public Response decode(Any payload) throws CobwebCodecException {
    try {
      MessageProtocol.DimAggCountResponse protoResponse = payload
          .unpack(MessageProtocol.DimAggCountResponse.class);
      this.cnt = protoResponse.getCnt();
      return this;
    } catch (InvalidProtocolBufferException e) {
      throw new CobwebCodecException(e);
    }
  }

  @Override
  public Message encode() throws CobwebCodecException {
    return MessageProtocol.DimAggCountResponse
        .newBuilder()
        .setCnt(this.cnt)
        .build();
  }
}
