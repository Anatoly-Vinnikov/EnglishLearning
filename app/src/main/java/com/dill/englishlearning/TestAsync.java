package com.dill.englishlearning;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class TestAsync extends AsyncTask<Integer, Integer, Integer> {

    private final ThreadLocal<TextView> enWord = new ThreadLocal<>();
    private String word;
    private final ThreadLocal<Button> btnDone = new ThreadLocal<>();

    TestAsync(TextView enWord, String word, Button btnDone) {
        this.enWord.set(enWord);
        this.word = word;
        this.btnDone.set(btnDone);
    }

    protected Integer doInBackground(Integer... urls) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 1;
    }

    protected void onPostExecute(Integer result) {
        enWord.get().setTextColor(Color.BLACK);
        enWord.get().setText(word);
        btnDone.get().setVisibility(View.VISIBLE);
        super.onPostExecute(result);
    }
}