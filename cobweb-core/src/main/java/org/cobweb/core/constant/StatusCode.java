package org.cobweb.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Status Code from Server, which related to target exception.
 *
 * @author meijie
 * @since 0.0.1
 */
@Getter
@AllArgsConstructor
public enum StatusCode {
  OK(0, "success"),
  CODEC_ERROR(11, "codec error"),
  UNKNOWN_ERROR(99, "unknown error from server");

  private int code;
  private String message;
}
