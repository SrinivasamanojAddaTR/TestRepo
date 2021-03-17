package com.thomsonreuters.pageobjects.utils.email;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.mail.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EmailMessageUtils {

	protected static final Logger LOG = LoggerFactory.getLogger(Mailbox.class);

	private static final String BASE_PATH = "C:/temp/test-downloads";
	private static final String DATE_PATTERN_FOR_FOLDER = "yyyy-MM-dd_hh-mm-ss";

	public boolean isEmailContainsText(Message message, String text) throws Exception {
		return getMessageBody(message).toLowerCase().contains(text.toLowerCase());
	}

	public boolean isEmailContainsRegex(Message message, String regex) throws Exception {
		Pattern p = Pattern.compile(regex);
		return p.matcher(getMessageBody(message)).find();
	}

	public String getMessageBody(Message message) throws Exception {
		if (message.isMimeType("text/*")) {
			return message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			String result = "";
			Multipart multipart = (Multipart) message.getContent();
			int count = multipart.getCount();
			for (int i = 0; i < count; i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/*")) {
					result = result + "\n" + bodyPart.getContent();
					break;
				}
			}
			return result;
		}
		throw new Exception("Message text was not found");
	}

	private boolean isMessageMimeType(Message message, String type) {
		try {
			return message.isMimeType(type);
		} catch (MessagingException exp) {
			throw new RuntimeException("Unable to verify the mime type", exp);
		}
	}

	public String retrieveAttachmentName(Message message) {
		try {
			if (isMessageMimeType(message, "multipart/*")) {
				Multipart multipart = (Multipart) message.getContent();
				for (int i = 0; i < multipart.getCount(); i++) {
					BodyPart bodyPart = multipart.getBodyPart(i);
					String fileName = bodyPart.getFileName();
					if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) && fileName != null) {
						return fileName;
					}
				}
			}
		} catch (MessagingException | IOException exp) {
			throw new RuntimeException("Unable to retrieve attachment", exp);
		}
		return StringUtils.EMPTY;
	}

	public File downloadAttachment(Message message) throws Exception {
		return downloadAttachment(message, BASE_PATH);
	}

	public File downloadAttachment(Message message, String baseFolder) throws Exception {

		File file = null;
		Multipart multipart = (Multipart) message.getContent();

		for (int i = 0; i < multipart.getCount(); i++) {
			BodyPart bodyPart = multipart.getBodyPart(i);
			if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) && bodyPart.getFileName() != null) {
				continue;
			}
			InputStream is = bodyPart.getInputStream();

			file = new File(baseFolder + "/" + new SimpleDateFormat(DATE_PATTERN_FOR_FOLDER).format(new Date()) + "/" + bodyPart.getFileName());
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = is.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}
			fos.close();
		}

		return file;
	}

	public String getAttachmentExtension(Message message) throws Exception {
		String attachmentName = retrieveAttachmentName(message);
		if (attachmentName == null || attachmentName.isEmpty()) {
			throw new Exception("Attachment file not found");
		}
		String[] parts = attachmentName.split("\\.");
		if (parts.length < 2) {
			LOG.info("File name does not contain extension: " + attachmentName);
			return "";
		}
		return parts[parts.length - 1];
	}

	public boolean hasAttachment(Message message) {
		return !retrieveAttachmentName(message).isEmpty();
	}

}
