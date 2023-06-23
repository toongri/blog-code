package toongri.blog.springmvcrequestmapping.mapping;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeaderTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * HttpMethodController > getFoosWithHeaders 메서드
     */
    @DisplayName("header값을 정확히 입력해주면 조건에 부합하는 기능에 매핑해준다")
    @Test
    void headerMapping() {
        RestAssured.given().headers("key1", "val1", "key2", "val2")
                .log().all()
                .when().get("/http-method/users/ex/headers")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value());
    }

    /**
     * HttpMethodController > getFoosWithHeaders 메서드
     */
    @DisplayName("header값을 정확히 입력해주지않으면 매핑해주지 않는다.")
    @Test
    void headerMappingNotFound() {
        RestAssured.given().headers("key1", "val1", "key2", "val1")
                .log().all()
                .when().get("/http-method/users/ex/headers")
                .then()
                .log().all()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
