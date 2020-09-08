package com.example.onroad.addLines.addFrom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.onroad.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class addFrom extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<addFromList> list;
    RecyclerView recyclerView;
    EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_from);
        ref= FirebaseDatabase.getInstance().getReference().child("Cities");
        recyclerView=(RecyclerView) findViewById(R.id.recycleviewaddsearch);
        searchView=(EditText) findViewById(R.id.addsearch);
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(ref!=null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        list=new ArrayList<>();
                        for(DataSnapshot ds: snapshot.getChildren()){
                            list.add(ds.getValue(addFromList.class));
                        }
                        addFromAdapter searchAdapter=new addFromAdapter(list,addFrom.this);
                        recyclerView.setAdapter(searchAdapter);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        if(searchView!=null){
            searchView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    search(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }
    private void search(String str){
        ArrayList<addFromList> mylist =new ArrayList<>();
        for (addFromList object:list){
            if(object.getName().toLowerCase().contains(str.toLowerCase())){
                mylist.add(object);
            }
        }
        addFromAdapter searchAdapter =new addFromAdapter(mylist,addFrom.this);
        recyclerView.setAdapter(searchAdapter);
    }
}