package com.example.remindme;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class 	Activity_Time_Setting extends AppCompatActivity  {

    private Button btnDatePicker, btnTimePicker, btnOK_datePick, btn_ok_timePick;
    private TextView txtDate, txtTme;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__time__setting);

        btnDatePicker=findViewById(R.id.btn_date);
        btnTimePicker=findViewById(R.id.btn_time);
        btnOK_datePick=findViewById(R.id.btn_ok_datePicker);
        btn_ok_timePick=findViewById(R.id.btn_ok_timePicker);

        txtDate=findViewById(R.id.in_date);
        txtTme=findViewById(R.id.in_time);

        datePicker=findViewById(R.id.datePicker);
        timePicker=findViewById(R.id.timePicker);

    }

}
