package com.mrdinner.domain.menu;

import com.mrdinner.domain.common.Money;

/**
 * Concrete dinner class for English cuisine dinner
 */
public class EnglishDinner extends Dinner {
    private static final Money TEA_SERVICE_FEE = Money.of(3.50, "USD");

    public EnglishDinner(String name, String description, Money basePrice) {
        super(name, description, basePrice, ServingStyle.FORMAL);
        setupEnglishMenu();
    }

    private void setupEnglishMenu() {
        // Add traditional English elements
        MenuItem tea = new MenuItem("English Tea", 
            "Traditional English breakfast tea", 
            Money.of(2.50, "USD"), ItemType.BEVERAGE);
        tea.setPreparationTimeMinutes(3);
        
        MenuItem pudding = new MenuItem("Yorkshire Pudding", 
            "Traditional English side dish", 
            Money.of(6.00, "USD"), ItemType.SIDE_DISH);
        pudding.setPreparationTimeMinutes(8);
        
        addMenuItem(tea);
        addMenuItem(pudding);
    }

    @Override
    public Money calculateTotalPrice() {
        Money total = super.calculateTotalPrice();
        return total.add(TEA_SERVICE_FEE);
    }

    @Override
    public String getDinnerType() {
        return "EnglishDinner";
    }

    public boolean includesTeaService() {
        return menuItems.stream()
            .anyMatch(item -> item.getName().toLowerCase().contains("tea"));
    }

    public String getCuisineStyle() {
        return "Traditional English Cuisine";
    }

    public String getSpecialInstructions() {
        return "Served with traditional English tea service";
    }

    public boolean isSundayRoast() {
        return name.toLowerCase().contains("sunday") || name.toLowerCase().contains("roast");
    }
}

