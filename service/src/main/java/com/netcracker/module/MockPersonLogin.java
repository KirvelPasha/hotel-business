package com.netcracker.module;

import java.util.Objects;

public class MockPersonLogin {

    private Integer id;
    private String login;
    private String password;

    public MockPersonLogin(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public MockPersonLogin(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockPersonLogin mockPersonLogin1 = (MockPersonLogin) o;
        return Objects.equals(login, mockPersonLogin1.login) &&
                Objects.equals(password, mockPersonLogin1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "Login{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
