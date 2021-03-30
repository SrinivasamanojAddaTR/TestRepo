package com.thomsonreuters.pageobjects.utils.document;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DateFormat {
	private static final String SPLIT = " ";
	private static final String FORMAT = "dd MMMM yyyy";

	public String convertDateToTheCorrectFormat(String dateText) {
		String[] word = dateText.split(SPLIT);
		String month = word[1];
		int day = Integer.parseInt(word[0]);
		int year = Integer.parseInt(word[2]);
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		Calendar calendar = getCalendar(year, day, month);
		return sdf.format(calendar.getTime());
	}

	public Calendar getCalendar(int year, int day, String month) {

		switch (month) {
		case "January":
			return new GregorianCalendar(year, 0, day);
		case "February":
			return new GregorianCalendar(year, 1, day);
		case "March":
			return new GregorianCalendar(year, 2, day);
		case "April":
			return new GregorianCalendar(year, 3, day);
		case "May":
			return new GregorianCalendar(year, 4, day);
		case "June":
			return new GregorianCalendar(year, 5, day);
		case "July":
			return new GregorianCalendar(year, 6, day);
		case "August":
			return new GregorianCalendar(year, 7, day);
		case "September":
			return new GregorianCalendar(year, 8, day);
		case "October":
			return new GregorianCalendar(year, 9, day);
		case "November":
			return new GregorianCalendar(year, 10, day);
		case "December":
			return new GregorianCalendar(year, 11, day);
		default:
			return null;
		}
	}

}
