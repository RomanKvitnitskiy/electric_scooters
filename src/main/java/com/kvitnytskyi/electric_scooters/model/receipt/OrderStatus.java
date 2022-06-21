package com.kvitnytskyi.electric_scooters.model.receipt;

public enum OrderStatus {

    PENDING("Pending"),
    REJECTED("Rejected"),
    RETURNED("Returned"),
    ACTIVE("Active"),
    CLOSED("Closed");

    OrderStatus(String status) {
    }
}
