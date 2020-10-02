package org.cobweb.core;

import java.util.List;
import org.cobweb.core.exception.CobwebCodecException;

public interface Codec<O, T> {

  O decode(T t) throws CobwebCodecException;

  List<O> decode(List<T> tList) throws CobwebCodecException;

  T encode(O o) throws CobwebCodecException;

  List<T> encode(List<O> oList) throws CobwebCodecException;
}
