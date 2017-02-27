package com.thomsonreuters.pageobjects.utils.urls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLsUtils {

    public static String getDocumentPlcRef(String url){
        Pattern p = Pattern.compile("(?<=uk\\/|com\\/)\\d{1}-\\d{3}-\\d{4}");
        Matcher m = p.matcher(url);
        if(m.find()){
            return m.group();
        } else {
            return "";
        }
    }

}
