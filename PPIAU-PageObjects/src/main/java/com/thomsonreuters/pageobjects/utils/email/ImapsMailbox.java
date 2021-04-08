package com.thomsonreuters.pageobjects.utils.email;

import com.thomsonreuters.driver.framework.AbstractPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import java.util.*;
import java.util.function.Function;

public class ImapsMailbox extends AbstractMailbox {

    private static final Logger LOG = LoggerFactory.getLogger(ImapsMailbox.class);

    private List<Message> messages = new ArrayList<Message>();

    ImapsMailbox(String userName, String domain, String password) {
        super(userName, domain, password);
        setProtocol("imaps");
        setIncomingServer("imap." + domain);
    }

    public void loadNewMessages() throws MessagingException {
        Message[] messages = fetchMessages(INBOX_FOLDER_NAME, Folder.READ_WRITE);
        for (Message message : messages) {
            Flags flags = message.getFlags();
            LOG.info("Message with title: '{}', system flags: {}, user flags: {}",
                    message.getSubject(), getSystemFlagsAsStrings(flags.getSystemFlags()), Arrays.asList(flags.getUserFlags()));
            if (!message.getFlags().contains(Flags.Flag.DELETED)) {
                this.messages.add(message);
                message.setFlag(Flags.Flag.DELETED, true);
            }
        }
    }

    @Override
    public void deleteAllInboxMessages() throws MessagingException {
        Message[] messages = fetchMessages(INBOX_FOLDER_NAME, Folder.READ_WRITE);
        for (Message message : messages) {
            if (!message.getFlags().contains(Flags.Flag.DELETED)) {
                message.setFlag(Flags.Flag.DELETED, true);
            }
        }
        close();
    }

    private Message[] fetchMessages(String folderName, int accessMode) throws MessagingException {
        initConnectionAndOpenFolder(folderName, accessMode);
        Message[] messages = openedFolder.getMessages();
        FetchProfile fetchProfile = new FetchProfile();
        fetchProfile.add(FetchProfile.Item.ENVELOPE);
        openedFolder.fetch(messages, fetchProfile);
        return messages;
    }

    private List<String> getSystemFlagsAsStrings(Flags.Flag[] flags) {
        List<String> flagsAsStrings = new ArrayList<>();
        for (Flags.Flag flag : flags) {
            if (flag.equals(Flags.Flag.ANSWERED)) {
                flagsAsStrings.add("ANSWERED");
            } else if (flag.equals(Flags.Flag.DELETED)) {
                flagsAsStrings.add("DELETED");
            } else if (flag.equals(Flags.Flag.DRAFT)) {
                flagsAsStrings.add("DRAFT");
            } else if (flag.equals(Flags.Flag.FLAGGED)) {
                flagsAsStrings.add("FLAGGED");
            } else if (flag.equals(Flags.Flag.RECENT)) {
                flagsAsStrings.add("RECENT");
            } else if (flag.equals(Flags.Flag.SEEN)) {
                flagsAsStrings.add("SEEN");
            } else if (flag.equals(Flags.Flag.USER)) {
                flagsAsStrings.add("USER");
            }
        }
        return flagsAsStrings;
    }

    @Override
    protected void close() {
        messages.clear();
        super.close();
    }

    // TODO Unused intervalSeconds arg. To remove
    @Override
    public Message waitForMessageWithTitle(final String title, int timeoutSeconds, int intervalSeconds) throws Throwable {
        // Wait while expected message won't be null.
        //TODO [Phase3] need to verify below changes for all mail tests
        Function<ImapsMailbox, Optional<Message>> waitCondition = mailbox -> {
            try {
                LOG.info("Attempt to wait a message with title: {}", title);
                mailbox.loadNewMessages();
                for (Message message : mailbox.messages) {
                    if (message.getSubject().contains(title)) {
                        return Optional.of(message);
                    }
                }
                return Optional.ofNullable(null);
            } catch (MessagingException e) {
                LOG.info("Could not load message with title: {}", title, e);
                return Optional.ofNullable(null);
            }
        };
        Optional<Message> expectedMessage = AbstractPage.waitFor(waitCondition, this, timeoutSeconds);
        if (expectedMessage.isPresent()) {
            return expectedMessage.get();
        }
        throw new MessagingException("The message with title '" + title + "' wasn't received in " + timeoutSeconds + " seconds");
    }


    @Override
    public Message waitForMessageWithTitleAndSender(String title, String sender, int timeoutSeconds, int intervalSeconds) throws Throwable {

        Message result = null;
        long stopTime = System.currentTimeMillis() + timeoutSeconds * 1000;
        while (stopTime > System.currentTimeMillis() && result == null) {
            try {
                loadNewMessages();

                for (Message m : messages) {
                    if (!m.getSubject().contains(title) || !m.getFrom()[0].toString().equals(sender)) {
                        continue;
                    }
                    result = m;
                }
            } catch (MessagingException e) {
                LOG.info("Could not load messages");
            }
            if (result == null) {
                Thread.sleep(intervalSeconds * 1000);
            }
        }
        if (result == null) {
            throw new Exception("Could not get message with title '" + title + "' and sender '" + sender + "'");
        }
        return result;
    }


}