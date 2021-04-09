package com.thomsonreuters.pageobjects.rest.auth;

import com.thomsonreuters.pageobjects.rest.auth.model.GetForSessionInfoResponse;
import com.thomsonreuters.pageobjects.rest.auth.model.GetForWorkProductTokenResponse;
import com.thomsonreuters.pageobjects.rest.auth.model.PostForRegKeysResponse;
import com.thomsonreuters.pageobjects.rest.auth.model.PostForUserGuidResponse;
import com.thomsonreuters.pageobjects.rest.auth.proxy.OnePassProxy;
import com.thomsonreuters.pageobjects.rest.auth.proxy.UDSProxy;

import com.thomsonreuters.utils.TimeoutUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UDSService {

    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UDSService.class);

    private UDSProxy udsProxy = new UDSProxy();
    private OnePassProxy onePassProxy = new OnePassProxy();

    public static final String PRODUCT = "CBT";

    /**
     * Method execute coin of requests to Onepass and UDS
     * to obtain credentials which allow to do Rest calls
     * to backend modules
     *
     * @param userName     user name for login to website
     * @param userPassword password for login to website
     * @param productView  product View of session
     * @param siteCookie   cookie of site instance e.g "site=B"
     * @return object with credentials info
     */
    public UDSCredentials getUdsCredentials(String userName, String userPassword, String productView, String siteCookie) {
        TimeoutUtils.sleepInSeconds(1500);
        PostForRegKeysResponse postForRegKeysResponse = onePassProxy.postForRegKeys(userName, userPassword, PRODUCT);
        String userId = postForRegKeysResponse.getProfile().getRegistrationKeys().get(0).getUserId();
        String pass = postForRegKeysResponse.getProfile().getRegistrationKeys().get(0).getPassword();
        PostForUserGuidResponse postForUserGuidResponse = udsProxy.postForUserGuid(userId, pass);
        String userGuid = postForUserGuidResponse.getPrismGuid();
        String site = siteCookie.split("=")[1];
        String coSessionToken = getCoSessionToken(userGuid, productView, site);
        GetForSessionInfoResponse getForSessionInfoResponse = udsProxy.getForSessionInfo(coSessionToken, siteCookie);
        String sessionId = getForSessionInfoResponse.getSessionId();
        GetForWorkProductTokenResponse getForCoSessionTokenResponse = udsProxy.getForWorkProductToken(sessionId);
        String workProductToken = getForCoSessionTokenResponse.getWorkProductToken();
        LOG.info("USER / CO_SESSIONTOKEN / SID: {} / {} / {}" ,userName,coSessionToken, sessionId);
        return new UDSCredentials(coSessionToken, sessionId, userGuid, workProductToken);
    }

    public String getCurrentSession(String userName, String userPassword, String productView, String siteCookie) {
        TimeoutUtils.sleepInSeconds(1500);
        PostForRegKeysResponse postForRegKeysResponse = onePassProxy.postForRegKeys(userName, userPassword, PRODUCT);
        String regKeysResponse = postForRegKeysResponse.toString();
        LOG.info("postForRegKeysResponse: {}" ,regKeysResponse);
        String userId = postForRegKeysResponse.getProfile().getRegistrationKeys().get(0).getUserId();
        LOG.info("userID: {}" , userId);
        String pass = postForRegKeysResponse.getProfile().getRegistrationKeys().get(0).getPassword();
        LOG.info("pass: {}" , pass);
        PostForUserGuidResponse postForUserGuidResponse = udsProxy.postForUserGuid(userId, pass);
        String userGuidResponse= postForUserGuidResponse.toString();
        LOG.info("postForUserGuidResponse: {}" , userGuidResponse);
        String userGuid = postForUserGuidResponse.getPrismGuid();
        LOG.info("userGuid: {}" , userGuid);
        String site = siteCookie.split("=")[1];
        LOG.info("site: {}" , site);
        String coSessionToken = getCoSessionToken(userGuid, productView, site);
        LOG.info("coSessionToken: {}" , coSessionToken);
        if (StringUtils.isEmpty(coSessionToken)){
        	LOG.error("coSessionToken is empty. Maybe current page is not PLPlusUK");
        	return "";
        }
        GetForSessionInfoResponse getForSessionInfoResponse = udsProxy.getForSessionInfo(coSessionToken, siteCookie);
        String sessionResponse = getForSessionInfoResponse.toString();
        LOG.info("getForSessionInfoResponse: {}",sessionResponse);
        return getForSessionInfoResponse.getSessionId();
    }
    
    
    public boolean isUserHasOnlineCoSessions(String userName, String userPassword) {
        String userGuid = getUserGuid(userName, userPassword);
        //return sessions for all sites and products
        String[] sessions  = udsProxy.getAllActiveCoSessionTokens(userGuid, "online", null, null);
        return !ArrayUtils.isEmpty(sessions);
    }
    
    private String getUserGuid(String userName, String userPassword){
    	PostForRegKeysResponse postForRegKeysResponse = onePassProxy.postForRegKeys(userName, userPassword, PRODUCT);
        String userId = postForRegKeysResponse.getProfile().getRegistrationKeys().get(0).getUserId();
        String pass = postForRegKeysResponse.getProfile().getRegistrationKeys().get(0).getPassword();
        PostForUserGuidResponse postForUserGuidResponse = udsProxy.postForUserGuid(userId, pass);
        return postForUserGuidResponse.getPrismGuid();
    }

    private String getCoSessionToken(String userGuid, String productView, String site) {
        String[] coSessionTokens = getAllCoSessionTokens(userGuid, productView, site);
        boolean coSessionTokensIsNotEmpty = coSessionTokens != null && coSessionTokens.length > 0;
        if (coSessionTokensIsNotEmpty) {
            if (productView != null) {
                return coSessionTokens[coSessionTokens.length - 1];
            } else {
                return getWlnCoSessionToken(coSessionTokens, site);
            }
        }
        return "";
    }

    private String getWlnCoSessionToken(String[] coSessionTokens, String site) {
        for (String coSessionToken : coSessionTokens) {
            GetForSessionInfoResponse getForSessionInfoResponse = udsProxy.getForSessionInfo(coSessionToken, "site=" + site);
            if (getForSessionInfoResponse.getProductView() == null) {
                return coSessionToken;
            }
            if (getForSessionInfoResponse.getProductView().equals("none")) {
                return coSessionToken;
            }
        }
        LOG.info("WLN Co Session token is null!");
        return "";
    }

    private String[] getAllCoSessionTokens(String userGuid, String productView, String site) {
        String[] coSessionTokens = udsProxy
                .getAllActiveCoSessionTokens(userGuid, "online", productView, site);
        if ((coSessionTokens != null && coSessionTokens.length == 0) || (coSessionTokens == null)) {
            coSessionTokens = udsProxy
                    .getAllActiveCoSessionTokens(userGuid, "authenticated", productView, site);
        }
        return coSessionTokens;
    }

	public String getZB(String userName, String userPassword, String siteCookie) {
		return udsProxy.getZB(getUserGuid(userName, userPassword), siteCookie).getOfficeId();
	}

	public void removeDashboardParameters(String userName, String userPassword, String siteCookie, String productView) {
		udsProxy.removeDashboardParameters(getUdsCredentials(userName, userPassword, productView, siteCookie).coSessionToken);
	}

}
