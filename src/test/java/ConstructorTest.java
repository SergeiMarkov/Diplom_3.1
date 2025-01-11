import user.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    private User user;

    @Before
    @Step("Создание пользователя для теста и добавление в список для удаления после теста")
    public void createTestUser(){
        user = User.createUser();
        addUserToDeleteListTearDown(user);
    }

    @Test
    @DisplayName("Проверка переключения активной кнопки на булки")
    public void checkBunsTabSelectedDefaultTabTest(){
        new MainPage();

        assertTrue(new MainPage().isBunsTabSelected());
    }

    @Test
    @DisplayName("Проверка переключения активной кнопки на соусы")
    public void checkSaucesTabSelectedIfClickSaucesTabTest(){
        new MainPage()
                .clickSaucesTab();

        assertTrue(new MainPage().isSaucesTabSelected());
    }

    @Test
    @DisplayName("Проверка переключения активной кнопки на начинки")
    public void checkFillingsTabSelectedIfClickBunsTabTest(){
        new MainPage()
                .clickFillingsTab();

        assertTrue(new MainPage().isFillingsTabSelected());
    }

    @Test
    @DisplayName("Проверка автоскролла при клике до булок в меню")
    public void checkScrolledToBunsHeaderIfClickOnBunsTabTest() {
        new MainPage()
                .clickSaucesTab()
                .clickBunsTab();

        assertTrue(new MainPage().isBunsIngredientSelected());
    }

    @Test
    @DisplayName("Проверка автоскролла при клике до соусов в меню")
    public void checkScrolledToSaucesHeaderIfClickOnSaucesTabTest() {
        new MainPage()
                .clickSaucesTab();

        assertTrue(new MainPage().isSaucesIngredientSelected());
    }

    @Test
    @DisplayName("Проверка автоскролла при клике до начинок в меню")
    public void checkScrolledToFillingsHeaderIfClickOnFillingsTabTest() {
        new MainPage()
                .clickFillingsTab();

        assertTrue(new MainPage().isFillingsIngredientSelected());
    }

    @Test
    @DisplayName("Проверка скролла до булок в меню")
    public void ScrollToBunsHeaderIfClickOnBunsTabTest() {
        assertTrue(
                new MainPage()
                        .isScrolledToBunsHeaderInMenu()
                        .isBunsTabSelected()
        );
    }

    @Test
    @DisplayName("Проверка скролла до соусов в меню")
    public void ScrolledToSaucesHeaderIfClickOnSaucesTabTest() {
        assertTrue(
                new MainPage()
                        .isScrolledToSaucesHeaderInMenu()
                        .isSaucesTabSelected()
        );
    }

    @Test
    @DisplayName("Проверка скролла до начинок в меню")
    public void ScrolledToFillingsHeaderIfClickOnFillingsTabTest() {
        assertTrue(
                new MainPage()
                        .isScrolledToFillingsHeaderInMenu()
                        .isFillingsTabSelected()
        );
    }

}
