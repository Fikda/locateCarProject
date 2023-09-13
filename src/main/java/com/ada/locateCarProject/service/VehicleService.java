package com.ada.locateCarProject.service;

import java.util.List;
import java.util.Optional;

public class VehicleService {

    private List<Vehicle> vehicles;

    public VehicleService(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        if (!isDuplicateVehicle(vehicle)) {
            vehicles.add(vehicle);
        } else {
            System.out.println("Existe um veículo com a mesma placa.");
        }
    }

    private boolean isDuplicateVehicle(Vehicle vehicle) {
        String plateToCheck = vehicle.getPlate();
        return vehicles.stream().anyMatch(v -> v.getPlate().equals(plateToCheck));
    }

    public boolean updateVehicle(String plate, String brand, CarSize carSize, boolean available){
        Optional<Vehicle> optionalVehicle = vehicles.stream()
                .filter(vehicle -> vehicle.getPlate().equals(plate))
                .findFirst();
        if(optionalVehicle.isPresent()){
            Vehicle vehicle = optionalVehicle.get();
            vehicle.updateVehicle(plate,brand,carSize,available);
            return true;
        }
        return false;
    }
}
