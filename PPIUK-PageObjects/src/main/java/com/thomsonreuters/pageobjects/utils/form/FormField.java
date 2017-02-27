package com.thomsonreuters.pageobjects.utils.form;


import org.openqa.selenium.By;

public interface FormField {

    By getBy();

    FormFieldStrategy getFormFieldStrategy();

}
