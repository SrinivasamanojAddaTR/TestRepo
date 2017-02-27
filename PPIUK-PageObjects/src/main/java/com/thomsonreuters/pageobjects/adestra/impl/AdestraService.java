package com.thomsonreuters.pageobjects.adestra.impl;

import com.thomsonreuters.pageobjects.adestra.AdestraServiceInterface;
import com.thomsonreuters.pageobjects.adestra.data.PreferenceList;
import org.apache.commons.beanutils.BeanUtils;
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

    private static final Integer coreTableId = 1;
    private static final Integer marketingTableId = 3;

    public List<Integer> getUserUnsubscribedListIds(String email) throws MalformedURLException, XmlRpcException {
        Object[] listIds = (Object[]) adestraWebService.callMethod(UNSUB_LIST_CHECK_ALL, email);
        List<Integer> ret = new ArrayList<Integer>();
        if (listIds != null) {
            for (Object id : listIds) {
                ret.add((Integer) id);
            }
        }
        return ret;
    }

    public Integer[] getContactSubscribedListIds(String email) throws MalformedURLException, XmlRpcException {
        Map userData = this.getContactData(email);
        if (!userData.isEmpty()) {
            Object[] usersSubscribedListIds = (Object[]) adestraWebService.callMethod(CONTACTLIST, userData.get(ID_KEY));
            return Arrays.copyOf(usersSubscribedListIds, usersSubscribedListIds.length, (new Integer[0]).getClass());
        } else {
            return new Integer[0];
        }
    }

    public int addSubscription(Integer accountId, List<Integer> subscriptionsToAdd) throws MalformedURLException, XmlRpcException {
        Object status = adestraWebService.callMethod(CONTACTSUBSCRIBE, accountId, subscriptionsToAdd, Collections.EMPTY_LIST);
        return (int) status;
    }

    public int removeSubscription(Integer accountId, List<Integer> subscriptionsToAdd) throws MalformedURLException, XmlRpcException {
        Object status = adestraWebService.callMethod(CONTACTUNSUBSCRIBE, accountId, subscriptionsToAdd, Collections.EMPTY_LIST);
        return (int) status;
    }

    public Map getContactData(String email) throws MalformedURLException, XmlRpcException {
        Map<String, String> searchCriteria = new HashMap<String, String>();
        searchCriteria.put(EMAIL_PARAM, email);
        List<Integer> dataTables = new ArrayList<Integer>();
        dataTables.add(marketingTableId);
        Object[] contacts = (Object[]) adestraWebService.callMethod(CONTACTSEARCH, coreTableId, searchCriteria, dataTables);
        if (contacts != null && contacts.length > 0) {
            return (Map) contacts[0];
        }
        return MapUtils.EMPTY_MAP;
    }

    private PreferenceList[] getAllLists() throws MalformedURLException, XmlRpcException {
        boolean keepRequesting = true;
        Object[] result = null;
        HashMap<String, Integer> paramsmap = new HashMap<String, Integer>();
        paramsmap.put(PAGE_SIZE, 250);
        for (int i = 1; keepRequesting; i++) {
            paramsmap.put(PAGE, i);
            Object[] listpage = (Object[]) adestraWebService.callMethod(LIST_ALL, paramsmap);
            if (listpage != null && listpage.length > 0) {
                result = ArrayUtils.addAll(result, listpage);
            } else {
                keepRequesting = false;
            }
        }
        return convertMapsToBeans(result);
    }

    public PreferenceList[] getAllUnsubLists() throws MalformedURLException, XmlRpcException {
        return convertMapsToBeans((Object[]) adestraWebService.callMethod(UNSUBLIST_ALL, Collections.emptyList()));
    }

    private PreferenceList[] convertMapsToBeans(Object[] results) throws MalformedURLException, XmlRpcException {
        PreferenceList[] preferences = new PreferenceList[results.length];
        for (int i = 0; i < results.length; i++) {
            PreferenceList bean = new PreferenceList();
            preferences[i] = bean;
            try {
                BeanUtils.populate(bean, (Map) results[i]);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return preferences;
    }

    public Map<Integer, PreferenceList> getSubscriptionListsById()throws MalformedURLException, XmlRpcException{
        Map<Integer, PreferenceList> subscriptionListsById = new HashMap<Integer, PreferenceList>();
        PreferenceList[] allSubscriptionLists = getAllLists();
        for (PreferenceList subscriptionList : allSubscriptionLists) {
            subscriptionListsById.put(subscriptionList.getId(), subscriptionList);
        }
        return subscriptionListsById;
    }

}
