package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDaoImpl;
import com.kvitnytskyi.electric_scooters.dao.user.UserDaoImpl;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.model.user.User;
import com.kvitnytskyi.electric_scooters.service.scooter.ScooterService;
import com.kvitnytskyi.electric_scooters.service.user.UserService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TakeOrderGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(TakeOrderGetCommand.class);
    private final ScooterService scooterService;
    private final UserService userService;
    private final String mainPage;
    private final String takeOrderPage;

    public TakeOrderGetCommand() {
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
        takeOrderPage = properties.getProperty("takeOrder");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing take order GET command");
        String resultPage = mainPage;

        if (req.getParameter("scooterId") != null
                && req.getParameter("userId") != null){
            long scooterId = Long.parseLong(req.getParameter("scooterId"));
            long userId = Long.parseLong(req.getParameter("userId"));
            User user = userService.getUserById(userId);
            Scooter scooter = scooterService.getScooterById(scooterId);
            if (user != null && scooter != null) {
                req.setAttribute("user", user);
                req.setAttribute("scooter", scooter);
                resultPage = takeOrderPage;
            }
        }
        return resultPage;
    }
}
