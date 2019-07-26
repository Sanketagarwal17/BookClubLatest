package com.example.android.bookclublatest.Authentication.SignUp;

public class SignUpModel {
    String name,email,admissionnumber,phonenumber,status,image_url;

    public SignUpModel() {
    }

    public SignUpModel(String name, String email, String admissionnumber, String phonenumber, String status,String url) {
        this.name = name;
        this.email = email;
        this.admissionnumber = admissionnumber;
        this.phonenumber = phonenumber;
        this.status = status;
        this.image_url=url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdmissionnumber() {
        return admissionnumber;
    }

    public void setAdmissionnumber(String admissionnumber) {
        this.admissionnumber = admissionnumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
