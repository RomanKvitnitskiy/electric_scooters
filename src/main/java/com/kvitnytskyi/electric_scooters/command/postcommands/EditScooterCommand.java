package com.kvitnytskyi.electric_scooters.command.postcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDaoImpl;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.service.scooter.ScooterService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditScooterCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(EditScooterCommand.class);
    private final ScooterService scooterService;
    private final String adminPage;
    private final String editScooterPage;

    public EditScooterCommand() {
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminPagePost");
        editScooterPage = properties.getProperty("editScooterPost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing edit scooter POST command");
        String resultPage = editScooterPage;

        String scooterName = req.getParameter("scooterName");
        String scooterClass = req.getParameter("scooterClass");
        String scooterMark = req.getParameter("scooterMark");
        String scooterCost = req.getParameter("scooterCost");
        long scooterId = Long.parseLong(req.getParameter("scooterId"));
        String[] params = {scooterName, scooterClass, scooterMark, scooterCost};
        Scooter scooterById = scooterService.getScooterById(scooterId);
        log.info("Processing update scooter");
        if (scooterById != null) {
            log.info("Scooter update start");
            scooterService.editScooter(scooterById, params);
            resultPage = adminPage;
        }
        return resultPage;
    }
}
