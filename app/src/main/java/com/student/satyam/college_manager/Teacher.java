package com.student.satyam.college_manager;

import java.util.List;

/**
 * Created by JAISMENE VERMA on 27-03-2018.
 */

public class Teacher {

        private String fName;
        private String lName;
        private String collegename;
        private String email;
        private String uid;
        private List<String> sem;

    public Teacher(String fName, String lName, String collegename, String email, String uid) {
        this.fName = fName;
        this.lName = lName;
        this.collegename = collegename;
        this.email = email;
        this.uid = uid;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
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

    public List<String> getSem() {return sem;}

    public void setSem(List<String> sem) {this.sem = sem;}
}

