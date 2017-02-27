package com.thomsonreuters.pageobjects.rest.service.impl;

import com.thomsonreuters.pageobjects.rest.model.response.Folder;
import com.thomsonreuters.pageobjects.rest.model.response.FolderCreationResponse;
import com.thomsonreuters.pageobjects.rest.model.response.StartPageDeleteResponse;
import com.thomsonreuters.pageobjects.rest.model.response.SuperDeleteResponse;
import com.thomsonreuters.pageobjects.rest.service.RestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class RestServiceFFHImpl extends RestServiceImpl implements RestService {

	/**
	 * GET request to Foldering/v3/{USERNAME}/folders/user/root/ancestors
	 *
	 * @return array of json objects
	 */
	public Folder[] getRootAncestors() {
		HttpHeaders httpHeaders = configureHeaders();
		String baseURL = getCurrentBaseUrl();
		String userName = getUserName();
		HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
		HttpEntity<Folder[]> response = getRestTemplate().exchange(getProtocol() + baseURL + "/" + "Foldering/v3/" + userName + "/folders/user/root/ancestors",
				HttpMethod.GET, requestEntity, Folder[].class);
		return response.getBody();
	}

	/**
	 * POST request to Foldering/v3/{USERNAME}/folders/user/{FOLDER_ID} with
	 * body _action=create&name={FOLDER_NAME}&source=saveTo
	 *
	 * @param newFolderName
	 *            name of new folder
	 * @param parentFolderID
	 *            id of parent folder
	 * @return json object
	 */
	public FolderCreationResponse postCreateFolder(String newFolderName, String parentFolderID) {
		HttpHeaders httpHeaders = configureHeaders();
		String baseURL = getCurrentBaseUrl();
		String userName = getUserName();
		HttpEntity<String> requestEntity = new HttpEntity<>("_action=create&name=" + newFolderName + "&source=saveTo", httpHeaders);
		String requestTo = getProtocol() + baseURL + "/" + "Foldering/v3/" + userName + "/folders/user/" + parentFolderID;
		return getRestTemplate().postForObject(requestTo, requestEntity, FolderCreationResponse.class);
	}

	/**
	 * This method delete all folders and history for user DELETE request to
	 * Foldering/v1/{USERNAME}/user
	 *
	 * @return json object
	 */
	public SuperDeleteResponse deleteDoSuperDelete() {
		HttpHeaders httpHeaders = configureHeaders();
		String baseURL = getCurrentBaseUrl();
		String userName = getUserName();
		HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
		String requestTo = getProtocol() + baseURL + "/" + "Foldering/v1/" + userName + "/user";
		LOG.info("Super delete REQUEST '" + requestEntity.toString() + "', TO: " + requestTo);
		HttpEntity<SuperDeleteResponse> response = getRestTemplate().exchange(requestTo, HttpMethod.DELETE, requestEntity, SuperDeleteResponse.class);
		LOG.info("Super delete response '" + response.getBody() + "'");
		return response.getBody();
	}

    /**
     * Delete category page that was marked as start for the user
     *
     * @return StartPageDeleteResponse
     */
	public StartPageDeleteResponse deleteStartPage() {
        HttpHeaders httpHeaders = configureHeaders();
        String baseURL = getCurrentBaseUrl();
        String userName = getUserName();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        String requestTo = getProtocol() + baseURL + "/" + "Foldering/v1/" + userName + "/categoryPage/startPage";
        LOG.info("Delete start page REQUEST '" + requestEntity.toString() + "', TO: " + requestTo);
        HttpEntity<StartPageDeleteResponse> response = getRestTemplate().exchange(requestTo, HttpMethod.DELETE, requestEntity, StartPageDeleteResponse.class);
        LOG.info("Delete start page RESPONSE '" + response.getBody() + "'");
        return response.getBody();
    }

	/**
	 * This method delete all folders and history for user DELETE request to
	 * 
	 * This method uses cookies from browser, instead of USD. 
	 * This cookies would not work for IE browser. Could be used for debug purposes.
	 * 
	 * Foldering/v1/{USERNAME}/user
	 *
	 * @return json object
	 */
	@Deprecated
	public SuperDeleteResponse wlnDeleteDoSuperDelete() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String environment = System.getProperty("base.url");
        httpHeaders.set("x-cobalt-host", "foldering.int.next." + environment + ".westlaw.com");
        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.set("Cookie", this.getCookies());
		String baseURL = getCurrentBaseUrl();
		String userName = getUserName();
		HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
		String requestTo = getProtocol() + baseURL + "/" + "Foldering/v1/" + userName + "/user";
		HttpEntity<SuperDeleteResponse> response = getRestTemplate().exchange(requestTo, HttpMethod.DELETE, requestEntity, SuperDeleteResponse.class);
		LOG.info("Super delete response '" + response.getBody() + "'");
		return response.getBody();
	}

	@Override
	public HttpHeaders configureHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		String environment = System.getProperty("base.url");
		httpHeaders.set("x-cobalt-host", "foldering.int.next." + environment + ".westlaw.com");
		httpHeaders.set("Content-Type", "application/json; charset=UTF-8");
		httpHeaders.set("Cookie", webDriverDiscovery.getBrowserCookiesAsString());
		return httpHeaders;
	}

	public String getCurrentSession() {
		return super.getCurrentSession();
	}
	
	public String getZB() {
		return super.getZB();
	}
	
	public void removeDashboardParameters() {
		super.removeDashboardParameters();
	}
	
}
