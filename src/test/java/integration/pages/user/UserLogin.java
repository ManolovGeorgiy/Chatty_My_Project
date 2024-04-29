package integration.pages.user;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserLogin {

    public static void main(String[] args) {
        String baseUrl = "http://chatty.telran-edu.de:8989";
        String endpoint = "/api/auth/login";

        // Подготовка данных для запроса
        String email = "tatar@abv.bg";
        String password = "Manowar33246";

        // Выполнение запроса
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(endpoint)
                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
                .post();

        // Получение ответа
        int statusCode = response.getStatusCode();
        System.out.println("Response Code: " + statusCode);

        // Печать тела ответа
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        // Если код ответа 200 OK, получаем токены доступа и обновления
        if (statusCode == 200) {
            String accessToken = response.jsonPath().getString("accessToken");
            String refreshToken = response.jsonPath().getString("refreshToken");
            long expiration = response.jsonPath().getLong("expiration");
            System.out.println("Access Token: " + accessToken);
            System.out.println("Refresh Token: " + refreshToken);
            System.out.println("Expiration: " + expiration);
        }
    }
}
