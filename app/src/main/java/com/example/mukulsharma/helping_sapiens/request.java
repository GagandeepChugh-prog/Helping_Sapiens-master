package com.example.mukulsharma.helping_sapiens;

public class request {
    String email;
    String category;
    String detail;
    String requestDateandTime;
    String latitude;
    String longitude;
    boolean open=true;
    String uniqueID="";
    request()
    {}


    request(String email,String category,String Detail,String requestDateandTime,String latitude,String longitude)
    {
        this.detail=Detail;
        this.email=email;
        this.category=category;
        this.requestDateandTime=requestDateandTime;
        this.latitude=latitude;
        this.longitude=longitude;
        String email_without_dot= email.replace("."," ");
        this.uniqueID= email_without_dot + "__" + requestDateandTime;

    }

    public String getEmail() {
        return email;
    }

    public String getCategory() {
        return category;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getDetail() {
        return detail;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getRequestDateandTime() {
        return requestDateandTime;
    }

    public String getUniqueID() {
        return uniqueID;
    }
    public boolean getopen()
    {
        return open;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setRequestDateandTime(String requestDateandTime) {
        this.requestDateandTime = requestDateandTime;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
