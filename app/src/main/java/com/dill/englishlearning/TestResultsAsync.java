package com.dill.englishlearning;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

class TestResultsAsync extends AsyncTask<Integer, Integer, Integer> {

    private final ThreadLocal<ProgressBar> resBar = new ThreadLocal<>();
    private final ThreadLocal<ProgressBar> pBar = new ThreadLocal<>();
    private final ThreadLocal<Button> btnDone = new ThreadLocal<>();
    private int correct, wrong;

    TestResultsAsync(int correct, int wrong, ProgressBar resBar, ProgressBar pBar, Button btnDone) {
        this.correct = correct;
        this.wrong = wrong;
        this.resBar.set(resBar);
        this.pBar.set(pBar);
        this.btnDone.set(btnDone);
    }

    protected void onPreExecute() {
    }

    protected Integer doInBackground(Integer... urls) {
        publishProgress(0);
        for (int i = 0; i < (float) correct / (wrong + correct) * 100; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i + 1);
        }

        return 1;
    }

    protected void onProgressUpdate(Integer... progress) {
        try {
            resBar.get().setProgress(progress[0]);
            pBar.get().setVisibility(View.INVISIBLE);
            resBar.get().setVisibility(View.VISIBLE);
            btnDone.get().setVisibility(View.INVISIBLE);
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}