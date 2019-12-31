package com.example.remindme;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class 	Activity_Time_Setting extends AppCompatActivity  {

    private static final int REQUEST_STORAGE_PERMISSION = 1;

    private EditText txt_reminderName;
    private Button btnDatePicker, btnTimePicker, btnOK_datePick, btn_ok_timePick,  btn_PickImage;
    private ImageButton btn_ADD,btn_cancel;
    private Switch switch_snooze,switch_alarm;
    private TextView txtDate, txtTme, txt_snooze, txt_repeat, txt_enableAlarm;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Spinner spinner;
    private ImageView imageView;
    private int year, month, day, hour, minute;
    private Bitmap bitmap;
    private Uri uri;
    private static final int RQS_PICK_IMAGE = 3;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(ContextCompat.checkSelfPermission(Activity_Time_Setting.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Activity_Time_Setting.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_STORAGE_PERMISSION);
            Toast.makeText(Activity_Time_Setting.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        if(requestCode==1 && resultCode==RESULT_OK){
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
                uri = data.getData();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //intent.setType("image/*");
            startActivityForResult(intent, RQS_PICK_IMAGE);
        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__time__setting);

        btn_PickImage = findViewById(R.id.btnPickImage);
        btnDatePicker=findViewById(R.id.btn_date);
        btnTimePicker=findViewById(R.id.btn_time);
        btnOK_datePick=findViewById(R.id.btn_ok_datePicker);
        btn_ok_timePick=findViewById(R.id.btn_ok_timePicker);
        btn_cancel = findViewById(R.id.btnCancel);
        btn_ADD = findViewById(R.id.btnAdd);

        imageView = findViewById(R.id.imageView);

        switch_alarm = findViewById(R.id.switch_alarm);
        switch_snooze = findViewById(R.id.switch_snooze);

        txt_reminderName = findViewById(R.id.txt_reminderName);
        txtDate=findViewById(R.id.in_date);
        txtTme=findViewById(R.id.in_time);
        txt_snooze= findViewById(R.id.txt_snooze);
        txt_repeat = findViewById(R.id.txt_repepat);
        txt_enableAlarm = findViewById(R.id.txt_enable_Alarm);

        spinner = findViewById(R.id.spinner);

        datePicker=findViewById(R.id.datePicker);
        timePicker=findViewById(R.id.timePicker);

        //////////////////////////////////////////////////////Adding items to Spinner/////////////////////////////////
        ArrayList<String> array = new ArrayList<String>();
        array.add("None");array.add("Daily"); array.add("Mon to Fri"); array.add("Custom");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        /////////////////////////////////////////////////****************************************/////////////////////////////////////////

        //////////////////////////////////////////////// Setting image pick Button//////////////////////////////////////////

        btn_PickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT < 23) {


                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Pick an Image"),1);
                    }
                    ActivityCompat.requestPermissions(Activity_Time_Setting.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
                }

                else
                {
                    if(isStoragePermissionGranted() == true)
                    {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Pick an Image"),1);
                    }
                    else
                        ActivityCompat.requestPermissions(Activity_Time_Setting.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);

                }


            }
        });


        ///////////////////////////////////////////////****************************/////////////////////////////////////////

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
                txt_reminderName.setVisibility(View.GONE);
                btn_ADD.setVisibility(View.GONE);
                switch_snooze.setVisibility(View.GONE);
                switch_alarm.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                txt_snooze.setVisibility(View.GONE);
                txt_repeat.setVisibility(View.GONE);
                txt_enableAlarm.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                btn_PickImage.setVisibility(View.GONE);
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
                txt_reminderName.setVisibility(View.VISIBLE);
                btn_ADD.setVisibility(View.VISIBLE);
                switch_snooze.setVisibility(View.VISIBLE);
                switch_alarm.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                txt_snooze.setVisibility(View.VISIBLE);
                txt_repeat.setVisibility(View.VISIBLE);
                txt_enableAlarm.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                btn_PickImage.setVisibility(View.VISIBLE);

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
                txt_reminderName.setVisibility(View.GONE);
                btn_ADD.setVisibility(View.GONE);
                switch_snooze.setVisibility(View.GONE);
                switch_alarm.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                txt_snooze.setVisibility(View.GONE);
                txt_repeat.setVisibility(View.GONE);
                txt_enableAlarm.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                btn_PickImage.setVisibility(View.GONE);
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
                txt_reminderName.setVisibility(View.VISIBLE);
                btn_ADD.setVisibility(View.VISIBLE);
                switch_snooze.setVisibility(View.VISIBLE);
                switch_alarm.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                txt_snooze.setVisibility(View.VISIBLE);
                txt_repeat.setVisibility(View.VISIBLE);
                txt_enableAlarm.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                btn_PickImage.setVisibility(View.VISIBLE);

            }
        });

        btn_ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("time",txtTme.getText().toString());
                i.putExtra("name",txt_reminderName.getText().toString());
                i.putExtra("uri",uri.toString());
                setResult(1,i);
                finish();

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
