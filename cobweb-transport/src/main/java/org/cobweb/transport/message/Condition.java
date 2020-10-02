package org.cobweb.transport.message;

import lombok.Getter;
import lombok.Setter;
import org.cobweb.core.AbstractCodec;

/**
 * @author meijie
 * @since 0.0.1
 */
@Getter
@Setter
public class Condition extends AbstractCodec<Condition, MessageProtocol.Condition> {

  private static final Condition instance = new Condition();
  private String column;
  private String value;

  private Condition() {
  }

  public static Condition getInstance() {
    return instance;
  }

  public Condition decode(MessageProtocol.Condition protobufCondition) {
    Condition condition = new Condition();
    condition.setColumn(protobufCondition.getColumn());
    condition.setValue(protobufCondition.getValue());
    return condition;
  }

  public MessageProtocol.Condition encode(Condition condition) {
    return MessageProtocol.Condition
        .newBuilder()
        .setColumn(condition.getColumn())
        .setValue(condition.getValue())
        .build();
  }
}
