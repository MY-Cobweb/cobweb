package org.cobweb.transport.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author meijie
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
public class Condition {

  private String column;
  private String value;

  protected static Condition toCondition(MessageProtocol.Condition protobufCondition) {
    Condition condition = new Condition();
    condition.setColumn(protobufCondition.getColumn());
    condition.setValue(protobufCondition.getValue());
    return condition;
  }

  protected static List<Condition> toConditions(
      List<MessageProtocol.Condition> protobufConditions) {
    List<Condition> conditions = new ArrayList<>();
    for (MessageProtocol.Condition protobufCondition : protobufConditions) {
      conditions.add(toCondition(protobufCondition));
    }
    return conditions;
  }

  protected static MessageProtocol.Condition encodeProtoCondition(Condition condition) {
    return MessageProtocol.Condition
        .newBuilder()
        .setColumn(condition.getColumn())
        .setValue(condition.getValue())
        .build();
  }

  protected static List<MessageProtocol.Condition> encodeProtoConditions(
      Collection<Condition> conditions) {
    List<MessageProtocol.Condition> protoCondList = new LinkedList<>();
    if (CollectionUtils.isNotEmpty(conditions)) {
      for (Condition condition : conditions) {
        protoCondList.add(encodeProtoCondition(condition));
      }
    }
    return protoCondList;
  }
}
