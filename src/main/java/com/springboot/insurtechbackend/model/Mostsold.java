package com.springboot.insurtechbackend.model;

public class Mostsold {
    String servicename;
    String count;

    public Mostsold(String servicename, String count) {
        this.servicename = servicename;
        this.count = count;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
