package com.springboot.insurtechbackend.model;

public class Mostsoldzip {
    String zipcode;
    String count;

    public Mostsoldzip(String zipcode, String count) {
        this.zipcode = zipcode;
        this.count = count;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
