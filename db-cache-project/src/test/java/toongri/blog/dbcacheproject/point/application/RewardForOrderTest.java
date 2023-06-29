package toongri.blog.dbcacheproject.point.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toongri.blog.dbcacheproject.point.application.port.in.RewardForOrderUsecase;
import toongri.blog.dbcacheproject.point.application.port.in.command.RewardForOrderCommand;
import toongri.blog.dbcacheproject.point.domain.Order;
import toongri.blog.dbcacheproject.point.domain.OrderStatus;
import toongri.blog.dbcacheproject.rds.grade.GradeJpa;
import toongri.blog.dbcacheproject.rds.grade.GradeJpaRepository;
import toongri.blog.dbcacheproject.rds.order.OrderJpa;
import toongri.blog.dbcacheproject.rds.order.OrderJpaRepository;
import toongri.blog.dbcacheproject.rds.point.AppPointJpa;
import toongri.blog.dbcacheproject.rds.point.AppPointJpaRepository;
import toongri.blog.dbcacheproject.rds.user.UserJpa;
import toongri.blog.dbcacheproject.rds.user.UserJpaRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RewardForOrderTest {

    @Autowired private RewardForOrderUsecase rewardForOrderUsecase;
    @Autowired private OrderJpaRepository orderJpaRepository;
    @Autowired private GradeJpaRepository gradeJpaRepository;
    @Autowired private UserJpaRepository userJpaRepository;
    @Autowired private AppPointJpaRepository appPointJpaRepository;
    @PersistenceContext private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        GradeJpa gradeJpa = new GradeJpa("abc", "짱짱맨", BigDecimal.valueOf(0.01), 0, "");
        gradeJpaRepository.save(gradeJpa);
        UserJpa user = new UserJpa("홍길동", gradeJpa);
        userJpaRepository.save(user);
        OrderJpa orderJpa = new OrderJpa(user, BigDecimal.valueOf(1000L), OrderJpa.OrderStatus.PAYED);
        orderJpaRepository.save(orderJpa);
    }

    @Test
    @DisplayName("주문에 대한 보상을 받는다")
    void 주문에_대한_보상을_받는다() throws Exception{
        //given
        Order order = Order.create(1L, 2L, BigDecimal.valueOf(1000L), OrderStatus.PAYED);
        RewardForOrderCommand command = new RewardForOrderCommand(order.getId());
        //when
        rewardForOrderUsecase.rewardForPayment(command);

        //then
        AppPointJpa point = appPointJpaRepository.findAll().get(0);

        Assertions.assertThat(point.getUserId()).isEqualTo(1L);
        Assertions.assertThat(point.getPoint().compareTo(BigDecimal.valueOf(10))).isSameAs(0);
    }
}
