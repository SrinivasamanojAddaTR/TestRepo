package com.thomsonreuters.pageobjects.adestra.impl;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Service
public class AdestraWebService {

    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AdestraWebService.class);

    private static XmlRpcClientConfigImpl config;
    private static final String SERVER_URL = "https://app.adestra.com/api/xmlrpc";
    private static final String USER_NAME = "plc_api.ylupinov";
    private static final String PASSWORD = "TESTaccount_plc2012";

    static {
        try {
            initXmlRpcClientConfig();
        } catch (MalformedURLException e) {
            LOG.info("context", e);
        }
    }

    public Object callMethod(String method, Object... params) throws MalformedURLException, XmlRpcException {
        return callMethod(method, Arrays.asList(params));
    }

    private static void initXmlRpcClientConfig() throws MalformedURLException {
        config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(SERVER_URL));
        config.setBasicUserName(USER_NAME);
        config.setBasicPassword(PASSWORD);
    }

    public Object callMethod(String method, List params) throws MalformedURLException, XmlRpcException {
        XmlRpcClient client = getClientConnection();
        Object result = client.execute(method, params);
        return result;
    }

    private XmlRpcClient getClientConnection() throws MalformedURLException {
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }

}
