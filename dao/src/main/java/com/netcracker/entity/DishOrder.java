package com.netcracker.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dishOrder")
public class DishOrder extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "personId")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "dishId")
    private Dish dish;

    public DishOrder(Integer id, Person person, Dish dish) {
        super(id);
        this.person = person;
        this.dish = dish;
    }

    public DishOrder() {
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Dish getDish() {
        return dish;
    }

    public Person getPerson() {
        return person;
    }
}
