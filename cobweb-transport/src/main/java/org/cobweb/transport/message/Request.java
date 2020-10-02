package org.cobweb.transport.message;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

public interface Request {

  RequestType type();

  Request fromMessage(Any any) throws InvalidProtocolBufferException;

  Message encode();
}
