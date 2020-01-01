package com.example.remindme;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
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
    public void onBindViewHolder(@NonNull final My_Holder holder, final int position) {
        Time_Setter time_setter =data.get(position);
        holder.textView_reminderName.setText(time_setter.remind_text);
        holder.textView_TimeRemaining.setText(time_setter.time_remaining_text);
        if(time_setter.uri != null)
            holder.imageView.setImageURI(time_setter.uri);

        /////////////////////////////////////////////         Alarm ////////////////////////////
        Intent intent = new Intent(context, My_Alarms.class);
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(context,5,intent,0);
        context.sendBroadcast(intent);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time_setter.time,pendingIntent);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();

                Intent intent = new Intent(context, My_Alarms.class);
                PendingIntent pendingIntent =  PendingIntent.getBroadcast(context,5,intent,0);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class  My_Holder extends RecyclerView.ViewHolder{
        private TextView textView_reminderName;
        private TextView textView_TimeRemaining;
        private ImageButton btnDelete;
        private ImageView imageView;

        public My_Holder(@NonNull View itemView) {
            super(itemView);
            textView_reminderName = itemView.findViewById(R.id.textView_Reminder);
            textView_TimeRemaining = itemView.findViewById(R.id.textView_remainingTime);
            imageView = itemView.findViewById(R.id.imageView);
            btnDelete = itemView.findViewById(R.id.delete);
        }
    }


}
