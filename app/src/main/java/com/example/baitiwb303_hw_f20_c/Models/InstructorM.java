package com.example.baitiwb303_hw_f20_c.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InstructorM implements Serializable {

    @SerializedName("instructor_id")
    String instructor_id;

    @SerializedName("instructor_first_name")
    String instructor_first_name;

    @SerializedName("instructor_last_name")
    String instructor_last_name;

    @SerializedName("instructor_gender")
    String instructor_gender;

    @SerializedName("instructor_address")
    String instructor_address;

    @SerializedName("instructor_mobile")
    String instructor_mobile;

    public String getInstructor_first_name() {
        return instructor_first_name;
    }

    public void setInstructor_first_name(String instructor_first_name) {
        this.instructor_first_name = instructor_first_name;
    }

    public String getInstructor_last_name() {
        return instructor_last_name;
    }

    public void setInstructor_last_name(String instructor_last_name) {
        this.instructor_last_name = instructor_last_name;
    }

    public String getInstructor_gender() {
        return instructor_gender;
    }

    public void setInstructor_gender(String instructor_gender) {
        this.instructor_gender = instructor_gender;
    }

    public String getInstructor_address() {
        return instructor_address;
    }

    public void setInstructor_address(String instructor_address) {
        this.instructor_address = instructor_address;
    }

    public String getInstructor_mobile() {
        return instructor_mobile;
    }

    public void setInstructor_mobile(String instructor_mobile) {
        this.instructor_mobile = instructor_mobile;
    }

    public String getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }
}
