package com.kvitnytskyi.electric_scooters.command.admin;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDaoImpl;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.service.scooter.ScooterService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteScooter implements ServletCommand {

    private static final Logger log = Logger.getLogger(DeleteScooter.class);
    private final String deleteScooter;
    private final ScooterService scooterService;

    public DeleteScooter() {
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        deleteScooter = properties.getProperty("deleteScooterPost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing delete car command");
        long scooterId = Long.parseLong(req.getParameter("scooterId"));
        Scooter scooter = scooterService.getScooterById(scooterId);
        scooterService.deleteScooter(scooter);
        log.info("Scooter deleting was successful");
        return deleteScooter;
    }
}
