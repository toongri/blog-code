package toongri.blog.springmvcrequestmapping.mapping;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import toongri.blog.springmvcrequestmapping.domain.User;

import static org.hamcrest.core.Is.is;

@DisplayName("Http Method")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpMethodTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * HttpMethodController > getFoosBySimplePath 메서드
     */
    @DisplayName("requestMapping의 method 속성을 정의하지 않으면 모든 HTTP 메서드에 대해 처리한다.")
    @Test
    void notDefinedMethod() {
        RestAssured.given().log().all()
                .when().get("/http-method/users/ex/foos")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured.given().log().all()
                .when().post("/http-method/users/ex/foos")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    /**
     * HttpMethodController > createUser 메서드
     */
    @DisplayName("Http Method - POST")
    @Test
    void createUser() {
        User user = new User("이름", "email@email.com");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .when().post("/http-method/users")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", "/users/1");
    }

    /**
     * HttpMethodController > showUser 메서드
     */
    @DisplayName("Http Method - GET")
    @Test
    void showUser() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/http-method/users")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2));
    }

}
