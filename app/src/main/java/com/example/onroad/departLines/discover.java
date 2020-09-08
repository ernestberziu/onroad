package com.example.onroad.departLines;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onroad.Background;
import com.example.onroad.DemoClass;
import com.example.onroad.MainActivity;
import com.example.onroad.R;
import com.example.onroad.Routes.Routes;
import com.example.onroad.returnLines.Return;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.JsonArray;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
    List<lines> linesList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    ArrayList<String> lineslist1;
    String selectedDate;
    String totalprice;
    MainActivity main = new MainActivity();
    Button tolbarnext;
    String url="https://onroaduniversal.000webhostapp.com/onroad/login.php?date=";
    CardView cardView;













    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);


        childs=findViewById(R.id.childs);
        childs.setText(DemoClass.childs);
        adults=findViewById(R.id.adults);
        adults.setText(DemoClass.adults);
        linesList = new ArrayList<>();
        tolbarprice = findViewById(R.id.tolbarprice);
        tolbarprice.setText(DemoClass.toolbarprice.toString());
        tolbarnext=findViewById(R.id.tolbarnext);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbardiscover);
        Toolbar tolbar1 =(Toolbar) findViewById(R.id.toolbarnext);
        recyclerView=findViewById(R.id.recycler1);




        ref= FirebaseDatabase.getInstance().getReference().child("");
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);








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







        //Tolbar

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
                .textColor(Color.BLACK, Color.BLACK)
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




//
////////////////////////Perl fare///////////////////////////////////////////////
//                startActivity(getIntent());
//                overridePendingTransition( 0, 0);
//                finish();
//                overridePendingTransition( 0, 0);
///////////////////////////////////////////////////////////////////////////////

                getData(DemoClass.departingdate.toString().trim());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewadapter = new RecyclerViewCardViewAdapter(linesList,getApplicationContext());
                recyclerView.setAdapter(recyclerViewadapter);



            }


        });
        setTotalprice(DemoClass.toolbarprice.toString());
        tolbarprice.setText(getTotalprice().toString());
        setSelectedDate(DemoClass.departingdate.toString());

        getData(getSelectedDate().toString().trim());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewadapter = new RecyclerViewCardViewAdapter(linesList,getApplicationContext());
        recyclerView.setAdapter(recyclerViewadapter);








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

    private void getData(String date) {

        linesList=new ArrayList<lines>();



        String url1 = url + date+"&cityd="+DemoClass.Cityd+"&citya="+DemoClass.Citya;



        StringRequest stringRequest = new StringRequest(url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(discover.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");


            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                lines line = new lines();
                line.setCityd(jo.getString("cityd"));
                line.setCitya(jo.getString("citya"));
                line.setTimed(jo.getString("timed"));
                line.setTimea(jo.getString("timea"));
                line.setPrice(jo.getString("price"));


                linesList.add(line);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewadapter = new RecyclerViewCardViewAdapter(linesList,getApplicationContext());
                recyclerView.setAdapter(recyclerViewadapter);

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

