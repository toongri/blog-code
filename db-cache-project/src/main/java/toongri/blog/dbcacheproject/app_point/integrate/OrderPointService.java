package toongri.blog.dbcacheproject.app_point.integrate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toongri.blog.dbcacheproject.app_point.AddPointUsecase;
import toongri.blog.dbcacheproject.order.AccumulatePoint;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class OrderPointService implements AccumulatePoint {
    private final AddPointUsecase addPointUsecase;

    @Override
    public void accumulatePoint(long userId, BigDecimal amount) {
        addPointUsecase.addPoint(userId, amount);
    }
}
