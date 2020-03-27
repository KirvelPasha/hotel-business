package com.netcracker.entity;

import javax.persistence.*;

@Entity
@Table(name = "produce")
public class Produce extends BaseEntity {

    private String name;

    private int grams;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dishId")
    private Dish dish;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }
}
