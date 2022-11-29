package com.springboot.insurtechbackend.model;

public class Bestrating {
    String serviceVehicleType;
    String reviewRating;

    public Bestrating(String serviceVehicleType, String reviewRating) {
        this.serviceVehicleType = serviceVehicleType;
        this.reviewRating = reviewRating;
    }

    public String getServiceVehicleType() {
        return serviceVehicleType;
    }

    public void setServiceVehicleType(String serviceVehicleType) {
        this.serviceVehicleType = serviceVehicleType;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }
}
