package com.example.android.bookclublatest.Admin.RequestsForMember;

public class RequetsForMemberModel {


    String name,admissionnumber,email,phonenumber;

    public RequetsForMemberModel(String name, String admissionnumber, String email, String phonenumber) {
        this.name = name;
        this.admissionnumber = admissionnumber;
        this.email = email;
        this.phonenumber = phonenumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmissionnumber() {
        return admissionnumber;
    }

    public void setAdmissionnumber(String admissionnumber) {
        this.admissionnumber = admissionnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
