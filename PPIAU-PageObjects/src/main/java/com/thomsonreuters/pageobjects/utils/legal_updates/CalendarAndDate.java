package com.thomsonreuters.pageobjects.utils.legal_updates;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarAndDate {

	private static final Logger LOG = LoggerFactory.getLogger(CalendarAndDate.class);

	private static final String DATE_FORMAT_PATTERN = "dd MMM yyyy";

	public static String convertMonthFromIntToStringRepresentation(int month) throws ParseException {
		String currentMonth = Integer.toString(month + 1);
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
	    SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
	    return monthDisplay.format(monthParse.parse(currentMonth));
	}
    
    public static String getCurrentMonth() throws ParseException {
		Calendar currentCalendar = Calendar.getInstance();
		int currentMonth = currentCalendar.get(Calendar.MONTH);
		return convertMonthFromIntToStringRepresentation(currentMonth);
	}
    
    public static String getCurrentYear() {
    	Calendar currentCalendar = Calendar.getInstance();
		return Integer.toString(currentCalendar.get(Calendar.YEAR));
    }
    
    public static int getWeekDayOfFirstDayInMonth(boolean moveMonthForward) {
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.roll(Calendar.MONTH, moveMonthForward);
		int month = currentCalendar.get(Calendar.MONTH);
		currentCalendar.set(Calendar.MONTH, month);
		currentCalendar.set(Calendar.DAY_OF_MONTH, currentCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		int dayOfWeek = currentCalendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0)
		    dayOfWeek = 7;
		
		return dayOfWeek;
	}
	
	public static int getWeekDayOfLastDayInMonth(boolean moveMonthForward) {
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.roll(Calendar.MONTH, moveMonthForward);
		int month = currentCalendar.get(Calendar.MONTH);
		currentCalendar.set(Calendar.MONTH, month);
		currentCalendar.set(Calendar.DAY_OF_MONTH, currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		int dayOfWeek = currentCalendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0)
		    dayOfWeek = 7;
		
		return dayOfWeek;
	}
	
	public static int getLastDayOfMonth(boolean moveMonthForward) {
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.roll(Calendar.MONTH, moveMonthForward);
		int month = currentCalendar.get(Calendar.MONTH);
		currentCalendar.set(Calendar.MONTH, month);
		return currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static String getCurrentDay(){
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}

	public static String getCurrentDay(String timezone){
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
		return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}

	public static String getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, 11);
		Date time = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
		return sdf.format(time);
	}

	public static String getCurrentDateEnglish() {
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN,Locale.ENGLISH);
        return sdf.format(time);
	}
	
	public static String getCurrentDateOldFormat() {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(time);
	}
	
	public static String getCurrentDateAndTime() {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm");
		return sdf.format(time);
	}
	
	public static String getCurrentDateAndTimeUK() {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		return sdf.format(time);
	}

	public static String getCurrentDateAndTimeOldFormat() {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm hh:mm a");
		return sdf.format(time);
	}
	
	public static Date convertPublishedDateStringInstanceToDateInstanceFromLUPage(String publishedDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.US);
		return sdf.parse(publishedDate);
	}

    public static List<Date> convertListOfStringToListOfDate(List<String> stringDates, String dateFormat) throws ParseException {
        List<Date> dates = new ArrayList<>();
        DateFormat sdrf = new SimpleDateFormat(dateFormat);
        for (int i = 0; i < stringDates.size(); i++) {
            Date date = sdrf.parse(stringDates.get(i));
            dates.add(date);
        }
        return dates;
    }

    public static List<String> convertListOfDateToListOfString(List<Date> dates, String dateFormat) {
        List<String> stringDates = new ArrayList<>();
        DateFormat sdrf = new SimpleDateFormat(dateFormat);
        for (int i = 0; i < dates.size(); i++) {
            stringDates.add(sdrf.format(dates.get(i)));
        }
        return stringDates;
    }
    
    public static Date convertStringToDate(String date, String dateFormat) throws ParseException {
		DateFormat sdrf = new SimpleDateFormat(dateFormat);
	    return sdrf.parse(date);
	}

	public static String convertDateToString(Date date, String dateFormat) {
		if (date == null || dateFormat == null) {
			LOG.info("There is no date to parse");
			return "";
		}
		DateFormat sdrf = new SimpleDateFormat(dateFormat);
		return sdrf.format(date);
	}
    public static String getAddSubToChosenMonth(String selectedMonth, int number) throws ParseException {
        Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(selectedMonth);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return convertMonthFromIntToStringRepresentation(cal.get(Calendar.MONTH)+number);
    }
    public static int convertTextMonthToIntMonth(String month) throws ParseException {
        Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

	public int getDifferenceInMonth(String date) throws ParseException {
		String currentDate = getCurrentDate();
		return getDifferenceInMonth(currentDate, date);
	}

    public int getDifferenceInMonth(String startDate, String endDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MMMMM yyyy");
        return Months.monthsBetween(new DateTime(format.parse(startDate)), new DateTime(format.parse(endDate))).getMonths();
    }
}
