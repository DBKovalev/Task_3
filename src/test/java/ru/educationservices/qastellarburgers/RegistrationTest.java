package ru.educationservices.qastellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.educationservices.qastellarburgers.PageObjects.LoginPage;
import ru.educationservices.qastellarburgers.PageObjects.MainPage;
import ru.educationservices.qastellarburgers.PageObjects.RegisterPage;
import ru.educationservices.qastellarburgers.User.User;
import ru.educationservices.qastellarburgers.User.UserAPI;

import java.util.UUID;

public class RegistrationTest {

    private WebDriver driver;
    private UserAPI userAPI;
    private String accessToken;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        String browser = System.getProperty("browser", "chrome");
        if ("yandex".equals(browser)) {
            String yandexBinary = "C:/Program Files/Yandex/YandexBrowser/Application/browser.exe";
            WebDriverManager.chromedriver()
                    .browserVersion("150")
                    .setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary(yandexBinary);
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        userAPI = new UserAPI();
    }

    @AfterEach
    void tearDown() {
        if (accessToken != null) {
            userAPI.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Пользователь может зарегистрироваться с валидными данными")
    public void successfulRegistrationWithValidDataTest() {
        String email = UUID.randomUUID() + "@example.com";
        String name = "TestName";
        String password = "TestPassword123.";
        User user = new User(email, password, name);

        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitProfilePageVisibility();
        mainPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.clickRegisterButton();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitRegisterPageVisibility();
        registerPage.loginUser(name, email, password);

        Assertions.assertTrue(loginPage.isLoginPageOpened());
        userAPI.setUser(user);
        accessToken = userAPI.loginUser().jsonPath().getString("accessToken");
    }

    @Test
    @DisplayName("Пользователь не может зарегистрироваться невалидным паролем")
    public void unsuccessfulRegistrationWithInvalidDataTest() {
        String email = UUID.randomUUID() + "@example.com";
        String name = "TestName";
        String password = "Tp1.";

        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitProfilePageVisibility();
        mainPage.clickEnterButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageVisibility();
        loginPage.clickRegisterButton();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitRegisterPageVisibility();
        registerPage.loginUser(name, email, password);

        Assertions.assertTrue(registerPage.isIncorrectPasswordErrorVisibility());
    }
}
