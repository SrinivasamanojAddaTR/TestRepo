package com.thomsonreuters.step_definitions.uk.assetPage;

import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.AssetDocumentPage;
import com.thomsonreuters.pageobjects.utils.plPlusResearchDocDisplay.AssetPageUtils;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class AssetPageTemplateStep extends BaseStepDef {

	private AssetDocumentPage assetDocumentPage = new AssetDocumentPage();
	private AssetPageUtils assetPageUtils = new AssetPageUtils();

	@Then("^the document contain content body$")
	public void theDocumentContainContentBody() throws Throwable {
		assertTrue("The document doesn't contain content body",
				assetDocumentPage.isElementDisplayed(assetDocumentPage.contentBody()));
	}

	@Then("^the content body contain end of document$")
	public void theContentBodyContainEndOfDocument() throws Throwable {
		assertTrue("The content body doesn't conatin ent of document",
				assetPageUtils.isTheContentBodyContainEndOfDocument());
	}

	@Then("^the end of document does not contain text$")
	public void theEndOfDocumentDoesNotContainText() throws Throwable {
		assertTrue("The End of Document contain text", assetPageUtils.isTheEndOfDocumentContainText());
	}

	@Then("^the document contain class with case-asset-doc$")
	public void theDocumentContainClassWithCaseAssetDoc() throws Throwable {
		assertTrue("The document doesn't contain class with case-asset-doc",
				assetPageUtils.isDocumentContainClassWithCaseAssetDoc());
	}

	@Then("^the bullets have \"(.*?)\" style$")
	public void theBulletsHaveStyle(String styleName) throws Throwable {
		assertTrue("The bullets do't have " + styleName + "style", assetPageUtils.isTheBulletsHaveStyle(styleName));
	}

	@Then("^the double lines have \"(.*?)\" style$")
	public void theDoubleLinesHaveStyle(String styleName) throws Throwable {
		assertTrue("The double lines do't have " + styleName + "style",
				assetPageUtils.isTheDoubleLinesHaveStyle(styleName));
	}

	@Then("^the spacing between double lines and links have \"(.*?)\" size$")
	public void theSpacingBetweenDoubleLinesAndLinksHaveSize(String size) throws Throwable {
		assertTrue("The spacing between double lines and links doesn't have " + size + "size",
				assetPageUtils.isTheSpacingBetweenDoubleLinesAndLinksHaveSize(size));
	}

	@Then("^the \"(.*?)\" has font size \"(.*?)\"$")
	public void theHasFontSize(String linkText, String fontSize) throws Throwable {
		assertTrue("The " + linkText + "doesn't has " + fontSize + "font size",
				assetPageUtils.getFontSizeOfLink(linkText).equals(fontSize));
	}

	@Then("^this link located on the \"(.*?)\" side$")
	public void thisLinkLocatedOnTheSide(String sideName) throws Throwable {
		assertTrue("The link doesn't located on " + sideName + "side",
				assetPageUtils.isTheLinkLocatedOnTheSide(sideName));
	}
	
	@Then("^the \"(.*?)\" displayed on the document$")
	public void theDisplayedOnTheDocument(String headerHame) throws Throwable {
		assertTrue("The document doesn't contain " + headerHame + "header",
				assetDocumentPage.isElementDisplayed(assetDocumentPage.sectionHeader(headerHame)));
	}
	
	@Then("^the \"(.*?)\" header has \"(.*?)\" font size$")
	public void theHeaderHasFontSize(String headerHame, String fontSize) throws Throwable {
		assertTrue("The " + headerHame + "doesn't has " + fontSize + "font size",
				assetPageUtils.getFontSizeOfHeader(headerHame).equals(fontSize));
	}
	
	@Then("^near the \"(.*?)\" bullet have \"(.*?)\" font size$")
	public void nearTheBulletHaveFontSize(String linkText, String fontSize) throws Throwable {
		assertTrue("The bullet doesn't has " + fontSize + "font size",
				assetPageUtils.getFontSizeOfBullet(linkText).equals(fontSize));
	}

}