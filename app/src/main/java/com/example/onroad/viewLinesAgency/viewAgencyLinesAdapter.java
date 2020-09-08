package com.example.onroad.viewLinesAgency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.onroad.DemoClass;
import com.example.onroad.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class viewAgencyLinesAdapter extends RecyclerView.Adapter<viewAgencyLinesAdapter.ViewHolder> {

    Context context;
    FirebaseDatabase ref;
    com.example.onroad.departLines.discover discover;


    List<viewAgencyLinesList> lineslist;



    public viewAgencyLinesAdapter(List<viewAgencyLinesList> getDataAdapter,Context context) {

        this.lineslist = getDataAdapter;



        this.context = context;
    }


    @Override
    public viewAgencyLinesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewagencylinescard, parent, false);

        viewAgencyLinesAdapter.ViewHolder viewHolder = new viewAgencyLinesAdapter.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(viewAgencyLinesAdapter.ViewHolder holder, int position) {

        viewAgencyLinesList getDataAdapter1 = lineslist.get(position);
            holder.cityd.setText(getDataAdapter1.getCityd());
            holder.citya.setText(getDataAdapter1.getCitya());
            holder.timed.setText(getDataAdapter1.getTimed());
            holder.timea.setText(getDataAdapter1.getTimea());
            holder.price.setText(getDataAdapter1.getPrice());









    }

    @Override
    public int getItemCount() {

        return lineslist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cityd, citya, timed, timea, price,date;
        public Button button;



        public ViewHolder(View itemView) {

            super(itemView);

            cityd = (TextView) itemView.findViewById(R.id.cityd);
            citya = (TextView) itemView.findViewById(R.id.citya);
            timed = (TextView) itemView.findViewById(R.id.timed);
            timea = (TextView) itemView.findViewById(R.id.timea);
            price = (TextView) itemView.findViewById(R.id.price);





        }
    }
}

