package com.thomsonreuters.pageobjects.pages.legalUpdates;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;


public class ResearchFoldersWidget extends AbstractPage {
	
	public ResearchFoldersWidget() {
		
	}
	
	public int getFolderDocumentsCount() {
		return findElements(By.xpath("//form[@class='co_dock_items']//li[contains(@id, 'cobalt_foldering_dock_li_documents_')]")).size();
	}
	
	public WebElement getSpecificDocument(int specificDocumentNumber) {
		return findElement(By.xpath("//form[@class='co_dock_items']//descendant::a[@id='cobalt_foldering_dock_document_link_" + Integer.toString(specificDocumentNumber)+ "']"));
	}
	
	public List<WebElement> getAllDocumentsTitles() {
		return findElements(By.xpath("//form[@class='co_dock_items']//descendant::a[contains(@id,'cobalt_foldering_dock_document_link_')]"));
	}
	
	public WebElement specificDocumentByTitleCheckBox(String documentTitle) {
		return findElement(By.xpath("//a[text()='" + documentTitle +"']//ancestor::li[contains(@id, 'cobalt_foldering_dock_li_documents_')]//input[@type='checkbox']"));
	}
	
	public WebElement deleteDocumentFromFolderButton() {
		return findElement(By.xpath("//a[@id='co_dock_trash' and text()='Delete']"));
	}
	
	public WebElement selectAllCheckBox() {
		return findElement(By.xpath("//input[@id='cobalt_foldering_dock_select_all']"));
	}
	
	public WebElement emptyFolderTextMessage() {
		return findElement(By.xpath("//li[text()='There are no documents in this folder.']"));
	}

}
