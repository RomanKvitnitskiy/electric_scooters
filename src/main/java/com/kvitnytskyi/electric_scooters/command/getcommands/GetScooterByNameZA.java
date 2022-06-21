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

public class GetScooterByNameZA implements ServletCommand {

    private static final Logger log = Logger.getLogger(GetScooterByNameZA.class);
    private final String resultPage;
    private final ScooterService scooterService;

    public GetScooterByNameZA() {
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("scooterByName");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing get scooter by name z-a command");
        List<Scooter> scooterNameZAList = scooterService.getScootersByNameZA();
        req.setAttribute("scooterByNameZA", scooterNameZAList);
        return resultPage;
    }
}
