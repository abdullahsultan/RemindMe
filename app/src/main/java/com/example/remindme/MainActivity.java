package com.example.remindme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String time,name;
    private ArrayList<Time_Setter> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setBackground(getDrawable(R.drawable.green_plus_big));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Activity_Time_Setting.class);
                startActivityForResult(intent,5);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 5 && resultCode==1)
        {
            time = data.getStringExtra("time");
            name = data.getStringExtra("name");

            //Bundle extras = getIntent().getExtras();
            //byte[] byteArray =  data.getByteArrayExtra("picture");                                    //extras.getByteArray("picture");

            //Bundle bundle = new Bundle();
            //bundle = data.getExtras();
            //bundle = data.getBundleExtra("picture");
            //byte[] byteArray = bundle.getByteArray("pic");

            //Log.i("CHACH", "onActivityResult: "+byteArray.length);
            //Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            String uri = data.getStringExtra("uri");
            Uri my_uri = Uri.parse(uri);
            Toast.makeText(this, my_uri.toString(), Toast.LENGTH_LONG).show();
            Time_Setter t =  new Time_Setter(name,time,my_uri);
            arrayList.add(t);
            Adapter_RecyclerView adapter_recyclerView = new Adapter_RecyclerView(this,R.layout.resource_file,arrayList);
            recyclerView.setAdapter(adapter_recyclerView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);

            recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

            recyclerView.setLayoutManager(linearLayoutManager);

        }
    }





}
