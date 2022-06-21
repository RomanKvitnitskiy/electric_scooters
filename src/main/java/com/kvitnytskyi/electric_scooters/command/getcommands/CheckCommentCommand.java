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

public class CheckCommentCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(CheckCommentCommand.class);
    private final ReceiptService receiptService;
    private final ScooterService scooterService;
    private final UserService userService;
    private final String userCabinet;
    private final String commentPage;

    public CheckCommentCommand() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        userCabinet = properties.getProperty("userCabinet");
        commentPage = properties.getProperty("commentPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing check comment GET command");
        String resultPage = userCabinet;
        if (req.getParameter("orderId") != null) {
            log.info("Getting info about user order");
            long orderId = Long.parseLong(req.getParameter("orderId"));
            Receipt receiptById = receiptService.getReceiptById(orderId);
            User userByReceipt = userService.getUserById(receiptById.getUserId());
            Scooter scooterByReceipt = scooterService.getScooterById(receiptById.getScooterId());

            if (userByReceipt != null && scooterByReceipt != null){
                req.setAttribute("userByReceipt", userByReceipt);
                req.setAttribute("scooterByReceipt", scooterByReceipt);
                req.setAttribute("order", receiptById);
                resultPage = commentPage;
            }
        }
        return resultPage;
    }
}