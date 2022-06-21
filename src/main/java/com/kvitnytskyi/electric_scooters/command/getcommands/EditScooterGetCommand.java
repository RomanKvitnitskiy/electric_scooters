package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDaoImpl;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.service.scooter.ScooterService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditScooterGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(EditScooterGetCommand.class);
    private final ScooterService scooterService;
    private final String editScooterPage;
    private final String adminPage;

    public EditScooterGetCommand() {
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        editScooterPage = properties.getProperty("editScooter");
        adminPage = properties.getProperty("adminPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing edit scooter GET command");
        String resultPage = adminPage;
        long scooterId = Long.parseLong(req.getParameter("scooterId"));
        Scooter scooter = scooterService.getScooterById(scooterId);
        if (scooter != null){
            req.setAttribute("scooter", scooter);
            resultPage = editScooterPage;
        }
        return resultPage;
    }
}
