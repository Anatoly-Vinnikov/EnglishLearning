package com.dill.englishlearning;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {

    private List<String> words;

    WordsAdapter(List<String> words) {
        this.words = words;
    }

    @Override
    public WordsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_activity_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WordsAdapter.ViewHolder holder, int position) {
        holder.word.setText(words.get(position));
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView word;

        ViewHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.item_word);
        }
    }
}