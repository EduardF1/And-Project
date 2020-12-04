package com.example.civilization_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.civilization_app.R;
import com.example.civilization_app.models.Civilization;

import java.util.ArrayList;
import java.util.List;

public class CivilizationsAdapter extends RecyclerView.Adapter<CivilizationsAdapter.ViewHolder> {


    private List<Civilization> civilizations = new ArrayList<>();


    @NonNull
    @Override
    public CivilizationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.civilizations_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void setCivilizations(List<Civilization> civilizations) {
        this.civilizations = civilizations;
        notifyDataSetChanged(); //TODO REMOVE LATER, TESTING
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (civilizations != null) {
            viewHolder.civName.setText(civilizations.get(position).getCivilizationName());
            viewHolder.civExpansion.setText(civilizations.get(position).getExpansion());
            viewHolder.civArmyType.setText(civilizations.get(position).getArmyType());
        }
    }


    @Override
    public int getItemCount() {
        if (civilizations != null) {
            return civilizations.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView civName;
        TextView civExpansion;
        TextView civArmyType;

        ViewHolder(View itemView) {
            super(itemView);
            civName = itemView.findViewById(R.id.tv_civilization_name);
            civExpansion = itemView.findViewById(R.id.tv_civilization_expansion);
            civArmyType = itemView.findViewById(R.id.tv_army_type);
        }
    }
}
