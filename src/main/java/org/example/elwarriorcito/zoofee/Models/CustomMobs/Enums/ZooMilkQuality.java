package org.example.elwarriorcito.zoofee.Models.CustomMobs.Enums;

public enum ZooMilkQuality {
    Fantasticow("Fantasticow"),
    Very_Good("Very Good"),
    Good("Good"),
    Bad("Bad");

    public final String label;

    private ZooMilkQuality(String label){
        this.label = label;
    }
}
