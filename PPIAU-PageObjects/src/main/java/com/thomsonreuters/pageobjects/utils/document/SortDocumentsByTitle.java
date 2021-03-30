package com.thomsonreuters.pageobjects.utils.document;



import java.util.Comparator;


public class SortDocumentsByTitle implements Comparator<Document>{

	@Override
	public int compare(Document o1, Document o2) {
            
             String str1 = o1.getTitle();
             String str2 = o2.getTitle();
            
             return str1.compareTo(str2);
	}
	
}
