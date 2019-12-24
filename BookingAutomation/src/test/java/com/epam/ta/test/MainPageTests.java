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
    private static final String TESTDATA_CASE_6_LANGUAGE_NAME = "testdata.case6.language.name";
    private static final String TESTDATA_CASE_7_PICK_UP_LOCATION = "testdata.case7.pick.up.location";
    private static final String TESTDATA_CASE_8_NOT_AVAILABLE_LOCATION = "testdata.case8.not.available.location";

    @Test(testName = "testcase 5 : incorrect pick up location input")
    public void incorrectPickUpLocationTest()
    {
        MainPage page = new MainPage(driver);
        page.openPage().inputPickUpLocation(TestDataReader.getTestData(TESTDATA_CASE_5_PICK_UP_LOCATION)).submitReservation();
        PageError expectedError = PageErrorCreator.incorrectLocationInput();
        Assert.assertTrue(page.checkIncorrectLocationMessage(expectedError));
    }

    @Test(testName = "testcase 6 : one can change language")
    public void changeLanguageTest(){
        MainPage page = new MainPage(driver);
        page.openPage()
        .changeLanguage();
        Assert.assertEquals(page.getLanguage(), TestDataReader.getTestData(TESTDATA_CASE_6_LANGUAGE_NAME));
    }

    @Test(testName = "testcase 7 : correct pick up location")
    public void correctPickUpLocationTest()
    {
        MainPage page = new MainPage(driver);
        SearchResultPage searchResultPage = page.openPage().inputCorrectPickUpLocation(TestDataReader.getTestData(TESTDATA_CASE_7_PICK_UP_LOCATION));
        Assert.assertEquals(TestDataReader.getTestData(TESTDATA_CASE_7_PICK_UP_LOCATION), searchResultPage.getResultPickUpCity());
    }

    @Test(testName = "testcase 8 : no car available")
    public void carNotAvailableTest()
    {
        MainPage page = new MainPage(driver);
        SearchResultPage searchResultPage = page.openPage().inputCorrectPickUpLocation(TestDataReader.getTestData(TESTDATA_CASE_8_NOT_AVAILABLE_LOCATION));
        PageError expectedError = PageErrorCreator.notAvailableLocationError();
        Assert.assertTrue(searchResultPage.checkNotAvailableLocationMessage(expectedError));
    }
}
