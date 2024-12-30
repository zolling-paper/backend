package com.zollingpaper.backend.paper.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import com.zollingpaper.backend.board.domain.BoardFixture;
import com.zollingpaper.backend.global.DatabaseCleaner;
import com.zollingpaper.backend.paper.domain.PaperFixture;
import com.zollingpaper.backend.paper.exception.PaperErrorCode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PaperControllerE2ETest {

    @LocalServerPort
    int serverPort;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    void beforeEach() {
        RestAssured.port = serverPort;
        databaseCleaner.execute();

        RestAssured.given().log().all()
                .when().body(BoardFixture.BOARD_SAVE_REQUEST_1)
                .contentType(ContentType.JSON).post("/board")
                .then().log().all()
                .statusCode(201)
                .header("Location", "/board/" + BoardFixture.BOARD_SAVE_RESPONSE_1.id());

        RestAssured.given().log().all()
                .when().body(PaperFixture.PAPER_SAVE_REQUEST_1)
                .contentType(ContentType.JSON).post("/paper")
                .then().log().all()
                .statusCode(201)
                .header("Location", "/paper/" + PaperFixture.PAPER_SAVE_RESPONSE_1.id());
    }

    @DisplayName("편지를 생성할 수 있다.")
    @Test
    void createPaper() {
        RestAssured.given().log().all()
                .when().body(PaperFixture.PAPER_SAVE_REQUEST_2)
                .contentType(ContentType.JSON).post("/paper")
                .then().log().all()
                .statusCode(201)
                .header("Location", "/paper/" + PaperFixture.PAPER_SAVE_RESPONSE_2.id());
    }

    @DisplayName("특정 편지를 조회할 수 있다.")
    @Test
    void readPaper() {
        RestAssured.given().log().all()
                .when().get("/paper/1")
                .then().log().all()
                .statusCode(200).body("id", is(PaperFixture.PAPER_SAVE_RESPONSE_1.id().intValue()));
    }

    @DisplayName("존재하지 않는 편지를 조회하는 경우 예외가 발생한다.")
    @Test
    void should_throw_exception_when_read_not_existing_paper() {
        RestAssured.given().log().all()
                .when().get("/paper/2")
                .then().log().all()
                .statusCode(400).body("message", is(PaperErrorCode.NOT_FOUND.getErrorMessage().message()));
    }

    @DisplayName("Board Id에 해당하는 모든 편지를 조회할 수 있다.")
    @TestFactory
    Stream<DynamicTest> should_read_papers_when_same_board_id_given() {
        return Stream.of(
                dynamicTest("1번 보드에 새 편지를 생성한다.", () -> {
                    RestAssured.given().log().all()
                            .when().body(PaperFixture.PAPER_SAVE_REQUEST_2)
                            .contentType(ContentType.JSON).post("/paper")
                            .then().log().all()
                            .statusCode(201)
                            .header("Location", "/paper/" + PaperFixture.PAPER_SAVE_RESPONSE_2.id());
                }),

                dynamicTest("1번 보드에 해당하는 편지를 모두 조회한다.", () -> {
                    RestAssured.given().log().all()
                            .when().get("/board/1/papers")
                            .then().log().all()
                            .statusCode(200).body("responses.size()", is(2));
                })
        );
    }
}
