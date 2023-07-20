package com.example.homie.java_classes;

public class Bookings {

    String Name;
    String Email;
    String Phone;
    String Address;
    String PinCode;
    String Date;
    String Time;

    public Bookings(String name, String email, String phone, String address, String pinCode, String date, String time) {
        this.Name = name;
        this.Email = email;
        this.Phone = phone;
        this.Address = address;
        this.PinCode = pinCode;
        this.Date = date;
        this.Time = time;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getPinCode() {
        return PinCode;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }
}
