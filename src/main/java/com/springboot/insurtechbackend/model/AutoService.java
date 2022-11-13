package com.springboot.insurtechbackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auto_service")
public class AutoService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auto_service_id;

    @Column(nullable = false)
    private String service_name;

    @Column(nullable = false)
    private int liability_bodily;

    @Column(nullable = false)
    private int liability_property;

    @Column(nullable = false)
    private int comprehensive;

    @Column(nullable = false)
    private int collision;

    @Column(nullable = false)
    private int medical;

    @Column(nullable = false)
    private int um_bodily;

    @Column(nullable = false)
    private int um_property;

    @Column(nullable = false)
    private int rental;

    @Column(nullable = false)
    private int type;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date create_time;

    public AutoService() {}

    public AutoService(
            String service_name, int liability_bodily, int liability_property, int comprehensive, int collision,
            int medical, int um_bodily, int um_property, int rental, int type, Date create_time
    ) {
        this.service_name=service_name;
        this.liability_bodily=liability_bodily;
        this.liability_property=liability_property;
        this.comprehensive=comprehensive;
        this.collision=collision;
        this.medical=medical;
        this.um_bodily=um_bodily;
        this.um_property=um_property;
        this.rental=rental;
        this.type=type;
        this.create_time=create_time;
    }

    public int getAuto_service_id() {
        return auto_service_id;
    }

    public void setAuto_service_id(int auto_service_id) {
        this.auto_service_id = auto_service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int getLiability_bodily() {
        return liability_bodily;
    }

    public void setLiability_bodily(int liability_bodily) {
        this.liability_bodily = liability_bodily;
    }

    public int getLiability_property() {
        return liability_property;
    }

    public void setLiability_property(int liability_property) {
        this.liability_property = liability_property;
    }

    public int getComprehensive() {
        return comprehensive;
    }

    public void setComprehensive(int comprehensive) {
        this.comprehensive = comprehensive;
    }

    public int getCollision() {
        return collision;
    }

    public void setCollision(int collision) {
        this.collision = collision;
    }

    public int getMedical() {
        return medical;
    }

    public void setMedical(int medical) {
        this.medical = medical;
    }

    public int getUm_bodily() {
        return um_bodily;
    }

    public void setUm_bodily(int um_bodily) {
        this.um_bodily = um_bodily;
    }

    public int getUm_property() {
        return um_property;
    }

    public void setUm_property(int um_property) {
        this.um_property = um_property;
    }

    public int getRental() {
        return rental;
    }

    public void setRental(int rental) {
        this.rental = rental;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
