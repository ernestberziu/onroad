package com.example.onroad.departLines;

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



public class discover extends AppCompatActivity {

    int month;
    int year;
    int day;
    DatabaseReference ref;
    TextView adults,childs,tolbarprice;
    String selected;
    List<Lines> linesList;
    RecyclerView recyclerView;
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
        setContentView(R.layout.activity_discover);



        linesList = new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference().child("");
        recyclerView = (RecyclerView) findViewById(R.id.recycler1);



        tolbarprice = findViewById(R.id.tolbarprice);
        tolbarprice.setText(DemoClass.toolbarprice.toString());
        tolbarnext=findViewById(R.id.tolbarnext);
        tolbarnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DemoClass.departstate==1) {
                    if (DemoClass.returningdate == "") {
                        Intent discover = new Intent(discover.this, Routes.class);
                        startActivity(discover);
                        finish();
                    } else {
                        Intent discover = new Intent(discover.this, Return.class);
                        startActivity(discover);
                        finish();
                    }
                }else{
                    Toast.makeText(discover.this,"Please select a route",Toast.LENGTH_SHORT).show();
                }
            }
        });




        childs=findViewById(R.id.childs);
        childs.setText(DemoClass.childs);
        adults=findViewById(R.id.adults);
        adults.setText(DemoClass.adults);
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        //search for date typed in MainActivity


        lineslist = new ArrayList<>();

        //Tolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbardiscover);
        Toolbar tolbar1 =(Toolbar) findViewById(R.id.toolbarnext);
        setSupportActionBar(tolbar1);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoClass.Cityd="";
                DemoClass.Citya="";
                DemoClass.departingdate="";
                DemoClass.returningdate="";
                Intent discover = new Intent(discover.this, MainActivity.class);
                startActivity(discover);
                finish();
            }
        });
        //get departing date from main
        setDay(DemoClass.dday);
        setMonth(DemoClass.dmont-1);
        setYear(DemoClass.dyear);
        setSelected(DemoClass.dday+"-"+DemoClass.dmont+"-"+DemoClass.dyear);

        // create horizontal date
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, 0);
        Calendar defaultSelectedDate = Calendar.getInstance();
        defaultSelectedDate.set(getYear(), getMonth(), getDay());
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 100);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
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
                DemoClass.departingdate=selectedDateStr.toString();

                String[] parts = selectedDateStr.split("-");
                DemoClass.dday=Integer.parseInt(parts[0]);
                DemoClass.dmont=Integer.parseInt(parts[1]);
                DemoClass.dyear=Integer.parseInt(parts[2]);


                //search for date on horizontal calendar
                getLines(DemoClass.departingdate.toString());


            }


        });
        setTotalprice(DemoClass.toolbarprice.toString());
        tolbarprice.setText(getTotalprice().toString());
        setSelectedDate(DemoClass.departingdate.toString());
        getLines(getSelectedDate().toString());






}

//get lines list with our requirements
public void getLines(String date){
    linesList = new ArrayList<Lines>();
    ref = FirebaseDatabase.getInstance().getReference().child("Lines").child((DemoClass.Cityd).toString()).child((DemoClass.Citya).toString()).child((date).toString());
//        ref1= ref.child(DemoClass.Cityd);

    ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                Lines s = dataSnapshot1.getValue(Lines.class);
                linesList.add(s);
            }

            recyclerViewadapter =new RecyclerViewCardViewAdapter(linesList, discover.this);
            recyclerView.setAdapter(recyclerViewadapter);


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(discover.this, "Error",Toast.LENGTH_SHORT).show();
        }

    });
}
    @Override
    public void onBackPressed() {
        DemoClass.Cityd="";
        DemoClass.Citya="";
        DemoClass.departingdate="";
        DemoClass.returningdate="";
        Intent discover = new Intent(discover.this, MainActivity.class);
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

