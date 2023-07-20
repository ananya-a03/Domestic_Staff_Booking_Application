package com.example.homie.java_classes;

public class baby_sitting {
    String Name;
    String Email;
    String Phone;
    String Address;
    String PinCode;
    String Date;
    String Time;

    public baby_sitting() {
    }

    public baby_sitting(String name, String email, String phone, String address, String pinCode, String date, String time) {
        Name = name;
        Email = email;
        Phone = phone;
        Address = address;
        PinCode = pinCode;
        Date = date;
        Time = time;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }


}
