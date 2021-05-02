package com.subadvisor.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private double price;
    private String date;
    private String time;

    public Event(){}

    public Event(Long id, String title, double price, String date, String time){
        this.id = id;
        this.title = title;
        this.price = price;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Double.compare(event.price, price) == 0 && Objects.equals(id, event.id) && Objects.equals(title, event.title) && Objects.equals(date, event.date) && Objects.equals(time, event.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, date, time);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}