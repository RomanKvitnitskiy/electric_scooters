package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.user.UserDaoImpl;
import com.kvitnytskyi.electric_scooters.model.user.User;
import com.kvitnytskyi.electric_scooters.service.user.UserService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopUpGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(TopUpGetCommand.class);
    private final UserService userService;
    private final String userCabinet;
    private final String topUpPage;

    public TopUpGetCommand() {
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        userCabinet = properties.getProperty("userCabinet");
        topUpPage = properties.getProperty("topUpPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing top up GET command");
        String resultPage = userCabinet;

        if (req.getParameter("userId") != null) {
            log.info("Getting user by id and redirect to top up page");
            long userId = Long.parseLong(req.getParameter("userId"));
            User user = userService.getUserById(userId);
            if (user != null) {
                req.setAttribute("user", user);
                resultPage = topUpPage;
            }
        }
        return resultPage;
    }
}
