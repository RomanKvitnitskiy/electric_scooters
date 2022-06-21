package com.kvitnytskyi.electric_scooters.command.admin;

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
import java.util.List;

public class AdminPage implements ServletCommand {

    private static final Logger log = Logger.getLogger(AdminPage.class);
    private final ScooterService scooterService;
    private final UserService userService;
    private final String adminPage;

    public AdminPage() {
        userService = new UserService(UserDaoImpl.getInstance());
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing admin page get command");
        List<Scooter> scooters = scooterService.getAllScooters();
        req.setAttribute("scooters", scooters);
        List<User> users = userService.getAllUsers();
        req.setAttribute("users", users);
        return adminPage;
    }
}
