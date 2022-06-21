package com.kvitnytskyi.electric_scooters.command.postcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDaoImpl;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.service.scooter.ScooterService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import com.kvitnytskyi.electric_scooters.util.ScooterUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewScooterCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AddNewScooterCommand.class);
    private ScooterService scooterService;
    private final String addScooterPage;
    private final String adminPage;

    public AddNewScooterCommand() {
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        addScooterPage = properties.getProperty("addNewScooterPost");
        adminPage = properties.getProperty("adminPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing additional new scooter by post command");

        String resultPage = addScooterPage;
        String scooterName = req.getParameter("scooterName");
        String scooterMark = req.getParameter("mark");
        String scooterClass = req.getParameter("scooter_class");
        String scooterCost = req.getParameter("scooterCost");
        String[] newScooterInfo = {scooterName, scooterMark, scooterClass, scooterCost};

        if (isaNewScooter(newScooterInfo)) {
            log.info("Addition new scooter");
            Scooter newScooter = ScooterUtil.createNewScooter(newScooterInfo);
            if (scooterService.addScooter(newScooter)) {
                log.info("Addition new scooter was successful");
                resultPage = adminPage;
            }
        }
        return resultPage;
    }

    private boolean isaNewScooter(String[] newScooterInfo) {
        return newScooterInfo[0] != null &&
                newScooterInfo[1] != null &&
                newScooterInfo[2] != null &&
                newScooterInfo[3] != null;
    }
}
