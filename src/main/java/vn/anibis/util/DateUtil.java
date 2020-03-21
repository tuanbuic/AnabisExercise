package vn.anibis.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static String convertDate (String dateStr, String format) throws Exception {
        Date currentItemDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(currentItemDate);
    }
    public static String getCurrentDate(String format){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.now();
       return dtf.format(localDate);
    }
}
