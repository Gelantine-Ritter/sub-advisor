package com.subadvisor.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeFormatter {

    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Timestamp parseTimestamp (String time_string){
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(time_string).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
