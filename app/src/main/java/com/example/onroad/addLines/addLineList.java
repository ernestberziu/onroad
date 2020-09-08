package com.example.onroad.addLines;

public class addLineList {
    String cityd,citya,minpass,totpass,lineame,price,timed,timea,date;


    public addLineList(String cityd, String citya, String minpass, String totpass, String lineame, String price, String timed, String timea, String date) {
        this.cityd = cityd;
        this.citya = citya;
        this.totpass = totpass;
        this.lineame = lineame;
        this.price = price;
        this.timed = timed;
        this.timea = timea;
        this.date = date;
        this.minpass=minpass;
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

    public String getTotpass() {
        return totpass;
    }

    public void setTotpass(String totpass) {
        this.totpass = totpass;
    }

    public String getLineame() {
        return lineame;
    }

    public void setLineame(String lineame) {
        this.lineame = lineame;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMinpass() {
        return minpass;
    }

    public void setMinpass(String minpass) {
        this.minpass = minpass;
    }
}
