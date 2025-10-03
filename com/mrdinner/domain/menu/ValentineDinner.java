package com.mrdinner.domain.menu;

import com.mrdinner.domain.common.Money;

/**
 * Concrete dinner class for Valentine's Day special dinner
 */
public class ValentineDinner extends Dinner {
    private static final Money ROMANTIC_SETUP_FEE = Money.of(15.00, "USD");
    private static final double VALENTINE_DISCOUNT = 0.10; // 10% discount

    public ValentineDinner(String name, String description, Money basePrice) {
        super(name, description, basePrice, ServingStyle.FORMAL);
        setupValentineMenu();
    }

    private void setupValentineMenu() {
        // Add romantic ambiance items
        MenuItem candlelight = new MenuItem("Romantic Candlelight", 
            "Ambient lighting for romantic atmosphere", 
            Money.of(5.00, "USD"), ItemType.BEVERAGE);
        candlelight.setPreparationTimeMinutes(5);
        
        MenuItem rose = new MenuItem("Fresh Rose", 
            "Single red rose for romantic setting", 
            Money.of(8.00, "USD"), ItemType.APPETIZER);
        rose.setPreparationTimeMinutes(2);
        
        addMenuItem(candlelight);
        addMenuItem(rose);
    }

    @Override
    protected Money applyDiscounts(Money price) {
        // Apply 10% Valentine's discount
        Money discount = price.multiply(VALENTINE_DISCOUNT);
        return price.subtract(discount);
    }

    @Override
    public Money calculateTotalPrice() {
        Money total = super.calculateTotalPrice();
        return total.add(ROMANTIC_SETUP_FEE);
    }

    @Override
    public String getDinnerType() {
        return "ValentineDinner";
    }

    public boolean isRomanticSetting() {
        return true;
    }

    public String getSpecialInstructions() {
        return "Romantic setting with candlelight and fresh rose included";
    }
}

