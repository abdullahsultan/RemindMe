package com.example.remindme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String name,repeat;
    private int day,month,year,hour,minute;
    private Boolean snooze,alarm;
    private ArrayList<Time_Setter> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this,Activity_Time_Setting.class);
                startActivityForResult(intent,5);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 5 && resultCode==1)
        {

            name = data.getStringExtra("name");
            day = data.getIntExtra("day",0);
            month = data.getIntExtra("month",0);
            year = data.getIntExtra("year",0);
            hour = data.getIntExtra("hour",0);
            minute = data.getIntExtra("minute",0);
            snooze = data.getBooleanExtra("snooze", false);
            alarm = data.getBooleanExtra("alarm",false);
            repeat = data.getStringExtra("repeat");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.HOUR_OF_DAY,hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND,0);
            long time =  calendar.getTimeInMillis();


            String uri = data.getStringExtra("uri");
            if(uri!=null)
            {Uri my_uri = Uri.parse(uri);
            Time_Setter t =  new Time_Setter(name,repeat,my_uri,time,calendar);
            arrayList.add(t);}
            else
            {Time_Setter t =  new Time_Setter(name,repeat,time,calendar);
                arrayList.add(t);
            }



            Adapter_RecyclerView adapter_recyclerView = new Adapter_RecyclerView(this,R.layout.resource_file,arrayList);
            recyclerView.setAdapter(adapter_recyclerView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);

            recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

            recyclerView.setLayoutManager(linearLayoutManager);

        }
    }





}
