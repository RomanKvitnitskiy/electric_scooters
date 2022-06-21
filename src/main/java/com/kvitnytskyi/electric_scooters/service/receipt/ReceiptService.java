package com.kvitnytskyi.electric_scooters.service.receipt;

import com.kvitnytskyi.electric_scooters.dao.receipt.ReceiptDao;
import com.kvitnytskyi.electric_scooters.model.receipt.Receipt;
import org.apache.log4j.Logger;

import java.util.List;

public class ReceiptService {

    private static final Logger log = Logger.getLogger(ReceiptService.class);
    private final ReceiptDao receiptDao;

    public ReceiptService(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }

    public boolean createRecipe(Receipt receipt) {
        return receipt != null && receiptDao.save(receipt).getId() != -1;
    }

    public List<Receipt> getRecipesByUserIdAndStatus(long userId, int statusId) {
        log.info("Getting recipes by user id and status");
        return receiptDao.getUserRecipes(userId, statusId);
    }

    public List<Receipt> getRecipesByStatus(int statusId) {
        log.info("Getting recipes by status");
        return receiptDao.getRecipesByStatus(statusId);
    }

    public void doReceiptTransaction(long orderId, long userId, double cost, long statusId) {
        log.info("Do receipt transaction");
        receiptDao.receiptTransaction(orderId, userId, cost, statusId);
    }

    public void closeOrderById(long closeOrderId, long statusID) {
        log.info("Closing order by id");
        receiptDao.closeOrder(closeOrderId, statusID);
    }

    public Receipt getReceiptById(long receiptId) {
        log.info("Getting receipt by id");
        return receiptDao.get(receiptId);
    }

    public void returnScooterById(long returnId, int statusId) {
        log.info("Returning ordered scooter by id");
        receiptDao.returnReceipt(returnId, statusId);
    }

    public void setRejectCommById(long id, String comm, long statusId) {
        log.info("Setting reject comment by id");
        receiptDao.setRejectComment(id, comm, statusId);
    }

    public void setActiveForRepairPayment(long orderId, double repairBill, long statusId) {
        log.info("Make order active for repair payment");
        receiptDao.setActiveRepair(orderId, repairBill, statusId);
    }
}
