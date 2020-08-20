package com.example.onroad.returnLines;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onroad.DemoClass;
import com.example.onroad.MainActivity;
import com.example.onroad.R;
import com.example.onroad.Routes;
import com.example.onroad.RoutesList;
import com.example.onroad.departLines.discover;
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



public class Return extends AppCompatActivity {

    int month;
    int year;
    int day;
    DatabaseReference ref;
    TextView adults,childs,tolbarReturnprice;
    String selected;
    List<returnLines> linesList;

    RecyclerView recyclerView2;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    ArrayList<String> lineslist;
    String selectedDate;
    String totalprice;
    MainActivity main = new MainActivity();

    Button tolbarnext;






    //a list to store all the products


    //the recyclerview





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);



        linesList = new ArrayList<>();

        ref= FirebaseDatabase.getInstance().getReference().child("");
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler2);



        tolbarReturnprice = findViewById(R.id.tolbarReturnprice);
        tolbarReturnprice.setText(DemoClass.toolbarReturnprice.toString());
//        tolbarReturnprice.setText(DemoClass.returnPrice.toString());
        tolbarnext=findViewById(R.id.tolbarReturnnext);
        tolbarnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DemoClass.returnstate==1) {
                    Intent discover = new Intent(Return.this, Routes.class);

                    startActivity(discover);
                    finish();

                }else{
                    Toast.makeText(Return.this, "Please select a route", Toast.LENGTH_SHORT).show();
                }
            }
        });





        childs=findViewById(R.id.childs);
        childs.setText(DemoClass.childs);
        adults=findViewById(R.id.adults);
        adults.setText(DemoClass.adults);
        recyclerView2.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(recyclerViewlayoutManager);


        //search for date typed in MainActivity


        lineslist = new ArrayList<>();

        //Tolbar
        Toolbar toolbarreturn = (Toolbar) findViewById(R.id.toolbarreturn);
        Toolbar tolbareturn1 =(Toolbar) findViewById(R.id.toolbarnext);
        setSupportActionBar(tolbareturn1);
        setSupportActionBar(toolbarreturn);
        toolbarreturn.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbarreturn.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoClass.departstate=0;
                Intent discover = new Intent(Return.this, com.example.onroad.departLines.discover.class);
                startActivity(discover);
                finish();
            }
        });
        //get departing date from main
        setDay(DemoClass.rday);
        setMonth(DemoClass.rmont-1);
        setYear(DemoClass.ryear);
        setSelected(DemoClass.rday+"-"+DemoClass.rmont+"-"+DemoClass.ryear);

        // create horizontal date
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, 0);
        Calendar defaultSelectedDate = Calendar.getInstance();
        defaultSelectedDate.set(getYear(), getMonth(), getDay());
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 100);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView1)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .mode(HorizontalCalendar.Mode.DAYS)
                .configure()
                .formatTopText("yyyy")
                .formatMiddleText("dd")
                .formatBottomText("MMM")
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.BLACK, Color.WHITE)
                .end()
                .defaultSelectedDate(defaultSelectedDate)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {


            //get infromation from selected date
            @Override
            public void onDateSelected(Calendar date, int position) {
                String selectedDateStr = DateFormat.format("dd-M-yyyy", date).toString();
                DemoClass.returningdate=selectedDateStr.toString();

                String[] parts = selectedDateStr.split("-");
                DemoClass.rday=Integer.parseInt(parts[0]);
                DemoClass.rmont=Integer.parseInt(parts[1]);
                DemoClass.ryear=Integer.parseInt(parts[2]);


                //search for date on horizontal calendar
                getLines(DemoClass.returningdate.toString());


            }


        });
        setTotalprice(DemoClass.returnPrice.toString());
//        tolbarReturnprice.setText(getTotalprice().toString());
        setSelectedDate(DemoClass.returningdate.toString());
        getLines(getSelectedDate().toString());






    }

    //get lines list with our requirements
    public void getLines(String date){
        linesList = new ArrayList<returnLines>();
        ref = FirebaseDatabase.getInstance().getReference().child("Lines").child((DemoClass.Citya).toString()).child((DemoClass.Cityd).toString()).child((date).toString());
//        ref1= ref.child(DemoClass.Cityd);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    returnLines s = dataSnapshot1.getValue(returnLines.class);
                    linesList.add(s);
                }

                recyclerViewadapter =new returnAdapter(linesList, Return.this);
                recyclerView2.setAdapter(recyclerViewadapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Return.this, "Error",Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void onBackPressed() {
        DemoClass.departstate=0;
        Intent discover = new Intent(Return.this, com.example.onroad.departLines.discover.class);
        startActivity(discover);
        finish();

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

