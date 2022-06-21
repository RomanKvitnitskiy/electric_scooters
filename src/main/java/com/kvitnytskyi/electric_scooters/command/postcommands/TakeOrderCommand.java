package com.kvitnytskyi.electric_scooters.command.postcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDaoImpl;
import com.kvitnytskyi.electric_scooters.dao.scooter.ScooterDaoImpl;
import com.kvitnytskyi.electric_scooters.model.receipt.Receipt;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;
import com.kvitnytskyi.electric_scooters.service.scooter.ScooterService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import com.kvitnytskyi.electric_scooters.util.ReceiptUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TakeOrderCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(TakeOrderCommand.class);
    private final ReceiptService receiptService;
    private final ScooterService scooterService;
    private final String mainPage;
    private final String takeOrderPage;

    public TakeOrderCommand() {
        scooterService = new ScooterService(ScooterDaoImpl.getInstance());
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPagePost");
        takeOrderPage = properties.getProperty("takeOrderPost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing take order POST command");
        String resultPage = takeOrderPage;

        String pass = req.getParameter("pass");
        String option = req.getParameter("option");
        String duration = req.getParameter("duration");
        String scooterId = req.getParameter("scooterId");
        String userId = req.getParameter("userId");
        if (isaNewOrder(pass, option, duration, scooterId, userId)) {
            log.info("Creating new order");
            Scooter scooter = scooterService.getScooterById(Long.parseLong(scooterId));
            Receipt receipt =
                    ReceiptUtil.createNewReceipt(scooter.getCost(), pass, option, duration, scooterId, userId);
            if (receiptService.createRecipe(receipt)) {
                log.info("Creating new order was successful");
                resultPage = mainPage;
            }
        }
        return resultPage;
    }

    private boolean isaNewOrder(String... param) {
        int i = 0;
        for (String s : param) {
            if (s != null) {
                ++i;
            }
        }
        return i == param.length && checkDate(param[2]);
    }

    private boolean checkDate(String date) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        return inputDate.isAfter(now);
    }
}
