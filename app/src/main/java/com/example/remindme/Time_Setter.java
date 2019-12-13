package com.example.remindme;
import android.net.Uri;


public class Time_Setter {

    public String remind_text;
    public String time_remaining_text;
    public Uri uri;

    public Time_Setter(String remind_text, String time_remaining_text, Uri uri) {
        this.remind_text = remind_text;
        this.time_remaining_text = time_remaining_text;
        this.uri = uri;
    }
}
