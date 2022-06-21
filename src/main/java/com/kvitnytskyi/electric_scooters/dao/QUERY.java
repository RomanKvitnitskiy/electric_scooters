package com.kvitnytskyi.electric_scooters.dao;

public enum QUERY {

    // User queries
    GET_USER("SELECT * FROM user WHERE id=?"),
    GET_ALL_USERS("SELECT * FROM user WHERE role_id = 1"),
    CREATE_USER("INSERT INTO user SET user_name = ?," +
            "user_surname = ?," +
            "email = ?," +
            "login = ?," +
            "password = ?," +
            "user_status = ?," +
            "role_id = ?," +
            "user_balance = ?"),
    GET_USER_BY_LOGIN_AND_PASSWORD("SELECT * FROM user WHERE " +
            "login = ? " +
            "AND password = ? ;"),
    GET_PASSWORD(
            "SELECT * FROM user WHERE " +
                    "password = ? ;"
    ),
    CHANGE_USER_STATUS("UPDATE user SET " +
            "user_status = ? " +
            "WHERE id = ?"),
    CHECK_USER("SELECT * FROM user " +
            "WHERE login = ?"),
    TOP_UP("UPDATE user " +
            "SET user_balance = user_balance + ? " +
            "WHERE id = ?"),

    // Scooter queries
    CREATE_SCOOTER("INSERT INTO scooters SET " +
            "scooters_name = ?, " +
            "class_id = ?, " +
            "mark_id = ?, " +
            "scooters_status = ?, " +
            "scooters_cost = ?"),
    GET_SCOOTER("SELECT * FROM scooters WHERE id = ?"),
    GET_ALL_SCOOTER("SELECT * FROM scooters"),
    GET_ALL_AVAILABLE_SCOOTER("SELECT * FROM scooters " +
            "WHERE scooters.id != ALL(SELECT scooters_id FROM report)"),
    GET_SCOOTER_BY_MARK("SELECT * FROM scooters WHERE mark_id = ?"),
    GET_SCOOTER_BY_CLASS("SELECT * FROM scooters WHERE class_id = ?"),
    GET_SORTED_SCOOTER_BY_NAME_A_Z("SELECT * FROM scooters ORDER BY scooters_name"),
    GET_SORTED_SCOOTER_BY_NAME_Z_A("SELECT * FROM scooters ORDER BY scooters_name DESC"),
    GET_SORTED_SCOOTER_BY_COST_C_E("SELECT * FROM scooters ORDER BY scooters_cost"),
    GET_SORTED_SCOOTER_BY_COST_E_C("SELECT * FROM scooters ORDER BY scooters_cost DESC"),
    UPDATE_SCOOTER_INFO("UPDATE scooters SET " +
            "scooters_name = ?, " +
            "class_id = ?, " +
            "mark_id = ?, " +
            "scooters_cost = ? " +
            "WHERE id = ?"),
    DELETE_SCOOTER("DELETE FROM scooters WHERE id = ?"),

    // Receipt queries
    GET_RECEIPT("SELECT * FROM report WHERE id = ?"),
    CREATE_RECEIPT("INSERT INTO report SET " +
            "user_id = ?, " +
            "scooters_id = ?, " +
            "passport = ?, " +
            "rent_duration = ?, " +
            "status_id = ?, " +
            "report_comm = ?, " +
            "bill_cost = ? "),
    GET_USER_RECIPES("SELECT * FROM report " +
            "WHERE user_id = ? " +
            "AND status_id = ?"),
    GET_RECIPES_BY_STATUS("SELECT * FROM report " +
            "WHERE status_id = ?"),
    DO_PAYMENT("UPDATE user SET " +
            "user_balance = user_balance - ? " +
            "WHERE id = ?"),
    SET_RECEIPT_STATUS("UPDATE report SET " +
            "status_id = ? " +
            "WHERE id = ?"),
    MAKE_ORDER_REJECTED("UPDATE report SET " +
            "status_id = ?, " +
            "report_comm = ? " +
            "WHERE id = ?"),
    MAKE_ORDER_REPAIR_ACTIVE("UPDATE report SET " +
            "status_id = ?, " +
            "remont_bill = ? " +
            "WHERE id = ?");


    private final String query;

    QUERY(String query) {
        this.query = query;
    }

    public String query() {
        return query;
    }
}
