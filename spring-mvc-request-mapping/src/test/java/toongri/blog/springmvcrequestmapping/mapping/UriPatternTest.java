package toongri.blog.springmvcrequestmapping.mapping;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import toongri.blog.springmvcrequestmapping.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UriPatternTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }


    /**
     * UriPatternController > pathVariable 메서드
     */
    @DisplayName("Uri Pattern - @PathVariable")
    @Test
    void pathVariable() {
        //given
        String url = "/uri-pattern/users/{userId}";
        long path = 1;

        //when
        ExtractableResponse<Response> apiResponse = RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get(url, path)
                .then().log().all()
                .statusCode(HttpStatus.OK.value()).extract();

        //then
        User response = apiResponse.body().as(User.class);
        Assertions.assertThat(response.getId()).isEqualTo(path);
    }

    /**
     * UriPatternController > pattern 메서드
     * > 각 요청을 하나의 메서드로 처리하기
     */
    @DisplayName("Uri Pattern - pattern")
    @Test
    void pattern() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns/a")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("pattern"));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns/bc")
                .then().log().all()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    /**
     * UriPatternController > patternStars 메서드
     * > 각 요청을 하나의 메서드로 처리하기
     */
    @DisplayName("Uri Pattern - pattern multi")
    @Test
    void patternStars() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns-2/multi")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("pattern-multi"));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns-2/all")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("pattern-multi"));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns-2/all/names")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("pattern-multi"));
    }
}
