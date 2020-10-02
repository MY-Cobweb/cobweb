package org.cobweb.transport.message;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;
import org.cobweb.core.exception.CobwebCodecException;

/**
 * Dimension aggregation data query request.
 *
 * @author meijie
 * @since 0.0.1
 */
public class DimAggCountRequest implements Request {

  private static final RequestType type = RequestType.DIM_AGG_CNT;

  private List<String> dimList;
  private List<Condition> condList;

  public DimAggCountRequest() {
  }

  @Override
  public RequestType type() {
    return type;
  }

  @Override
  public Request decode(Any any) throws CobwebCodecException {
    try {
      checkNotNull(any);
      MessageProtocol.DimAggCountRequest protobufRequest = any
          .unpack(MessageProtocol.DimAggCountRequest.class);
      this.dimList = protobufRequest.getDimList();
      this.condList = Condition.getInstance().decode(protobufRequest.getCondList());
      return this;
    } catch (InvalidProtocolBufferException e) {
      throw new CobwebCodecException(e);
    }
  }

  @Override
  public MessageProtocol.DimAggCountRequest encode() throws CobwebCodecException {
    return MessageProtocol.DimAggCountRequest
        .newBuilder()
        .addAllDim(dimList)
        .addAllCond(Condition.getInstance().encode(condList))
        .build();
  }
}
