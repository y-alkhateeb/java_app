package com.example.baitiwb303_hw_f20_c.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EnrollmentM implements Serializable {
    @SerializedName("enrollment_id")
    String enrollment_id;

    @SerializedName("account_id")
    String account_id;

    @SerializedName("course_id")
    String course_id;

    @SerializedName("section_id")
    String section_id;

    @SerializedName("enrollment_grade")
    String enrollment_grade;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getEnrollment_id() {
        return enrollment_id;
    }

    public void setEnrollment_id(String enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    public String getEnrollment_grade() {
        return enrollment_grade;
    }

    public void setEnrollment_grade(String enrollment_grade) {
        this.enrollment_grade = enrollment_grade;
    }
}
