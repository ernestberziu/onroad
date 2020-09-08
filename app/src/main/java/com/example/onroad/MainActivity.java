package com.example.onroad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.onroad.loginandregister.Register;
import com.example.onroad.toSearch.toSearch;
import com.example.onroad.viewLinesAgency.viewaAgencyLines;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.DateTime;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private Button departingDate;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener1;
    Button returnDate;
    ImageButton imageButton;
    ImageButton clearButton;
    ImageButton swap;
    Button main_from;
    Button main_to;
    Button adultsFrom;
    Button childsFrom;
    FirebaseAuth auth;
    int dday;
    int dmonth;
    int dyear;

    DateTime departDate;
    DatabaseReference ref;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ref= FirebaseDatabase.getInstance().getReference().child("");
        main_from= (Button) (findViewById(R.id.main_from));
        main_to= (Button) (findViewById(R.id.main_to));
        imageButton = (ImageButton) (findViewById(R.id.searchbutton));
        clearButton = (ImageButton)(findViewById(R.id.clear));
        main_from.setText(DemoClass.Cityd);
        main_to.setText(DemoClass.Citya);
        DemoClass.returningdate="";
        auth=FirebaseAuth.getInstance();


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnDate.setText("");
                DemoClass.returningdate="";
                DemoClass.rday=0;
                DemoClass.rmont=0;
                DemoClass.ryear=0;
            }
        });


        main_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fromSearch = new Intent(MainActivity.this, com.example.onroad.fromSearch.fromSearch.class);
                startActivity(fromSearch);

            }
        });
        main_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(main_from.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Choose departing city first", Toast.LENGTH_LONG).show();
                }else {
                    Intent fromSearch = new Intent(MainActivity.this, toSearch.class);
                    startActivity(fromSearch);
                }
            }
        });





        swap = (ImageButton)(findViewById(R.id.swap));
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toData=main_to.getText().toString();
                String fromData=main_from.getText().toString();
                main_from.setText(toData);
                main_to.setText(fromData);
                DemoClass.Cityd=toData;
                DemoClass.Citya=fromData;
            }
        });




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(main_from.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please select a depart city!", Toast.LENGTH_LONG).show();
                    }
                    else if(main_to.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please select a arriving city!", Toast.LENGTH_LONG).show();
                    }
                    else if(departingDate.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please select a depart date!", Toast.LENGTH_LONG).show();
                    }
                    else if(adultsFrom.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please set number of adults", Toast.LENGTH_LONG).show();
                    }
                    else {


                        DemoClass.toolbarprice="0";


                        Intent discover = new Intent(MainActivity.this, com.example.onroad.departLines.discover.class);
                        startActivity(discover);
                        finish();
                    }

            }
        });

        adultsFrom= findViewById(R.id.adults_from);
        adultsFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, adultsFrom);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.adult_menu, popup.getMenu());


                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        adultsFrom.setText(item.getTitle());
                        DemoClass.adults=item.getTitle().toString();
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });
        childsFrom= findViewById(R.id.childs);
        childsFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, childsFrom);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.childs_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        childsFrom.setText(item.getTitle());
                        DemoClass.childs=item.getTitle().toString();
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOverflowIcon(getDrawable(R.drawable.ic_baseline_menu_24));

        departingDate = findViewById(R.id.main_departing);
        returnDate = findViewById(R.id.return_main);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);




//depart and return button on click
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(departingDate.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please select a depart date!", Toast.LENGTH_LONG).show();
                }else {

                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener1, year, month, day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.getDatePicker().setMinDate(departDate.getMillis());
                    datePickerDialog.show();
                }
            }
        });
        departingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();




            }
        });
//set button text

        setListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date = day + "-" + month + "-" + year;;
                DemoClass.departingdate=date;
                departingDate.setText(DemoClass.departingdate);
                String[] parts = departingDate.getText().toString().split("-");
                DemoClass.dday=Integer.parseInt(parts[0]);
                DemoClass.dmont=Integer.parseInt(parts[1]);
                DemoClass.dyear=Integer.parseInt(parts[2]);


                departDate= new DateTime(year,month,day,0,0,0);


            }
        };




        setListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date = day + "-" + month + "-" + year;
                DemoClass.returningdate=date;
                returnDate.setText(DemoClass.returningdate);
                String[] parts = returnDate.getText().toString().split("-");
                DemoClass.rday=Integer.parseInt(parts[0]);
                DemoClass.rmont=Integer.parseInt(parts[1]);
                DemoClass.ryear=Integer.parseInt(parts[2]);


            }
        };


    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(auth.getCurrentUser()!=null){

            if(DemoClass.agencyState==1){
                getMenuInflater().inflate(R.menu.agency_menu, menu);

            }else {
                getMenuInflater().inflate(R.menu.logout_menu, menu);
            }
        }
        else{
            getMenuInflater().inflate(R.menu.main_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuid = item.getItemId();

        if (menuid == R.id.account) {
                Intent discover = new Intent(MainActivity.this, Register.class);
                startActivity(discover);
                finish();
            }
        if(menuid==R.id.logout){
                FirebaseAuth.getInstance().signOut();
            Intent discover = new Intent(MainActivity.this, MainActivity.class);
            finish();
            startActivity(discover);



        }if(menuid==R.id.viewLines){
            Intent discover = new Intent(MainActivity.this, viewaAgencyLines.class);
            startActivity(discover);

        }
            return super.onOptionsItemSelected(item);

        }



}