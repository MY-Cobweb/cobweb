package org.cobweb.transport.message;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.cobweb.core.AbstractCodec;

/**
 * @author meijie
 * @since 0.0.1
 */
@Getter
@Setter
public class Column extends AbstractCodec<Column, MessageProtocol.Column> {

  public static final Column instance = new Column();
  private String name;
  private List<String> valueList;

  private Column() {
  }

  public static Column getInstance() {
    return instance;
  }

  public Column decode(MessageProtocol.Column protoColumn) {
    Column column = new Column();
    column.setName(protoColumn.getName());
    column.setValueList(protoColumn.getValueList());
    return column;
  }

  @Override
  public MessageProtocol.Column encode(Column o) {
    return MessageProtocol.Column.newBuilder()
        .setName(o.getName())
        .addAllValue(o.getValueList())
        .build();
  }

}
