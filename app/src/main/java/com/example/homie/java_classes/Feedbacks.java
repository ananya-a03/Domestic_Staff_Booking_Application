package com.example.homie.java_classes;

public class Feedbacks {
    String Subject;
    String DetQuery;

    public Feedbacks() {
    }

    public Feedbacks(String subject, String detQuery) {
        Subject = subject;
        DetQuery = detQuery;
    }



    public String getSubject() {
        return Subject;
    }

    public String getDetQuery() {
        return DetQuery;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setDetQuery(String detQuery) {
        DetQuery = detQuery;
    }
}
