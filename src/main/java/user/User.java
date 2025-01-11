package user;

import api.APIUserStep;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class User {
    private String email;
    private String password;
    private String name;

    @Step("Создание данных пользователя")
    public static User generateUser(){
        Faker faker = new Faker();
        User user = new User();
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(6, 12));

        return user;
    }

    @Step("Создание некорректных данных пользователя (некорректный password)")
    public static User generateUncorrectUser(){
        Faker faker = new Faker();
        User user = new User();
        user.setName(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(1, 5));

        return user;
    }

    @Step("Создание пользователя")
    public static User createUser(){
        APIUserStep userApi = new APIUserStep();
        User user = generateUser();
        userApi.registerUser(user);

        return user;
    }

    @Step("Удаление тестового пользователя")
    public static void deleteUsers(ArrayList<User> users){
        APIUserStep apiUserStep = new APIUserStep();
        for (User user : users){
            Response response = apiUserStep.loginUser(user);
            String accessToken = response.jsonPath().getString("accessToken");
            apiUserStep.deleteUser(accessToken);
        }
    }

}