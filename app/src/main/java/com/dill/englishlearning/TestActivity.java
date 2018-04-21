package com.dill.englishlearning;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    public static final String unitID = "0";
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    List wordsEN, wordsRU;
    ArrayList<Integer> IDs = new ArrayList<>();
    Random random = new Random();
    ProgressBar pBar, resBar;
    int curWord = 0, wrong = 0, correct = 0;
    InputMethodManager imm;

    TextView enWord;
    EditText ruWord;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Unit " + getIntent().getStringExtra(unitID));

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        enWord = findViewById(R.id.enWord);
        ruWord = findViewById(R.id.ruWord);
        btnDone = findViewById(R.id.btnDone);
        pBar = findViewById(R.id.pBar);
        resBar = findViewById(R.id.resBar);

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

                while (IDs.size() < 10) {
                    Integer id = random.nextInt(wordsEN.size());
                    if (!IDs.contains(id)) {
                        IDs.add(id);
                    }
                }

                enWord.setText(String.valueOf(wordsEN.get(IDs.get(curWord))));
                ruWord.setVisibility(View.VISIBLE);
                btnDone.setVisibility(View.VISIBLE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void btnDone(View view) {
        btnDone.setVisibility(View.INVISIBLE);
        String answer = ruWord.getText().toString().toLowerCase();
        if (answer.length() != 0 && answer.charAt(answer.length() - 1) == ' ') {
            answer = answer.substring(0, answer.length() - 1);
        }
        if (wordsRU.get(IDs.get(curWord)).toString().toLowerCase().equals(answer)) {
            enWord.setTextColor(Color.GREEN);
            correct++;
        } else {
            enWord.setTextColor(Color.RED);
            wrong++;
        }
        ruWord.getText().clear();
        curWord++;
        pBar.setProgress(curWord * 10);
        if (curWord < 10) {
            new TestAsync(enWord, String.valueOf(wordsEN.get(IDs.get(curWord))), btnDone).execute();
        } else {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            String str = String.format(Locale.getDefault(), "%s %d\n%s %d", getString(R.string.correct_ans), correct, getString(R.string.wrong_ans), wrong);
            new TestAsync(enWord, str, btnDone).execute();
            ruWord.setVisibility(View.INVISIBLE);
            new TestResultsAsync(correct, wrong, resBar, pBar, btnDone).execute();
        }
    }
}
