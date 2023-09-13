package com.ada.locateCarProject.model;

import java.time.LocalDateTime;

public class Rental {
    private String location;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;

    private Vehicle vehicle;

    private Client client;

    public Rental(String location, LocalDateTime rentalStartDate, LocalDateTime rentalEndDate) {
        this.location = location;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getRentalStartDate() {
        return rentalStartDate;
    }

    public LocalDateTime getRentalEndDate() {
        return rentalEndDate;
    }

}
