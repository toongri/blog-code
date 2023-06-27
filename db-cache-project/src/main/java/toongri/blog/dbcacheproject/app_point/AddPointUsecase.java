package toongri.blog.dbcacheproject.app_point;

import java.math.BigDecimal;

public interface AddPointUsecase {

    void addPoint(long userId, BigDecimal amount);
}
