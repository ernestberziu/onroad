package com.example.onroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class launcher extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        auth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference().child("");
        parentName();

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {

                doWork();
                startApp();
                finish();
            }
        }).start();
    }


    public void parentName(){
        if(auth.getCurrentUser()!=null) {

            ref = FirebaseDatabase.getInstance().getReference().child("agency");

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        boolean parent = childSnapshot.getKey().contains(auth.getCurrentUser().getUid().toString());
                        if (parent == true) {
                            DemoClass.agencyState = 1;
                        } else {
                            DemoClass.agencyState = 0;
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void doWork() {

            for (int progress = 0; progress <=100; progress += 30) {
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }
    private void startApp() {
        Intent intent = new Intent(launcher.this, MainActivity.class);
        startActivity(intent);
    }



}


