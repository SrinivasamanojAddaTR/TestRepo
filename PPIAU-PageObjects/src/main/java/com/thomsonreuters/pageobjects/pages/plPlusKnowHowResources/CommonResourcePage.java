package com.thomsonreuters.pageobjects.pages.plPlusKnowHowResources;

import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.DocumentDisplayAbstractPage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class will contain the pageobjects objects for KH and ASK resources
 */

public class CommonResourcePage extends DocumentDisplayAbstractPage {

	private static final String STICKY_BAR_HEADING = ".co_title .subSectionTitle";
	private static final By SUBSECTION_HEADING = By
			.xpath("//*[@id='co_docStickyHeader']//*[contains(@class,'subSectionTitle')]");
	private static final By ADDITIONAL_TEXT_STICKY_BAR = By.xpath("//*[@id='co_docStickyHeader']//h1");

    public WebElement title() {
        return waitForExpectedElement(By.cssSelector("#co_docHeaderContainer .co_title"));
    }

    public List<WebElement> allHeadings() {
        return contentBody().findElements(By.className("co_headtext"));
    }

	public WebElement headingInDocument(String heading) {
		return contentBody().findElement(By.xpath(".//h2[contains(.,'" + heading + "')]"));
	}

    // Sticky Bar
    public WebElement stickyBar() {
        return waitForExpectedElement(By.xpath("//div[@id='co_docContentHeader']"));
    }
    
    public WebElement titleOnStickyBar() {
        return waitForExpectedElement(By.cssSelector("#co_docStickyHeader .co_title"));
    }

    public WebElement stickyBarTitle(String title) {
        return findElement(By.xpath("//div[@id='co_docHeaderContainer']//h1[text()='" + title + "']"));
    }

    public WebElement subHeadingOnStickyBar() {
		return waitForExpectedElement(By.cssSelector(STICKY_BAR_HEADING));
    }

    public WebElement subSectionHeadingOnStickyBar() {
		return waitForExpectedElement(SUBSECTION_HEADING);
	}

	public WebElement additionalTextOnStickyBar() {
        return waitForExpectedElement(ADDITIONAL_TEXT_STICKY_BAR);
    }

	public boolean isSubSectionHeadingOnStickyBarExists() {
		return isExists(SUBSECTION_HEADING);
    }


    public WebElement relatedContentLinkOnStickyBar() {
        return waitForExpectedElement(By.className("co_relatedContentStickyLink")).findElement(By.linkText("Related Content"));
    }

    public WebElement relatedContentHeading() {
        return waitForExpectedElement(By.xpath("//div[@id='co_relatedContent']//h2[text()='Related Content']"));
    }

    public WebElement plcRef() {
        return waitForExpectedElement(By.cssSelector("#co_endOfDocument span.co_documentId>span"));
    }

    public WebElement askAQuestion() {
        return waitForExpectedElement(By.linkText("Ask a question"));
    }
}
