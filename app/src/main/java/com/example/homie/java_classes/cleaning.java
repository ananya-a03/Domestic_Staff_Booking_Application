package com.example.homie.java_classes;

public class cleaning {
    String Name;
    String Email;
    String Phone;
    String Address;
    String PinCode;
    String Date;
    String Time;

    public cleaning() {
    }

    public cleaning(String name, String email, String phone, String address, String pinCode, String date, String time) {
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
}
