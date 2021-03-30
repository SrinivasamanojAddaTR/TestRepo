package com.thomsonreuters.pageobjects.utils.form;

import org.openqa.selenium.WebElement;

import com.thomsonreuters.pageobjects.pages.delivery.EmailOptionsPage;

public class FormUtils {

    private EmailOptionsPage page = new EmailOptionsPage();

    public String getValue(FormField formField) {
        return formField.getFormFieldStrategy().getValue(findElement(formField));
    }

    public WebElement findElement(FormField form) {
        return page.findElement(form.getBy());
    }

    public void editValue(FormField form, String value) {
        form.getFormFieldStrategy().editValue(findElement(form), value);
    }

    public boolean isNotDisplayed(FormField deliveryForm) {
		return !page.isElementDisplayed(deliveryForm.getBy());
    }
    
    public boolean isElementSelected(FormField form){
		return page.isElementDisplayed(form.getBy()) && page.findElement(form.getBy()).isSelected();
    	
    }


}
