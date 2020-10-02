package org.cobweb.transport.message;

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
  public static Request buildFrom(CodedInputStream cis)
      throws InvalidProtocolBufferException {
    MessageProtocol.CobwebRequest protobufRequest = MessageProtocol.CobwebRequest
        .getDefaultInstance().getParserForType().parseFrom(cis);
    CobwebRequestHeader header = protobufRequest.getHeader();
    RequestType type = RequestType.valueOf(header.getParamType());
    switch (type) {
      case DIM_AGG: {
        DimAggRequest request = new DimAggRequest();
        request.fromMessage(protobufRequest.getPayload());
        return request;
      }
      case DIM_AGG_CNT: {
        DimAggCountRequest request = new DimAggCountRequest();
        request.fromMessage(protobufRequest.getPayload());
        return request;
      }
      default:
        throw new UnsupportedOperationException("unsupported request type " + type.name());
    }
  }

  public static Message encode(Request request) {
    return request.encode();
  }
}
