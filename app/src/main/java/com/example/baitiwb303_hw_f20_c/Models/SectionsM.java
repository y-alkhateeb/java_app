package com.example.baitiwb303_hw_f20_c.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SectionsM implements Serializable {

    @SerializedName("section_id")
    String section_id;

    @SerializedName("course_id")
    String course_id;

    @SerializedName("course_name")
    String course_name;

    @SerializedName("instructor_id")
    String instructor_id;

    @SerializedName("instructor_name")
    String instructor_name;

    @SerializedName("section_section_no")
    String section_section_no;

    @SerializedName("section_room_no")
    String section_room_no;

    @SerializedName("section_time")
    String section_time;

    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getInstructor_name() {
        return instructor_name;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }

    public String getSection_section_no() {
        return section_section_no;
    }

    public void setSection_section_no(String section_section_no) {
        this.section_section_no = section_section_no;
    }

    public String getSection_room_no() {
        return section_room_no;
    }

    public void setSection_room_no(String section_room_no) {
        this.section_room_no = section_room_no;
    }

    public String getSection_time() {
        return section_time;
    }

    public void setSection_time(String section_time) {
        this.section_time = section_time;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }
}
