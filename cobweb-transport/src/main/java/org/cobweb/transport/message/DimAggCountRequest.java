package org.cobweb.transport.message;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;

/**
 * @author meijie
 * @since 0.0.1
 */
public class DimAggCountRequest implements Request {

  public static final RequestType type = RequestType.DIM_AGG_CNT;
  private List<String> dimList;
  private List<Condition> condList;

  @Override
  public RequestType type() {
    return type;
  }

  @Override
  public Request fromMessage(Any any) throws InvalidProtocolBufferException {
    checkNotNull(any);
    MessageProtocol.DimAggCountRequest protobufRequest = any
        .unpack(MessageProtocol.DimAggCountRequest.class);
    this.dimList = protobufRequest.getDimList();
    this.condList = Condition.toConditions(protobufRequest.getCondList());
    return this;
  }

  @Override
  public MessageProtocol.DimAggCountRequest encode() {
    return MessageProtocol.DimAggCountRequest
        .newBuilder()
        .addAllDim(dimList)
        .addAllCond(Condition.encodeProtoConditions(condList))
        .build();

  }
}
