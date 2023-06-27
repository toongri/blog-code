package toongri.blog.dbcacheproject.user;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import toongri.blog.dbcacheproject.utils.BaseTimeEntity;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "grades")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Grade extends BaseTimeEntity implements Persistable<String> {
  @Id
  private String code;

  private String name;

  private BigDecimal accrualRate;

  private Integer paymentGoal;

  private String image;

  @Override
  public String getId() {
    return code;
  }

  @Override
  public boolean isNew() {
    return getCreatedDate() == null;
  }
}
