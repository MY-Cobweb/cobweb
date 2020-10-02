package org.cobweb.transport.message;

import com.google.protobuf.Any;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cobweb.transport.message.MessageProtocol.CobwebRequestHeader;

/**
 * CobwebRequest
 *
 * @author meijie
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
public class CobwebRequest {

  /**
   * Build rpc request from protobuf stream
   *
   * @param cis
   * @return
   * @throws InvalidProtocolBufferException
   */
  public static Request decode(CodedInputStream cis)
      throws Exception {
    MessageProtocol.CobwebRequest protobufRequest = MessageProtocol.CobwebRequest
        .getDefaultInstance().getParserForType().parseFrom(cis);
    CobwebRequestHeader header = protobufRequest.getHeader();
    RequestType type = RequestType.valueOf(header.getType());
    switch (type) {
      case DIM_AGG: {
        return new DimAggRequest().decode(protobufRequest.getPayload());
      }
      case DIM_AGG_CNT: {
        return new DimAggCountRequest().decode(protobufRequest.getPayload());
      }
      default:
        throw new UnsupportedOperationException("unsupported request type " + type.name());
    }
  }

  public static Message encode(Request request) throws Exception {
    CobwebRequestHeader header = CobwebRequestHeader.newBuilder()
        .setType(request.type().name())
        .build();

    return MessageProtocol.CobwebRequest.newBuilder()
        .setHeader(header)
        .setPayload(Any.pack(request.encode()))
        .build();
  }
}
