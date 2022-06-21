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

public class GetScooterByCostCE implements ServletCommand {

    private static final Logger log = Logger.getLogger(GetScooterByCostCE.class);
    private final String resultPage;
    private final ScooterService scooterService;

    public GetScooterByCostCE(){
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("scooterByCost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing get scooter by cost c-E command");
        List<Scooter> scooterCostCEList = scooterService.getScootersByCostCE();
        req.setAttribute("scooterByCostCE", scooterCostCEList);
        return resultPage;
    }
}
