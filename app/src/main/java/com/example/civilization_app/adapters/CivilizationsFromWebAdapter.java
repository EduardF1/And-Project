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

public class CivilizationsFromWebAdapter extends RecyclerView.Adapter<CivilizationsFromWebAdapter.ViewHolder> {

    private List<Civilization> civilizations = new ArrayList<>();

    @NonNull
    @Override
    public CivilizationsFromWebAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.civilizations_list_item_web, parent, false);
        return new ViewHolder(view);
    }

    public void setCivilizations(List<Civilization> civilizations) {
        this.civilizations = civilizations;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (civilizations != null) {
            viewHolder.civilizationName.setText(civilizations.get(position).getCivilizationName());
            viewHolder.civilizationExpansion.setText(civilizations.get(position).getExpansion());
            viewHolder.civilizationArmyType.setText(civilizations.get(position).getArmyType());
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

        TextView civilizationName;
        TextView civilizationExpansion;
        TextView civilizationArmyType;

        ViewHolder(View itemView) {
            super(itemView);
            civilizationName = itemView.findViewById(R.id.tv_web_civilization_name);
            civilizationExpansion = itemView.findViewById(R.id.tv_web_civilization_expansion);
            civilizationArmyType = itemView.findViewById(R.id.tv_web_army_type);

        }
    }
}

