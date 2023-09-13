package com.ada.locateCarProject.util;

public class DiscountPJuridica implements RentalDiscount{
    @Override
    public double calculate(int totalRentalDate) {
        if(totalRentalDate > 3) {
            return 0.10;
        }
        return 0.0;
    }
}
