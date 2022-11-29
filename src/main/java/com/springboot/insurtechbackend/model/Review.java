package com.springboot.insurtechbackend.model;

import java.io.*;

public class Review implements Serializable {

    String userName;
    String userAge;
    String userOccupation;
    String userState;

    String userZipcode;
    String vehicleMaker;
    String vehicleType;
    String vehicleYear;
    String vehicleMilage;
    String serviceVehicleType;
    String serviceChosen;
    String reviewRating;
    String reviewText;

    public Review(String userName, String userAge, String userOccupation, String userState, String userZipcode, String vehicleMaker, String vehicleType, String vehicleYear, String vehicleMilage, String serviceVehicleType, String serviceChosen, String reviewRating, String reviewText) {
        this.userName = userName;
        this.userAge = userAge;
        this.userOccupation = userOccupation;
        this.userState = userState;
        this.userZipcode = userZipcode;
        this.vehicleMaker = vehicleMaker;
        this.vehicleType = vehicleType;
        this.vehicleYear = vehicleYear;
        this.vehicleMilage = vehicleMilage;
        this.serviceVehicleType = serviceVehicleType;
        this.serviceChosen = serviceChosen;
        this.reviewRating = reviewRating;
        this.reviewText = reviewText;
    }

    public Review(String userZipcode, String serviceVehicleType, String reviewRating, String reviewText) {
        this.userZipcode = userZipcode;
        this.serviceVehicleType = serviceVehicleType;
        this.reviewRating = reviewRating;
        this.reviewText = reviewText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserZipcode() {
        return userZipcode;
    }

    public void setUserZipcode(String userZipcode) {
        this.userZipcode = userZipcode;
    }

    public String getVehicleMaker() {
        return vehicleMaker;
    }

    public void setVehicleMaker(String vehicleMaker) {
        this.vehicleMaker = vehicleMaker;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleMilage() {
        return vehicleMilage;
    }

    public void setVehicleMilage(String vehicleMilage) {
        this.vehicleMilage = vehicleMilage;
    }

    public String getServiceVehicleType() {
        return serviceVehicleType;
    }

    public void setServiceVehicleType(String serviceVehicleType) {
        this.serviceVehicleType = serviceVehicleType;
    }

    public String getServiceChosen() {
        return serviceChosen;
    }

    public void setServiceChosen(String serviceChosen) {
        this.serviceChosen = serviceChosen;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
