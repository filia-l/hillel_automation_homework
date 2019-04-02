package lesson_13_tests;

import core.be.dto.CnnApi;
import core.be.dto.Result;
import core.fe.lesson_13_hw.CnnPage;
import io.restassured.RestAssured;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Arrays;
import java.util.List;

public class CnnTestSuite extends BaseTest {

    @Test
    public void verifySearchResults() {
        webDriver.get("https://edition.cnn.com/");
        final CnnPage cnnPage = new CnnPage(webDriver);
        cnnPage.inputSearchText("bitcoin");

        final List<Result> expectedData = Arrays.asList(cnnPage.getSearchResults().getResult());

        RestAssured.baseURI = "https://search.api.cnn.io/";
        final String searchResultsUri = "content?size=10&q=bitcoin";

        final CnnApi cnnApi = new CnnApi();
        final List<Result> actualData = Arrays.asList(cnnApi.getSearchResults(searchResultsUri).getResult());

        Assert.assertEquals("Actual list size is not equal to expected", expectedData.size(), actualData.size());

        for (int i = 0; i < actualData.size(); i++) {
            ReflectionAssert.assertReflectionEquals("Search results data is not equal!",expectedData.get(i), actualData.get(i));
        }
    }
}
