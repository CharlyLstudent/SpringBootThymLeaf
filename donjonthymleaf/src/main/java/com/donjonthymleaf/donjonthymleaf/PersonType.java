package com.donjonthymleaf.donjonthymleaf;

public enum PersonType {
    GUERRIER("Guerrier"),
    MAGICIEN("Magicien");

    private final String displayName;

    PersonType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
