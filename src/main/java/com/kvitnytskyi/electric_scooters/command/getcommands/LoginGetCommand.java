package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDaoImpl;
import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDaoImpl;
import com.kvitnytskyi.electric_scooters.dao.user.UserDaoImpl;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.model.user.User;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;
import com.kvitnytskyi.electric_scooters.service.scooter.ScooterService;
import com.kvitnytskyi.electric_scooters.service.user.UserService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import com.kvitnytskyi.electric_scooters.util.ReceiptUtil;
import com.kvitnytskyi.electric_scooters.util.ScooterUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(LoginGetCommand.class);
    private static String loginPage;
    private static String mainPage;
    private final String adminPage;
    private final String managerPage;
    private final UserService userService;
    private final ScooterService scooterService;
    private final ReceiptService receiptService;

    public LoginGetCommand() {
        userService = new UserService(UserDaoImpl.getInstance());
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
        loginPage = properties.getProperty("loginPage");
        adminPage = properties.getProperty("adminPage");
        managerPage = properties.getProperty("managerPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing login page command");
        String resultPage = loginPage;
        HttpSession session = req.getSession();

        if (session.getAttribute("authorized") != null
                && session.getAttribute("authorized").equals(true)) {
            resultPage = mainPage;

            if (session.getAttribute("role") != null) {
                ScooterUtil.setFilterToSession(req);

                log.info("Return all scooters info");
                List<Scooter> scooters = scooterService.getAllAvailableScooters();
                req.setAttribute("scooters", scooters);

                if (session.getAttribute("role").equals("admin")) {
                    List<User> users = userService.getAllUsers();
                    req.setAttribute("users", users);
                    resultPage = adminPage;

                } else if (session.getAttribute("role").equals("manager")) {
                    ReceiptUtil.setRecipesAttributes(req, receiptService);
                    resultPage = managerPage;
                }
            }
            return resultPage;
        }
        log.info("Returning to login page");
        return resultPage;
    }
}
