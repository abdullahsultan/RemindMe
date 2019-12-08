package com.example.remindme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_RecyclerView extends RecyclerView.Adapter<Adapter_RecyclerView.My_Holder> {

    private Context context;
    private int layout;
    private ArrayList<Time_Setter> data;

    public Adapter_RecyclerView(Context context, int layout, ArrayList<Time_Setter> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }



    @NonNull
    @Override
    public My_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(layout,parent,false);
            My_Holder holder = new My_Holder(view);
            return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull My_Holder holder, int position) {
        Time_Setter time_setter =data.get(position);
        holder.textView_reminderName.setText(time_setter.remind_text);
        holder.textView_TimeRemaining.setText(time_setter.time_remaining_text);
        holder.imageView.setImageBitmap(time_setter.bitmap);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class  My_Holder extends RecyclerView.ViewHolder{
        private TextView textView_reminderName;
        private TextView textView_TimeRemaining;
        private ImageView imageView;

        public My_Holder(@NonNull View itemView) {
            super(itemView);
            textView_reminderName = itemView.findViewById(R.id.textView_Reminder);
            textView_TimeRemaining = itemView.findViewById(R.id.textView_remainingTime);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
