package com.example.onroad.addLines;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.example.onroad.DemoClass;
import com.example.onroad.R;
import com.example.onroad.departLines.discover;

import java.util.ArrayList;
import java.util.List;

public class addDatesAdapter extends RecyclerView.Adapter<addDatesAdapter.ViewHolder> {

    Context context;
    com.example.onroad.departLines.discover discover;
    addLines addLines;


   private List<addDatesList> lineslist;


    public addDatesAdapter(List<addDatesList> getDataAdapter, Context context) {

        this.lineslist = getDataAdapter;


        this.context = context;
    }

    @Override
    public addDatesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adddatescard, parent, false);

        addDatesAdapter.ViewHolder viewHolder = new addDatesAdapter.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(addDatesAdapter.ViewHolder holder, int position) {

        addDatesList getDataAdapter1 = lineslist.get(position);

        holder.dateCard.setText(getDataAdapter1.getDate());

        holder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               lineslist.remove(position);

                notifyDataSetChanged();



            }
        });


    }

    @Override
    public int getItemCount() {

        return lineslist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dateCard;
        public ImageView button;


        public ViewHolder(View itemView) {

            super(itemView);

            dateCard = (TextView) itemView.findViewById(R.id.dateCard);
            button=(ImageView)itemView.findViewById(R.id.deleteDate);




        }
    }
}

