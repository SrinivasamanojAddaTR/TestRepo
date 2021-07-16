package com.thomsonreuters.pageobjects.utils.bcitools.calc_config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRegistry;

import org.slf4j.Logger;

import com.thomsonreuters.pageobjects.utils.bcitools.corporate_manslaughter_calcconfig.CMCalcConfig;
import com.thomsonreuters.pageobjects.utils.bcitools.environment_offence_calcconfig.EOCalcConfig;
import com.thomsonreuters.pageobjects.utils.bcitools.food_safety_calcconfig.FSCalcConfig;
import com.thomsonreuters.pageobjects.utils.bcitools.fraud_bribery_money_offence_calcconfig.FBMOCalcConfig;
import com.thomsonreuters.pageobjects.utils.bcitools.health_and_safety_calcconfig.HSCalcConfig;

@XmlRegistry
public class CalcConfigFactory {
	
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(CalcConfigFactory.class);
	
	public FBMOCalcConfig createFBMOCalcConfig() {
        return createInstance(FBMOCalcConfig.class);
    }
  
    public EOCalcConfig createEOCalcConfig() {
        return createInstance(EOCalcConfig.class);
    }
    
    public HSCalcConfig createHSCalcConfig() {
        return createInstance(HSCalcConfig.class);
    }
    
    public CMCalcConfig createCMCalcConfig() {
        return createInstance(CMCalcConfig.class);
    }
    
    public FSCalcConfig createFSCalcConfig() {
        return createInstance(FSCalcConfig.class);
    }
  
    @SuppressWarnings("unchecked")
	private <T> T createInstance(Class<T> anInterface) {
        return (T) Proxy.newProxyInstance(anInterface.getClassLoader(), new Class[] {anInterface}, new InterfaceInvocationHandler());
    }
  
    private static class InterfaceInvocationHandler implements InvocationHandler {
    	
    	private final int BEGIN_INDEX = 3;
  
        private Map<String, Object> values = new HashMap<String, Object>();
  
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            if(methodName.startsWith("get")) {
                return values.get(methodName.substring(BEGIN_INDEX));
            } else {
                values.put(methodName.substring(BEGIN_INDEX), args[0]);
                LOG.error("error situation. object is null");
                return null;
            }
        }
  
    }

}
