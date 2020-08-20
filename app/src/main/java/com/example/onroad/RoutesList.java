package com.example.onroad;



public class RoutesList {
    String cityd;
    String citya;
    String timed;
    String timea;
    String price;
    String information;
    String date;



    public RoutesList() {
    }

    public RoutesList(String cityd, String citya, String timed, String timea, String price, String information,String date) {
        this.cityd = cityd;
        this.citya = citya;
        this.timed = timed;
        this.timea = timea;
        this.price = price;
        this.information=information;
        this.date=date;

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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
