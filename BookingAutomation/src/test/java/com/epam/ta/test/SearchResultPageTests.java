package com.epam.ta.test;

import com.epam.ta.model.PageError;
import com.epam.ta.model.User;
import com.epam.ta.page.LoginPage;
import com.epam.ta.page.SearchResultPage;
import com.epam.ta.service.PageErrorCreator;
import com.epam.ta.service.TestDataReader;
import com.epam.ta.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SearchResultPageTests extends CommonConditions{
    private static final String TESTDATA_CASE_4_EXPECTED_CURRENCY = "testdata.case4.expected.currency";

    @Test(testName = "testcase 4 : One can change currency")
    public void changingCurrencyToUsdTest(){
        String currentCurrency = new SearchResultPage(driver)
                .openPage()
                .changeCurrencyToUsd()
                .getCurrentCurrency();
        assertThat(currentCurrency, is(equalTo(TestDataReader.getTestData(TESTDATA_CASE_4_EXPECTED_CURRENCY))));
    }
}
