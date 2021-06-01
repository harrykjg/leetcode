package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateConvert {
    public static void main(String[] args){
        DateConvert dc=new DateConvert();
        System.out.println("rrrrrr"+dc.getUTCTimestampFromDB("Tue Dec 31 16:00:00 PST 2019"));
    }

    public Date getUTCTimestampFromDB(String date) {
        SimpleDateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatTo.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat formatNow = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
//        formatNow.setTimeZone(TimeZone.getTimeZone("CST"));
        try {
            Date dateNow= formatNow.parse(date);
            System.out.println("111111"+dateNow);
            String dateNeeded=formatTo.format(dateNow);
            System.out.println("222222"+dateNeeded);
            Date dateNeeded2=formatTo.parse(dateNeeded);
            System.out.println("333333"+dateNeeded2);
            return dateNeeded2;
        } catch (ParseException ex) {

            System.out.println("eeeee");
            return null;
        }
    }
}
