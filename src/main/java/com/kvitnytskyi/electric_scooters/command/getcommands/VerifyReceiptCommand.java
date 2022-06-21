package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDaoImpl;
import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDaoImpl;
import com.kvitnytskyi.electric_scooters.dao.user.UserDaoImpl;
import com.kvitnytskyi.electric_scooters.model.receipt.Receipt;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.model.user.User;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;
import com.kvitnytskyi.electric_scooters.service.scooter.ScooterService;
import com.kvitnytskyi.electric_scooters.service.user.UserService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyReceiptCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(VerifyReceiptCommand.class);
    private final ReceiptService receiptService;
    private final ScooterService scooterService;
    private final UserService userService;
    private final String managerPage;
    private final String verifyPage;

    public VerifyReceiptCommand(){
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        managerPage = properties.getProperty("managerPage");
        verifyPage = properties.getProperty("verifyPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing verify receipt GET command");
        String resultPage = managerPage;
        if (req.getParameter("orderId") != null){
            log.info("Getting info about order to verify it");
            long orderId = Long.parseLong(req.getParameter("orderId"));
            Receipt receipt = receiptService.getReceiptById(orderId);
            Scooter scooter = scooterService.getScooterById(receipt.getScooterId());
            User user = userService.getUserById(receipt.getUserId());

            req.setAttribute("orderUser", user);
            req.setAttribute("orderScooter", scooter);
            req.setAttribute("orderVerify", receipt);
            resultPage = verifyPage;
        }
        return resultPage;
    }
}
