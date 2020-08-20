package com.example.onroad.returnLines;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onroad.DemoClass;
import com.example.onroad.R;

import java.util.List;

public class returnAdapter extends RecyclerView.Adapter<returnAdapter.ViewHolder> {

    Context context;
    com.example.onroad.departLines.discover discover;




    List<returnLines> lineslist;


    public returnAdapter(List<returnLines> getDataAdapter, Context context){

        this.lineslist = getDataAdapter;


        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.returnlist, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        returnLines getDataAdapter1 =  lineslist.get(position);

        holder.cityd.setText(getDataAdapter1.getCityd());
        holder.citya.setText(getDataAdapter1.getCitya());
        holder.timed.setText(getDataAdapter1.getTimed());
        holder.timea.setText(getDataAdapter1.getTimea());
        holder.price.setText(getDataAdapter1.getPrice());

        holder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                holder.button.setBackgroundColor(Color.parseColor("#f4021a"));
                DemoClass.returnstate=1;
                DemoClass.returndCity=getDataAdapter1.getCityd().toString();
                DemoClass.returnaCity=getDataAdapter1.getCitya().toString();
                DemoClass.returndTime=getDataAdapter1.getTimed().toString();
                DemoClass.returnaTime=getDataAdapter1.getTimea().toString();
                DemoClass.returnPrice=getDataAdapter1.getPrice().toString();
                DemoClass.ringormation="Return Route Inforamtion";
                DemoClass.toolbarReturnprice=String.valueOf(Integer.parseInt(DemoClass.adults)*(Integer.parseInt(DemoClass.returnPrice))+(Integer.parseInt(DemoClass.childs))*((Integer.parseInt(DemoClass.returnPrice)/2)));
                Intent episodes = new Intent(context, Return.class);

                context.startActivity(episodes);
                episodes.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            }
        });

    }

    @Override
    public int getItemCount() {

        return lineslist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView cityd,citya,timed,timea,price;
        public Button button;


        public ViewHolder(View itemView) {

            super(itemView);

            cityd = (TextView) itemView.findViewById(R.id.cityd) ;
            citya = (TextView) itemView.findViewById(R.id.citya) ;
            timed = (TextView) itemView.findViewById(R.id.timed) ;
            timea = (TextView) itemView.findViewById(R.id.timea) ;
            price = (TextView) itemView.findViewById(R.id.price) ;
            button = (Button) itemView.findViewById(R.id.bookbutton1);




        }
    }






}
