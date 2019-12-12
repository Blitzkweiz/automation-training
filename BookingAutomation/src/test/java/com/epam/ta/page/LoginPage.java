package com.epam.ta.page;

import com.epam.ta.model.PageError;
import com.epam.ta.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();
	private final String PAGE_URL = "https://account.booking.com/sign-in";

	@FindBy(id = "username")
	private WebElement inputLogin;

	@FindBy(id = "password-error")
	private WebElement incorrectPasswordInput;

	@FindBy(id = "username-error")
	private WebElement notRegisteredEmailInput;

	public LoginPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public LoginPage openPage()
	{
		driver.navigate().to(PAGE_URL);
		logger.info("Login page opened");
		return this;
	}

	public MainPage login(User user)
	{
		inputEmail(user.getEmail());
		driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		inputPassword(user.getPassword());
		logger.info("Login performed");
		return new MainPage(driver);
	}

	public void inputEmail(String email)
	{
		inputLogin.sendKeys(email);
		inputLogin.sendKeys(Keys.ENTER);
	}

	public void inputPassword(String password)
	{
		WebElement inputPassword = driver.findElement(By.id("password"));
		inputPassword.sendKeys(password);
		inputPassword.sendKeys(Keys.ENTER);
	}

	public boolean checkIncorrectPasswordMessage(PageError error){
		return incorrectPasswordInput.isDisplayed()
				&& incorrectPasswordInput.getText()
				.contains(error.getErrorDescription());
	}

	public boolean checkNotRegisteredEmailMessage(PageError error){
		return notRegisteredEmailInput.isDisplayed()
				&& notRegisteredEmailInput.getText()
				.contains(error.getErrorDescription());
	}

}
