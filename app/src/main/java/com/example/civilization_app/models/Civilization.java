package com.example.civilization_app.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "civilization_table")
public class Civilization {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String civilizationName;
    public String expansion;
    public String armyType;

    public Civilization() {

    }

    public Civilization(String civilizationName, String expansion, String armyType) {
        this.civilizationName = civilizationName;
        this.expansion = expansion;
        this.armyType = armyType;
    }

    public String getCivilizationName() {
        return civilizationName;
    }

    public String getExpansion() {
        return expansion;
    }

    public String getArmyType() {
        return armyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCivilizationName(String civilizationName) {
        this.civilizationName = civilizationName;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public void setArmyType(String armyType) {
        this.armyType = armyType;
    }
}
