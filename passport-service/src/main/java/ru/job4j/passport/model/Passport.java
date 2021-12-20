package ru.job4j.passport.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date created = new Date(System.currentTimeMillis());

    private int seria;

    private int number;

    private boolean isActive;

    public Passport() {
    }

    public Passport(int seria, int number, boolean isActive) {
        this.seria = seria;
        this.number = number;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getSeria() {
        return seria;
    }

    public void setSeria(int seria) {
        this.seria = seria;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passport passport = (Passport) o;
        return id == passport.id
                && seria == passport.seria
                && number == passport.number
                && isActive == passport.isActive
                && Objects.equals(created, passport.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, seria, number, isActive);
    }
}
