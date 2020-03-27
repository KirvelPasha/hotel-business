package com.netcracker.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "apartmentTypes")
public class ApartmentTypes extends BaseEntity {


    private String type;

    @OneToMany(mappedBy = "apartmentTypes")
    private List<Apartment> apartmentList;

    public ApartmentTypes(Integer id, String type) {
        super(id);
        this.type = type;
    }

    public ApartmentTypes() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setApartmentList(List<Apartment> apartmentList) {
        this.apartmentList = apartmentList;
    }
}
