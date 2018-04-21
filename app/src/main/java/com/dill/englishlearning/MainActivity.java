package com.dill.englishlearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference myRef;
    RecyclerView unitsList;
    UnitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.keepSynced(true);

        unitsList = findViewById(R.id.unitsList);
        unitsList.setLayoutManager(new LinearLayoutManager(this));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                List UNS = snapshot.child("units_numbers").getValue(t);
                List UNT = snapshot.child("units_themes").getValue(t);
                adapter = new UnitAdapter(UNS, UNT);
                unitsList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void updateDB() {
        String[] unitsNumbers = getResources().getStringArray(R.array.units_numbers);
        List<String> listUnitsNumbers = new ArrayList<>(Arrays.asList(unitsNumbers));
        myRef.child("units_numbers").setValue(listUnitsNumbers);

        String[] unitsThemes = getResources().getStringArray(R.array.units_themes);
        List<String> listUnitsThemes = new ArrayList<>(Arrays.asList(unitsThemes));
        myRef.child("units_themes").setValue(listUnitsThemes);

        String[] unit_4_en = getResources().getStringArray(R.array.unit_4_en);
        List<String> listUnit4EN = new ArrayList<>(Arrays.asList(unit_4_en));
        myRef.child("unit_4_en").setValue(listUnit4EN);

        String[] unit_4_ru = getResources().getStringArray(R.array.unit_4_ru);
        List<String> listUnit4RU = new ArrayList<>(Arrays.asList(unit_4_ru));
        myRef.child("unit_4_ru").setValue(listUnit4RU);

        String[] unit_5_en = getResources().getStringArray(R.array.unit_5_en);
        List<String> listUnit5EN = new ArrayList<>(Arrays.asList(unit_5_en));
        myRef.child("unit_5_en").setValue(listUnit5EN);

        String[] unit_5_ru = getResources().getStringArray(R.array.unit_5_ru);
        List<String> listUnit5RU = new ArrayList<>(Arrays.asList(unit_5_ru));
        myRef.child("unit_5_ru").setValue(listUnit5RU);

        String[] unit_6_en = getResources().getStringArray(R.array.unit_6_en);
        List<String> listUnit6EN = new ArrayList<>(Arrays.asList(unit_6_en));
        myRef.child("unit_6_en").setValue(listUnit6EN);

        String[] unit_6_ru = getResources().getStringArray(R.array.unit_6_ru);
        List<String> listUnit6RU = new ArrayList<>(Arrays.asList(unit_6_ru));
        myRef.child("unit_6_ru").setValue(listUnit6RU);

        String[] unit_7_en = getResources().getStringArray(R.array.unit_7_en);
        List<String> listUnit7EN = new ArrayList<>(Arrays.asList(unit_7_en));
        myRef.child("unit_7_en").setValue(listUnit7EN);

        String[] unit_7_ru = getResources().getStringArray(R.array.unit_7_ru);
        List<String> listUnit7RU = new ArrayList<>(Arrays.asList(unit_7_ru));
        myRef.child("unit_7_ru").setValue(listUnit7RU);

        String[] unit_8_en = getResources().getStringArray(R.array.unit_8_en);
        List<String> listUnit8EN = new ArrayList<>(Arrays.asList(unit_8_en));
        myRef.child("unit_8_en").setValue(listUnit8EN);

        String[] unit_8_ru = getResources().getStringArray(R.array.unit_8_ru);
        List<String> listUnit8RU = new ArrayList<>(Arrays.asList(unit_8_ru));
        myRef.child("unit_8_ru").setValue(listUnit8RU);

        String[] unit_9_en = getResources().getStringArray(R.array.unit_9_en);
        List<String> listUnit9EN = new ArrayList<>(Arrays.asList(unit_9_en));
        myRef.child("unit_9_en").setValue(listUnit9EN);

        String[] unit_9_ru = getResources().getStringArray(R.array.unit_9_ru);
        List<String> listUnit9RU = new ArrayList<>(Arrays.asList(unit_9_ru));
        myRef.child("unit_9_ru").setValue(listUnit9RU);

        String[] unit_10_en = getResources().getStringArray(R.array.unit_10_en);
        List<String> listUnit10EN = new ArrayList<>(Arrays.asList(unit_10_en));
        myRef.child("unit_10_en").setValue(listUnit10EN);

        String[] unit_10_ru = getResources().getStringArray(R.array.unit_10_ru);
        List<String> listUnit10RU = new ArrayList<>(Arrays.asList(unit_10_ru));
        myRef.child("unit_10_ru").setValue(listUnit10RU);
    }
}
