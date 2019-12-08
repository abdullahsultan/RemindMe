package com.example.remindme;

import android.widget.ImageView;

public class Time_Setter {

    public String remind_text;
    public String time_remaining_text;
    public ImageView imageView;

    public Time_Setter(String remind_text, String time_remaining_text) {
        this.remind_text = remind_text;
        this.time_remaining_text = time_remaining_text;
    }
}
