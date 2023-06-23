package toongri.blog.springmvcrequestmapping.mapping;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParamTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * ParamHeaderController > message 메서드
     */
    @DisplayName("Parameter Header")
    @Test
    void message() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/param-header/message")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("message"));
    }

    /**
     * ParamHeaderController > messageForParam 메서드
     * > params 설정을 통해 요청 맵핑하기
     */
    @DisplayName("Parameter Header - Params")
    @Test
    void messageForParam() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/param-header/message?name=hello")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("hello"));
    }

    /**
     * ParamHeaderController > messageForParam 메서드
     * > params 설정을 통해 요청 맵핑하기
     */
    @DisplayName("Parameter Header - Params")
    @Test
    void messageForMultiParam() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/param-header/message/multi?name=hello&message=something")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("hello message"));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/param-header/message/multi?name=hello")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
