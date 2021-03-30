package com.thomsonreuters.pageobjects.adestra.impl;

import com.thomsonreuters.pageobjects.adestra.AdestraServiceInterface;
import com.thomsonreuters.pageobjects.adestra.data.PreferenceList;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.*;

@Service
public class AdestraService implements AdestraServiceInterface {

    AdestraWebService adestraWebService = new AdestraWebService();

    private static final Integer CORETABLEID = 1;
    private static final Integer MARKETINGTABLEID = 3;

    public List<Integer> getUserUnsubscribedListIds(String email) throws MalformedURLException, XmlRpcException {
        Object[] listIds = (Object[]) adestraWebService.callMethod(AdestraService.UNSUB_LIST_CHECK_ALL.getService(), email);
        List<Integer> ret = new ArrayList<>();
        if (listIds != null) {
            for (Object id : listIds) {
                ret.add((Integer) id);
            }
        }
        return ret;
    }

    public Integer[] getContactSubscribedListIds(String email) throws XmlRpcException {
        Map<Object, Object> userData = this.getContactData(email);
        if (!userData.isEmpty()) {
            Object[] usersSubscribedListIds = (Object[]) adestraWebService.callMethod(AdestraService.CONTACTLIST.getService(), userData.get(AdestraService.ID_KEY));
            return Arrays.copyOf(usersSubscribedListIds, usersSubscribedListIds.length, (new Integer[0]).getClass());
        } else {
            return new Integer[0];
        }
    }

    public int addSubscription(Integer accountId, List<Integer> subscriptionsToAdd) throws XmlRpcException {
        Object status = adestraWebService.callMethod(AdestraService.CONTACTSUBSCRIBE.getService(), accountId, subscriptionsToAdd, Collections.emptyList());
        return (int) status;
    }

    public int removeSubscription(Integer accountId, List<Integer> subscriptionsToAdd) throws XmlRpcException {
        Object status = adestraWebService.callMethod(AdestraService.CONTACTUNSUBSCRIBE.getService(), accountId, subscriptionsToAdd, Collections.emptyList());
        return (int) status;
    }

    public Map<Object, Object> getContactData(String email) throws XmlRpcException {
        Map<String, String> searchCriteria = new HashMap<>();
        searchCriteria.put(AdestraService.EMAIL_PARAM.getService(), email);
        List<Integer> dataTables = new ArrayList<>();
        dataTables.add(MARKETINGTABLEID);
        Object[] contacts = (Object[]) adestraWebService.callMethod(AdestraService.CONTACTSEARCH.getService(), CORETABLEID, searchCriteria, dataTables);
        if (contacts != null && contacts.length > 0) {
            return (Map) contacts[0];
        }
        return MapUtils.EMPTY_MAP;
    }

    private PreferenceList[] getAllLists() throws XmlRpcException {
        boolean keepRequesting = true;
        Object[] result = null;
        HashMap<String, Integer> paramsmap = new HashMap<>();
        paramsmap.put(AdestraService.PAGE_SIZE.getService(), 250);
        for (int i = 1; keepRequesting; i++) {
            paramsmap.put(AdestraService.PAGE.getService(), i);
            Object[] listpage = (Object[]) adestraWebService.callMethod(AdestraService.LIST_ALL.getService(), paramsmap);
            if (listpage != null && listpage.length > 0) {
                result = ArrayUtils.addAll(result, listpage);
            } else {
                keepRequesting = false;
            }
        }
        if (result == null)
            throw new NullPointerException("There are no list of pages");
        return convertMapsToBeans(result);
    }

    public PreferenceList[] getAllUnsubLists() throws XmlRpcException {
        return convertMapsToBeans((Object[]) adestraWebService.
                callMethod(AdestraService.UNSUBLIST_ALL.getService(), Collections.emptyList()));
    }

    private PreferenceList[] convertMapsToBeans(Object[] results) {
        PreferenceList[] preferences = new PreferenceList[results.length];
        for (int i = 0; i < results.length; i++) {
            PreferenceList bean = new PreferenceList();
            preferences[i] = bean;
        }
        return preferences;
    }

    public Map<Integer, PreferenceList> getSubscriptionListsById() throws XmlRpcException, InvocationTargetException, IllegalAccessException {
        Map<Integer, PreferenceList> subscriptionListsById = new HashMap<>();
        PreferenceList[] allSubscriptionLists = getAllLists();
        for (PreferenceList subscriptionList : allSubscriptionLists) {
            subscriptionListsById.put(subscriptionList.getId(), subscriptionList);
        }
        return subscriptionListsById;
    }

}
