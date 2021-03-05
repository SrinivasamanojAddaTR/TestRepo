package com.thomsonreuters.pageobjects.adestra;

import com.thomsonreuters.pageobjects.adestra.data.PreferenceList;
import org.apache.xmlrpc.XmlRpcException;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;


public interface AdestraServiceInterface {

    List<Integer> getUserUnsubscribedListIds(String email) throws MalformedURLException, XmlRpcException;

    Integer[] getContactSubscribedListIds(String email) throws MalformedURLException, XmlRpcException;

    public Map<Integer, PreferenceList> getSubscriptionListsById() throws MalformedURLException, XmlRpcException, InvocationTargetException, IllegalAccessException;

    enum AdestraService {

        LIST_ALL("list.all"),
        UNSUBLIST_ALL("unsubList.all"),
        UNSUB_LIST_CHECK_ALL("unsubList.checkAll"),
        CONTACTLIST("contact.lists"),
        CONTACTSEARCH("contact.search"),
        CONTACTSUBSCRIBE("contact.subscribe"),
        CONTACTUNSUBSCRIBE("contact.unsubscribe"),
        EMAIL_PARAM("email"),
        ID_KEY("id"),
        PAGE("page"),
        PAGE_SIZE("page_size");

        private String service;

        private AdestraService(String service) {
            this.service = service;

        }

        public String getService() {
            return service;
        }

    }
}