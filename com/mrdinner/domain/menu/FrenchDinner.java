package com.mrdinner.domain.menu;

import com.mrdinner.domain.common.Money;

/**
 * Concrete dinner class for French cuisine dinner
 */
public class FrenchDinner extends Dinner {
    private static final double FRENCH_PREMIUM = 0.25; // 25% premium for French cuisine

    public FrenchDinner(String name, String description, Money basePrice) {
        super(name, description, basePrice, ServingStyle.FORMAL);
        setupFrenchMenu();
    }

    private void setupFrenchMenu() {
        // Add classic French elements
        MenuItem frenchBread = new MenuItem("Baguette", 
            "Fresh French baguette with butter", 
            Money.of(4.00, "USD"), ItemType.BREAD);
        frenchBread.setPreparationTimeMinutes(3);
        
        MenuItem wine = new MenuItem("House Wine", 
            "French wine pairing recommendation", 
            Money.of(12.00, "USD"), ItemType.BEVERAGE);
        wine.setPreparationTimeMinutes(2);
        
        addMenuItem(frenchBread);
        addMenuItem(wine);
    }

    @Override
    protected Money applyDiscounts(Money price) {
        // Apply 25% premium for French cuisine
        Money premium = price.multiply(FRENCH_PREMIUM);
        return price.add(premium);
    }

    @Override
    public String getDinnerType() {
        return "FrenchDinner";
    }

    public boolean includesWinePairing() {
        return menuItems.stream()
            .anyMatch(item -> item.getName().toLowerCase().contains("wine"));
    }

    public String getCuisineStyle() {
        return "Traditional French Cuisine";
    }

    public String getSpecialInstructions() {
        return "Served in traditional French style with wine pairing";
    }
}

