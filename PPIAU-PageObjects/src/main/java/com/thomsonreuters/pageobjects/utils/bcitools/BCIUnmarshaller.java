package com.thomsonreuters.pageobjects.utils.bcitools;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;

import com.thomsonreuters.pageobjects.utils.bcitools.calc_config.CalcConfig;
import com.thomsonreuters.pageobjects.utils.bcitools.calc_config.CalcConfigFactory;

public class BCIUnmarshaller {
	
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(BCIUnmarshaller.class);

	public CalcConfig unmarshall(CalcConfig config, String xmlFilePath) {
		try {

			File file = new File(xmlFilePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(CalcConfigFactory.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			config = (CalcConfig) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			LOG.error("Something went wrong during unmarshalling: " + e.getMessage());
		}
		
		return config;
	}
	
}
