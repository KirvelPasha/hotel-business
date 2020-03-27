package com.netcracker.requestbody;

import org.springframework.stereotype.Component;

@Component
public class PersonUpdate {
    private Integer personId;
    private Integer personRoleId;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(Integer personRoleId) {
        this.personRoleId = personRoleId;
    }
}
