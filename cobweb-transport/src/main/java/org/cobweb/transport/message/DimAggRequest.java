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
  private List<String> dimList;
  private List<String> metricList;
  private List<Condition> condList;
  private List<String> orderList;
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
    MessageProtocol.DimAggRequest protobufRequest = any.unpack(MessageProtocol.DimAggRequest.class);
    this.dimList = protobufRequest.getDimsList();
    this.metricList = protobufRequest.getMetricList();
    this.condList = Condition.toConditions(protobufRequest.getCondList());
    this.orderList = protobufRequest.getOrderList();
    this.asc = protobufRequest.getAsc();
    this.page = protobufRequest.getPage();
    this.pageSize = protobufRequest.getPageSize();
    return this;
  }

  @Override
  public MessageProtocol.DimAggRequest encode() {
    return MessageProtocol.DimAggRequest
        .newBuilder()
        .addAllDims(dimList)
        .addAllMetric(metricList)
        .addAllCond(Condition.encodeProtoConditions(condList))
        .addAllOrder(orderList)
        .setAsc(asc)
        .setPage(page)
        .setPageSize(pageSize)
        .build();
  }
}
