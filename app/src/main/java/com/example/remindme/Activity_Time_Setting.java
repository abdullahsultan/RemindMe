package com.example.remindme;
import android.os.Bundle;
import android.view.View;
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
    private int year, month, day, hour, minute;

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

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                btnOK_datePick.setVisibility(View.VISIBLE);


                btnTimePicker.setVisibility(View.GONE);
                btnDatePicker.setVisibility(View.GONE);
                btn_ok_timePick.setVisibility(View.GONE);
                txtDate.setVisibility(View.GONE);
                txtTme.setVisibility(View.GONE);
                timePicker.setVisibility(View.GONE);
            }
        });

        btnOK_datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = datePicker.getYear();
                month = datePicker.getMonth();
                day = datePicker.getDayOfMonth();
                String date = month + " / " + day + " / " + year;
                txtDate.setText(date);

                datePicker.setVisibility(View.GONE);
                btnOK_datePick.setVisibility(View.GONE);


                btnTimePicker.setVisibility(View.VISIBLE);
                btnDatePicker.setVisibility(View.VISIBLE);
                btn_ok_timePick.setVisibility(View.GONE);
                txtDate.setVisibility(View.VISIBLE);
                txtTme.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.GONE);

            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                btn_ok_timePick.setVisibility(View.VISIBLE);

                btnTimePicker.setVisibility(View.GONE);
                btnDatePicker.setVisibility(View.GONE);
                datePicker.setVisibility(View.GONE);
                txtDate.setVisibility(View.GONE);
                txtTme.setVisibility(View.GONE);
                btnOK_datePick.setVisibility(View.GONE);
            }
        });

        btn_ok_timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = timePicker.getHour();
                minute = timePicker.getMinute();

                String time = hour + " : " + minute;
                txtTme.setText(time);

                datePicker.setVisibility(View.GONE);
                btnOK_datePick.setVisibility(View.GONE);
                btnTimePicker.setVisibility(View.VISIBLE);
                btnDatePicker.setVisibility(View.VISIBLE);
                btn_ok_timePick.setVisibility(View.GONE);
                txtDate.setVisibility(View.VISIBLE);
                txtTme.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.GONE);

            }
        });



    }

}
