package com.kvitnytskyi.electric_scooters.util;

import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.model.scooter.ScooterClass;
import com.kvitnytskyi.electric_scooters.model.scooter.ScooterMark;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScooterUtil {

    public static Scooter createScooterFromResultSet(ResultSet resultSet) throws SQLException {
        return Scooter.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("scooters_name"))
                .cost(resultSet.getDouble("scooters_cost"))
                .scooterClass(ScooterClass.values()[resultSet.getInt("class_id")])
                .scooterMark(ScooterMark.values()[resultSet.getInt("mark_id")])
                .status(resultSet.getBoolean("scooters_status"))
                .build();
    }

    public static void setFilterToSession(HttpServletRequest req) {
        req.getSession().setAttribute("scooterMarks", ScooterMark.values());
        req.getSession().setAttribute("scooterClass", ScooterClass.values());
    }

    public static Scooter createNewScooter(String[] newScooterInfo) {
        return Scooter.builder()
                .name(newScooterInfo[0])
                .scooterMark(ScooterMark.valueOf(newScooterInfo[1]))
                .scooterClass(ScooterClass.valueOf(newScooterInfo[2]))
                .cost(Double.parseDouble(newScooterInfo[3]))
                .status(true)
                .build();
    }
}
