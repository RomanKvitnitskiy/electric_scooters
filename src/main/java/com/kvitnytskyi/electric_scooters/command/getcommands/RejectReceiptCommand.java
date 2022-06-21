package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDaoImpl;
import com.kvitnytskyi.electric_scooters.model.receipt.Receipt;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RejectReceiptCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(RejectReceiptCommand.class);
    private final ReceiptService receiptService;
    private final String managerPage;
    private final String rejectPage;

    public RejectReceiptCommand() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        managerPage = properties.getProperty("managerPage");
        rejectPage = properties.getProperty("rejectPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing reject receipt GET command");
        String resultPage = managerPage;
        if (req.getParameter("orderId") != null) {
            long orderId = Long.parseLong(req.getParameter("orderId"));
            Receipt receipt = receiptService.getReceiptById(orderId);
            resultPage = rejectPage;
            req.setAttribute("order", receipt);
        }
        return resultPage;
    }
}
