package com.example.onroad.viewLinesAgency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onroad.DemoClass;
import com.example.onroad.MainActivity;
import com.example.onroad.R;
import com.example.onroad.departLines.RecyclerViewCardViewAdapter;
import com.example.onroad.departLines.discover;
import com.example.onroad.departLines.lines;
import com.example.onroad.loginandregister.Register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class viewaAgencyLines extends AppCompatActivity {

    DatabaseReference ref,ref1;
    TextView adults,childs,tolbarprice;

    List<viewAgencyLinesList> linesList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    ArrayList<String> lineslist;

    String totalprice;

    FirebaseAuth auth;
    String url="https://onroaduniversal.000webhostapp.com/onroad/getAgencyLines.php?date=";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewa_agency_lines);
        auth=FirebaseAuth.getInstance();





        linesList = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.viewAgencyLines);

        recyclerView=findViewById(R.id.recyclerViewLines);



        linesList = new ArrayList<>();


        ref= FirebaseDatabase.getInstance().getReference().child("");
        ref1=FirebaseDatabase.getInstance().getReference().child("");
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        lineslist = new ArrayList<>();

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
                Intent discover = new Intent(viewaAgencyLines.this, MainActivity.class);
                startActivity(discover);
                finish();
            }
        });


        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, 0);
        Calendar defaultSelectedDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 100);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarViewAgency)
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
                .defaultSelectedDate(startDate)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {


            //get infromation from selected date
            @Override
            public void onDateSelected(Calendar date, int position) {
                String selectedDateStr = DateFormat.format("dd-M-yyyy", date).toString();
//                DemoClass.departingdate=selectedDateStr.toString();

//                String[] parts = selectedDateStr.split("-");
//                DemoClass.dday=Integer.parseInt(parts[0]);
//                DemoClass.dmont=Integer.parseInt(parts[1]);
//                DemoClass.dyear=Integer.parseInt(parts[2]);
                getData(selectedDateStr.trim());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewadapter =new viewAgencyLinesAdapter(linesList, viewaAgencyLines.this);
                recyclerView.setAdapter(recyclerViewadapter);




//
////////////////////////Perl fare///////////////////////////////////////////////
//                startActivity(getIntent());
//                overridePendingTransition( 0, 0);
//                finish();
//                overridePendingTransition( 0, 0);
///////////////////////////////////////////////////////////////////////////////

//                getData(DemoClass.departingdate.toString().trim());
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerViewadapter = new RecyclerViewCardViewAdapter(linesList,getApplicationContext());
//                recyclerView.setAdapter(recyclerViewadapter);




            }


        });

        Calendar current= Calendar.getInstance();
        String currentDate=DateFormat.format("dd-M-yyyy",current).toString();
        getData(currentDate.trim());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewadapter =new viewAgencyLinesAdapter(linesList, viewaAgencyLines.this);
        recyclerView.setAdapter(recyclerViewadapter);




//
////////////////////////Perl fare///////////////////////////////////////////////
//                startActivity(getIntent());
//                overridePendingTransition( 0, 0);
//                finish();
//                overridePendingTransition( 0, 0);
///////////////////////////////////////////////////////////////////////////////







    }
//    public void getLines(String line){
//        linesList = new ArrayList<viewAgencyLinesList>();
//
//        ref = FirebaseDatabase.getInstance().getReference().child("agency").child(auth.getCurrentUser().getUid()).child(line);
//
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                    viewAgencyLinesList s = dataSnapshot1.getValue(viewAgencyLinesList.class);
//                    linesList.add(s);
//                }
//
//                recyclerViewadapter =new viewAgencyLinesAdapter(linesList, viewaAgencyLines.this);
//                recyclerView.setAdapter(recyclerViewadapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(viewaAgencyLines.this, "Error",Toast.LENGTH_SHORT).show();
//            }
//
//
//        });
//
//
//    }







    @Override
    public void onBackPressed() {
        DemoClass.Cityd="";
        DemoClass.Citya="";
        DemoClass.departingdate="";
        DemoClass.returningdate="";
        Intent discover = new Intent(viewaAgencyLines.this, MainActivity.class);
        startActivity(discover);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.addline, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuid = item.getItemId();

        if (menuid == R.id.addLine) {
            Intent discover = new Intent(viewaAgencyLines.this, com.example.onroad.addLines.addLines.class);
            startActivity(discover);
            finish();
        }
        return super.onOptionsItemSelected(item);

    }


    private void getData(String date) {

        linesList=new ArrayList<viewAgencyLinesList>();



        String url1 = url + date+"&agency="+auth.getCurrentUser().getUid().toString();



        StringRequest stringRequest = new StringRequest(url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(viewaAgencyLines.this, error.getMessage(), Toast.LENGTH_LONG).show();
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
                viewAgencyLinesList line = new viewAgencyLinesList();
                line.setId(jo.getInt("id"));
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
        recyclerViewadapter =new viewAgencyLinesAdapter(linesList, viewaAgencyLines.this);
        recyclerView.setAdapter(recyclerViewadapter);

    }







    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
}

