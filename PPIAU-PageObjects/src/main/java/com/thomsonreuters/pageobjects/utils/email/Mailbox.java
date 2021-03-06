package com.thomsonreuters.pageobjects.utils.email;

import javax.mail.Message;
import javax.mail.MessagingException;


public interface Mailbox {

    void deleteAllInboxMessages() throws MessagingException;


    Message waitForMessageWithTitle(final String title, int timeoutSeconds, int intervalSeconds) throws MessagingException;


    Message waitForMessageWithTitleAndSender(String title, String sender, int timeoutSeconds, int intervalSeconds) throws MessagingException;

}
