package org.cobweb.transport.message;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;

/**
 * @author meijie
 * @since 0.0.1
 */
public class DimAggCountRequest implements Request{

  public static final RequestType type = RequestType.DIM_AGG_CNT;
  private List<String> dims;
  private List<Condition> conds;

  @Override
  public RequestType type() {
    return type;
  }

  @Override
  public Request fromMessage(Any any) throws InvalidProtocolBufferException {
    checkNotNull(any);
    ServiceProtocol.DimAggCountRequest protobufRequest = any.unpack(ServiceProtocol.DimAggCountRequest.class);
    this.dims = protobufRequest.getDimsList();
    this.conds = Condition.toConditions(protobufRequest.getCondsList());

    return this;
  }
}
