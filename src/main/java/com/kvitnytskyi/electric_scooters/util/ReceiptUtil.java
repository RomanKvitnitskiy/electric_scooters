package com.kvitnytskyi.electric_scooters.util;

import com.kvitnytskyi.electric_scooters.model.receipt.OrderStatus;
import com.kvitnytskyi.electric_scooters.model.receipt.Receipt;
import com.kvitnytskyi.electric_scooters.service.receipt.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReceiptUtil {

    public static Receipt createNewReceipt(double cost, String... param) {
        return Receipt.builder()
                .passport(Integer.parseInt(param[0]))
                .duration(param[2])
                .scooterId(Long.parseLong(param[3]))
                .userId(Long.parseLong(param[4]))
                .billCost(cost)
                .repairBill(0)
                .comment("")
                .orderStatus(OrderStatus.PENDING)
                .build();
    }

    public static Receipt createReceiptFromResultSet(ResultSet resultSet) throws SQLException {
        return Receipt.builder()
                .id(resultSet.getInt("id"))
                .userId(resultSet.getInt("user_id"))
                .scooterId(resultSet.getInt("scooters_id"))
                .passport(resultSet.getInt("passport"))
                .duration(String.valueOf(resultSet.getDate("rent_duration"))) // TODO make useful variable type
                .orderStatus(OrderStatus.values()[resultSet.getInt("status_id")])
                .comment(resultSet.getString("report_comm"))
                .billCost(resultSet.getDouble("bill_cost"))
                .build();
    }

    public static void setRecipesAttributes(HttpServletRequest req, ReceiptService receiptService, long userId) {
        List<Receipt> pending =
                receiptService.getRecipesByUserIdAndStatus(userId, OrderStatus.PENDING.ordinal());
        List<Receipt> active =
                receiptService.getRecipesByUserIdAndStatus(userId, OrderStatus.ACTIVE.ordinal());
        List<Receipt> rejected =
                receiptService.getRecipesByUserIdAndStatus(userId, OrderStatus.REJECTED.ordinal());
        List<Receipt> closed =
                receiptService.getRecipesByUserIdAndStatus(userId, OrderStatus.CLOSED.ordinal());
        req.setAttribute("pending", pending);
        req.setAttribute("active", active);
        req.setAttribute("rejected", rejected);
        req.setAttribute("closed", closed);
    }

    public static void setRecipesAttributes(HttpServletRequest req, ReceiptService receiptService) {
        List<Receipt> pendingRecipes =
                receiptService.getRecipesByStatus(OrderStatus.PENDING.ordinal());
        List<Receipt> activeRecipes =
                receiptService.getRecipesByStatus(OrderStatus.ACTIVE.ordinal());
        List<Receipt> returnedRecipes =
                receiptService.getRecipesByStatus(OrderStatus.RETURNED.ordinal());
        List<Receipt> closedRecipes =
                receiptService.getRecipesByStatus(OrderStatus.CLOSED.ordinal());

        req.setAttribute("pendingRecipes", pendingRecipes);
        req.setAttribute("paidRecipes", activeRecipes);
        req.setAttribute("returnedRecipes", returnedRecipes);
        req.setAttribute("closedRecipes", closedRecipes);
    }
}
