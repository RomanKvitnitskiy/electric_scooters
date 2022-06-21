package com.kvitnytskyi.electric_scooters.command.postcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDaoImpl;
import com.kvitnytskyi.electric_scooters.model.receipt.OrderStatus;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyReceipt implements ServletCommand {

    private static final Logger log = Logger.getLogger(VerifyReceipt.class);
    private final ReceiptService receiptService;
    private final String verifyPagePost;
    private final String managerPagePost;

    public VerifyReceipt() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        verifyPagePost = properties.getProperty("verifyPagePost");
        managerPagePost = properties.getProperty("managerPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing verify order POST command");
        String resultPage = verifyPagePost;
        if (req.getParameter("repair") != null && req.getParameter("orderId") != null) {
            log.info("Make order closed or active for repair payment");
            double repairBill = Double.parseDouble(req.getParameter("repair"));
            long orderId = Long.parseLong(req.getParameter("orderId"));

            if (repairBill > 0) {
                log.info("Making order active for repair payment");
                receiptService.setActiveForRepairPayment(orderId, repairBill, OrderStatus.ACTIVE.ordinal());
                log.info("Order was made active successfully");
            } else {
                log.info("Making order closed");
                receiptService.closeOrderById(orderId, OrderStatus.CLOSED.ordinal());
                log.info("Order closing was successful");
            }
            resultPage = managerPagePost;
        }
        return resultPage;
    }
}
