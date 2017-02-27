package com.thomsonreuters.pageobjects.utils.email;

import com.thomsonreuters.pageobjects.common.ExcelFileReader;

public class MailboxFactory {

    public static Mailbox getMailboxByEmail(String email) {
        String[] parts = email.split("@");
        String password = ExcelFileReader.getEmailPassword(email);
        return new ImapsMailbox(parts[0], parts[1], password);
    }

    public static Mailbox getParametrizedMailboxByEmail(String email) {
        String[] parts = email.split("@");
        String password = ExcelFileReader.getEmailPassword(email);
        return new ParametrizedMailbox(parts[0], parts[1], password);
    }

}
