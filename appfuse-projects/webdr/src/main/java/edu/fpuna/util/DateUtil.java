package edu.fpuna.util;import org.apache.commons.logging.Log;import org.apache.commons.logging.LogFactory;import edu.fpuna.Constants;import org.springframework.context.i18n.LocaleContextHolder;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.*;import java.sql.Timestamp;/** * Date Utility Class used to convert Strings to Dates and Timestamps *  * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> *  Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>  *  to correct time pattern. Minutes should be mm not MM (MM is month).  */public class DateUtil {    private static Log log = LogFactory.getLog(DateUtil.class);    private static final String TIME_PATTERN = "HH:mm";    /**     * Checkstyle rule: utility classes should not have public constructor     */    private DateUtil() {    }    /**     * Return default datePattern (MM/dd/yyyy)     * @return a string representing the date pattern on the UI     */    public static String getDatePattern() {        Locale locale = LocaleContextHolder.getLocale();        String defaultDatePattern;        try {            defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale)                .getString("date.format");        } catch (MissingResourceException mse) {            defaultDatePattern = "MM/dd/yyyy";        }        return defaultDatePattern;    }    public static String getDateTimePattern() {        return DateUtil.getDatePattern() + " HH:mm:ss";    }    public static String getTimePattern() {        return "HH:mm:ss";    }        /**     * This method attempts to convert an Oracle-formatted date     * in the form dd-MMM-yyyy to mm/dd/yyyy.     *     * @param aDate date from database as a string     * @return formatted string for the ui     */    public static String getDate(Date aDate) {        SimpleDateFormat df;        String returnValue = "";        if (aDate != null) {            df = new SimpleDateFormat(getDatePattern());            returnValue = df.format(aDate);        }        return (returnValue);    }    /**     * This method generates a string representation of a date/time     * in the format you specify on input     *     * @param aMask the date pattern the string is in     * @param strDate a string representation of a date     * @return a converted Date object     * @see java.text.SimpleDateFormat     * @throws ParseException when String doesn't match the expected format     */    public static Date convertStringToDate(String aMask, String strDate)      throws ParseException {        SimpleDateFormat df;        Date date;        df = new SimpleDateFormat(aMask);        if (log.isDebugEnabled()) {            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");        }        try {            date = df.parse(strDate);        } catch (ParseException pe) {            //log.error("ParseException: " + pe);            throw new ParseException(pe.getMessage(), pe.getErrorOffset());        }        return (date);    }    /**     * This method returns the current date time in the format:     * MM/dd/yyyy HH:MM a     *     * @param theTime the current time     * @return the current date/time     */    public static String getTimeNow(Date theTime) {        return getDateTime(TIME_PATTERN, theTime);    }    /**     * This method returns the current date in the format: MM/dd/yyyy     *      * @return the current date     * @throws ParseException when String doesn't match the expected format     */    public static Calendar getToday() throws ParseException {        Date today = new Date();        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());        // This seems like quite a hack (date -> string -> date),        // but it works ;-)        String todayAsString = df.format(today);        Calendar cal = new GregorianCalendar();        cal.setTime(convertStringToDate(todayAsString));        return cal;    }    /**     * This method generates a string representation of a date's date/time     * in the format you specify on input     *     * @param aMask the date pattern the string is in     * @param aDate a date object     * @return a formatted string representation of the date     *      * @see java.text.SimpleDateFormat     */    public static String getDateTime(String aMask, Date aDate) {        SimpleDateFormat df = null;        String returnValue = "";        if (aDate == null) {            log.error("aDate is null!");        } else {            df = new SimpleDateFormat(aMask);            returnValue = df.format(aDate);        }        return (returnValue);    }        /**     * This method generates a string representation of a date based     * on the System Property 'dateFormat'     * in the format you specify on input     *      * @param aDate A date to convert     * @return a string representation of the date     */    public static String convertDateToString(Date aDate) {        return getDateTime(getDatePattern(), aDate);    }    /**     * This method converts a String to a date using the datePattern     *      * @param strDate the date to convert (in format MM/dd/yyyy)     * @return a date object     * @throws ParseException when String doesn't match the expected format     */    public static Date convertStringToDate(String strDate)      throws ParseException {        Date aDate = null;        try {            if (log.isDebugEnabled()) {                log.debug("converting date with pattern: " + getDatePattern());            }            aDate = convertStringToDate(getDatePattern(), strDate);        } catch (ParseException pe) {            log.error("Could not convert '" + strDate + "' to a date, throwing exception");            pe.printStackTrace();            throw new ParseException(pe.getMessage(),                                     pe.getErrorOffset());        }        return aDate;    }	/*	 * FUNCIONES AGREGADAS	 * Se Agregaron estas funciones para manejar el Timestamp	 */	/**	 * Convierte un Date a un formato string del tipo DateTime.	 */    public static String convertDateTimeToString(Date aDate) {		log.debug("converting (toString) dateTime with pattern: " + getDateTimePattern());   		return getDateTime(getDateTimePattern(), (Date) aDate);    }	/**	 * Convierte un String a un Timestamp.	 */    public static Timestamp convertStringToDateTime(String strDate) throws ParseException {        Date aDate = null;		Timestamp tiempo = null;        try {            if (log.isDebugEnabled()) {                log.debug("converting (toDateTime) dateTime with pattern: " + getDateTimePattern());            }            aDate = convertStringToDate(getDateTimePattern(), strDate);			tiempo = new Timestamp(0,0,0,aDate.getHours(),aDate.getMinutes(),aDate.getSeconds(),0);        } catch (ParseException pe) {            log.error("Could not convert '" + strDate + "' to a dateTime, throwing exception");            pe.printStackTrace();            throw new ParseException(pe.getMessage(),pe.getErrorOffset());        }        return tiempo;    }    public static String convertTimeToString(java.sql.Time aTime) {        return aTime.toString();    }	/**	 * Convierte un String a un Timestamp.	 */    public static java.sql.Time convertStringToTime(String strTime) throws Exception {        java.sql.Time aTime = new java.sql.Time(0);        String s[] = strTime.split(":");                try {            if(s.length == 3 || s.length == 2){                aTime.setHours(Integer.parseInt(s[0]));                aTime.setMinutes(Integer.parseInt(s[1]));                if(s.length == 3){                    aTime.setSeconds(Integer.parseInt(s[2]));                }else{                    aTime.setSeconds(0);                }            }else{                log.error("Mal formato de Time");                log.debug("Mal formato de Time");            }                    } catch (Exception pe) {            log.error("No podemos contertir '" + strTime + "' a un java.sql.Time, throwing exception");            pe.printStackTrace();            throw new Exception(pe.getMessage());        }        return aTime;    }    }