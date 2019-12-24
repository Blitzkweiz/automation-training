package com.epam.ta.page;

import com.epam.ta.model.PageError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class SearchResultPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://cars.booking.com/SearchResults.do";

    @FindBy(id = "rch-select-currency")
    private WebElement currencyBtn;

    @FindBy(id = "cur_USD")
    private WebElement usdCurrency;

    @FindBy(className = "rch-icon")
    private WebElement currentCurrency;

    @FindBy(className = "ab-SearchSummary_PickUp_City")
    private WebElement resultPickUpCity;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SearchResultPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("SearchResultPage opened");
        return this;
    }

    public SearchResultPage changeCurrencyToUsd()
    {
        currencyBtn.click();
        usdCurrency.click();
        logger.info("currency changed to USD");
        return this;
    }

    public String getCurrentCurrency()
    {
        return currentCurrency.getText();
    }

    public String getResultPickUpCity()
    {
        return resultPickUpCity.getText();
    }

    public boolean checkNotAvailableLocationMessage(PageError error){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"backMoreCarsForm\"]/div[2]/h1"));
        logger.info(errorMessage.getText());
        return errorMessage.isDisplayed()
                && errorMessage.getText()
                .contains(error.getErrorDescription());
    }
}
