package lesson_15_tests;

import core.be.TempMailApi;
import core.fe.lesson_15_hw.TempMailCreateEmailPage;
import core.fe.lesson_4_5_hw.hotline_pages.HotlineRegistrationPage;
import io.restassured.response.Response;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class HotlineApiTestSuite extends BaseTest {

    @Test
    public void checkHotlineRegistrationLink() {
        webDriver.get("https://temp-mail.org/ru/option/change/");
        final TempMailCreateEmailPage createEmailPage = new TempMailCreateEmailPage(webDriver);

        String login = generateRandomString().toLowerCase();
        String password = generateRandomString();
        String domain = createEmailPage.getDefaultDomainName();
        String loginHash = convertStringToHash(login);

        createEmailPage.createNewEmail(login);
        String expectedEmailName = login+domain;
        String actualEmailName = createEmailPage.getCreatedEmail();

        Assert.assertEquals("Email was created incorrectly", expectedEmailName, actualEmailName);

        webDriver.get("https://hotline.ua/register/");
        final HotlineRegistrationPage registrationPage = new HotlineRegistrationPage(webDriver);

        registrationPage.registerNewUser(actualEmailName, password, login);

        final TempMailApi tempMailApi = new TempMailApi();
        Response response = tempMailApi.getEmailsList(loginHash);

        System.out.println("Response is: " + response);
    }
}
