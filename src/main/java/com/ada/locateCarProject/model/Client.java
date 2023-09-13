package com.ada.locateCarProject.model;

public class Client {
    protected String name;
    protected String telephone;
    protected RentalDiscount rentalDiscount;
    protected String identifier;

    public Client(String name, String telephone, RentalDiscount rentalDiscount) {
        this.name = name;
        this.telephone = telephone;
        this.rentalDiscount = rentalDiscount;
    }

    public String getName() { return name; }

    public String getTelephone() { return telephone; }

    public RentalDiscount getRentalDiscount() { return rentalDiscount; }

    public String getIdentifier() { return ""; }

    public Double getDiscount(int totalRentalDate) {
        return rentalDiscount.calculate(totalRentalDate);
    }
    public void updateClient(String name, String telephone, RentalDiscount rentalDiscount,String identifier) {
        if (name != null) {
            this.name = name;
        }
        if (telephone != null) {
            this.telephone = telephone;
        }
        if (rentalDiscount != null) {
            this.rentalDiscount = rentalDiscount;
        }
        if(identifier != null) {
            this.identifier = identifier;
        }
    }
}
