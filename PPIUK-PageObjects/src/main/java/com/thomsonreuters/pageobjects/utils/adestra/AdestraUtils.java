package com.thomsonreuters.pageobjects.utils.adestra;

import com.thomsonreuters.pageobjects.adestra.AdestraServiceInterface;
import com.thomsonreuters.pageobjects.adestra.data.PreferenceList;
import com.thomsonreuters.pageobjects.adestra.impl.AdestraService;
import com.thomsonreuters.pageobjects.utils.Product;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;

import java.net.MalformedURLException;
import java.util.*;

public class AdestraUtils {

    protected static final Logger LOG = org.slf4j.LoggerFactory.getLogger(AdestraUtils.class);

    private AdestraService as = new AdestraService();
    private AdestraSubscriptionXMLParser adestraSubscriptionXMLParser = new AdestraSubscriptionXMLParser();

    public List<SubscriptionParameters> getServiceForSpecifiedRegion(String region, Product product) {
        adestraSubscriptionXMLParser.parse(product);
        List<SubscriptionParameters> services = new ArrayList<SubscriptionParameters>();
        for (SubscriptionParameters parameter : adestraSubscriptionXMLParser.getResult()) {
            if (parameter.getCategoryName().equals(region)) {
                services.add(parameter);
            }
        }
        return services;
    }

    public int createSubscriptionViaAPI(String userEmail, List<String> subscriptionsPLCID) throws Exception {
        int contactId = getUserIDFromAdestra(userEmail);
        List<Integer> subscriptionsID = new ArrayList<Integer>();
        for (String subscriptionPLCID : subscriptionsPLCID) {
            subscriptionsID.add(getSubscriptionIdFromAdestraByPLCId(subscriptionPLCID));
        }
        return as.addSubscription(contactId, subscriptionsID);
    }

    public int removeSubscriptionViaAPI(String userEmail) throws Exception {
        Integer[] emailsubsIds = as.getContactSubscribedListIds(userEmail);
        int contactId = getUserIDFromAdestra(userEmail);
        return as.removeSubscription(contactId, Arrays.asList(emailsubsIds));
    }

    public List<SubscriptionParameters> removeSubscriptionFromSpecifiedServices(List<SubscriptionParameters> specifiedRegionServices, String region, String service, List<String> frequencies) {
        for (Iterator<SubscriptionParameters> it = specifiedRegionServices.iterator(); it.hasNext(); ) {
            SubscriptionParameters subscriptionParameter = it.next();
            for (String frequency : frequencies) {
                if (subscriptionParameter.getCategoryName().equals(region) && subscriptionParameter.getCommonName().equals(service) && subscriptionParameter.getProductFrequency().equals(frequency)) {
                    it.remove();
                }
            }
        }
        return specifiedRegionServices;
    }

    public int getUserIDFromAdestra(String userEmail) throws MalformedURLException, XmlRpcException {
        return (Integer) as.getContactData(userEmail).get(AdestraServiceInterface.ID_KEY);
    }

    public int getSubscriptionIdFromAdestraByPLCId(String subscriptionPLCID) throws Exception {
        int subscriptionID = 0;
        Map<Integer, PreferenceList> subscriptionListsById = as.getSubscriptionListsById();
        for (PreferenceList pref : subscriptionListsById.values()) {
            if (pref.getName().equals(subscriptionPLCID)) {
                subscriptionID = pref.getId();
            }
        }
        return subscriptionID;
    }

    public List<SubscriptionParameters> getSpecifiedServices(String region, String service, List<String> frequencies, Product product) {
        adestraSubscriptionXMLParser.parse(product);
        List<SubscriptionParameters> services = new ArrayList<>();
        for (SubscriptionParameters parameter : adestraSubscriptionXMLParser.getResult()) {
            for (String frequency : frequencies) {
                if (parameter.getCategoryName().equals(region) && parameter.getCommonName().equals(service) && parameter.getProductFrequency().equals(frequency)) {
                    services.add(parameter);
                }
            }
        }
        return services;
    }

    public List<String> getAdestraSubscriptionNameListForPLCUK(String userEmail) throws Exception {
        List<String> ids = new ArrayList<String>();
        Integer[] emailsubsIds = as.getContactSubscribedListIds(userEmail);
        Map<Integer, PreferenceList> subscriptionListsById = as.getSubscriptionListsById();
        for (int i = 0; i < emailsubsIds.length; i++) {
            ids.add(subscriptionListsById.get(emailsubsIds[i]).getName());
        }
        return ids;
    }

    public List<SubscriptionParameters> getServicesByIDs(List<SubscriptionParameters> servicesForSpecifiedRegion, List<String> subscriptionIDS, String region) {
        List<SubscriptionParameters> listOfServices = new ArrayList<SubscriptionParameters>();
        for (int i = 0; i < servicesForSpecifiedRegion.size(); i++) {
            for (int j = 0; j < subscriptionIDS.size(); j++) {
                if (servicesForSpecifiedRegion.get(i).getCategoryName().equals(region) && servicesForSpecifiedRegion.get(i).getName().equals(subscriptionIDS.get(j))) {
                    listOfServices.add(servicesForSpecifiedRegion.get(i));
                }
            }
        }
        return listOfServices;
    }

    public boolean isUserHasCorrectSubscriptions(List<SubscriptionParameters> subscriptionParameters, List<String> adestraExistingIDS) {
        List<String> subscriptionIDS = new ArrayList<String>();
        for (SubscriptionParameters subscriptionParameter : subscriptionParameters) {
            subscriptionIDS.add(subscriptionParameter.getName());
        }
        Collections.sort(subscriptionIDS);
        LOG.info("subscriptions that should be selected on widget: " + subscriptionIDS.toString());
        Collections.sort(adestraExistingIDS);
        LOG.info("subscriptions from adestra that were saved: " + adestraExistingIDS.toString());
        return adestraExistingIDS.containsAll(subscriptionIDS);
    }

}
