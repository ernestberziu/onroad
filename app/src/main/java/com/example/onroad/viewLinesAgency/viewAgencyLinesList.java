package com.example.onroad.viewLinesAgency;

import android.widget.Button;

public class viewAgencyLinesList {




    String cityd;
    String citya;
    String timed;
    String timea;
    String price;

    String date;
    int id;

    Button button;

    public viewAgencyLinesList() {
    }

    public viewAgencyLinesList(String cityd, String citya, String timed, String timea, String price,String date,int id) {
        this.cityd = cityd;
        this.citya = citya;
        this.timed = timed;
        this.timea = timea;
        this.price = price;
        this.date=date;
        this.id =id;

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


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
