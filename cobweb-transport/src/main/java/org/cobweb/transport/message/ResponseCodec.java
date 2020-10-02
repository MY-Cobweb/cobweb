package org.cobweb.transport.message;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import org.cobweb.core.exception.CobwebCodecException;

/**
 * Response Message codec
 *
 * @author meijie
 * @since 0.0.1
 */
public interface ResponseCodec {

  /**
   * Decode Response Message from transport
   *
   * @return Response
   */
  Response decode(Any payload) throws CobwebCodecException;

  /**
   * Encode Response Message to protobuf format.
   *
   * @return message
   */
  Message encode() throws CobwebCodecException;
}
