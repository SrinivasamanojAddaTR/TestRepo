package com.thomsonreuters.pageobjects.utils.email;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.function.Function;

public class ParametrizedMailbox extends AbstractMailbox {

    private static final Logger LOG = LoggerFactory.getLogger(ParametrizedMailbox.class);

    private static final String PROP_PROTOCOL = ".protocol";
    private static final String PROP_INCOMING_SERVER = ".incoming.server";
    private static final String PROP_IS_USER_NAME_WITH_DOMAIN = ".isUserNameWithDomain";
    private static Properties emailProperties = new Properties();
    static  {
        // Load properties from email.properties file in resource directory of Page Objects module
        try (InputStream emailPropsStream = ParametrizedMailbox.class.getClassLoader().getResourceAsStream("email.properties")) {
            emailProperties.load(emailPropsStream);
        } catch (IOException e) {
            LOG.error("Unable to read email properties", e);
        }
    }

    ParametrizedMailbox(String userName, String domain, String password) {
        super(userName, domain, password);
        setProtocol(getEmailProperty(PROP_PROTOCOL));
        setIncomingServer(getEmailProperty(PROP_INCOMING_SERVER));
        setUserName(
                getEmailProperty(PROP_IS_USER_NAME_WITH_DOMAIN).equalsIgnoreCase("true") ?
                        userName + "@" + domain :
                        userName);
        if (StringUtils.isEmpty(getProtocol()) || StringUtils.isEmpty(getIncomingServer()) || StringUtils.isEmpty(getUserName())) {
            throw new IllegalArgumentException("The settings for given domain '" + domain + "' were not found. " +
                    "Please, check email.properties in PageObjects");
        }
    }

    public void deleteAllInboxMessages() throws MessagingException {
        deleteMessagesFromFolder(INBOX_FOLDER_NAME);
    }

    public void deleteAllSentMessages() throws MessagingException {
        deleteMessagesFromFolder(SENT_FOLDER_NAME);
    }

    private void deleteMessagesFromFolder(String folder) throws MessagingException {
        initConnectionAndOpenFolder(folder, Folder.READ_WRITE);
        Flags deleteFlags = new Flags(Flags.Flag.DELETED);
        openedFolder.setFlags(openedFolder.getMessages(), deleteFlags, true);
        close();
    }

    @Override
    public Message waitForMessageWithTitle(final String title, int timeoutSeconds, int intervalSeconds) throws Throwable {
        return waitForMessageWithTitleAndSender(title, null, timeoutSeconds, intervalSeconds);
    }

    @Override
    public Message waitForMessageWithTitleAndSender(final String title, final String sender, int timeoutSeconds, int intervalSeconds) throws Throwable {
        final boolean withSender = !StringUtils.isEmpty(sender);
        final String withSenderMessage = withSender ? " and sender '" + sender + "'" : "";
        final SearchTerm searchTerm = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if ((withSender && message.getFrom()[0].toString().equalsIgnoreCase(sender) && message.getSubject().contains(title)) ||
                            (!withSender && message.getSubject().contains(title))) {
                        message.setFlag(Flags.Flag.DELETED, true);
                        return true;
                    }
                    return false;
                } catch (MessagingException e) {
                    LOG.info("Could not load message with title '{}' {}",title,withSenderMessage,e);
                    return false;
                }
            }
        };
        // Wait while expected message won't be null.
        Function<ParametrizedMailbox, Message> waitCondition = mailbox -> {
            try {
                LOG.info("Attempt to wait a message with title '{}' {}", title, withSenderMessage);
                mailbox.initConnectionAndOpenFolder(INBOX_FOLDER_NAME, Folder.READ_WRITE);
                Message[] foundMessages = openedFolder.search(searchTerm);
                LOG.info("Folder '{}' has new messages - {}", openedFolder.getName(), openedFolder.hasNewMessages());
                // Expect only one message - the first
                return foundMessages.length > 0 ? foundMessages[0] : null;
            } catch (MessagingException e) {
                LOG.info("Wait of message with title: '{}' {} was not successful",title,withSenderMessage);
                return null;
            }
        };
        return new FluentWait<>(this).
                withTimeout(Duration.ofSeconds(timeoutSeconds)).
                pollingEvery(Duration.ofSeconds(1)).
                withMessage("The message with title '" + title + "'" + withSenderMessage + " wasn't received in " + timeoutSeconds + " seconds").
                until(waitCondition);
    }

    /**
     * Get properties from {@link #emailProperties}
     * WARNING! {@link #domain} field should be set at first for proper method work
     *
     * @param propertyAppendix Property appendix (e.g.: ".test" for localhost.co.test)
     * @return Value of the necessary property
     */
    private String getEmailProperty(String propertyAppendix) {
        return emailProperties.getProperty(domain + propertyAppendix, "");
    }

}
