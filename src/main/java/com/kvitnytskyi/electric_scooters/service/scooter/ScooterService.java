package com.kvitnytskyi.electric_scooters.service.scooter;

import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDao;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.model.scooter.ScooterClass;
import com.kvitnytskyi.electric_scooters.model.scooter.ScooterMark;
import org.apache.log4j.Logger;

import java.util.List;

public class ScooterService {

    private static final Logger log = Logger.getLogger(ScooterService.class);
    private final ScooterDao scooterDao;

    public ScooterService(ScooterDao scooterDao) {
        this.scooterDao = scooterDao;
    }

    public List<Scooter> getScootersByMark(String mark) {
        log.info("Getting scooters by mark with scooter service");
        long markId = ScooterMark.valueOf(mark).ordinal();
        return scooterDao.getScootersByMark(markId);
    }

    public List<Scooter> getScootersByClass(String scooter_class) {
        log.info("Getting scooters by class with scooter service");
        long classId = ScooterClass.valueOf(scooter_class).ordinal();
        return scooterDao.getScootersByClass(classId);
    }

    public boolean addScooter(Scooter newScooter) {
        log.info("Add new scooter");
        return newScooter != null && scooterDao.save(newScooter).getId() != -1;
    }

    public List<Scooter> getAllScooters() {
        log.info("Getting all scooters");
        return scooterDao.getAll();
    }

    public List<Scooter> getAllAvailableScooters() {
        log.info("Getting all available scooters");
        return scooterDao.getAllAvailableScooters();
    }

    public Scooter getScooterById(long scooterId) {
        log.info("Getting scooter by id");
        return scooterDao.get(scooterId);
    }

    public void editScooter(Scooter scooterById, String[] params) {
        log.info("Edit scooter by id with some parameters");
        scooterDao.update(scooterById, params);
    }

    public void deleteScooter(Scooter scooter) {
        log.info("Processing car delete");
        scooterDao.delete(scooter);
    }

    public List<Scooter> getScootersByNameAZ() {
        log.info("Sorting scooter by name A-z");
        return scooterDao.getScootersByNameAZ();
    }

    public List<Scooter> getScootersByNameZA() {
        log.info("Sorting scooter by name z-A");
        return scooterDao.getScootersByNameZA();
    }

    public List<Scooter> getScootersByCostEC() {
        log.info("Sorting scooter by cost E-c");
        return scooterDao.getScootersByCostEC();
    }

    public List<Scooter> getScootersByCostCE() {
        log.info("Sorting scooter by cost c-E");
        return scooterDao.getScootersByCostCE();
    }
}
