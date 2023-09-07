package org.automator.tests;

import org.automater.setup.AppEvents;
import org.automater.setup.BaseSetup;
import org.testng.annotations.Test;

public class BasicTest extends BaseSetup{
    AppEvents appEvents;

    @Test
    public void execBasicTest() {
        appEvents = new AppEvents(getDriver());
        appEvents.visitHomeLinks();
    }
}
