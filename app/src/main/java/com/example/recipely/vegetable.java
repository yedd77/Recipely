package com.example.recipely;

public class vegetable {

    private int garlic, onion;
    private String onionDateExpiry, garlicDateExpiry;

    public vegetable(){}


    public vegetable(int garlic, String garlicDateExpiry,  int onion, String onionDateExpiry) {
        this.garlic = garlic;
        this.onion = onion;
        this.onionDateExpiry = onionDateExpiry;
        this.garlicDateExpiry = garlicDateExpiry;

    }

    public int getGarlic() {
        return garlic;
    }

    public void setGarlic(int garlic) {
        this.garlic = garlic;
    }

    public int getOnion() {
        return onion;
    }

    public void setOnion(int onion) {
        this.onion = onion;
    }

    public String getOnionDateExpiry() {
        return onionDateExpiry;
    }

    public void setOnionDateExpiry(String onionDateExpiry) {
        this.onionDateExpiry = onionDateExpiry;
    }

    public String getGarlicDateExpiry() {
        return garlicDateExpiry;
    }

    public void setGarlicDateExpiry(String garlicDateExpiry) {
        this.garlicDateExpiry = garlicDateExpiry;
    }
}
