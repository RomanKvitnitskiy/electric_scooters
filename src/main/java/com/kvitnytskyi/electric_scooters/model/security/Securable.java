package com.kvitnytskyi.electric_scooters.model.security;

public interface Securable {

    String encryptPassword(String password);
}
