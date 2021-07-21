package com.thomsonreuters.pageobjects.utils.email;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.mail.*;

import com.thomsonreuters.pageobjects.common.PropertyLoaderUtility;
import com.thomsonreuters.pageobjects.exceptions.EmailException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EmailMessageUtils {

	protected static final Logger LOG = LoggerFactory.getLogger(EmailMessageUtils.class);

	private static final String DATE_PATTERN_FOR_FOLDER = "yyyy-MM-dd_hh-mm-ss";
	private static final String MESSAGE_MIMETYPE_TEXT = "text/*";
	private static final String MESSAGE_MIMETYPE_MULTIPART = "multipart/*";
	private static final String PROPERTIES_FILE = "pageobjects.properties";

	public boolean isEmailContainsText(Message message, String text) {
		return StringUtils.containsIgnoreCase(getMessageBody(message), text.toLowerCase());
	}

	public boolean isEmailContainsRegex(Message message, String regex) {
		Pattern p = Pattern.compile(regex);
		return p.matcher(getMessageBody(message)).find();
	}

	public String getMessageBody(Message message) {
		String messageBody = StringUtils.EMPTY;
		if (isMessageMimeType(message, MESSAGE_MIMETYPE_TEXT)) {
			messageBody = getMessageContentWithTextMime(message);
		} else if (isMessageMimeType(message, MESSAGE_MIMETYPE_MULTIPART)) {
			messageBody = getMessageContentWithMultiPartMime(message);
		}
		return messageBody;
	}

	private String getMessageContentWithMultiPartMime(Message message) {
		StringBuilder resultBuilder = new StringBuilder();
		try {
			Multipart multipart = (Multipart) message.getContent();
			int count = multipart.getCount();
			for (int i = 0; i < count; i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				if (bodyPart.isMimeType(MESSAGE_MIMETYPE_TEXT)) {
					resultBuilder.append(StringUtils.LF);
					resultBuilder.append(bodyPart.getContent());
					break;
				}
			}
		} catch (MessagingException | IOException exp) {
			throw new EmailException("Unable get content from MultiPartMime message ", exp);
		}
		return resultBuilder.toString();
	}

	private String getMessageContentWithTextMime(Message message) {
		try {
			return message.getContent().toString();
		} catch (MessagingException | IOException exp) {
			throw new EmailException("Unable to verify the mime type", exp);
		}
	}

	private boolean isMessageMimeType(Message message, String type) {
		try {
			return message.isMimeType(type);
		} catch (MessagingException exp) {
			throw new EmailException("Unable to verify the mime type", exp);
		}
	}

	public String retrieveAttachmentName(Message message) {
		try {
			if (isMessageMimeType(message, MESSAGE_MIMETYPE_MULTIPART)) {
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
			throw new EmailException("Unable to retrieve attachment", exp);
		}
		return StringUtils.EMPTY;
	}

	public File downloadAttachment(Message message) {
		return downloadAttachment(message, System.getProperty("user.dir") + "/target/test-downloads");
	}

	public File downloadAttachment(Message message, String baseFolder) {
		File attachmentFile = null;

		try {
			Multipart multipart = (Multipart) message.getContent();
			for (int index = 0; index < multipart.getCount(); index++) {
				BodyPart bodyPart = multipart.getBodyPart(index);
				if (isValidAttachmentInBodyPart(bodyPart)) {
					attachmentFile = writeBodyPartToAttachmentFile(baseFolder, bodyPart);
					break;
				}
			}
		} catch (MessagingException | IOException exp) {
			throw new EmailException("Unable to download attachment", exp);
		}
		return attachmentFile;
	}

	public String getAttachmentExtension(Message message) {
		// Get attachment file name and replcae everything except extension
		// For "Some document.doc" only "doc" will be returned
		return retrieveAttachmentName(message).replaceAll(".*(?=\\.)\\.", StringUtils.EMPTY);
	}

	private boolean isValidAttachmentInBodyPart(BodyPart bodyPart) {
		return !isNotValidAttachmentInBodyPart(bodyPart);
	}

	private boolean isNotValidAttachmentInBodyPart(BodyPart bodyPart) {
		try {
			return (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())
					&&
					bodyPart.getFileName() == null);
		} catch (MessagingException exp) {
			throw new EmailException("Error getting file name from body part", exp);
		}
	}

	private File writeBodyPartToAttachmentFile(String baseFolder, BodyPart bodyPart) throws IOException, MessagingException {
		File attachmentFile;
		InputStream inputStream = bodyPart.getInputStream();
		attachmentFile = createDirectoriesAndFile(baseFolder, bodyPart);
		writeToFile(attachmentFile, inputStream);
		return attachmentFile;
	}

	private File createDirectoriesAndFile(String baseFolder, BodyPart bodyPart) {
		File file;
		file = new File(buildDownloadFilePath(baseFolder, bodyPart));

		if (!file.getParentFile().mkdirs()) {
			LOG.info("unable to create directory structure!");
		}

		try {
			if (!file.createNewFile()) {
				LOG.info("file already exists! ");
			}
		} catch (IOException exp) {
			throw new EmailException("Error while creating a new file! " + exp.getMessage(), exp);
		}

		return file;
	}

	private String buildDownloadFilePath(String baseFolder, BodyPart bodyPart) {
		try {
			return baseFolder
					+ "/"
					+ new SimpleDateFormat(DATE_PATTERN_FOR_FOLDER).format(new Date())
					+ "/"
					+ bodyPart.getFileName();
		} catch (MessagingException exp) {
			throw new EmailException("Error extracting Filename from bodyPart ", exp);
		}
	}

	private void writeToFile(File file, InputStream inputStream) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException exp) {
			throw new EmailException("Error while writing to the file " + exp.getMessage(), exp);
		}
	}

	public boolean hasAttachment(Message message) {
		return !retrieveAttachmentName(message).isEmpty();
	}

}
