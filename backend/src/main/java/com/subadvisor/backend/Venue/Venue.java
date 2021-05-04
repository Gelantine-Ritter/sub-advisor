package com.subadvisor.backend.Venue;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="venues")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String info;

    public Venue () {}

    public Venue(Long id, String name, String email, String info) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
