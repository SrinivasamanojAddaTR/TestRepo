package com.thomsonreuters.step_definitions.browse;

import com.thomsonreuters.pageobjects.rest.service.impl.RestServiceDeliveryImpl;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;


public class AnzSiteMap extends BaseStepDef {


    private RestServiceDeliveryImpl deliveryService = new RestServiceDeliveryImpl();

	private static final File baseUserDir = new File(System.getProperty("user.dir"));
	private static final File schemaLocation = new File(baseUserDir + "/src/test/resources/siteMap.xsd");
	private static final String path = "https://a.au.practicallaw.demo.thomsonreuters.com/sitemap/build?cs=w_plc_anz_algo&content=KNOWHOW&num=5";

     
    @Then("^user verifies the site map xml$")
    public void userVerifiesTheSiteMap() throws Throwable {
    	File file = deliveryService.getFileViaHttp(path, "sitemap.xml");
    	Source xmlFile = new StreamSource(file);
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = factory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();
        try {
        	  validator.validate(xmlFile);
        	} catch (SAXException e) {
        		Assert.fail("xml file is not valid: "+e.getLocalizedMessage());
        	}        
    }



}
