package com.kvitnytskyi.electric_scooters.command.manager;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDaoImpl;
import com.kvitnytskyi.electric_scooters.model.receipt.OrderStatus;
import com.kvitnytskyi.electric_scooters.model.receipt.Receipt;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckReceipt implements ServletCommand {

    private static final Logger log = Logger.getLogger(CheckReceipt.class);
    private final ReceiptService receiptService;
    private final String managerPagePost;

    public CheckReceipt() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        managerPagePost = properties.getProperty("managerPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Execute check receipt POST command");
        if (req.getParameter("approve") != null) {
            log.info("Approving order by id");
            long approveId = Long.parseLong(req.getParameter("approve"));
            approveReceipt(approveId);
        }
        else if (req.getParameter("closeOrder") != null) {
            log.info("Closing the order");
            long closeOrderId = Long.parseLong(req.getParameter("closeOrder"));
            closeOrder(closeOrderId);
        } else {
            log.info("Check receipt command was failed, returning to manager page");
        }
        return managerPagePost;
    }

    private void approveReceipt(long approveId) {
        log.info("Approving order");
        Receipt receipt = receiptService.getReceiptById(approveId);
        receiptService.doReceiptTransaction(approveId, receipt.getUserId(),
                receipt.getBillCost(), OrderStatus.ACTIVE.ordinal());
        log.info("Approving was successful");
    }

    private void closeOrder(long closeOrderId) {
        log.info("Closing the order");
        receiptService.closeOrderById(closeOrderId, OrderStatus.CLOSED.ordinal());
        log.info("The closure was successful");
    }
}
