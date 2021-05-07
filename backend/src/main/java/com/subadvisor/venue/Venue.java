package com.subadvisor.venue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Venue {

    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    private String info;

    public Venue () {}

    public Venue(String name, String email, String info) {
        this.name = name;
        this.email = email;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Venue setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Venue setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public Venue setInfo(String info) {
        this.info = info;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return Objects.equals(id, venue.id) && Objects.equals(name, venue.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, info);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
