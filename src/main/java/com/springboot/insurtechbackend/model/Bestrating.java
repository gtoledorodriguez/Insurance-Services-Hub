package com.springboot.insurtechbackend.model;

public class Bestrating {
    String serviceVehicleType;
    String service;
    String reviewRating;

    public Bestrating(String serviceVehicleType, String service,String reviewRating) {
        this.serviceVehicleType = serviceVehicleType;
        this.service = service;
        this.reviewRating = reviewRating;
    }

    public String getServiceVehicleType() {
        return serviceVehicleType;
    }

    public void setServiceVehicleType(String serviceVehicleType) {
        this.serviceVehicleType = serviceVehicleType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }
}
