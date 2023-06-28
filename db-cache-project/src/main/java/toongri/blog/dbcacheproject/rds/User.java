package toongri.blog.dbcacheproject.rds;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  private String name;

  private Long gradeId;

  private User(String name) {
    this.name = name;
  }

  public static User createUser(String name) {
    return new User(name);
  }

}
