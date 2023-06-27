package toongri.blog.dbcacheproject.order;

import java.math.BigDecimal;

public interface AccumulatePoint {
    void accumulatePoint(long userId, BigDecimal amount);
}
