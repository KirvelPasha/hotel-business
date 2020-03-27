package com.netcracker.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
public class PersonRole extends BaseEntity {

    private String role;

    @OneToMany(mappedBy = "personRole")
    private Set<Person> people;

    public PersonRole(Integer id, String role) {
        super(id);
        this.role = role;
    }

    public PersonRole() {
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonRole that = (PersonRole) o;
        return Objects.equals(role, that.role) &&
                Objects.equals(people, that.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, people);
    }
}
