package com.example.onroad.loginandregister;

public class accountDetailsList {

    String Name;
    String Surname;
    String Email;
    String Password;
    String birthdaydate;

    public accountDetailsList(String name, String surname, String email, String password,String birthdaydate) {
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
        this.Password = password;
        this.birthdaydate=birthdaydate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getBirthdaydate() {
        return birthdaydate;
    }

    public void setBirthdaydate(String birthdaydate) {
        this.birthdaydate = birthdaydate;
    }
}
