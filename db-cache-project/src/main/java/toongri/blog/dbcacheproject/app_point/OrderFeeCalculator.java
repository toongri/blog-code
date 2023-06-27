package toongri.blog.dbcacheproject.app_point;

import java.math.BigDecimal;

public interface OrderFeeCalculator {

    BigDecimal calculateOrderFee(OrderSheet orderSheet);
}
