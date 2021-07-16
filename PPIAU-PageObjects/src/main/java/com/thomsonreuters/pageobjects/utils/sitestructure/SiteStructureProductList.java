package com.thomsonreuters.pageobjects.utils.sitestructure;

import com.thomsonreuters.pageobjects.common.CommonMethods;
import com.thomsonreuters.pageobjects.common.SortOptions;
import com.thomsonreuters.pageobjects.pages.header.WLNHeader;
import com.thomsonreuters.pageobjects.pages.site_structure.SwitchProductDropdownList;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SiteStructureProductList {
    private WLNHeader wlnHeader = new WLNHeader();
    private SwitchProductDropdownList switchProductDropdownList = new SwitchProductDropdownList();
    private SiteStructureUtils siteStructureUtils = new SiteStructureUtils();
    private CommonMethods commonMethods = new CommonMethods();

    public List<String> getProductNamesFromSwitchProductList(){
        wlnHeader.compartmentToggleDropDownArrow().click();
        List<WebElement> productNamesList = switchProductDropdownList.getProductNamesList();
        return commonMethods.getTextsFromWebElements(productNamesList, ".*\\n");
    }

    public String getCurrentProductName(){
        return siteStructureUtils.getNodeTextWithoutChild(switchProductDropdownList.getCurrentProduct());
    }

    public boolean checkIfProductsAreSortedAlphabetically(){
        List<WebElement> listOfAvailableProducts = switchProductDropdownList.getListOfAvailableProducts();
        List<String> productsList = commonMethods.getTextsFromWebElements(listOfAvailableProducts);

        return commonMethods.isSorted(productsList, SortOptions.ASC);
    }
}
