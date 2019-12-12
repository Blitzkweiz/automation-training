package com.epam.ta.service;

import com.epam.ta.model.PageError;

public class PageErrorCreator {
    private static final String TESTDATA_CASE_2_EXPECTED_MESSAGE = "testdata.case2.expected.message";
    private static final String TESTDATA_CASE_3_EXPECTED_MESSAGE = "testdata.case3.expected.message";
    private static final String TESTDATA_CASE_5_EXPECTED_MESSAGE = "testdata.case5.expected.message";

    public static PageError incorrectUserDataError(){
        return new PageError(TestDataReader.getTestData(TESTDATA_CASE_2_EXPECTED_MESSAGE));
    }

    public static PageError notRegisteredEmailError(){
        return new PageError(TestDataReader.getTestData(TESTDATA_CASE_3_EXPECTED_MESSAGE));
    }

    public static PageError incorrectLocationInput(){
        return new PageError(TestDataReader.getTestData(TESTDATA_CASE_5_EXPECTED_MESSAGE));
    }
}
