package toongri.blog.dbcacheproject.point.domain;

import org.springframework.stereotype.Component;

@Component
public class PointValidator {

    public void validate(AppPoint point, Order order) {
        if (point.isEmpty()) {
            throw new RuntimeException("포인트가 없습니다.");
        }

        if (!order.isPayed()) {
            throw new IllegalArgumentException("결제되지 않은 주문입니다.");
        }
    }
}
