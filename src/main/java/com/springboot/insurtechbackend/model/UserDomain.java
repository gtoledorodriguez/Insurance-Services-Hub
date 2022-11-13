package com.springboot.insurtechbackend.model;

import java.io.Serializable;

public class UserDomain implements Serializable {
    private int userid;
    private String UserName;
    private String Password;
    private String UserType;
    private String CreditCardID;
    private String AddressID;


    public UserDomain(){

    }

    public UserDomain(int userid, String userName, String password, String userType, String creditCardID, String addressID) {
        this.userid = userid;
        UserName = userName;
        Password = password;
        UserType = userType;
        CreditCardID = creditCardID;
        AddressID = addressID;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getCreditCardID() {
        return CreditCardID;
    }

    public void setCreditCardID(String creditCardID) {
        CreditCardID = creditCardID;
    }

    public String getAddressID() {
        return AddressID;
    }

    public void setAddressID(String addressID) {
        AddressID = addressID;
    }
}
