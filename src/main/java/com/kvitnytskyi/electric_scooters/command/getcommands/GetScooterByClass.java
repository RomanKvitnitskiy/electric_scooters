package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDaoImpl;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.service.scooter.ScooterService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetScooterByClass implements ServletCommand {

    private static final Logger log = Logger.getLogger(GetScooterByClass.class);
    private static String resultPage;
    private ScooterService scooterService;

    public GetScooterByClass() {
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("scooterByClass");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String scooterClass = req.getParameter("scooter_class");
        log.info("Getting scooter by class");

        log.info("Scooters by scooter was executed");
        List<Scooter> scootersByClass = scooterService.getScootersByClass(scooterClass);
        req.setAttribute("scootersByClassList", scootersByClass);

        return resultPage;
    }
}
