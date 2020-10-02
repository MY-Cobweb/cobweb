package org.cobweb.transport.message;

public interface Request extends RequestCodec {

  RequestType type();
}
