package com.student.satyam.college_manager;

public class Admin {
    private String fName;
    private String lName;
    private String collegename;
    private String collegenameRaw;
    private String state;
    private String city;
    private String pincode;
    private String email;
    private String uid;

    public Admin(){}

    public Admin(String fName, String lName, String collegename, String state, String city, String pincode, String email) {
        this.fName = fName;
        this.lName = lName;
        this.collegename = collegename;
        this.state = state;
        this.city = city;
        this.pincode = pincode;
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {this.fName = fName;}

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegenameRaw(String collegenameRaw) {
        this.collegename = collegenameRaw;
    }

    public String getCollegenameRaw() {
        return collegenameRaw;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
