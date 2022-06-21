package com.kvitnytskyi.electric_scooters.dao.receipt;

import com.kvitnytskyi.electric_scooters.dao.DAO;
import com.kvitnytskyi.electric_scooters.model.receipt.Receipt;

import java.util.List;

public interface ReceiptDao extends DAO<Receipt> {

    List<Receipt> getUserRecipes(long userId, int statusId);

    List<Receipt> getRecipesByStatus(int statusId);

    void receiptTransaction(long orderId, long userId, double cost, long statusId);

//    void repairPayment(long paymentId, long userId);

    void closeOrder(long closeOrderId, long userId);

    void returnReceipt(long returnId, int statusId);

    void setRejectComment(long id, String comm, long statusId);

    void setActiveRepair(long orderId, double repairBill, long statusId);
}
