package com.sridhar.testprojects.Tsrtc;

import org.testng.annotations.Test;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;

public class GoogleTest {

    @Test
    @WebTest
    public void test1() {
        Grid.driver().get("http://www.google.com");
    }

}
