package com.ada.locateCarProject.service;

import com.ada.locateCarProject.model.Client;
import com.ada.locateCarProject.model.Rental;
import com.ada.locateCarProject.model.Vehicle;
import com.ada.locateCarProject.util.CalculatePricePerDay;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private List<Client> clients = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Rental> rentals;


    public RentalService(List<Client> clients, List<Vehicle> vehicles) {
        this.clients = clients;
        this.vehicles = vehicles;
        this.rentals = new ArrayList<>();
    }

    public RentalService() {
        this.clients = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    public void rentVehicle(Client client, Vehicle vehicle, String location, LocalDateTime rentalStartDate, LocalDateTime rentalEndDate, CalculatePricePerDay pricingStrategy) {
        if (clients.contains(client) && vehicles.contains(vehicle) && vehicle.isAvailable() && rentalStartDate.isBefore(rentalEndDate)) {
            int totalRentalDays = calculateTotalRentalDays(rentalStartDate, rentalEndDate);

            double discount = client.getDiscount(totalRentalDays);
            double basePricePerDay = pricingStrategy.calculate();
            double rentalCost = basePricePerDay * totalRentalDays * (1 - discount);


            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            vehicle.rent(location, rentalStartDate, rentalEndDate);
            rentals.add(new Rental(location, rentalStartDate, rentalEndDate));

            System.out.println("Veículo alugado: " + vehicle.getPlate() + " | Tipo: " + vehicle.getCarSize() + "| Modelo-Marca: " + vehicle.getBrand());
            System.out.println("Custo da locação R$ " + rentalCost + " | Referente a diária de: " + basePricePerDay + "| Total de dias: " + totalRentalDays);
            System.out.println("Período de locação: " + dateTimeFormatter.format(rentalStartDate) + " até " + dateTimeFormatter.format(rentalEndDate));
        } else {
            System.out.println("Esse veículo já está alugado. " + "Placa: "+vehicle.getPlate() + " | Tipo: " + vehicle.getCarSize() + "| Modelo-Marca: " + vehicle.getBrand());;
        }
    }
    private int calculateTotalRentalDays(LocalDateTime rentalStartDate, LocalDateTime rentalEndDate) {
        return (int) ChronoUnit.DAYS.between(rentalStartDate, rentalEndDate);
    }

    public void returnVehicle(Vehicle vehicle) {
        if (!vehicle.isAvailable()) {
            vehicle.returnVehicle();
            System.out.println("Veículo devolvido: " + vehicle.getPlate() +" | Tipo: " + vehicle.getCarSize() + "| Modelo-Marca: " + vehicle.getBrand());
        } else {
            System.out.println("Este veículo não está alugado. ");
        }
    }

    public void listAvailableVehicles() {
        System.out.println("Veículos disponíveis para locação :");
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                System.out.println("Número da placa: " + vehicle.getPlate() + " | Tipo: " + vehicle.getCarSize() + "| Modelo-Marca: " + vehicle.getBrand());
            }

        }
    }
}
