package com.ada.locateCarProject;

import com.ada.locateCarProject.model.Client;
import com.ada.locateCarProject.model.ClientPessoaFisica;
import com.ada.locateCarProject.model.ClientPessoaJuridica;
import com.ada.locateCarProject.model.Vehicle;
import com.ada.locateCarProject.service.ClientService;
import com.ada.locateCarProject.service.RentalService;
import com.ada.locateCarProject.service.VehicleService;
import com.ada.locateCarProject.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.ada.locateCarProject.enums.CarSize.*;

@SpringBootApplication
public class LocateCarProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(LocateCarProjectApplication.class, args);
		List<Client> clients = new ArrayList<>();
		List<Vehicle> vehicles = new ArrayList<>();

		RentalService rentalService = new RentalService(clients, vehicles);
		ClientService clientService = new ClientService(clients);
		VehicleService vehicleService = new VehicleService(vehicles);

		CalculatePricePerDay smallVehiclePrice = new PriceSmallVehicle();
		CalculatePricePerDay mediumVehiclePrice = new PriceMediumVehicle();
		CalculatePricePerDay suvVehiclePrice = new PriceSUVVehicle();

		RentalDiscount pfDiscount = new DiscountPFisica();
		RentalDiscount pjDiscount = new DiscountPJuridica();

		Client clientPF = new ClientPessoaFisica("Sarah Jessica Park", "99456-5489", pfDiscount, "515-879-489-56");
		clientPF.updateClient("Sarah Jessica Park", "987-654-3210", pfDiscount, "515-879-489-56");
		System.out.println(clientPF.toString());

		Client clientPJ = new ClientPessoaJuridica("Microsoft", "11-3589-7847", pjDiscount, "01.898.444/0001-66");

		Vehicle smallCar = new Vehicle("FRT1892", "MOBI-FIAT", SMALL, false);
		Vehicle mediumCar = new Vehicle("XWZ841", "COROLLA-TOYOTA", MEDIUM, true);
		Vehicle suvCar = new Vehicle("VIV101", "PAJERO-MITSUBISHI", SUV, true);
		Vehicle smallCar2 = new Vehicle("ADS2021", "KWID - RENAULT", SMALL, true);
		Vehicle mediumCar2 = new Vehicle("FLW8752", "T-CROSS-VOLKSWAGEN", MEDIUM, true);
		Vehicle suvCar2 = new Vehicle("MIC842", "KICKS-NISSAN", SUV, true);
		suvCar2.updateVehicle("MIC842", "COMPASS-JEEP", SUV, true);

		clientService.addClient(clientPF);
		clientService.addClient(clientPJ);
		vehicleService.addVehicle(smallCar);
		vehicleService.addVehicle(mediumCar);
		vehicleService.addVehicle(suvCar);
		vehicleService.addVehicle(smallCar2);
		vehicleService.addVehicle(mediumCar2);
		vehicleService.addVehicle(suvCar2);

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");

		System.out.println("________________");
		rentalService.rentVehicle(clientPF, smallCar, "AV.Paulista", LocalDateTime.of(2023, 9, 5, 10, 00), LocalDateTime.of(2023, 9, 26, 15, 30), smallVehiclePrice);

		System.out.println("________________");
		rentalService.rentVehicle(clientPJ, mediumCar, "Congonhas", LocalDateTime.of(2023, 9, 11, 6, 45), LocalDateTime.of(2023, 9, 15, 6, 30), mediumVehiclePrice); // 150 * 4 = 600.00 * 0.10 = 540.00

		System.out.println("__________________");
		rentalService.rentVehicle(clientPF, suvCar, "Guarulhos", LocalDateTime.of(2023, 9, 7, 9, 25), LocalDateTime.of(2023, 9, 13, 21, 25), suvVehiclePrice); // 200 * 6 = 1200 * 0.05 = 1140.00

		System.out.println("________________");
		rentalService.returnVehicle(smallCar);
		rentalService.listAvailableVehicles();

	}

}
