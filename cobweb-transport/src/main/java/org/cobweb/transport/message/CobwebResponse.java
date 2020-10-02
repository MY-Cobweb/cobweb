package org.cobweb.transport.message;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import lombok.extern.slf4j.Slf4j;
import org.cobweb.core.exception.CobwebCodecException;

/**
 * @author meijie
 * @since 0.0.1
 */
@Slf4j
public class CobwebResponse {

  public static Response decode(CodedInputStream cis) throws CobwebCodecException {
    try {
      MessageProtocol.CobwebResponse protoResponse = MessageProtocol.CobwebResponse
          .getDefaultInstance().getParserForType().parseFrom(cis);
      MessageProtocol.CobwebResponseHeader header = protoResponse.getHeader();
      RequestType type = RequestType.valueOf(header.getType());
      switch (type) {
        case DIM_AGG: {
          return new DimAggResponse().decode(protoResponse.getPayload());
        }
        case DIM_AGG_CNT: {
          return new DimAggCountResponse().decode(protoResponse.getPayload());
        }
        default:
          throw new UnsupportedOperationException();
      }
    } catch (InvalidProtocolBufferException e) {
      throw new CobwebCodecException(e.getCause());
    }
  }

  public static Message encode(Response response) throws CobwebCodecException {
    return null;
  }
}
