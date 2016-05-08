package com.williamhill.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Agustin on 5/4/2016.
 */
public class SportPage extends MainPage {

    private WebDriver driver;

    /**
     * Contains events for Highlights & BET LIVE
     */
    @FindBys(@FindBy(css = "#newBetLiveHolder div[class='marketHolderExpanded'] table tbody a span, #matchHighlightsFrame div[class='sportHighlights'] table tbody a span"))
    private List<WebElement> events;

    public SportPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Verify if the given event exists
     *
     * @param event
     * @return true if the event exists, false if not
     */
    public boolean verifiyIfEventExists(String event) {
        boolean result = false;
        for (WebElement e : events) {
            if (e.getText().trim().equals(event)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Navigate to given event
     *
     * @param event
     * @return Event page
     */
    public EventPage navigateToEvent(String event) {
        EventPage page = null;
        for (WebElement e : events) {
            if (e.getText().trim().equals(event)) {
                e.click();
                page = PageFactory.initElements(driver, EventPage.class);
                break;
            }
        }
        return page;
    }

}
