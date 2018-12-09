package com.example.mukulsharma.helping_sapiens;

public class Userinformation {
    private String email;
    private String name;
    private String phonenumber;

    Userinformation(String e,String name,String phonenumber)
    {
        email=e;
        this.name=name;
        this.phonenumber=phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
