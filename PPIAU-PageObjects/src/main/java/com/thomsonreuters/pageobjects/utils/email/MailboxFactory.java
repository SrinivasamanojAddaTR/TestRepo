package com.thomsonreuters.pageobjects.utils.email;

import com.thomsonreuters.driver.configuration.GridProperties;
import com.thomsonreuters.pageobjects.common.ExcelFileReader;

public class MailboxFactory {

    private MailboxFactory() {
    }

    public static Mailbox getMailboxByEmail(String email) {
        return getMailboxByEmail(email, GridProperties.getInstance().getMailboxPassword());
    }

    public static Mailbox getMailboxByEmail(String email, String password) {
        String[] parts = email.split("@");
        return new ImapsMailbox(parts[0], parts[1], password);
    }

    public static Mailbox getParametrizedMailboxByUserName(String userName) {
        String email = ExcelFileReader.getCobaltEmail(userName);
        return getParametrizedMailboxByEmail(email);
    }

    public static Mailbox getParametrizedMailboxByEmail(String email) {
        String[] parts = email.split("@");
        String password = GridProperties.getInstance().getMailboxPassword();
        return new ParametrizedMailbox(parts[0], parts[1], password);
    }

}
