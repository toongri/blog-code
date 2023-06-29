package toongri.blog.dbcacheproject.point.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toongri.blog.dbcacheproject.point.application.port.in.RewardForOrderUsecase;
import toongri.blog.dbcacheproject.point.application.port.in.command.RewardForOrderCommand;
import toongri.blog.dbcacheproject.point.application.port.out.InsertAppPointPort;
import toongri.blog.dbcacheproject.point.application.port.out.LoadAccumulatePolicyPort;
import toongri.blog.dbcacheproject.point.application.port.out.LoadOrderPort;
import toongri.blog.dbcacheproject.point.domain.AccumulatePolicy;
import toongri.blog.dbcacheproject.point.domain.AppPoint;
import toongri.blog.dbcacheproject.point.domain.Order;
import toongri.blog.dbcacheproject.point.domain.PointValidator;

@Service
@RequiredArgsConstructor
class RewardForOrderService implements RewardForOrderUsecase {

    private final LoadOrderPort loadOrderPort;
    private final LoadAccumulatePolicyPort loadAccumulatePolicyPort;
    private final InsertAppPointPort insertAppPointPort;
    private final PointValidator pointValidator;

    @Override
    public void rewardForPayment(RewardForOrderCommand command) {
        long orderId = command.orderId();

        Order order = loadOrderPort.loadOrder(orderId);
        AccumulatePolicy accumulatePolicy = loadAccumulatePolicyPort.loadAccumulatePolicy(order);

        AppPoint appPoint = AppPoint.accumulate(order, accumulatePolicy, pointValidator);

        insertAppPointPort.insertAppPoint(appPoint);
        return;
    }
}
