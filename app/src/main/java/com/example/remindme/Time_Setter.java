package com.example.remindme;
import android.net.Uri;

import java.util.Calendar;


public class Time_Setter {

    public String remind_text;
    public String time_remaining_text;
    public Uri uri;
    public long time;
    public  Calendar c;

    public Time_Setter(String remind_text, String time_remaining_text, Uri uri, long time, Calendar c) {
        this.remind_text = remind_text;
        this.time_remaining_text = time_remaining_text;
            this.uri = uri;
            this.time = time;
            this.c = c;
    }
    public Time_Setter(String remind_text, String time_remaining_text,long time,Calendar c) {
        this.remind_text = remind_text;
        this.time_remaining_text = time_remaining_text;
        this.time=time;
        this.c=c;
    }
}
