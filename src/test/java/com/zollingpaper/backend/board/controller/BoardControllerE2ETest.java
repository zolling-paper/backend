package com.zollingpaper.backend.board.controller;

import static org.hamcrest.Matchers.is;

import com.zollingpaper.backend.board.domain.BoardFixture;
import com.zollingpaper.backend.board.exception.BoardErrorCode;
import com.zollingpaper.backend.global.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BoardControllerE2ETest {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    public void beforeEach() {
        RestAssured.port = serverPort;
        databaseCleaner.execute();

        RestAssured.given().log().all()
                .when().body(BoardFixture.BOARD_SAVE_REQUEST_1)
                .contentType(ContentType.JSON).post("/board")
                .then().log().all()
                .statusCode(201)
                .header("Location", "/board/" + BoardFixture.BOARD_SAVE_RESPONSE_1.id());
    }

    @DisplayName("보드를 생성할 수 있다.")
    @Test
    void createBoard() {
        RestAssured.given().log().all()
                .when().body(BoardFixture.BOARD_SAVE_REQUEST_2)
                .contentType(ContentType.JSON).post("/board")
                .then().log().all()
                .statusCode(201)
                .header("Location", "/board/" + BoardFixture.BOARD_SAVE_RESPONSE_2.id());
    }

    @DisplayName("보드를 조회할 수 있다.")
    @Test
    void readBoard() {
        RestAssured.given().log().all()
                .when().get("/board/1")
                .then().log().all()
                .statusCode(200).body("id", is(BoardFixture.BOARD_SAVE_RESPONSE_1.id().intValue()));
    }

    @DisplayName("존재하지 않는 보드를 조회하는 경우 예외가 발생한다.")
    @Test
    void should_throw_exception_when_read_not_existing_board() {
        RestAssured.given().log().all()
                .when().get("/board/2")
                .then().log().all()
                .statusCode(400).body("message", is(BoardErrorCode.NOT_FOUND.getErrorMessage().message()));
    }
}
