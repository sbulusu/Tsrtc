package com.sridhar.testprojects.Tsrtc;

import org.testng.annotations.Test;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;

public class GoogleTest {

    @Test
    @WebTest
    public void test1() {
        // Grid.driver().get("http://www.google.com");
        Grid.driver().get("http://loner-staging.blacklinegps.com");
        // try {
        // Thread.sleep(10000);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // Grid.driver().navigate().refresh();
        // Set<Cookie> cookies = Grid.driver().manage().getCookies();
        // String sessionId = null;
        // for (Cookie cookie : cookies) {
        // if (cookie.getName().equals("session")) {
        // sessionId = cookie.getValue();
        // }
        // }
        // Assert.assertNotNull(sessionId, "Could not find the session.");
        // String cookie = "session=" + sessionId;
        // System.out.println(cookie);
    }

}
