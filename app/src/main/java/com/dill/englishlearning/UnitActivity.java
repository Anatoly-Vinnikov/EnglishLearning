package com.dill.englishlearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UnitActivity extends AppCompatActivity {

    public static final String unitID = "0";
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    RecyclerView wordsList;
    WordsAdapter adapter;
    List wordsEN, wordsRU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Unit " + getIntent().getStringExtra(unitID));

        wordsList = findViewById(R.id.wordsList);
        wordsList.setLayoutManager(new LinearLayoutManager(this));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};

                switch (getIntent().getStringExtra(unitID)) {
                    case "4":
                        wordsEN = snapshot.child("unit_4_en").getValue(t);
                        wordsRU = snapshot.child("unit_4_ru").getValue(t);
                        break;
                    case "5":
                        wordsEN = snapshot.child("unit_5_en").getValue(t);
                        wordsRU = snapshot.child("unit_5_ru").getValue(t);
                        break;
                    case "6":
                        wordsEN = snapshot.child("unit_6_en").getValue(t);
                        wordsRU = snapshot.child("unit_6_ru").getValue(t);
                        break;
                    case "7":
                        wordsEN = snapshot.child("unit_7_en").getValue(t);
                        wordsRU = snapshot.child("unit_7_ru").getValue(t);
                        break;
                    case "8":
                        wordsEN = snapshot.child("unit_8_en").getValue(t);
                        wordsRU = snapshot.child("unit_8_ru").getValue(t);
                        break;
                    case "9":
                        wordsEN = snapshot.child("unit_9_en").getValue(t);
                        wordsRU = snapshot.child("unit_9_ru").getValue(t);
                        break;
                    case "10":
                        wordsEN = snapshot.child("unit_10_en").getValue(t);
                        wordsRU = snapshot.child("unit_10_ru").getValue(t);
                        break;
                }

                ArrayList<String> words = new ArrayList<>();
                for (int i = 0; i < wordsEN.size(); i++) {
                    words.add(wordsEN.get(i) + " - " + wordsRU.get(i));
                }

                adapter = new WordsAdapter(words);
                wordsList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void startTest(View view) {
        Intent intent = new Intent(view.getContext(), TestActivity.class);
        intent.putExtra(TestActivity.unitID, String.valueOf(getIntent().getStringExtra(unitID)));
        view.getContext().startActivity(intent);
    }
}
