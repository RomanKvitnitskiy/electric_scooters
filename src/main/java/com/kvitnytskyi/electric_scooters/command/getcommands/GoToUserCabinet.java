package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDaoImpl;
import com.kvitnytskyi.electric_scooters.dao.user.UserDaoImpl;
import com.kvitnytskyi.electric_scooters.model.user.User;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;
import com.kvitnytskyi.electric_scooters.service.user.UserService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import com.kvitnytskyi.electric_scooters.util.ReceiptUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToUserCabinet implements ServletCommand {

    private static final Logger log = Logger.getLogger(GoToUserCabinet.class);
    private final UserService userService;
    private final ReceiptService receiptService;
    private final String mainPage;
    private final String userCabinet;

    public GoToUserCabinet() {
        userService = new UserService(UserDaoImpl.getInstance());
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
        userCabinet = properties.getProperty("userCabinet");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Execute go to user cabinet command");
        String resultPage = mainPage;

        long userId = Long.parseLong(req.getParameter("userId"));
        User userById = userService.getUserById(userId);

        if (userById != null) {
            log.info("Redirecting to user cabinet");
            ReceiptUtil.setRecipesAttributes(req, receiptService, userId);
            resultPage = userCabinet;
        }
        return resultPage;
    }
}
