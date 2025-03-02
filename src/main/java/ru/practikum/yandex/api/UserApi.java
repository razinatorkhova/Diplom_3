package ru.practikum.yandex.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.practikum.yandex.model.lombok.LoginDataLombok;
import ru.practikum.yandex.model.lombok.UserDataLombok;

import static io.restassured.RestAssured.given;

public class UserApi extends RestApi {
    /**
     * URI для создания пользователя
     */
    public static final String CREATE_USER_URI = "/api/auth/register";
    /**
     * URI для авторизации пользователя
     */
    public static final String LOGIN_USER_URI = "/api/auth/login";
    /**
     * URI для получения, обновления и удаления пользователя
     */
    public static final String GET_CHANGE_DELETE_DATA_USER_URI = "/api/auth/user";

    @Step("Create user")
    public ValidatableResponse createUserLombok(UserDataLombok user) {
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(CREATE_USER_URI)
                .then();
    }


    @Step("Delete user")
    public ValidatableResponse deleteUser(String userAccessToken) {
        return given()
                .spec(requestSpecification())
                .header("Authorization", userAccessToken) // Добавляем токен в заголовки
                .when()
                .delete(GET_CHANGE_DELETE_DATA_USER_URI)
                .then();
    }

    @Step("Authorized user")
    public ValidatableResponse loginUser(LoginDataLombok user) {
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(LOGIN_USER_URI)
                .then();
    }
}
