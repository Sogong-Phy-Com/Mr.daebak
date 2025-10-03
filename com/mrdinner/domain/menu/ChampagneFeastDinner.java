package com.mrdinner.domain.menu;

import com.mrdinner.domain.common.Money;

/**
 * Concrete dinner class for Champagne Feast dinner with style constraints
 * This is a premium dinner that must be served in formal style only
 */
public class ChampagneFeastDinner extends Dinner {
    private static final Money CHAMPAGNE_PREMIUM = Money.of(50.00, "USD");
    private static final double LUXURY_PREMIUM = 0.40; // 40% luxury premium

    public ChampagneFeastDinner(String name, String description, Money basePrice) {
        super(name, description, basePrice, ServingStyle.FORMAL); // Always formal for champagne feast
        setupChampagneFeastMenu();
    }

    private void setupChampagneFeastMenu() {
        // Add luxury champagne elements
        MenuItem champagne = new MenuItem("Premium Champagne", 
            "High-quality champagne for celebration", 
            Money.of(45.00, "USD"), ItemType.BEVERAGE);
        champagne.setPreparationTimeMinutes(5);
        
        MenuItem caviar = new MenuItem("Caviar", 
            "Premium caviar as appetizer", 
            Money.of(25.00, "USD"), ItemType.APPETIZER);
        caviar.setPreparationTimeMinutes(3);
        
        MenuItem truffle = new MenuItem("Truffle Garnish", 
            "Fresh truffle shavings", 
            Money.of(15.00, "USD"), ItemType.SIDE_DISH);
        truffle.setPreparationTimeMinutes(2);
        
        addMenuItem(champagne);
        addMenuItem(caviar);
        addMenuItem(truffle);
    }

    @Override
    public void setServingStyle(ServingStyle servingStyle) {
        // Champagne feast can only be served in formal style
        if (servingStyle != ServingStyle.FORMAL) {
            throw new IllegalArgumentException("Champagne feast can only be served in formal style");
        }
        super.setServingStyle(servingStyle);
    }

    @Override
    protected Money applyDiscounts(Money price) {
        // Apply 40% luxury premium
        Money premium = price.multiply(LUXURY_PREMIUM);
        return price.add(premium);
    }

    @Override
    public Money calculateTotalPrice() {
        Money total = super.calculateTotalPrice();
        return total.add(CHAMPAGNE_PREMIUM);
    }

    @Override
    public String getDinnerType() {
        return "ChampagneFeastDinner";
    }

    public boolean includesChampagne() {
        return menuItems.stream()
            .anyMatch(item -> item.getName().toLowerCase().contains("champagne"));
    }

    public boolean isLuxuryDinner() {
        return true;
    }

    public String getCuisineStyle() {
        return "Luxury Champagne Feast";
    }

    public String getSpecialInstructions() {
        return "Premium luxury dinner with champagne - formal service only";
    }

    public Money getChampagneValue() {
        return menuItems.stream()
            .filter(item -> item.getName().toLowerCase().contains("champagne"))
            .findFirst()
            .map(MenuItem::getPrice)
            .orElse(Money.zero("USD"));
    }
}

