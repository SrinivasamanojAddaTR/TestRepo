package com.thomsonreuters.pageobjects.adestra;

import com.thomsonreuters.pageobjects.adestra.data.PreferenceList;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;


public interface AdestraServiceInterface {
    public static final String LIST_ALL = "list.all";
    public static final String UNSUBLIST_ALL = "unsubList.all";
    public static final String UNSUB_LIST_CHECK_ALL = "unsubList.checkAll";
    public static final String CONTACTLIST = "contact.lists";
    public static final String CONTACTSEARCH = "contact.search";
    public static final String CONTACTSUBSCRIBE = "contact.subscribe";
    public static final String CONTACTUNSUBSCRIBE = "contact.unsubscribe";
    public static final String EMAIL_PARAM = "email";
    public static final String ID_KEY = "id";
    public static final String PAGE = "page";
    public static final String PAGE_SIZE = "page_size";

    List<Integer> getUserUnsubscribedListIds(String email) throws MalformedURLException, XmlRpcException;

    Integer[] getContactSubscribedListIds(String email) throws MalformedURLException, XmlRpcException;

    public Map<Integer, PreferenceList> getSubscriptionListsById() throws Exception;

}
