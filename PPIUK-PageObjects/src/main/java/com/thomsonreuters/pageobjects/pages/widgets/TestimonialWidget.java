package com.thomsonreuters.pageobjects.pages.widgets;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;

public class TestimonialWidget extends AbstractPage {

	private static final String CITATION = "Market to keep up-to-date with market practice and our peer groups.";
	private static final String NAME = "Daniel Wilson, ";
	private static final String ROLE = "Group Compliance Counsel British American Tobacco";
	private static final String WIDGET_NAME = "Testimonial";

	public boolean isHeaderPresent() {
		return isElementPresent(By.xpath("//h3[text()='" + WIDGET_NAME + "']"));
	}

	public boolean isTestimanialTextPresent() {
		return isElementPresent(By.xpath(
				"//h3[text()='" + WIDGET_NAME + "']/following::div[@class='co_genericBoxContent']//ol[contains(.,\""
						+ CITATION + "\") and contains(.,'" + NAME + "') and contains(.,'" + ROLE + "')]"));
	}

}
