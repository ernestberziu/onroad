package com.example.onroad.addLines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.onroad.Background;
import com.example.onroad.DemoClass;
import com.example.onroad.R;
import com.example.onroad.addLines.addFrom.addFrom;
import com.example.onroad.viewLinesAgency.viewaAgencyLines;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class addLines extends AppCompatActivity {
    Button addCityd,addCitya,addDate ,add;
    ImageButton addDateButton;
    EditText addTotPassagers ,addPrice,addLinename,addDtime,addAtime;
    int random;

    FirebaseAuth rAuth;
    addLineList list;
    DatabaseReference ref,ref1;
    ArrayList<addDatesList> linesList;
    ArrayList<String> stringlist;
    RecyclerView.Adapter addDatesAdapter;
    RecyclerView addDateRecycler;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    DatePickerDialog.OnDateSetListener setListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lines);
        ref= FirebaseDatabase.getInstance().getReference().child("");
        ref1= FirebaseDatabase.getInstance().getReference().child("");
        addDateButton=findViewById(R.id.addDateButton);
        rAuth=FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.addLineToolbar);

        addCityd=findViewById(R.id.addDepartCity);
        addDateRecycler = findViewById(R.id.addDateRecycler);


        recyclerViewlayoutManager = new LinearLayoutManager(this);
        addDateRecycler.setLayoutManager(recyclerViewlayoutManager);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoClass.addCityd="";
                DemoClass.addCitya="";

                Intent discover = new Intent(addLines.this, viewaAgencyLines.class);
                startActivity(discover);
                finish();
            }
        });

        addCityd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(addLines.this, addFrom.class);
                startActivity(intent);
            }

        });
        addCityd.setText(DemoClass.addCityd);
        addCitya=findViewById(R.id.addArriveCity);
        addCitya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addCityd.getText().toString().isEmpty()){
                    Toast.makeText(addLines.this, "Please Choose departing city first", Toast.LENGTH_LONG).show();
                }else{
                Intent intent= new Intent(addLines.this, com.example.onroad.addLines.addTo.addTo.class);
                startActivity(intent);}
            }
        });

        addCitya.setText(DemoClass.addCitya);
        addDate=findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        addLines.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                if(day<=9){
                    String date = "0"+day + "-" + month + "-" + year;
                    addDate.setText(date);
                    DemoClass.selectedDate=addDate.getText().toString();
                }else {
                    String date = day + "-" + month + "-" + year;
                    addDate.setText(date);
                    DemoClass.selectedDate=addDate.getText().toString();
                }


            }
        };
        linesList=new ArrayList();
        stringlist=new ArrayList<>();

        addDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (addDatesList a : linesList) {
                DemoClass.a=a.getDate();
                }


                if (linesList.size()!= 0) {

                        if (DemoClass.a.equals(addDate.getText().toString())) {
                            Toast.makeText(addLines.this, "Select Another Date", Toast.LENGTH_SHORT).show();
                        } else if (addDate.getText().toString().isEmpty()) {
                            Toast.makeText(addLines.this, "Add a Date", Toast.LENGTH_SHORT).show();
                        } else {
                            addDatesList addDatesList = new addDatesList(addDate.getText().toString());
                            linesList.add(addDatesList);
                            addDatesAdapter = new addDatesAdapter(linesList, addLines.this);
                            addDateRecycler.setAdapter(addDatesAdapter);
                            addDate.setText("");

                        }

                } else if(linesList.size()==0){
                    if (addDate.getText().toString().isEmpty()) {
                        Toast.makeText(addLines.this, "Add a Date", Toast.LENGTH_SHORT).show();
                    } else {
                        addDatesList addDatesList = new addDatesList(addDate.getText().toString());
                        linesList.add(addDatesList);
                        addDatesAdapter = new addDatesAdapter(linesList, addLines.this);
                        addDateRecycler.setAdapter(addDatesAdapter);
                        addDate.setText("");

                    }


                }


            }
        });

        addDtime=findViewById(R.id.adddepartTime);
        addAtime=findViewById(R.id.addarriveTime);
        addTotPassagers=findViewById(R.id.addTotalPassagers);
        addPrice=findViewById(R.id.addPrice);
        addLinename=findViewById(R.id.lineName);
        add=findViewById(R.id.addbutton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((addCityd.getText().toString().isEmpty()) || (addCitya.getText().toString().isEmpty()) || (addTotPassagers.getText().toString().isEmpty()) || (addPrice.getText().toString().isEmpty()) || (addLinename.getText().toString().isEmpty()) || (addDtime.getText().toString().isEmpty()) || (addAtime.getText().toString().isEmpty()) || linesList.isEmpty()) {
                    Toast.makeText(addLines.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (rAuth.getCurrentUser() != null) {
                        for (addDatesList b : linesList) {
                            String type = "add";

                            String cityd = addCityd.getText().toString();
                            String citya = addCitya.getText().toString();
                            String timed = addDtime.getText().toString();
                            String timea = addAtime.getText().toString();
                            String price = addPrice.getText().toString();
                            String date = b.getDate().toString();
                            String minpas = "0";
                            String maxpass = addTotPassagers.getText().toString();
                            String agency = String.valueOf(rAuth.getCurrentUser().getUid());
                            String linename = addLinename.getText().toString();


//                    writeUser();

                            Background background = new Background(getApplicationContext());
                            background.execute(type, cityd, citya, timed, timea, price, date, minpas, maxpass, agency, linename);
                        }
                    } else {
                        Toast.makeText(addLines.this, "nu user", Toast.LENGTH_SHORT);
                    }

                }
            }

        });

        random=(int)(Math.random()*(10000000-1))+1;



    }

    public void writeUser(){


//        else{
//            for(addDatesList b: linesList){
//                ref= FirebaseDatabase.getInstance().getReference("agency/"+rAuth.getCurrentUser().getUid()+"/"+addCityd.getText().toString()+"-"+addCitya.getText().toString()+"/"+random);
//                ref1= FirebaseDatabase.getInstance().getReference("Lines/"+b.getDate().toString()+"/"+addCityd.getText().toString()+"-"+addCitya.getText().toString()+"/"+random);
//                list= new addLineList(addCityd.getText().toString(),addCitya.getText().toString(),String.valueOf(0),addTotPassagers.getText().toString(),addLinename.getText().toString(),addPrice.getText().toString(),addDtime.getText().toString(),addAtime.getText().toString(),b.getDate().toString());
//                ref.setValue(list);
//                ref1.setValue(list);
//
//            }
//
//        }
//



    }
    @Override
    public void onBackPressed() {
        DemoClass.addCityd="";
        DemoClass.addCitya="";

        Intent discover = new Intent(addLines.this, viewaAgencyLines.class);
        startActivity(discover);
        finish();

    }


}