package org.cobweb.transport.message;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import org.cobweb.core.exception.CobwebCodecException;

/**
 * Request Message Codec.
 *
 * @author meijie
 * @since 0.0.1
 */
public interface RequestCodec {

  Message encode() throws CobwebCodecException;

  Request decode(Any any) throws CobwebCodecException;
}
