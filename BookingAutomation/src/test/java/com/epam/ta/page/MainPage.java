package com.epam.ta.page;

import com.epam.ta.model.PageError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class MainPage extends AbstractPage {
	private final String BASE_URL = "https://www.booking.com//cars/";
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(className = "user_firstname")
	private WebElement linkLoggedInUser;

	@FindBy(id = "ss_origin")
	private WebElement pickUpLocationInput;

	@FindBy(className = "calendar-restructure-sb")
	private WebElement calendarBtn;

	@FindBy(xpath = "//*[@id=\"destination__error\"]/div")
	private WebElement incorrectLocationMessage;

	@FindBy(className = "sb-searchbox__button")
	private WebElement submitBtn;

	@FindBy(id = "b_tt_holder_1")
	private WebElement languageBtn;

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public MainPage openPage() {
		driver.navigate().to(BASE_URL);
		logger.info("MainPage opened");
		return this;
	}

	public String getLoggedInUserName() {
		return linkLoggedInUser.getText();
	}

	public MainPage inputPickUpLocation(String pickUpLocation)
	{
		pickUpLocationInput.sendKeys(pickUpLocation);
		logger.info("PickUpLocation changed");
		return this;
	}

	public SearchResultPage inputCorrectPickUpLocation(String pickUpLocation)
	{
		pickUpLocationInput.sendKeys(pickUpLocation);
		driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.findElements(By.className("search_h1_name"));
		pickUpLocationInput.sendKeys(Keys.ARROW_DOWN);
		pickUpLocationInput.sendKeys(Keys.ARROW_DOWN);
		pickUpLocationInput.sendKeys(Keys.ENTER);
		submitBtn.click();
		return new SearchResultPage(driver);
	}

	public MainPage inputPickUpDates(WebElement pickUpDate, WebElement returnDate)
	{
		calendarBtn.click();
		pickUpDate.click();
		returnDate.click();
		logger.info("PickUpDates changed");
		return this;
	}

	public MainPage changeLanguage()
	{
		driver.findElements(By.className("popover_trigger")).get(1).click();
		WebElement langBtn = driver.findElement(By.id("b_tt_holder_1"));
		langBtn.click();
		driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		WebElement deutschLanguageBtn = driver.findElement(By.className("slang-de"));
		deutschLanguageBtn.click();
		return this;
	}

	public String getLanguage(){
		return languageBtn.getText();
	}

	public MainPage submitReservation(){
		submitBtn.click();
		return this;
	}

	public boolean checkIncorrectLocationMessage(PageError error)
	{
		return incorrectLocationMessage.isDisplayed()
				&& incorrectLocationMessage.getText()
				.contains(error.getErrorDescription());
	}
}
