package com.epam.ta.test;

import com.epam.ta.model.PageError;
import com.epam.ta.model.User;
import com.epam.ta.page.LoginPage;
import com.epam.ta.page.MainPage;
import com.epam.ta.page.SearchResultPage;
import com.epam.ta.service.PageErrorCreator;
import com.epam.ta.service.TestDataReader;
import com.epam.ta.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MainPageTests extends CommonConditions{
    private static final String TESTDATA_CASE_5_PICK_UP_LOCATION = "testdata.case5.pick.up.location";

    @Test(testName = "testcase 5 : incorrect pick up location input")
    public void incorrectPickUpLocationTest()
    {
        MainPage page = new MainPage(driver);
        page.openPage().inputPickUpLocation(TestDataReader.getTestData(TESTDATA_CASE_5_PICK_UP_LOCATION)).submitReservation();
        PageError expectedError = PageErrorCreator.incorrectLocationInput();
        Assert.assertTrue(page.checkIncorrectLocationMessage(expectedError));
    }
}
