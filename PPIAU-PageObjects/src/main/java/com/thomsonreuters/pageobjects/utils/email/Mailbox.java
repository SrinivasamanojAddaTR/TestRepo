package com.thomsonreuters.pageobjects.utils.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.List;

public interface Mailbox {

    void deleteAllInboxMessages() throws MessagingException;

    // TODO Unused intervalSeconds arg. To remove
    Message waitForMessageWithTitle(final String title, int timeoutSeconds, int intervalSeconds) throws Throwable;

    // TODO Unused intervalSeconds arg. To remove
    Message waitForMessageWithTitleAndSender(String title, String sender, int timeoutSeconds, int intervalSeconds) throws Throwable;

}
