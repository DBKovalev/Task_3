package ru.educationservices.qastellarburgers.User;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class UserAPI {

    private User user;
    private static final Gson gson = new Gson();
    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";

    public void setUser(User user) {
        this.user = user;
    }

    @Step("Создать пользователя")
    public Response createUser() {
        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(gson.toJson(user))
                .when()
                .post(BASE_URL + "/api/auth/register");
    }

    @Step("Логин пользователя")
    public Response loginUser() {
        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(gson.toJson(user))
                .when()
                .post(BASE_URL + "/api/auth/login");
    }

    @Step("Удалить пользователя по токену")
    public void deleteUser(String accessToken) {
        if (accessToken == null) {
            return;
        }
        RestAssured.given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + "/api/auth/user")
                .then().assertThat()
                .statusCode(Matchers.anyOf(Matchers.equalTo(200), Matchers.equalTo(202)));
    }
}
