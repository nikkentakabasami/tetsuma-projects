package ru.tet.utils;

import java.math.RoundingMode;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;


/**
 * Форматы и функции форматирования чисел и дат для использования на серверной
 * стороне.
 * 
 * @author tetsuma
 *
 */
public class TetFormatUtils {

  public static Locale LOCALE = new Locale("ru", "RU");
  public static Locale LOCALE_EN = Locale.US;

  
//  private static final DecimalFormatSymbols DF_SYMBOLS = new DecimalFormatSymbols(LOCALE_EN);

  
  public static final NumberFormat CURRENCY_INSTANCE = NumberFormat.getCurrencyInstance(LOCALE);
  public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.##;-#0.##");
  public static final DecimalFormat DECIMAL_PRECISE_FORMAT = new DecimalFormat("#0.####;-#0.####");
  public static final DecimalFormat DECIMAL_PRECISE_FORMAT6 = new DecimalFormat("#0.######;-#0.######");

  public static final DecimalFormat DECIMAL_FIXED_FORMAT = new DecimalFormat("#0.00;-#0.00");

  
  public static final DecimalFormat DECIMAL_PRECISE_FORMAT3 = new DecimalFormat("###,###.###;-###,###.###");
  
  //для денег
  public static final DecimalFormat DECIMAL_BIG_FORMAT = new DecimalFormat("###,##0.00;-###,##0.00");
  
  
  public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy", LOCALE);
  public static final SimpleDateFormat SIMPLE_DATETIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm", LOCALE);
  public static final SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat("HH:mm", LOCALE);

  public static final SimpleDateFormat SIMPLE_DATETIME_FILE_FORMAT =
      new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", LOCALE);

  public static final SimpleDateFormat SIMPLE_MONTH_FORMAT = new SimpleDateFormat("MM", LOCALE);
  public static final SimpleDateFormat SIMPLE_LONG_MONTH_FORMAT = new SimpleDateFormat("MMMM", LOCALE);
  public static final SimpleDateFormat SIMPLE_YEAR_FORMAT = new SimpleDateFormat("yyyy", LOCALE);

  public static String POSITIVE_INTEGER_REGEX = "^\\s*\\d+\\s*$";
  public static String SIMPLE_DATE_REGEX = "\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d";
  
  
  static {
    DECIMAL_FORMAT.setRoundingMode(RoundingMode.HALF_UP);
    DECIMAL_PRECISE_FORMAT.setRoundingMode(RoundingMode.HALF_UP);
    DECIMAL_PRECISE_FORMAT6.setRoundingMode(RoundingMode.HALF_UP);
  }

  
  public static String formatDate(Date d){
    if (d==null){
      return "";
    }
    return SIMPLE_DATE_FORMAT.format(d);
  }
  
  public static String formatDateTime(Date d){
    if (d==null){
      return "";
    }
    return SIMPLE_DATETIME_FORMAT.format(d);
  }
  
  
  public static boolean isCorrectMail(String mail) {
    if (mail == null || mail.indexOf('@') <= 0)
      return false;
    return true;
  }

  public static String replaceComma(String str) {
    return str.trim().replace(',', '.');
  }

  public static String removeBlanksFromNumber(String str) {
	    return str.replaceAll("[^0-9\\.,\\-]+", "");
	  }
  
  
  //--------------floats------------------


	public static String doubleToStringNeat(double d) {
		if (d == (long) d)
			return String.format("%d", (long) d);
		else
			return String.format("%s", d);
	}
  
  public static double strToDouble(String str) {

    if (StringUtils.isEmpty(str))
      return 0.0;

    str = replaceComma(str);
    str = removeBlanksFromNumber(str);
    return Double.parseDouble(str);
  }

  public static String doubleToStringPrecise(Object d) {
    if (d == null) {
      return "";
    }
    return DECIMAL_PRECISE_FORMAT.format(d);
  }

  public static String doubleToStringPrecise6(Object d) {
    if (d == null) {
      return "";
    }
    return DECIMAL_PRECISE_FORMAT6.format(d);
  }

  public static String doubleToStringPrecise2(Object d) {
    if (d == null) {
      return "";
    }
    return DECIMAL_FORMAT.format(d);
  }
  
	public static String formatMoney(Object d) {
		if (d == null) {
			return "";
		}
		return DECIMAL_BIG_FORMAT.format(d);

	}
  
  
  public static String intToString(Number i) {
	  return doubleToStringPrecise2(i);
  }
  
  
  public static double roundNumber(Number d, int precision) {
    if (d == null) {
      return 0;
    }
    
    double m = Math.pow(10, precision);
    double r = Math.round(d.doubleValue()*m); 
    return r/m;
  }
  

  public static String doubleToString(Number d, int precision) {
  	
//    if (d == null) {
//      return "";
//    }
//    
//    double m = Math.pow(10, precision);
//    double r = Math.round(d.doubleValue()*m); 
//    d = r/m;
    
    return String.valueOf(roundNumber(d, precision));
  }
  
  
  public static boolean strIsPositiveInt(String str) {
    if (str == null)
      return false;

    return str.matches(POSITIVE_INTEGER_REGEX);
  }

  public static int getMonth(Date d) {
    if (d==null){
      return 0;
    }
    return DateUtils.toCalendar(d).get(Calendar.MONTH);
  }

  public static int getYear(Date d) {
    if (d==null){
      return 0;
    }
    return DateUtils.toCalendar(d).get(Calendar.YEAR);
  }

  public static int getQuartal(Date d) {
    int currentMonth = getMonth(d);
    return currentMonth / 3 + 1;
  }
  
  public static Date parseSimpleDate(String exp) throws ParseException{
  	if (StringUtils.isBlank(exp)){
  		return null;
  	}
  	return SIMPLE_DATE_FORMAT.parse(exp);
  }
  
  
  /**
   * Преобразовать строку в дату 
   * При этом допускаются выражения вида:
   * "2007"
   * "04.2007"
   * "01,02,2007"
   * 
   * @param exp
   * @return
   */
  public static Date parseDateTolerant(String exp){
  	if (StringUtils.isBlank(exp)){
  		return null;
  	}
    
    exp = exp.replace(',', '.');
    
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(0);
    if (exp.matches("\\d\\d\\d\\d")){
      int year = Integer.parseInt(exp);
      c.set(year, 0, 1, 0, 0);
    } else if (exp.matches("\\d\\d\\.\\d\\d\\d\\d")){
      int year = Integer.parseInt(exp.substring(3));
      int month = Integer.parseInt(exp.substring(0, 2));
      if (month<1 || month>12){
        return null;
      }
      c.set(year, month-1, 1, 0, 0);
    } else if (exp.matches("\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d")){
      int day = Integer.parseInt(exp.substring(0, 2));
      int month = Integer.parseInt(exp.substring(3, 5));
      int year = Integer.parseInt(exp.substring(6));
      
      c.set(year, month-1, day, 0, 0);
    } else {
      return null;
    }

    return c.getTime();
  }
  
  /**
   * Январь = 0.
   * @param monthNo
   * @return
   */
  public static String getMonthName(Integer monthNo) {
  	if (monthNo==null){
  		return null;
  	}
  	
    Calendar c = Calendar.getInstance();
    c.set(Calendar.MONTH, monthNo);
    return SIMPLE_LONG_MONTH_FORMAT.format(c.getTime()).toLowerCase();
  }
  
  /**
   * Преобразовыает название месяца в его номер.
   * янвать = 0
   * 
   * @param monthName
   * @return
   * @throws ParseException
   */
	public static int parseMonth(String monthName) throws ParseException {

		Date d = TetFormatUtils.SIMPLE_LONG_MONTH_FORMAT.parse(monthName);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int m = c.get(Calendar.MONTH);
		return m;

	}
  
  public static String listToString(Collection<? extends Object> l) {

    StringBuilder sb = new StringBuilder();
    for (Object o : l) {
      if (sb.length() > 0)
        sb.append(',');
      sb.append(o.toString());
    }

    return sb.toString();
  }

  public static Date roundToDay(Date d) {
    return DateUtils.round(d, Calendar.DATE);
  }

  /**
   * Возвращает диапазон между двумя датами в строковом виде (с точностью до
   * минуты)
   * 
   * @param date1
   * @param date2
   * @return
   */
  public static String getDiapazonInMinutes(Date date1, Date date2) {

    long minutesFull = Math.abs(TimeUnit.MILLISECONDS.toMinutes(date1.getTime() - date2.getTime()));

    long hours = minutesFull / 60;
    long minutes = minutesFull % 60;
    return hours + ":" + minutes;

  }

  public static String getDiapazonInMinutes(long date1, long date2) {

	    long minutesFull = Math.abs(TimeUnit.MILLISECONDS.toMinutes(date1 - date2));

	    long hours = minutesFull / 60;
	    long minutes = minutesFull % 60;
	    
	    return String.format("%02d:%02d", hours, minutes);	    

	  }
  
  
  public static int getCurrentYear() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    return currentYear;
  }

  public static int getCurrentQuartal() {
    int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
    return currentMonth / 3 + 1;
  }
  
  public String getCurrentDateTimeString(){
    return SIMPLE_DATETIME_FORMAT.format(new Date());
  }
  
  
  /**
   * Форматирует размер данных в читабельный формат.
   * 
   * @param bytes
   * @return
   */
  public static String formatFileSize(Integer bytes) {
    if (bytes==null) {
      return "";
    }
    long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
    if (absB < 1024) {
        return bytes + " Б";
    }
    long value = absB;
//    CharacterIterator ci = new StringCharacterIterator("KMGTPE");
    CharacterIterator ci = new StringCharacterIterator("KMГTПE");
    for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
        value >>= 10;
        ci.next();
    }
    value *= Long.signum(bytes);
//    return String.format("%.1f %cБ", value / 1024.0, ci.current());
    return String.format("%.0f %cБ", value / 1024.0, ci.current());  }
  
  
  
  
  
  
  
  public static void main(String[] args) {
	  
	  System.out.println(formatFileSize(592));
	  
	System.out.println(DECIMAL_PRECISE_FORMAT3.format(-2333444555.544));
	System.out.println(DECIMAL_PRECISE_FORMAT3.format(5.0));
}
  

}
