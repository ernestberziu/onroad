package com.example.onroad.Routes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.onroad.R;

import java.util.List;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.ViewHolder> {

    Context context;
    com.example.onroad.departLines.discover discover;


    List<RoutesList> lineslist;


    public RoutesAdapter(List<RoutesList> getDataAdapter, Context context) {

        this.lineslist = getDataAdapter;


        this.context = context;
    }

    @Override
    public RoutesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routeslist, parent, false);

        RoutesAdapter.ViewHolder viewHolder = new RoutesAdapter.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RoutesAdapter.ViewHolder holder, int position) {

        RoutesList getDataAdapter1 = lineslist.get(position);

        holder.cityd.setText(getDataAdapter1.getCityd());
        holder.citya.setText(getDataAdapter1.getCitya());
        holder.timed.setText(getDataAdapter1.getTimed());
        holder.timea.setText(getDataAdapter1.getTimea());
        holder.price.setText(getDataAdapter1.getPrice());
        holder.information.setText(getDataAdapter1.getInformation());
        holder.date.setText(getDataAdapter1.getDate());


    }

    @Override
    public int getItemCount() {

        return lineslist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cityd, citya, timed, timea, price,information,date;
        public Button button;


        public ViewHolder(View itemView) {

            super(itemView);

            cityd = (TextView) itemView.findViewById(R.id.cityd);
            citya = (TextView) itemView.findViewById(R.id.citya);
            timed = (TextView) itemView.findViewById(R.id.timed);
            timea = (TextView) itemView.findViewById(R.id.timea);
            price = (TextView) itemView.findViewById(R.id.price);
            information=(TextView)itemView.findViewById(R.id.information);
            date=(TextView)itemView.findViewById(R.id.date);


        }
    }
}

