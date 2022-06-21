package com.kvitnytskyi.electric_scooters.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private boolean status;
    private UserRole userRole;
    private double balance;

    @Override
    public String toString() {
        return name + ' ' + surname + ' ' + email + ' ' /*+ userRole*/ + ' ' + ((status) ? "Active" : "Blocked");
    }
}
