package lesson_15_tests;

import core.fe.lesson_15_hw.TempMailCreateEmailPage;
import core.fe.lesson_7_hw.sportcheck_pages.SportcheckMainPage;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SportchekApiTestSuite extends BaseTest {

    @Test
    public void checkAddingItemWithRegisteredUser() {
        webDriver.get("https://temp-mail.org/ru/option/change/");
        final TempMailCreateEmailPage createEmailPage = new TempMailCreateEmailPage(webDriver);

        final String login = generateRandomString().toLowerCase();
        final String password = generateRandomString();
        final String expectedEmailName = createEmailPage.createNewEmail(login);

        final String actualEmailName = createEmailPage.getCreatedEmail();
        Assert.assertEquals("Email was created incorrectly", expectedEmailName, actualEmailName);

        webDriver.get("https://www.sportchek.ca/");
        final SportcheckMainPage sportcheckMainPage = new SportcheckMainPage(webDriver);
        sportcheckMainPage.registerUser(expectedEmailName, password);
        sportcheckMainPage.goToMyAccount();
        final String actualRegisteredUser = sportcheckMainPage.getRegisteredEmail();

        Assert.assertEquals("Incorrect registered email is shown!", expectedEmailName, actualRegisteredUser);
    }
}
