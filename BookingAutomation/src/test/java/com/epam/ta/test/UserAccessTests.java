package com.epam.ta.test;

import com.epam.ta.model.PageError;
import com.epam.ta.model.User;
import com.epam.ta.page.LoginPage;
import com.epam.ta.service.PageErrorCreator;
import com.epam.ta.service.UserCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class UserAccessTests extends CommonConditions {

	private final Logger logger = LogManager.getRootLogger();

	@Test(testName = "testcase 1 : One can login Booking")
	public void loginBookingTest()
	{
		User testUser = UserCreator.withCredentialsFromProperty();
		String loggedInUserName = new LoginPage(driver)
				.openPage()
				.login(testUser)
				.getLoggedInUserName();
		assertThat(loggedInUserName, is(equalTo(testUser.getUsername())));
	}

	@Test(testName = "testcase 2 : Login with incorrect password")
	public void incorrectPasswordLoginTest()
	{
		User testUser = UserCreator.withIncorrectData();
		LoginPage page = new LoginPage(driver);
		page.openPage().login(testUser);
		PageError expectedError = PageErrorCreator.incorrectUserDataError();
		Assert.assertTrue(page.checkIncorrectPasswordMessage(expectedError));
	}

	@Test(testName = "testcase 3 : Login with not registered email")
	public void notRegisteredEmailTest()
	{
		User testUser = UserCreator.withNotRegisteredEmail();
		LoginPage page = new LoginPage(driver);
		page.openPage().inputEmail(testUser.getEmail());
		PageError expectedError = PageErrorCreator.notRegisteredEmailError();
		Assert.assertTrue(page.checkNotRegisteredEmailMessage(expectedError));
	}
}
