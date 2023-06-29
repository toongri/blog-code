package toongri.blog.dbcacheproject.rds.user;


import jakarta.persistence.*;
import lombok.*;
import toongri.blog.dbcacheproject.rds.BaseTimeEntityJpa;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserJpa extends BaseTimeEntityJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  private String name;

  private String gradeCode;

  private UserJpa(String name) {
    this.name = name;
  }

}
