package ru.job4j.bank.model;

import java.util.Objects;

public class Client {
    private int id;

    private Passport passport;

    public Client() {
    }

    public Client(int id, Passport passport) {
        this.id = id;
        this.passport = passport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return id == client.id
                && Objects.equals(passport, client.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passport);
    }
}
