package org.cobweb.core;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.cobweb.core.exception.CobwebCodecException;

/**
 * @author meijie
 * @since 0.0.1
 */
public abstract class AbstractCodec<O, T> implements Codec<O, T> {

  @Override
  public List<O> decode(List<T> ts) throws CobwebCodecException {
    List<O> os = new LinkedList<>();
    if (CollectionUtils.isNotEmpty(ts)) {
      for (T t : ts) {
        os.add(decode(t));
      }
    }
    return os;
  }

  @Override
  public List<T> encode(List<O> os) throws CobwebCodecException {
    List<T> ts = new LinkedList<>();
    for (O o : os) {
      ts.add(encode(o));
    }
    return ts;
  }
}
