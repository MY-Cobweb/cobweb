package org.cobweb.transport.message;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.cobweb.core.exception.CobwebCodecException;

/**
 * @author meijie
 * @since 0.0.1
 */
@Getter
@Setter
public class DimAggResponse implements Response {

  private List<Column> columnList;

  @Override
  public Response decode(Any payload) throws CobwebCodecException {
    try {
      MessageProtocol.DimAggResponse protoResponse = payload
          .unpack(MessageProtocol.DimAggResponse.class);
      this.setColumnList(Column.getInstance().decode(protoResponse.getColumnList()));
      return this;
    } catch (InvalidProtocolBufferException e) {
      throw new CobwebCodecException(e);
    }
  }

  @Override
  public Message encode() throws CobwebCodecException {
    return MessageProtocol.DimAggResponse.newBuilder()
        .addAllColumn(Column.getInstance().encode(columnList))
        .build();
  }
}
