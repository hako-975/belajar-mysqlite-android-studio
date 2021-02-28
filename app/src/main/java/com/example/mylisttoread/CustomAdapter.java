package com.example.mylisttoread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;

    private Activity activity;

    private ArrayList list_id, list_title, list_reason, list_last_read_pages;

    private Animation translate_anim;

    CustomAdapter(Activity activity,
                Context context,
                ArrayList list_id,
                ArrayList list_title,
                ArrayList list_reason,
                ArrayList list_last_read_pages)
    {
        this.activity = activity;
        this.context = context;
        this.list_id = list_id;
        this.list_title = list_title;
        this.list_reason = list_reason;
        this.list_last_read_pages = list_last_read_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.list_id_txt.setText(String.valueOf(list_id.get(position)));
        holder.list_title_txt.setText(String.valueOf(list_title.get(position)));
        holder.list_reason_txt.setText(String.valueOf(list_reason.get(position)));
        holder.list_last_read_pages_txt.setText(String.valueOf(list_last_read_pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(list_id.get(position)));
                intent.putExtra("title", String.valueOf(list_title.get(position)));
                intent.putExtra("reason", String.valueOf(list_reason.get(position)));
                intent.putExtra("last_read_pages", String.valueOf(list_last_read_pages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView list_id_txt, list_title_txt, list_reason_txt, list_last_read_pages_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_id_txt = itemView.findViewById(R.id.list_id_txt);
            list_title_txt = itemView.findViewById(R.id.list_title_txt);
            list_reason_txt = itemView.findViewById(R.id.list_reason_txt);
            list_last_read_pages_txt = itemView.findViewById(R.id.list_last_read_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
