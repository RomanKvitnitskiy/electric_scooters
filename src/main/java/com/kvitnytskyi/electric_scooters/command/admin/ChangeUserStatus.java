package com.kvitnytskyi.electric_scooters.command.admin;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.user.UserDaoImpl;
import com.kvitnytskyi.electric_scooters.model.user.User;
import com.kvitnytskyi.electric_scooters.service.user.UserService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserStatus implements ServletCommand {

    private static final Logger log = Logger.getLogger(ChangeUserStatus.class);
    private final UserService userService;
    private final String adminPage;

    public ChangeUserStatus() {
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing change user status command");
        if (req.getParameter("userId") != null) {
            long userId = Long.parseLong(req.getParameter("userId"));
            User user = userService.getUserById(userId);
            boolean status = !user.isStatus();
            if (userService.changeUserStatus(userId, status)){
                log.info("Changing status was successful");
            }
        }
        return adminPage;
    }
}
