package com.kvitnytskyi.electric_scooters.command.postcommands;

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
import com.kvitnytskyi.electric_scooters.util.UserUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private static UserService userService;
    private final ScooterService scooterService;
    private final ReceiptService receiptService;
    private static String loginPage;
    private static String mainPage;
    private static String adminPage;
    private static String managerPage;

    public LoginCommand() {
        userService = new UserService(UserDaoImpl.getInstance());
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        loginPage = properties.getProperty("loginPagePost");
        mainPage = properties.getProperty("mainPagePost");
        adminPage = properties.getProperty("adminPagePost");
        managerPage = properties.getProperty("managerPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing logging command");
        String resultPage = loginPage;
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && password != null) {
            User user = userService.getUserByCredentials(login, password);

            if (user != null && user.isStatus()) {
                UserUtil.putToSession(req, user);
                ScooterUtil.setFilterToSession(req);

                switch (user.getUserRole()) {
                    case USER:
                        log.info("Return all scooters info");
                        List<Scooter> availableScooters = scooterService.getAllAvailableScooters();
                        req.setAttribute("scooters", availableScooters);
                        log.info("Redirecting to user page");
                        resultPage = mainPage;
                        break;
                    case MANAGER:
                        log.info("Redirecting to manager page");
                        ReceiptUtil.setRecipesAttributes(req, receiptService);
                        resultPage = managerPage;
                        break;
                    case ADMIN:
                        log.info("Return all scooters info");
                        List<Scooter> scooters = scooterService.getAllScooters();
                        List<User> users = userService.getAllUsers();
                        req.setAttribute("scooters", scooters);
                        req.setAttribute("users", users);
                        log.info("Redirecting to admin page");
                        resultPage = adminPage;
                        break;
                }
            }
        }
        return resultPage;
    }
}
