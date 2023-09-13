package com.ada.locateCarProject.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    private String plate;

    private String brand;
    private CarSize carSize;
    private boolean available;

    private List<Rental> activeRentals;


    public Vehicle(String plate, String brand, CarSize carSize, boolean available) {
        this.plate = plate;
        this.brand = brand;
        this.carSize = carSize;
        this.available = available;
        this.activeRentals = new ArrayList<>();
    }

    public String getPlate() {
        return plate;
    }

    public String getBrand() {
        return brand;
    }

    public CarSize getCarSize() {
        return carSize;
    }

    public boolean isAvailable() {
        return available;
    }


    public void rent(String location, LocalDateTime rentalStartDate, LocalDateTime rentalEndDate) {
        if (available) {
            available = false;
            int rentalDurationInHours = (int) ChronoUnit.HOURS.between(rentalStartDate, rentalEndDate);
            LocalDateTime returnDate = rentalStartDate.plusHours(rentalDurationInHours);
            Rental rental = new Rental(location, rentalStartDate, returnDate);
            activeRentals.add(rental);
            available = false;
        }
    }

    public void returnVehicle() {
        if (!available) {
            available = true;
            activeRentals.clear();
        }
    }

    public boolean isRented() {
        return !available;
    }

    public List<Rental> getActiveRentals() {
        return activeRentals;
    }

    private int calculateRentalDurationInHours(LocalDateTime rentalStartDate, LocalDateTime rentalEndDate) {
        return (int) ChronoUnit.HOURS.between(rentalStartDate, rentalEndDate);
    }

    private LocalDateTime calculateReturnDate(LocalDateTime rentalStartDate, int rentalDurationInHours) {
        return rentalStartDate.plusHours(rentalDurationInHours);
    }

    public void updateVehicle(String plate, String brand, CarSize carSize, boolean available){
        if( plate!= null){ this.plate = plate; }
        if( brand != null) {this.brand = brand;}
        if(carSize != null) {this.carSize = carSize;}
        if(available != true ) {this.available = false;}
    }
}
