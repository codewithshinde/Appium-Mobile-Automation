package org.automator.rakesh;

import org.testng.annotations.DataProvider;

public class NrMembersDataProviders {

    @DataProvider(name = "loginDataProvider")
    public static Object[][] validInvalidLogins() {
        return new Object[][] {
                {"valid_data","Shazam","valid_pass","Test@123"},
                {"valid_data2","kshinde","valid_pass2","Test@123"}
        };
    }
}
