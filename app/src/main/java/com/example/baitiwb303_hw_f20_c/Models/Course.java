package com.example.baitiwb303_hw_f20_c.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Course implements Serializable {
    @SerializedName("course_id")
    String course_id;

    @SerializedName("course_tittle")
    String course_tittle;

    @SerializedName("course_hours")
    String course_hours;

    public String getCourse_tittle() {
        return course_tittle;
    }

    public void setCourse_tittle(String course_tittle) {
        this.course_tittle = course_tittle;
    }

    public String getCourse_hours() {
        return course_hours;
    }

    public void setCourse_hours(String course_hours) {
        this.course_hours = course_hours;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
}
