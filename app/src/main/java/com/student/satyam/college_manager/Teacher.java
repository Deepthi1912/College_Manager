package com.student.satyam.college_manager;

/**
 * Created by JAISMENE VERMA on 27-03-2018.
 */

public class Teacher {

        private String tfName;
        private String tlName;
        private String tcollegename;
        private String temail;
        private String tuid;

        public Teacher(){}

        public Teacher(String fName, String lName, String collegename, String state, String city, String pincode, String email,String tuid) {
            this.tfName = tfName;
            this.tlName = tlName;
            this.tcollegename = tcollegename;
            this.temail = temail;
            this.tuid = tuid;
        }


        public String getfName() {
            return tfName;
        }

        public void setfName(String fName) {
            this.tfName = tfName;
        }

        public String getlName() {
            return tlName;
        }

        public void setlName(String lName) {
            this.tlName = tlName;
        }

        public String getCollegename() {
            return tcollegename;
        }

        public void setCollegename(String tcollegename) {this.tcollegename = tcollegename;}

        public String getEmail() {
            return temail;
        }

        public void setEmail(String temail) {
            this.temail = temail;
        }

        public String getUid() {
            return tuid;
        }

        public void setUid(String tuid) {this.tuid = tuid;}
    }

