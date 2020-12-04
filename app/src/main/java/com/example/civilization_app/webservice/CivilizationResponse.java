package com.example.civilization_app.webservice;

import com.example.civilization_app.models.Civilization;

import java.util.ArrayList;

public class CivilizationResponse {

    private int id;
    private String name;
    private String expansion;
    private String army_type;
    private ArrayList<Civilization> civilizations;

    public Civilization getCivilization() {
        return new Civilization(name, expansion, army_type);
    }


    public ArrayList<Civilization> getCivilizations() {
        return civilizations;
    }


}
