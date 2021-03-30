package com.thomsonreuters.pageobjects.utils.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.ResourceLoader;
import org.apache.pdfbox.util.TextPosition;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Parser extends PDFTextStripper {
	
	final private static String PATH_TO_PAGE_DRAWER_PROPERTIES = "org/apache/pdfbox/resources/PageDrawer.properties";
	private int step;
	private List<TargetPositionKey> targets;
	private String textToFind;
	private List<Float> fontSizes;
	private List<Color> fontColors;

	public Parser() throws IOException {
		super(ResourceLoader.loadProperties(PATH_TO_PAGE_DRAWER_PROPERTIES, true));
		super.setSortByPosition(true);
		targets = new ArrayList<TargetPositionKey>();
		fontSizes = new ArrayList<Float>();
		fontColors = new ArrayList<Color>();		
	}

	public void parse(String path, String text) throws IOException {
		textToFind = text;
		PDDocument document = PDDocument.load(path);
		getText(document);
		for (int i = 0; i < targets.size(); i++) {
			fontColors.add(null);
		}
		step++;
		getText(document);
		document.close();
	}

	@Override
	protected void processTextPosition(TextPosition text) {
		if (step == 1) {
			try {
				int index = targets.indexOf(new TargetPositionKey(text));
				if (index >= 0) {
					fontColors.set(index, getGraphicsState().getNonStrokingColor().getJavaColor());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		super.processTextPosition(text);
	}

	@Override
	protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
		if (step == 0) {
			int fromIndex = -1;
			int index = text.indexOf(textToFind, fromIndex + 1);
			while (index >= 0) {
				TextPosition targetPosition = textPositions.get(index);
				targets.add(new TargetPositionKey(targetPosition));
				fontSizes.add(targetPosition.getFontSize());
				fromIndex = index;
				index = text.indexOf(textToFind, fromIndex + 1);
			}
		}
		super.writeString(text, textPositions);
	}

	public float getFontSize(int index) {
		if (index > 0 && index < fontSizes.size())
			return fontSizes.get(index);
		return fontSizes.get(0);
	}

	public Color getFontColor(int index) {
		if (index >= 0 && index < fontColors.size()) {
			return fontColors.get(index);
		} else
			return fontColors.get(0);
	}

}
