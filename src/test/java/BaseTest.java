import com.codeborne.selenide.Configuration;
import user.User;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import pageobject.MainPage;

import java.io.IOException;
import java.util.ArrayList;

import static browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

abstract public class BaseTest {

    private final ArrayList<User> usersForDelete = new ArrayList<>();

    @Before
    @Step("Запуск драйвера и настройки браузера и селенида")
    public void setup() throws IOException {
        initDriver();
        Configuration.timeout = 4000;
        Configuration.browserSize = "1920x1080";
        open(MainPage.MAIN_PAGE_URL);
    }

    @After
    @Step("Закрытие браузера и удаление тестовых данных")
    public void tearDown(){
        closeWebDriver();
        User.deleteUsers(usersForDelete);
    }

    @Step("Добавление пользователя в список на удаление")
    public void addUserToDeleteListTearDown(User user){
        usersForDelete.add(user);
    }

}
