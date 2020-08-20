package com.example.onroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onroad.DemoClass;
import com.example.onroad.MainActivity;
import com.example.onroad.R;
import com.example.onroad.returnLines.Return;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;



public class Routes extends AppCompatActivity {

    int month;
    int year;
    int day;
    DatabaseReference ref;
    TextView adults,childs,tolbarfinalprice;
    String selected;
    List<RoutesList> linesList;
    RecyclerView recyclerView2;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    ArrayList<String> lineslist;
    String selectedDate;
    String totalprice;
    MainActivity main = new MainActivity();







    //a list to store all the products


    //the recyclerview





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);




        ref= FirebaseDatabase.getInstance().getReference().child("");
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler3);



          tolbarfinalprice = findViewById(R.id.toolbarfinalprice);
          tolbarfinalprice.setText(String.valueOf(Integer.parseInt(DemoClass.toolbarprice)+Integer.parseInt(DemoClass.toolbarReturnprice)));
//





        childs=findViewById(R.id.childs);
        childs.setText(DemoClass.childs);
        adults=findViewById(R.id.adults);
        adults.setText(DemoClass.adults);
        recyclerView2.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(recyclerViewlayoutManager);


        //search for date typed in MainActivity




        //Tolbar
        Toolbar toolbarreturn = (Toolbar) findViewById(R.id.toolbarRoutes);
        Toolbar tolbareturn1 =(Toolbar) findViewById(R.id.toolbarnext);
        setSupportActionBar(tolbareturn1);
        setSupportActionBar(toolbarreturn);
        toolbarreturn.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbarreturn.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DemoClass.returningdate.isEmpty()){
                    DemoClass.toolbarReturnprice="0";
                    DemoClass.toolbarprice="0";
                    Intent discover = new Intent(Routes.this, com.example.onroad.departLines.discover.class);
                    DemoClass.departstate=0;
                    startActivity(discover);
                    finish();
                }else {
                    DemoClass.toolbarReturnprice="0";

                    Intent discover = new Intent(Routes.this, com.example.onroad.returnLines.Return.class);
                    startActivity(discover);
                    DemoClass.returnstate=0;
                    finish();
                }
            }
        });
        linesList=new ArrayList<RoutesList>();
        if(DemoClass.returningdate.isEmpty()) {
            RoutesList routesList = new RoutesList(DemoClass.departdCity.toString(), DemoClass.departaCity.toString(), DemoClass.departdTime.toString(), DemoClass.departaTime.toString(), DemoClass.toolbarprice.toString(), DemoClass.dinformation.toString(),DemoClass.departingdate.toString());
            linesList.add(routesList);
            recyclerViewadapter = new RoutesAdapter(linesList, Routes.this);
            recyclerView2.setAdapter(recyclerViewadapter);
        }else{
            RoutesList routesList = new RoutesList(DemoClass.departdCity.toString(), DemoClass.departaCity.toString(), DemoClass.departdTime.toString(), DemoClass.departaTime.toString(), DemoClass.toolbarprice.toString(), DemoClass.dinformation.toString(),DemoClass.departingdate.toString());
            RoutesList routesList1 = new RoutesList(DemoClass.returndCity.toString(), DemoClass.returnaCity.toString(), DemoClass.returndTime.toString(), DemoClass.returnaTime.toString(), DemoClass.toolbarReturnprice.toString(), DemoClass.ringormation.toString(),DemoClass.returningdate.toString());
            linesList.add(routesList);
            linesList.add(routesList1);
            recyclerViewadapter = new RoutesAdapter(linesList, Routes.this);
            recyclerView2.setAdapter(recyclerViewadapter);
        }

    }

    public void onBackPressed() {
        if (DemoClass.returningdate.isEmpty()){
            DemoClass.toolbarReturnprice="0";
            DemoClass.toolbarprice="0";
            Intent discover = new Intent(Routes.this, com.example.onroad.departLines.discover.class);
            DemoClass.departstate=0;
            startActivity(discover);
            finish();
        }else {
            DemoClass.toolbarReturnprice="0";

            Intent discover = new Intent(Routes.this, com.example.onroad.returnLines.Return.class);
            startActivity(discover);
            DemoClass.returnstate=0;
            finish();
        }

    }








    // getter and setter
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
}

