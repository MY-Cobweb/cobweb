package org.cobweb.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author meijie
 * @since 0.0.1
 */
@Builder
@AllArgsConstructor
@Getter
public class CobwebServerConfig {

  private String host = "localhost";
  private int port = 9930;
}
