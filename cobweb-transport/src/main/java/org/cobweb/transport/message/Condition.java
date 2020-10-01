package org.cobweb.transport.message;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  protected static Condition toCondition(ServiceProtocol.Condition protobufCondition) {
    Condition condition = new Condition();
    condition.setColumn(protobufCondition.getColumn());
    condition.setValue(protobufCondition.getValue());
    return condition;
  }

  protected static List<Condition> toConditions(List<ServiceProtocol.Condition> protobufConditions) {
    List<Condition> conditions = new ArrayList<>();
    for (ServiceProtocol.Condition protobufCondition : protobufConditions) {
      conditions.add(toCondition(protobufCondition));
    }
    return conditions;
  }
}
