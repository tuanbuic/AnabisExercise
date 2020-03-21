package vn.anibis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String convertDate (String dateStr, String format) throws Exception {
        Date currentItemDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(currentItemDate);
    }
}
