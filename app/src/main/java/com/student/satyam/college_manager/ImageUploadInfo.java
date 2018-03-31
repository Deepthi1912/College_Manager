package com.student.satyam.college_manager;

/**
 * Created by oj on 3/30/2018.
 */
public class ImageUploadInfo {

    private String key;

    private String imageURL;

    private String imagename;

    public ImageUploadInfo() {

    }



    public ImageUploadInfo(String key, String url, String imagename) {

        this.key = key;
        this.imageURL = url;
        this.imagename = imagename;
    }

    public String getkey() {
        return key;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

}