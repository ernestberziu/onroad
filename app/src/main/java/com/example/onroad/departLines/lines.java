package com.example.onroad.departLines;

import android.widget.Button;

public class lines {




    String cityd;
    String citya;
    String timed;
    String timea;
    String price;

    Button button;

    public lines() {
    }

    public lines(String cityd, String citya, String timed, String timea, String price) {
        this.cityd = cityd;
        this.citya = citya;
        this.timed = timed;
        this.timea = timea;
        this.price = price;

    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getCityd() {
        return cityd;
    }

    public void setCityd(String cityd) {
        this.cityd = cityd;
    }

    public String getCitya() {
        return citya;
    }

    public void setCitya(String citya) {
        this.citya = citya;
    }

    public String getTimed() {
        return timed;
    }

    public void setTimed(String timed) {
        this.timed = timed;
    }

    public String getTimea() {
        return timea;
    }

    public void setTimea(String timea) {
        this.timea = timea;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
