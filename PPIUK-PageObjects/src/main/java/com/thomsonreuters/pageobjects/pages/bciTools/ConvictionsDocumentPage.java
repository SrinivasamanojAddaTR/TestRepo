package com.thomsonreuters.pageobjects.pages.bciTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.thomsonreuters.pageobjects.pages.plPlusResearchDocDisplay.document.DocumentDisplayAbstractPage;

public class ConvictionsDocumentPage extends DocumentDisplayAbstractPage{
	
	public WebElement contentExtras(){
		return waitForExpectedElement(By.xpath("//div[@id='co_docHeaderContainer']//table[@class='co_doc_content_extras']"));
	}
	
	public WebElement contentTable(){
		return waitForExpectedElement(By.className("kh_docContentTable"));
	}
	
	public WebElement getValueByFieldNames (String name){
		return waitForExpectedElement(By.xpath("//*[text()='" + name + "']//ancestor::tr/td[2]"));
	}
	

}
