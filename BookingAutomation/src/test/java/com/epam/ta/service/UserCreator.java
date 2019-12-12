package com.epam.ta.service;

import com.epam.ta.model.User;

public class UserCreator {

    public static final String TESTDATA_CASE_1_USER_NAME = "testdata.case1.user.name";
    public static final String TESTDATA_CASE_1_USER_PASSWORD = "testdata.case1.user.password";
    public static final String TESTDATA_CASE_1_USER_EMAIL = "testdata.case1.user.email";

    public static final String TESTDATA_CASE_2_USER_PASSWORD = "testdata.case2.user.password";
    public static final String TESTDATA_CASE_2_USER_EMAIL = "testdata.case2.user.email";

    public static final String TESTDATA_CASE_3_USER_EMAIL = "testdata.case3.user.email";

    public static User withCredentialsFromProperty(){
        return new User(TestDataReader.getTestData(TESTDATA_CASE_1_USER_NAME),
                TestDataReader.getTestData(TESTDATA_CASE_1_USER_PASSWORD),
                TestDataReader.getTestData(TESTDATA_CASE_1_USER_EMAIL));
    }

    public static User withIncorrectData(){
        return new User().setPassword(TestDataReader.getTestData(TESTDATA_CASE_2_USER_PASSWORD))
                .setEmail(TestDataReader.getTestData(TESTDATA_CASE_2_USER_EMAIL));
    }

    public static User withNotRegisteredEmail(){
        return new User().setEmail(TestDataReader.getTestData(TESTDATA_CASE_3_USER_EMAIL));
    }
}
