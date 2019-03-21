package lesson_9_tets;

import core.lesson_9_hw.JqueryBootgridPage;
import lesson_4_5_hw_tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.util.List;

public class JQueryBootgridTestSuite extends BaseTest {

    @Test
    public void checkSortingByColumn() {
        webDriver.get("http://www.jquery-bootgrid.com/examples#selection");
        final JqueryBootgridPage jqueryBootgridPage = new JqueryBootgridPage(webDriver);
        jqueryBootgridPage.formTable();
        jqueryBootgridPage.selectRowsQty("All");
        final String columnToSort = "Received";
        jqueryBootgridPage.sortColumn(columnToSort);

        final List<WebElement> columnElements = jqueryBootgridPage.getColumnValuesElements(columnToSort);
        final List<LocalDateTime> columnResults = getDateTimeValues(getStringValues(columnElements));
        Assert.assertTrue("Column values are not sorted!", isDatesListSorted(columnResults));
    }

    @Test
    public void checkSortingByTwoColumns() {
        webDriver.get("http://www.jquery-bootgrid.com/examples#selection");
        final JqueryBootgridPage jqueryBootgridPage = new JqueryBootgridPage(webDriver);
        jqueryBootgridPage.formTable();
        jqueryBootgridPage.selectRowsQty("All");
        final String firstColumnToSort = "Received";
        jqueryBootgridPage.sortColumn(firstColumnToSort);

        final String secondColumnToSort = "Sender";
        jqueryBootgridPage.sortColumn(secondColumnToSort);

        final List<WebElement> firstColumnElements = jqueryBootgridPage.getColumnValuesElements(firstColumnToSort);
        final List<LocalDateTime> firstColumnResults = getDateTimeValues(getStringValues(firstColumnElements));

        final List<WebElement> secondColumnElements = jqueryBootgridPage.getColumnValuesElements(secondColumnToSort);
        final List<String> secondColumnResults = getStringValues(secondColumnElements);

        Assert.assertFalse("Column values are sorted!", isDatesListSorted(firstColumnResults));
        Assert.assertTrue("Column values are not sorted!", isStringListSorted(secondColumnResults));
    }

    @Test
    public void checkExcludingColumn() {
        webDriver.get("http://www.jquery-bootgrid.com/examples#selection");
        final JqueryBootgridPage jqueryBootgridPage = new JqueryBootgridPage(webDriver);
        jqueryBootgridPage.formTable();
        jqueryBootgridPage.selectRowsQty("All");
        final String columnToExclude = "Link";
        jqueryBootgridPage.selectColumn(columnToExclude);

        Assert.assertFalse("The column is still present in the table!", jqueryBootgridPage.hasColumn(columnToExclude));
    }

    @Test
    public void checkSearchResults() throws InterruptedException {
        webDriver.get("http://www.jquery-bootgrid.com/examples#selection");
        final JqueryBootgridPage jqueryBootgridPage = new JqueryBootgridPage(webDriver);
        jqueryBootgridPage.formTable();
        jqueryBootgridPage.selectRowsQty("All");
        final String columnToSearchBy = "ID";
        final String searchInput = "99";
        jqueryBootgridPage.searchBy(searchInput);

        Assert.assertTrue("Search results are not correct!",
                jqueryBootgridPage.hasCorrectSearchResults(columnToSearchBy, searchInput));
    }
}
