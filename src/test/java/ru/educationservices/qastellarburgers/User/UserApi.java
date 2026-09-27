package ru.educationservices.qastellarburgers.User;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class UserApi {

    private User user;

    private static final Gson gson = new Gson();

    public void setUser(User user) {
        this.user = user;
    }

    @Step("Создать пользователя")
    public Response createUser() {
        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(gson.toJson(user))
                .when()
                .post("/register");
    }

    @Step("Логин пользователя")
    public Response loginUser() {
        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(gson.toJson(user))
                .when()
                .post("/login");
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
                .delete("/user")
                .then().assertThat()
                .statusCode(anyOf(equalTo(200), equalTo(202)));
    }
}