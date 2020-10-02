package org.cobweb.transport.message;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;
import org.cobweb.core.exception.CobwebCodecException;

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

  public DimAggRequest() {
  }


  @Override
  public RequestType type() {
    return type;
  }

  @Override
  public Request decode(Any any) throws CobwebCodecException {
    try {
      MessageProtocol.DimAggRequest protobufRequest = any
          .unpack(MessageProtocol.DimAggRequest.class);
      this.dimList = protobufRequest.getDimsList();
      this.metricList = protobufRequest.getMetricList();
      this.condList = Condition.getInstance().decode(protobufRequest.getCondList());
      this.orderList = protobufRequest.getOrderList();
      this.asc = protobufRequest.getAsc();
      this.page = protobufRequest.getPage();
      this.pageSize = protobufRequest.getPageSize();
      return this;
    } catch (InvalidProtocolBufferException e) {
      throw new CobwebCodecException(e);
    }
  }

  @Override
  public MessageProtocol.DimAggRequest encode() throws CobwebCodecException {
    return MessageProtocol.DimAggRequest
        .newBuilder()
        .addAllDims(dimList)
        .addAllMetric(metricList)
        .addAllCond(Condition.getInstance().encode(condList))
        .addAllOrder(orderList)
        .setAsc(asc)
        .setPage(page)
        .setPageSize(pageSize)
        .build();
  }
}
