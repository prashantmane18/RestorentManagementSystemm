package com.restorentInfo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "restaurants")
public class Restorent {

    @Id
    private String id;

    private String name;
    private String address;
    private String best;
    private double rating;

    public Restorent() {
    }

    public Restorent(String name, String address,
                     String best, double rating) {
        this.name = name;
        this.address = address;
        this.best = best;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBest() {
        return best;
    }

    public void setBest(String best) {
        this.best = best;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}