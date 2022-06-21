package com.kvitnytskyi.electric_scooters.dao.scooter;

import com.kvitnytskyi.electric_scooters.dao.DAO;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;

import java.util.List;

public interface ScooterDao extends DAO<Scooter> {

    List<Scooter> getScootersByMark(Long markId);

    List<Scooter> getScootersByClass(long classId);

    List<Scooter> getScootersByNameAZ();

    List<Scooter> getScootersByNameZA();

    List<Scooter> getScootersByCostEC();

    List<Scooter> getScootersByCostCE();

    List<Scooter> getAllAvailableScooters();
}
