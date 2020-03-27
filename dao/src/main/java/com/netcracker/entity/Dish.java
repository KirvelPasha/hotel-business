package com.netcracker.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "dish")
public class Dish extends BaseEntity {

    private String name;

    private int price;

    @OneToMany(mappedBy = "dish")
    private List<Produce> produceList;

    public Dish(Integer id, String name, int price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Produce> getProduceList() {
        return produceList;
    }

    public void setProduceList(List<Produce> produceList) {
        this.produceList = produceList;
    }
}
