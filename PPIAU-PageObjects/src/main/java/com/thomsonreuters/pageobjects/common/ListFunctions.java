package com.thomsonreuters.pageobjects.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/*  =====================================================================================================================
    This library contains the below functions
    1) getSelectedValueList - This function returns the current selected value in the list
    2) SelectValueInList -  This function selects the value in the list
    ====================================================================================================================== */
public class ListFunctions {

    public String getSelectedValueList(WebElement list) {
        String value = null;
        Select selectedOption = new Select(list);
        value = selectedOption.getFirstSelectedOption().getText();
        return value;
    }

    public void SelectValueInList(WebElement list, String value) {
        Select selectedOption = new Select(list);
        selectedOption.selectByValue(value);
    }

    public void selectByVisibleText(WebElement list, String text) {
        new Select(list).selectByVisibleText(text);
    }

    public void selectByIndex(WebElement list, int index) {
        new Select(list).selectByIndex(index);
    }

    public List<WebElement> getAllOptionElements(WebElement list) {
       return new Select(list).getOptions();
    }

    public WebElement getFirstSelectedOption(WebElement list) {
        return new Select(list).getFirstSelectedOption();
    }
    public int getFirstSelectedOptionIndex(WebElement list) {
         List<WebElement> optionList= new Select(list).getOptions();
         WebElement selectedOption=new Select(list).getFirstSelectedOption();
        for(int index=0; index<optionList.size();index++){
            if(selectedOption.getText().equalsIgnoreCase(optionList.get(index).getText())){
                return index;
            }
        }
        return -1;
    }
    public String getValueByIndex(WebElement list, int index) {
        List<WebElement> optionList= new Select(list).getOptions();
        return optionList.get(index).getText().trim();
    }

}
