package com.kvitnytskyi.electric_scooters.model.receipt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Receipt {

    private long id;
    private long userId;
    private long scooterId;
    private int passport;
    private String duration;
    private RentOption rentOption;
    private OrderStatus orderStatus;
    private String comment;
    private double billCost;
    private double repairBill;

    @Override
    public String toString() {
        return "№" + id + " "
                + " To: " + duration
                + " Cost: " + billCost;
    }
}
