package toongri.blog.dbcacheproject.order;

import jakarta.persistence.*;
import lombok.*;
import toongri.blog.dbcacheproject.app_point.AddPointUsecase;
import toongri.blog.dbcacheproject.utils.BaseTimeEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
class Order extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  private Long userId;

  private BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  // ==연관관계 메소드==//

  // ==생성 메소드==//

  public static Order createOrder() {

    Order order = new Order();

    return order;
  }

  // ==수정 메소드==//

  // ==비즈니스 로직==//

  public void accepted(AddPointUsecase addPointUsecase) {
    this.status = OrderStatus.ORDERED;
    addPointUsecase.addPoint(this.userId, totalPrice);
  }

  // ==조회 로직==//

}
