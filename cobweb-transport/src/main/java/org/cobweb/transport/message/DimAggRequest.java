package org.cobweb.transport.message;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;

/**
 * @author meijie
 * @since 0.0.1
 */
public class DimAggRequest implements Request {

  private static final RequestType type = RequestType.DIM_AGG;
  private List<String> dims;
  private List<String> metrics;
  private List<Condition> conds;
  private List<String> orders;
  private boolean asc;
  // page start from 1
  private Integer page;
  private Integer pageSize;

  @Override
  public RequestType type() {
    return type;
  }

  @Override
  public Request fromMessage(Any any) throws InvalidProtocolBufferException {
    ServiceProtocol.DimAggRequest protobufRequest = any.unpack(ServiceProtocol.DimAggRequest.class);
    this.dims = protobufRequest.getDimsList();
    this.metrics = protobufRequest.getMetricsList();
    this.conds = Condition.toConditions(protobufRequest.getCondsList());
    this.orders = protobufRequest.getOrdersList();
    this.asc = protobufRequest.getAsc();
    this.page = protobufRequest.getPage();
    this.pageSize = protobufRequest.getPageSize();
    return this;
  }
}
