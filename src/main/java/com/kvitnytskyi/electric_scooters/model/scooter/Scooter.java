package com.kvitnytskyi.electric_scooters.model.scooter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Scooter {

    private long id;
    private String name;
    private double cost;
    private ScooterClass scooterClass;
    private ScooterMark scooterMark;
    private boolean status;

    @Override
    public String toString() {
        return name + ' ' + scooterMark + ' ' + scooterClass + ' ' + cost + '$';
    }
}
