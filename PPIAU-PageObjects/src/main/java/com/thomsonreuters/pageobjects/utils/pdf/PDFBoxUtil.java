package com.thomsonreuters.pageobjects.utils.pdf;

import com.google.common.collect.HashMultimap;
import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.common.Link;
import com.thomsonreuters.pageobjects.exceptions.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.type.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDChoiceField;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PDFBoxUtil {

	private static final Logger LOG = LoggerFactory.getLogger(PDFBoxUtil.class);

	public static final String LINE_BREAK_IN_PDF = "\\u000d\\u000a";

	public PDDocument readDocument(String urlToPdf) {
		PDDocument document;
		try {
			document = PDDocument.loadNonSeq(new File(urlToPdf), null);
		} catch (IOException e) {
			throw new ParseException(e.getMessage());
		}
		return document;
	}

	@SuppressWarnings("unchecked")
	private PDPage getFirstPage(PDDocument document) {
		List<Object> pages = document.getDocumentCatalog().getAllPages();
		return (PDPage) pages.get(0);
	}

	private PDFStreamParser parsePage(PDPage page) {
		PDFStreamParser parser;
		try {
			parser = new PDFStreamParser(page.getContents().getStream());
			parser.parse();
		} catch (IOException e) {
			throw new ParseException(e.getMessage());
		}
		return parser;
	}

	public void editName(PDDocument document, String newName) {
		PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
		try {
			PDField nameField = (PDField) acroForm.getFields().get(0);
			nameField.setValue(newName);
		} catch (IOException e) {
			throw new ParseException(e.getMessage());
		}
	}

	public void editOccupation(PDDocument document, String newOccupation) {
		PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
		try {
			PDField occupationField = (PDField) acroForm.getFields().get(7);
			occupationField.setValue(newOccupation);
		} catch (IOException e) {
			throw new ParseException(e.getMessage());
		}
	}

	public void editBirthDate(PDDocument document, String newBirthDate,
			String newBirthMonth, String newBirthYear) {
		PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
		try {
			PDChoiceField dateOfBirthDate = (PDChoiceField) acroForm
					.getFields().get(1);
			dateOfBirthDate.setValue(newBirthDate);

			PDChoiceField dateOfBirthMonth = (PDChoiceField) acroForm
					.getFields().get(2);
			dateOfBirthMonth.setValue(newBirthMonth);

			PDChoiceField dateOfBirthYear = (PDChoiceField) acroForm
					.getFields().get(3);
			dateOfBirthYear.setValue(newBirthYear);
		} catch (IOException e) {
			throw new PageOperationException(e.getMessage());
		}
	}

	public void save(PDDocument document, String urlToPdf) {
		PDStream updatedStream = new PDStream(document);
		PDPage page = getFirstPage(document);
		PDFStreamParser parser = parsePage(page);
		try {
			OutputStream out = updatedStream.createOutputStream();
			ContentStreamWriter tokenWriter = new ContentStreamWriter(out);
			tokenWriter.writeTokens(parser.getTokens());
		} catch (IOException e1) {
			throw new ParseException(e1.getMessage());
		}
		page.setContents(updatedStream);
		document.setAllSecurityToBeRemoved(true);
		try {
			document.save(urlToPdf);
			document.close();
		} catch (COSVisitorException | IOException e) {
			throw new ParseException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private HashMultimap<String, PDAction> extractLinks(PDDocument document) throws IOException {
		HashMultimap<String, PDAction> links = HashMultimap.create();
		for (PDPage page : (List<PDPage>) document.getDocumentCatalog()
				.getAllPages()) {
			links.putAll(extractLinks(page));
		}
		return links;
	}

	private HashMultimap<String, PDAction> extractLinks(PDPage page) throws IOException {
		HashMultimap<String, PDAction> links = HashMultimap.create();
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		List<PDAnnotation> annotations = page.getAnnotations();
		// First setup the text extraction regions.
		for (int j = 0; j < annotations.size(); j++) {
			PDAnnotation annotation = annotations.get(j);
			if (annotation instanceof PDAnnotationLink) { // public class
															// PDAnnotationLink
															// extends
															// PDAnnotation
				PDAnnotationLink link = (PDAnnotationLink) annotation;
				PDRectangle rect = link.getRectangle();
				// Need to reposition link rectangle to match text space.
				float x = rect.getLowerLeftX();
				float y = rect.getUpperRightY();
				float width = rect.getWidth();
				float height = rect.getHeight();
				int rotation = page.findRotation();
				if (rotation == 0) {
					PDRectangle pageSize = page.findMediaBox();
					y = pageSize.getHeight() - y;
				} else if (rotation == 90) {
					// Do nothing.
				}
				Rectangle2D.Float awtRect = new Rectangle2D.Float(x, y, width,
						height);
				stripper.addRegion(String.valueOf(j), awtRect);
			}
		}
		stripper.extractRegions(page);
		for (int j = 0; j < annotations.size(); j++) {
			PDAnnotation annotation = annotations.get(j);
			if (annotation instanceof PDAnnotationLink) {
				PDAnnotationLink link = (PDAnnotationLink) annotation;
				String label = stripper.getTextForRegion(String.valueOf(j))
						.trim();
				stripper.getFonts();
				LOG.info("label: {}", label);
				links.put(label, link.getAction());
			}
		}
		return links;
	}

	public Map<String, String> extractURLs(String path) throws IOException {
		Map<String, String> urls = new HashMap<>();
		PDDocument document = null;
		try {
			document = PDDocument.load(path);
			for (Map.Entry<String, PDAction> entry : extractLinks(document).entries()) {
				if (entry.getValue() instanceof PDActionURI) { // public class
																// PDActionURI
																// extends
																// PDAction
					PDActionURI uri = (PDActionURI) entry.getValue();
					urls.put(entry.getKey(), uri.getURI());
				}
			}
		} finally {
			if (document != null) {
				document.close();
			}
		}
		return urls;
	}

    public List<Link> extractURLsToLinks(String path) throws IOException {
        List<Link> links = new ArrayList<>();
        PDDocument document = null;
        try {
            document = PDDocument.load(path);
            for (Map.Entry<String, PDAction> entry : extractLinks(document).entries()) {
                // public class PDActionURI extends PDAction
                if (entry.getValue() instanceof PDActionURI) {
                    PDActionURI uri = (PDActionURI) entry.getValue();
                    links.add(new Link(uri.getURI(), entry.getKey()));
                }
            }
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return links;
    }

	public String extractText(String path) throws IOException {
		PDDocument document = readDocument(path);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String parsedText = pdfStripper.getText(document);
		document.close();
		return parsedText;
	}

	public String extractTextJoinLines(String path) throws IOException {
		String textWithLineBreaks = extractText(path);
		return textWithLineBreaks.replace(LINE_BREAK_IN_PDF, StringUtils.SPACE);

	}

	public String extractFirstPageText(String path) throws IOException {
		PDDocument document = readDocument(path);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		pdfStripper.setStartPage(1);
		pdfStripper.setEndPage(1);
		return pdfStripper.getText(document);
	}

	public float getFontSizeFromPdf(String path, String text, int index)
			throws IOException {
		Parser stripper = new Parser();
		stripper.parse(path, text);
		return stripper.getFontSize(index);
	}

	public Color getFontColorFromPdf(String path, String text, int index)
			throws IOException {
		Parser stripper = new Parser();
		stripper.parse(path, text);
		return stripper.getFontColor(index);
	}

	public String[] getFontLineByLineFromPdf(String path) throws IOException {
		PDDocument doc = PDDocument.load(path);
		Parser stripper = new Parser();
		String content = stripper.getText(doc);
		doc.close();
		return content.split("\\r?\\n");
	}
	
	
	public boolean getAndSaveImagesFromFirstPage(PDDocument document, String path){
		PDPage page = getFirstPage(document);
        PDResources pdResources = page.getResources();
        Map<String, PDXObject> objects = pdResources.getXObjects();
    	if (objects == null)
    		return false;
    	File file;
    	int count = 0;
		for (PDXObject object : objects.values())
    		if (object instanceof PDXObjectImage){
    			count++;
    			file = new File(path + count + ".jpg");
                    try {
    					ImageIO.write(((PDXObjectImage) object).getRGBImage(), "jpg", file);
    				} catch (IOException e) {
    					throw new PageOperationException(e.getMessage());
    				}
    		}
		return true;
	}
	
	public BufferedImage getImageFromFirstPage(PDDocument document, int count){
		PDPage page = getFirstPage(document);
        PDResources pdResources = page.getResources();
        Map<String, PDXObject> objects = pdResources.getXObjects();
    	if (objects == null)
    		return null;
    	int countActual = 0;
		for (PDXObject object : objects.values())
    		if (object instanceof PDXObjectImage){
    			countActual++;
    			if (countActual==count){
    				try {
    					return ((PDXObjectImage) object).getRGBImage();
					} catch (IOException e) {
    					throw new PageOperationException(e.getMessage());
					}
    			}
    		}
		return null;
	}	


	public String extractPageTextByPageNumber(String path, int startPage, int endPage) throws IOException{
		PDDocument document = readDocument(path);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		pdfStripper.setStartPage(startPage);
		pdfStripper.setEndPage(endPage);
		return pdfStripper.getText(document);
	}
	
	public String extractPageTextByPageNumberJoinLines(String path, int startPage, int endPage) throws IOException {
		String textWithLineBreaks = extractPageTextByPageNumber(path, startPage, endPage);
		return textWithLineBreaks.replace(LINE_BREAK_IN_PDF, StringUtils.SPACE);
	}

}
