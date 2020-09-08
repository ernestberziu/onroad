package com.example.onroad.departLines;

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

public class RecyclerViewCardViewAdapter extends  RecyclerView.Adapter<RecyclerViewCardViewAdapter.ViewHolder>{
    Context context;
    List<lines> lineslist;


    public RecyclerViewCardViewAdapter(List<lines> getDataAdapter, Context context){

        this.lineslist = getDataAdapter;


        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineslist, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        lines getDataAdapter1 =  lineslist.get(position);

        holder.cityd.setText(getDataAdapter1.getCityd());
        holder.citya.setText(getDataAdapter1.getCitya());
        holder.timed.setText(getDataAdapter1.getTimed());
        holder.timea.setText(getDataAdapter1.getTimea());
        holder.price.setText(getDataAdapter1.getPrice());

        holder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                holder.button.setBackgroundColor(Color.parseColor("#f4021a"));
                DemoClass.departstate=1;
                DemoClass.departdCity=getDataAdapter1.getCityd().toString();
                DemoClass.departaCity=getDataAdapter1.getCitya().toString();
                DemoClass.departdTime=getDataAdapter1.getTimed().toString();
                DemoClass.departaTime=getDataAdapter1.getTimea().toString();
                DemoClass.price=getDataAdapter1.getPrice().toString();
                DemoClass.dinformation="Depart Route Inforamtion";
                DemoClass.toolbarprice=String.valueOf(Integer.parseInt(DemoClass.adults)*(Integer.parseInt(DemoClass.price))+(Integer.parseInt(DemoClass.childs))*((Integer.parseInt(DemoClass.price)/2)));
                Intent intent=new Intent(view.getContext(), com.example.onroad.departLines.discover.class);
                view.getContext().startActivity(intent);
                ((discover) view.getContext()).overridePendingTransition(0,0);
                ((discover) view.getContext()).finish();
                ((discover) view.getContext()).overridePendingTransition(0,0);


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
            button = (Button) itemView.findViewById(R.id.bookbutton);




        }

    }

}
