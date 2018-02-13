package com.example.toshiba.vhealth.model;

import java.util.List;

/**
 * Created by TOSHIBA on 13/11/2017.
 */

public class ChartViewModel {
    private String name;
    private List<Float> values;

    public ChartViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Float> getValues() {
        return values;
    }

    public void setValues(List<Float> values) {
        this.values = values;
    }

    public void addValues(float value){
        this.values.add(value);

    }
}
