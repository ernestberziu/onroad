package com.example.onroad.addLines.addFrom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroad.DemoClass;
import com.example.onroad.MainActivity;
import com.example.onroad.R;
import com.example.onroad.addLines.addLines;

import java.util.ArrayList;

public class addFromAdapter extends RecyclerView.Adapter<addFromAdapter.MyViewHolder> {
    ArrayList<addFromList> list;
    Context context;
    public addFromAdapter(ArrayList<addFromList> list, Context c) {
        context =c;
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchcard,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.searchdeparting.setText(list.get(position).getName());


        holder.searchdeparting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(DemoClass.addCitya==list.get(position).getName()) {
                    Toast.makeText(context, "Choose another city",Toast.LENGTH_LONG).show();
                }else {
                    DemoClass.addCityd = list.get(position).getName();
                    Intent episodes = new Intent(context, addLines.class);
                    episodes.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(episodes);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView searchdeparting;
        public MyViewHolder(@Nullable View itemView){
            super(itemView);

            searchdeparting=itemView.findViewById(R.id.searchdeparting);
        }
    }
}
