package com.example.onroad.loginandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onroad.DemoClass;
import com.example.onroad.MainActivity;
import com.example.onroad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Register extends AppCompatActivity {
    EditText nameRegister,surnameRegister, emailRegister, passwordRegister;
    Button registerBirthDay, register;
    DatePickerDialog.OnDateSetListener setListener2;
    FirebaseAuth rAuth;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ref= FirebaseDatabase.getInstance().getReference().child("");


        nameRegister=findViewById(R.id.nameRegister);
        surnameRegister=findViewById(R.id.surnameRegister);
        emailRegister=findViewById(R.id.emailRegister);
        passwordRegister=findViewById(R.id.passwordRegister);
        registerBirthDay=findViewById(R.id.registerBirthDay);
        register=findViewById(R.id.register);
        rAuth=FirebaseAuth.getInstance();

        if(rAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), login.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaR =nameRegister.getText().toString().trim();
                String surnameR=surnameRegister.getText().toString().trim();

                String emailR=emailRegister.getText().toString().trim();
                String passwordR=passwordRegister.getText().toString().trim();
                String birthdayR=registerBirthDay.getText().toString().trim();
                if(TextUtils.isEmpty(namaR)){
                    nameRegister.setError("Please write your name!");
                    return;
                }
                if(TextUtils.isEmpty(surnameR)){
                    nameRegister.setError("Please write your surname!");
                    return;
                }
                if(TextUtils.isEmpty(emailR)){
                    emailRegister.setError("Please write your email!");
                    return;
                }
                if(TextUtils.isEmpty(passwordR)){
                    passwordRegister.setError("Please write your password!");
                    return;
                }

                if(TextUtils.isEmpty(birthdayR)){
                    registerBirthDay.setError("Please set your birthday!");
                    return;
                }
                rAuth.createUserWithEmailAndPassword(emailR,passwordR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            writeUser();

                            Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),login.class));

                        }else{
                            Toast.makeText(Register.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });


//set Birthday
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        registerBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Register.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener2, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                datePickerDialog.show();
            }
        });

        setListener2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date = day + "-" + month + "-" + year;;
                DemoClass.birthdate=date;
                registerBirthDay.setText(DemoClass.birthdate);
//                String[] parts = registerBirthDay.getText().toString().split("-");
//                DemoClass.dday=Integer.parseInt(parts[0]);
//                DemoClass.dmont=Integer.parseInt(parts[1]);
//                DemoClass.dyear=Integer.parseInt(parts[2]);





            }
        };




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRegister);
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
                Intent discover = new Intent(Register.this, MainActivity.class);
                startActivity(discover);
                finish();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registermennu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.login) {
            Intent discover = new Intent(Register.this, login.class);
            startActivity(discover);
            finish();


        }
        return super.onOptionsItemSelected(item);

    }
    public void writeUser(){
        ref= FirebaseDatabase.getInstance().getReference("clients/"+rAuth.getCurrentUser().getUid());
        accountDetailsList list= new accountDetailsList(nameRegister.getText().toString(),surnameRegister.getText().toString(),emailRegister.getText().toString(),passwordRegister.getText().toString(),registerBirthDay.getText().toString());
        ref.setValue(list);
    }
}