package com.sridhar.common;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.test.bluefin.testcomponents.test.HomePage;
import com.paypal.test.bluefin.testcomponents.test.OneSixtyBy2LoginPage;
import com.paypal.test.bluefin.testcomponents.test.SelectBusPage;

public class commonMethods {
	
	private HomePage homePage = new HomePage();
	private OneSixtyBy2LoginPage oneSixtyBy2LoginPage = new OneSixtyBy2LoginPage();
	private SelectBusPage selectBusPage = new SelectBusPage();
	
	public void checkIfBookingIsAllowed(String date) {
		
		Grid.driver().get("http://tsrtcbus.in");
		homePage.setFromTextFieldValue("NIZAMABAD - NZB");
		WebDriverWaitUtils.waitUntilElementIsVisible(homePage.getFromUlTextField().getLocator());
		String locator = homePage.getFromLiTextField().getLocator();
		locator = locator.split("css=")[1];
		java.util.List<WebElement> elements = Grid.driver().findElements(By.cssSelector(locator));
		Boolean optionFound = false;
		for(WebElement element : elements) {
			if(element.findElement(By.tagName("a")).getText().contains("NIZAMABAD - NZB")){
				optionFound = true;
				element.findElement(By.tagName("a")).click();
				break;
			}
		}
		Assert.assertTrue("The given option could not be found", optionFound);
		homePage.setToTextFieldValue("BNG KEMPEGOWDA BS TERMINAL 1 - BENGALURE");
		WebDriverWaitUtils.waitUntilElementIsVisible(homePage.getToUlTextField().getLocator());
		locator = homePage.getToLiTextField().getLocator();
		locator = locator.split("css=")[1];
		elements = Grid.driver().findElements(By.cssSelector(locator));
		optionFound = false;
		for(WebElement element : elements) {
			if(element.findElement(By.tagName("a")).getText().contains("BNG KEMPEGOWDA BS TERMINAL 1 - BENGALURE")){
				optionFound = true;
				element.findElement(By.tagName("a")).click();
				break;
			}
		}
		Assert.assertTrue("The given option could not be found", optionFound);
		homePage.setAdultsTextFieldValue("1");
		homePage.setChildsTextFieldValue("0");
		homePage.selectBusTypeSelectListByLabel("A/C CLASS");
		homePage.selectConcessionSelectListByLabel("GENERAL PUBLIC");
		homePage.setDepartOnTextFieldValue(date);
		homePage.clickCheckAvailabilityButton();
		
		WebDriverWaitUtils.waitUntilElementIsVisible(selectBusPage.getBackButton().getLocator());
		
		Boolean found = SeleniumUtils.isElementVisible(selectBusPage.getOddRowTextField().getLocator());

		String message = null;
		if(found) {
			message = "You can now book the tickets for: "+date;
		} else {
		}
		
		if(found) {
			Grid.driver().get("http://www.160by2.com/Index");
			oneSixtyBy2LoginPage.setMobileNumberTextFieldValue("9902565036");
			oneSixtyBy2LoginPage.setPasswordTextFieldValue("sairam");
			oneSixtyBy2LoginPage.clickLoginButton();
			
			Grid.driver().switchTo().frame("by2Frame");
			oneSixtyBy2LoginPage.clickSendFreeSMSButton(oneSixtyBy2LoginPage.getEnterMobileNumberOrNameTextField().getLocator());
			oneSixtyBy2LoginPage.setEnterMobileNumberOrNameTextFieldValue("9902565036");
			oneSixtyBy2LoginPage.setEnterYourMessageTextFieldValue(message);
			oneSixtyBy2LoginPage.clickSendNowButton();
		}
	}
}
