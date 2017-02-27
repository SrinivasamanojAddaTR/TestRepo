package com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JournalRequestFormPage extends DocumentDisplayAbstractPage {

	public WebElement widget() {
		return retryingFindElement(By.xpath("//div[@id='co_purchaseForm_widget']"));
	}

	public WebElement widgetTitle() {
		return findChildElement(widget(), By.xpath(".//h3"));
	}

	public WebElement formFieldLabel(String label) {
		return retryingFindElement(By.xpath("//form[@id='co_mainForm']//div[@class='co_purchaseForm_line']/label[contains(text(),'" + label
				+ "')]"));
	}

	public WebElement formField(String label) {
		return findChildElement(formFieldLabel(label), By.xpath("./../input"));
	}

	public WebElement radioButton(String label) {
		return retryingFindElement(By
				.xpath("//*[@id='co_purchaseForm_deliveryMethod']/div[@class='co_purchaseForm_line']/label[contains(.,'" + label
						+ "')]/input"));
	}

	public WebElement agreeCheckbox() {
		return retryingFindElement(By.id("co_purchaseForm_policyCheck"));
	}


	public WebElement xButton() {
		return retryingFindElement(By.id("co_purchaseForm_lightbox_closeButton"));
	}

	public Alert waitForAlertDialog() {
		Wait wait = new WebDriverWait(getDriver, 30);
		Alert alert = (Alert) wait.until(visibilityOfAlert());
		return alert;
	}

	public ExpectedCondition<Alert> visibilityOfAlert() {
		return new ExpectedCondition<Alert>() {
			public Alert apply(WebDriver driver) {
				try {
					Alert alert = getDriver.switchTo().alert();
					return alert;
				} catch (NoAlertPresentException e) {
					try {
						Thread.sleep(1000L);
						return null;
					} catch (InterruptedException ie) {
						throw new RuntimeException(ie);
					}
				}
			}
		};
	}
}
