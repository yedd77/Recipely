package com.example.recipely;

public class fridgeItem {

    public int garlic, onion;
    public String garlicDateExpiry, onionDateExpiry;

    public fridgeItem(){}

    public fridgeItem(int garlic, int onion, String garlicDateExpiry, String onionDateExpiry) {
        this.garlic = garlic;
        this.onion = onion;
        this.garlicDateExpiry = garlicDateExpiry;
        this.onionDateExpiry = onionDateExpiry;
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

    public String getGarlicDateExpiry() {
        return garlicDateExpiry;
    }

    public void setGarlicDateExpiry(String garlicDateExpiry) {
        this.garlicDateExpiry = garlicDateExpiry;
    }

    public String getOnionDateExpiry() {
        return onionDateExpiry;
    }

    public void setOnionDateExpiry(String onionDateExpiry) {
        this.onionDateExpiry = onionDateExpiry;
    }
}
