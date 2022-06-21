package com.kvitnytskyi.electric_scooters.command.postcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDaoImpl;
import com.kvitnytskyi.electric_scooters.model.receipt.OrderStatus;
import com.kvitnytskyi.electric_scooters.model.receipt.Receipt;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReceiptAction implements ServletCommand {

    private static final Logger log = Logger.getLogger(ReceiptAction.class);
    private final ReceiptService receiptService;
    private final String mainPagePost;

    public ReceiptAction() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPagePost = properties.getProperty("mainPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Execute receipt action POST command");
        if (req.getParameter("return") != null) {
            log.info("Returning ordered scooter");
            long returnId = Long.parseLong(req.getParameter("return"));
            returnScooter(returnId);
        } else if (req.getParameter("repair") != null) {
            log.info("Do repair payment");
            long repairId = Long.parseLong(req.getParameter("repair"));
            repairPayment(repairId);
        } else {
            log.info("Receipt action was failed, returning to main page");
        }
        return mainPagePost;
    }

    private void returnScooter(long returnId) {
        log.info("Returning ordered scooter by id");
        receiptService.returnScooterById(returnId, OrderStatus.RETURNED.ordinal());
        log.info("Returning was successful");
    }

    private void repairPayment(long repairId) {
        log.info("Doing repair payment");
        Receipt broken = receiptService.getReceiptById(repairId);
        receiptService.doReceiptTransaction(repairId, broken.getUserId(),
                broken.getRepairBill(), OrderStatus.CLOSED.ordinal());
        log.debug("Repair payment was successful");
    }
}
