package com.mrdinner.domain.menu;

/**
 * Enumeration representing different serving styles for dinner items
 */
public enum ServingStyle {
    FORMAL("Formal dining experience with multiple courses"),
    CASUAL("Casual dining with relaxed presentation"),
    FAMILY("Family-style serving for sharing"),
    INDIVIDUAL("Individual portions for single serving"),
    BUFFET("Self-service buffet style");

    private final String description;

    ServingStyle(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace("_", " ");
    }
}

