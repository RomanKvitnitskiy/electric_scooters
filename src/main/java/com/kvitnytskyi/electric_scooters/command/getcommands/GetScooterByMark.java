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

public class GetScooterByMark implements ServletCommand {

    private static final Logger log = Logger.getLogger(GetScooterByMark.class);
    private static String resultPage;
    private ScooterService scooterService;

    public GetScooterByMark() {
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("scooterByMark");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String mark = req.getParameter("mark");
        log.info("Getting scooter by mark");

        log.info("Scooters by mark was executed");
        List<Scooter> scootersByMark = scooterService.getScootersByMark(mark);
        req.setAttribute("scootersByMarkList", scootersByMark);
        req.setAttribute("markName", mark);

        return resultPage;
    }
}
