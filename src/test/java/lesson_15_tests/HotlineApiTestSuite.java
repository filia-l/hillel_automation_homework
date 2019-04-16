package lesson_15_tests;

import core.be.TempMailApi;
import core.fe.lesson_15_hw.TempMailCreateEmailPage;
import core.fe.lesson_4_5_hw.hotline_pages.HotlineMainPage;
import core.fe.lesson_4_5_hw.hotline_pages.HotlineRegistrationPage;
import lesson_4_5_hw_tests.BaseTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

public class HotlineApiTestSuite extends BaseTest {

    @Test
    public void checkHotlineRegistrationLink() {
        webDriver.get("https://temp-mail.org/ru/option/change/");
        final TempMailCreateEmailPage createEmailPage = new TempMailCreateEmailPage(webDriver);

        final String login = generateRandomString().toLowerCase();
        final String password = generateRandomString();
        final String expectedEmailName = createEmailPage.createNewEmail(login);
        final String emailHash = convertStringToHash(expectedEmailName);

        final String actualEmailName = createEmailPage.getCreatedEmail();

        Assert.assertEquals("Email was created incorrectly", expectedEmailName, actualEmailName);

        webDriver.get("https://hotline.ua/register/");
        final HotlineRegistrationPage registrationPage = new HotlineRegistrationPage(webDriver);

        registrationPage.registerNewUser(actualEmailName, password, login);

        final TempMailApi tempMailApi = new TempMailApi();
        final String response = tempMailApi.getEmailsList(emailHash);

        final Document email = Jsoup.parse(response);
        final Element linkElement = email.select("a[href*=hotline.ua/confirm]").first();
        final String registrationLink = linkElement.attr("href");

        webDriver.get(registrationLink);
        final String expectedMessage = "Вы успешно зарегистрировались на hotline.ua";

        Assert.assertEquals("Success message is incorrect!", expectedMessage, registrationPage.getConfirmationMessage());

        webDriver.get("https://hotline.ua/");
        final HotlineMainPage hotlineMainPage = new HotlineMainPage(webDriver);
        final String actualLoggedUser = hotlineMainPage.getLoggedInUserName();

        Assert.assertEquals("Incorrect user is displayed!", login, actualLoggedUser);
    }
}
