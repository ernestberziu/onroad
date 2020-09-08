package com.example.onroad.loginandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onroad.DemoClass;
import com.example.onroad.MainActivity;
import com.example.onroad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText emailLogin,passwordLogin;
    Button login,loginRegister;
    FirebaseAuth lAuth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ref= FirebaseDatabase.getInstance().getReference().child("");



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);

        emailLogin=findViewById(R.id.emailLogin);
        passwordLogin=findViewById(R.id.passwordLogin);
        login=findViewById(R.id.loginbutton);
        loginRegister=findViewById(R.id.loginRegister);
        lAuth= FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailL=emailLogin.getText().toString().trim();
                String passwordL=passwordLogin.getText().toString().trim();
                if(TextUtils.isEmpty(emailL)){
                    emailLogin.setError("Please write your email!");
                    return;
                }
                if(TextUtils.isEmpty(passwordL)){
                    passwordLogin.setError("Please write your password!");
                    return;
                }
                lAuth.signInWithEmailAndPassword(emailL,passwordL).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){



                            parentName();

                            Toast.makeText(login.this,"Logged In",Toast.LENGTH_SHORT).show();



                        }else{
                            Toast.makeText(login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });


        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoClass.Cityd="";
                DemoClass.Citya="";
                DemoClass.departingdate="";
                DemoClass.returningdate="";
                Intent discover = new Intent(login.this, MainActivity.class);
                startActivity(discover);
                finish();
            }
        });
    }
    public void parentName(){
        if(lAuth.getCurrentUser()!=null) {

            ref = FirebaseDatabase.getInstance().getReference().child("agency");

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        boolean parent = childSnapshot.getKey().contains(lAuth.getCurrentUser().getUid().toString());
                        if (parent == true) {
                            DemoClass.agencyState = 1;
                        } else {
                            DemoClass.agencyState = 0;
                        }
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


}