package com.sridhar.common;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.paypal.selion.logger.SeLionLogger;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.test.utilities.logging.SimpleLogger;

public class SeleniumUtils {

    static SimpleLogger logger = SeLionLogger.getLogger();

    /**
     * Wait for any of the given text inputs to be present on the page.
     * 
     * @param text
     *            - text input
     * @param timeout
     *            - a cancellation or cessation that automatically occurs when
     *            the predefined interval of time has passed without a certain
     *            event occurring.
     */
    public static void waitForTextToBePresentOnPage(String[] text,
            int... timeout) {

        Boolean found = false;
        String pageText = null;
        int maxTimeOut;

        // WebReporter.log(Grid.driver().getTitle(), true, true);

        if (timeout.length == 0) {
            maxTimeOut = 120;
        } else {
            maxTimeOut = timeout[0];
        }

        for (int i = 0; i < maxTimeOut; i++) {

            pageText = Grid.driver().findElement(By.tagName("body")).getText();

            for (String s : text) {
                if (pageText.contains(s)) {
                    found = true;
                    break;
                }
            }

            if (found.equals(true)) {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
        Assert.assertTrue(found, "Could not locate text on the page.");
    }

    /**
     * Verify that any of the given text inputs are present on the page.
     * 
     * @param searchStrings
     *            : The search strings to be verified on that page.
     * @param pageText
     *            : The text on the page.
     */
    public static Boolean isTextPresentOnPage(String[] searchStrings,
            String... pageText) {

        Boolean found = false;
        String textOnThePage = null;

        if (pageText.length == 0) {
            textOnThePage = Grid.driver().findElement(By.tagName("body"))
                    .getText();
        } else {
            textOnThePage = pageText[0];
        }

        for (String s : searchStrings) {
            if (textOnThePage.contains(s)) {
                found = true;
                break;
            }
        }

        return found;
    }

    /**
	 * 
	 */
    public static void doWaitForElementToEnable(String locator, int... timeout) {

        int maxTimeOut;

        if (timeout.length == 0) {
            maxTimeOut = 120;
        } else {
            maxTimeOut = timeout[0];
        }

        String selector = locator.split("=")[1];

        WebDriverWait webDriverWait = new WebDriverWait(Grid.driver(),
                maxTimeOut);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By
                .cssSelector(selector)));
    }

    public static int waitForEitherOfTheElements(String locator1,
            String locator2) {

        int found = 0;

        locator1 = locator1.replace("css=", "");
        locator2 = locator2.replace("css=", "");

        for (int i = 0; i < 60; i++) {

            try {
                Grid.driver().findElement(By.cssSelector(locator1));
                found = 1;
                break;
            } catch (Exception e) {
            }

            try {
                Grid.driver().findElement(By.cssSelector(locator2));
                found = 2;
                break;
            } catch (Exception e) {
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

        }

        if (1 != found && 2 != found) {
            Assert.fail("Could not locate any of the 2 elements on the webpage.");
        }
        return found;

    }

    public static void refreshPageUntilElementIsVisible(String locator,
            int... timeout) {
        locator = locator.replace("css=", "");
        int maxTimeOut;
        Boolean found = false;

        if (timeout.length == 0) {
            maxTimeOut = 120;
        } else {
            maxTimeOut = timeout[0];
        }

        for (int i = 0; i < maxTimeOut; i++) {
            try {
                Grid.driver().findElement(By.cssSelector(locator));
                found = true;
                break;
            } catch (Exception e) {
            }

            if (found.equals(true)) {
                break;
            } else {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                Grid.driver().navigate().refresh();
            }
        }

        if (found.equals(false)) {
            Assert.fail("The element could not be located on the page.");
        }

    }

    public static Boolean isElementVisible(String locator) {
        locator = locator.split("css=")[1];
        Boolean found = false;
        try {
            Grid.driver().findElement(By.cssSelector(locator));
            found = true;
        } catch (Exception e) {
        }
        return found;
    }

    public static void openBlankPage() {
        Grid.driver().get("about:blank");
    }

    public static void clearcookies() {
        Grid.driver().manage().deleteAllCookies();
    }
}