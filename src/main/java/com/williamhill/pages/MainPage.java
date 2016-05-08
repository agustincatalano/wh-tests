package com.williamhill.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {

    @FindBy(css="#mainNavB")
    private WebElement mainNavBar;

    @FindBy(id="football")
    private WebElement footballLink;

    @FindBy(id="cricket")
    private WebElement cricketLink;

    @FindBy(id = "horse_racing")
    private WebElement horseRacingLink;

    @FindBy(id = "tennis")
    private WebElement tennisLink;

    @FindBys(@FindBy(css="#footerSub a"))
    private List<WebElement> footerLinks;

    @FindBy(id="slipEmpty")
    private WebElement slipEmpty;

    private WebDriver driver;

    private final String URL = "http://sports.williamhill.com/";

    public final String FOOTBALL = "Football";
    public final String CRICKET  = "Cricket";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void get() {
        driver.get(URL);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Clicks on the given sport
     * @param sport
     * @return Sport page
     */
    public MainPage navigateToSport(String sport) {
        if(FOOTBALL.equals(sport)) {
            footballLink.click();
        }else if(CRICKET.equals(sport)) {
            cricketLink.click();
        }
        return PageFactory.initElements(driver, SportPage.class);
    }

    /**
     * Verifies that Football link exists
     * @return true if exists, false if not
     */
    public boolean footBallLinkExist() {
        return footballLink.isDisplayed();
    }

    /**
     * Verifies that Horse Racing link exists
     * @return true if exists, false if not
     */
    public boolean horseRacingLinkExist(){
        return horseRacingLink.isDisplayed();
    }

    /**
     * Verifies that Tennis link exists
     * @return true if exists, false if not
     */
    public boolean tennisLinkExist(){
            return tennisLink.isDisplayed();
    }

    /**
     * Verify the GambleAware Link is present
     * @return true if the link is present, false if not
     */
    public boolean verifyGambleAwareLink() {
        boolean res = false;
        final String gambleAware = "http://www.gambleaware.co.uk/";
        for(WebElement link : footerLinks) {
            if(gambleAware.equals(link.getAttribute("href"))) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * Verifies that Betslip section exists
     * @return true if exists, false if not
     */
    public boolean verifyBetSlip() {
        return slipEmpty.isDisplayed();
    }
}
