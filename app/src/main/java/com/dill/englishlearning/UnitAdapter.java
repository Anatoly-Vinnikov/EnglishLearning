package com.dill.englishlearning;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class UnitAdapter extends android.support.v7.widget.RecyclerView.Adapter<UnitAdapter.ViewHolder> {

    private List<String> unitsNumbers, unitsThemes;

    UnitAdapter(List<String> unitsNumbers, List<String> unitsThemes) {
        this.unitsNumbers = unitsNumbers;
        this.unitsThemes = unitsThemes;
    }

    @Override
    public UnitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UnitAdapter.ViewHolder holder, int position) {
        holder.unitNumber.setText(unitsNumbers.get(position));
        holder.unitTheme.setText(unitsThemes.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UnitActivity.class);
                intent.putExtra(UnitActivity.unitID, String.valueOf(holder.getAdapterPosition() + 4));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return unitsNumbers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView unitNumber, unitTheme;

        ViewHolder(View itemView) {
            super(itemView);
            unitNumber = itemView.findViewById(R.id.item_unit_number);
            unitTheme = itemView.findViewById(R.id.item_unit_theme);
        }
    }
}