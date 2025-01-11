package api;

import user.User;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIUserStep {

    private final static String REGISTRATION_USER_URL = "https://stellarburgers.nomoreparties.site/api/auth/register";
    private final static String LOGIN_USER_URL = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private final static String DELETE_USER_URL = "https://stellarburgers.nomoreparties.site/api/auth/user";

    @Step("Отправка POST запроса для регистрации пользователя")
    public Response registerUser(User user) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(REGISTRATION_USER_URL);
    }

    @Step("Отправка POST запроса для входа (логина) в систему")
    public Response loginUser(User user) {
        user.setName(null);
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(LOGIN_USER_URL);
    }

    @Step("Отправка DELETE запроса для удаления пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .and()
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER_URL);
    }

}
