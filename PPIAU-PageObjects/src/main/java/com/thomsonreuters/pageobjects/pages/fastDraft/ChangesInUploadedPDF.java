package com.thomsonreuters.pageobjects.pages.fastDraft;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
//Class is used for Fast Draft suite for the feature when user is able to upload a PDF, cahnge it and then upload back.
//Url: http://d100-infra.dev.practicallaw.com:8080/da/uploadPDFToDoc 
//Name - ChangesInUploadedPDFFastDraft 

public class ChangesInUploadedPDF extends AbstractPage {

	private static final String ERROR = "PDF Upload error : The form you selected does not belong to the project you tried to load it into. Please select the correct form and try again";
	private static final String TYPE_ERROR = "PDF Upload error : The selected file is not the correct type (PDF). Please try again ensuring you select a PDF file";

    private CommonMethods comMethods = new CommonMethods();

	public void checkChangesInUploadedPDFDisplayed() {
		WebElement changesInUploadedPDF = comMethods
				.waitForElementToBeVisible(By.xpath("//*[text()='Changes in the uploaded PDF']"));
		Assert.assertNotNull(changesInUploadedPDF, "Changes in the uploaded PDF absents");
	}

	public void checkSectionHasOriginalAndRevicedValues(String sectionName, String originalValue, String revisedValue) {
		WebElement sectionWithValues = comMethods.waitForElementToBeVisible(
				By.xpath("//tr[contains(.,'" + sectionName + "') and contains(.,'" + originalValue
						+ "') and contains(.,'" + revisedValue + "')]"));
		Assert.assertNotNull(sectionWithValues, "Section with values absents");
	}

	public WebElement section(String sectionName) {
		return comMethods.waitForElementToBeVisible(By.xpath("//tr[contains(.,'" + sectionName + "')]//td/input[@id and @type='checkbox']"));
	}

	public WebElement acceptChanges() {
		return waitForExpectedElement(By.xpath("//input[@value='Accept Changes']"));
	}

	public boolean isDocumentPageWithErrorPresents() {
		return isElementDisplayed(By.xpath("//*[text()='" + ERROR + "']")) && getCurrentUrl().contains("uploadPDF");	
	}

	public boolean isDocumentPageWithUploadNotCorrectTypeErrorPresents() {
		return isElementDisplayed(By.xpath("//*[text()='" + TYPE_ERROR + "']")) && getCurrentUrl().contains("uploadPDF");
	}
}