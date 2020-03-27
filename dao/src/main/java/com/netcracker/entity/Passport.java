package com.netcracker.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passport")
public class Passport extends BaseEntity {


    private int serialNumber;

    private String dateExpire;

    @OneToOne(mappedBy = "passport")
    private Person person;

    public Passport(Integer id, int serialNumber, String dateExpire) {
        super(id);
        this.serialNumber = serialNumber;
        this.dateExpire = dateExpire;
    }

    public Passport() {
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(String dateExpire) {
        this.dateExpire = dateExpire;
    }


}
