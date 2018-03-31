package com.student.satyam.college_manager;

/**
 * Created by oj on 3/31/2018.
 */

public class AdminNotice {

    private String subject;
    private String content;

    public AdminNotice() {
    }

    public AdminNotice(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
