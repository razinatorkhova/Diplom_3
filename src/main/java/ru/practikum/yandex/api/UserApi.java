package ru.practikum.yandex.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.practikum.yandex.model.lombok.LoginDataLombok;
import ru.practikum.yandex.model.lombok.UserDataLombok;

import static io.restassured.RestAssured.given;

public class UserApi extends RestApi {

    @Step("Create user")
    public ValidatableResponse createUserLombok(UserDataLombok user) {
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(Endpoints.CREATE_USER_API_URI)
                .then();
    }


    @Step("Delete user")
    public ValidatableResponse deleteUser(String userAccessToken) {
        return given()
                .spec(requestSpecification())
                .header("Authorization", userAccessToken) // Добавляем токен в заголовки
                .when()
                .delete(Endpoints.GET_CHANGE_DELETE_DATA_USER_API_URI)
                .then();
    }

    @Step("Authorized user")
    public ValidatableResponse loginUser(LoginDataLombok user) {
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(Endpoints.LOGIN_USER_API_URI)
                .then();
    }
}
