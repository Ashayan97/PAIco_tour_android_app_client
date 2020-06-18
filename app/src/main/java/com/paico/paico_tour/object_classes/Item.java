package com.paico.paico_tour.object_classes;

public class Item {
    private String info;
    private String title;
    private String[] imgUrls;
    private String imgProfile;
    private float point;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getImgUrls() {
        return imgUrls;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }

    public void setImgUrls(String[] imgUrls) {
        this.imgUrls = imgUrls;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }
}
