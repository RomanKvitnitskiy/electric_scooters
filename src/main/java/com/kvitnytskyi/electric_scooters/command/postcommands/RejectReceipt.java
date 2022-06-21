package com.kvitnytskyi.electric_scooters.command.postcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDaoImpl;
import com.kvitnytskyi.electric_scooters.model.receipt.OrderStatus;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RejectReceipt implements ServletCommand {

    private static final Logger log = Logger.getLogger(RejectReceipt.class);
    private final ReceiptService receiptService;
    private final String rejectPagePost;
    private final String managerPagePost;

    public RejectReceipt(){
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        rejectPagePost = properties.getProperty("rejectPagePost");
        managerPagePost = properties.getProperty("managerPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing reject receipt POST command");
        String resultPage = rejectPagePost;

        String orderId = req.getParameter("orderId");
        String comm = req.getParameter("comm");
        if (orderId != null && comm != null){
            long id = Long.parseLong(orderId);
            receiptService.setRejectCommById(id, comm, OrderStatus.REJECTED.ordinal());
            resultPage = managerPagePost;
        }
        return resultPage;
    }
}
