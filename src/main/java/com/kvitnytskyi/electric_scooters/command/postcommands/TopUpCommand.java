package com.kvitnytskyi.electric_scooters.command.postcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.user.UserDaoImpl;
import com.kvitnytskyi.electric_scooters.service.user.UserService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopUpCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(TopUpCommand.class);
    private final UserService userService;
    private final String mainPage;
    private final String topUpPage;

    public TopUpCommand() {
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPagePost");
        topUpPage = properties.getProperty("topUpPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing top up POST command");
        String resultPage = topUpPage;

        if (req.getParameter("userId") != null
                && req.getParameter("balance") != null) {
            log.info("Getting user by id");
            long userId = Long.parseLong(req.getParameter("userId"));
            double balance = Double.parseDouble(req.getParameter("balance"));

            if (userService.topUpUserBalance(userId, balance)) {
                log.info("Top up was successful, redirecting to main page");
                resultPage = mainPage;
            }
        }
        return resultPage;
    }
}
