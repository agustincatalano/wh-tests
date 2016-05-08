package com.williamhill;

import java.util.List;

import com.williamhill.driverFactory.DriverFactory;
import com.williamhill.pages.EventPage;
import com.williamhill.reader.TestDataReader;
import com.williamhill.reader.TestDataReader.TestData;
import com.williamhill.pages.MainPage;
import com.williamhill.pages.SportPage;
import com.williamhill.utils.TestUtils;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SportsbookBrowserTest extends TestCase {

    private List<TestData> testData;
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        testData = TestDataReader.getTestData();
        driver = new DriverFactory().getWebDriver();
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    @After
    public void tearDown() {
        driver.close();
    }


    @Test
    public void testElementValidation_TC1() {
        mainPage.get();
        TestUtils.closeAllPopUps(driver);
        assertLinks(mainPage);
    }

    @Test
    public void testNavigateToSport_TC2() {
        for (TestData data : testData) {
            mainPage.get();
            TestUtils.closeAllPopUps(driver);
            SportPage sportPage = (SportPage) mainPage.navigateToSport(data.getSport());
            Assert.assertTrue("We couldn't find the gamble aware link in page belonging to " + sportPage.getClass().toString(), sportPage.verifyGambleAwareLink());
            Assert.assertTrue("We couldn't find the bet slip empty in " + sportPage.getClass().toString(), sportPage.verifyBetSlip());
            Assert.assertTrue("The tile does not contain: " + data.getSport(), sportPage.getTitle().contains(data.getSport()));
        }
    }

    /**
     * This test case will fail if the databank.csv is not update properly, we are going to compare the data in the csv with
     * the data in UI page for each sport and event
     */
    @Test
    public void testNavigateToEvent_TC3() {
        for (TestData data : testData) {
            mainPage.get();
            TestUtils.closeAllPopUps(driver);
            SportPage sportPage = (SportPage) mainPage.navigateToSport(data.getSport());
            Assert.assertTrue("The event : " + data.getEvent() + " was not found, we can't proceed further", sportPage.verifiyIfEventExists(data.getEvent()));
            EventPage eventPage = sportPage.navigateToEvent(data.getEvent());
            Assert.assertNotNull("The event : " + data.getEvent() + " was initially found but it is now missing, probably the events list refreshed and the event is no longer present", eventPage);
            Assert.assertTrue("We couldn't find the gamble aware link in page belonging to " + sportPage.getClass().toString(), sportPage.verifyGambleAwareLink());
            Assert.assertTrue("We couldn't find the bet slip empty in " + sportPage.getClass().toString(), sportPage.verifyBetSlip());
            Assert.assertTrue("The tile does not contain: " + data.getEvent(), eventPage.getTitle().replace(" ", "").contains(data.getEvent().replace(" ", "")));

        }

    }

    private static void assertLinks(MainPage mainPage) {
        Assert.assertTrue("Football link is not present", mainPage.footBallLinkExist());
        Assert.assertTrue("Horse Racing link is not present", mainPage.horseRacingLinkExist());
        Assert.assertTrue("Tennis link is not present", mainPage.tennisLinkExist());
        Assert.assertTrue("Gamble aware link is not present", mainPage.verifyGambleAwareLink());
    }


}
