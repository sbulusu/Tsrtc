package com.sridhar.testprojects.Tsrtc;

import java.text.ParseException;

import org.testng.annotations.Test;

import com.paypal.selion.annotations.WebTest;
import com.sridhar.common.commonMethods;

public class CheckIfBookingsAllowed {

    private commonMethods commonMethods = new commonMethods();

    @Test
    @WebTest
    public void checkIfBookingIsAllowed() throws ParseException {

        commonMethods.checkIfBookingIsAllowed("16/01/2015");
    }
}
