package org.cobweb.client;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Cobweb Request
 * @author meijie
 * @since 0.0.1
 */
@Getter
@Builder
@AllArgsConstructor
public class CobwebRequest {
  private String topicName;
  private Set<String> dimSet;
  private Set<String> metricSet;
  private String dt;
}
