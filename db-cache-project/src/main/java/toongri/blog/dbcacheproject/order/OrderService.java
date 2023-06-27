package toongri.blog.dbcacheproject.order;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toongri.blog.dbcacheproject.app_point.AddPointUsecase;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class OrderService implements AcceptOrderUsecase {

    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final AddPointUsecase addPointUsecase;
    @Override
    public void acceptOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);

    }
}
