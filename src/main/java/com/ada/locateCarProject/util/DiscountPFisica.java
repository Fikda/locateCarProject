package com.ada.locateCarProject.util;

public class DiscountPFisica implements RentalDiscount{

    @Override
    public double calculate(int totalRentalDate) {
        if(totalRentalDate > 5 ){
            return 0.05;
        }
        return 0.0;
    }
}
