package com.kvitnytskyi.electric_scooters.dao.receipt;


import com.kvitnytskyi.electric_scooters.connection.BasicConnectionPool;
import com.kvitnytskyi.electric_scooters.connection.ConnectionCreator;
import com.kvitnytskyi.electric_scooters.dao.QUERY;
import com.kvitnytskyi.electric_scooters.model.receipt.Receipt;
import com.kvitnytskyi.electric_scooters.util.ReceiptUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiptDaoImpl implements ReceiptDao {

    private static final Logger log = Logger.getLogger(ReceiptDaoImpl.class);
    private static ReceiptDaoImpl instance;
    private static BasicConnectionPool connectionPool;
    private Connection connection;

    private ReceiptDaoImpl() {
        try {
            connectionPool = BasicConnectionPool.create();
            connection = connectionPool.getConnection();
        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    public static ReceiptDaoImpl getInstance() {
        if (instance == null) {
            instance = new ReceiptDaoImpl();
        }
        return instance;
    }

    @Override
    public Receipt save(Receipt receipt) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CREATE_RECEIPT.query(), Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("save");
            statement.setLong(1, receipt.getUserId());
            statement.setLong(2, receipt.getScooterId());
            statement.setInt(3, receipt.getPassport());
            statement.setDate(4, Date.valueOf(receipt.getDuration()));
            statement.setLong(5, receipt.getOrderStatus().ordinal());
            statement.setString(6, receipt.getComment());
            statement.setDouble(7, receipt.getBillCost());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.error("Receipt creation is failed");
            } else {
                log.info("Receipt creation is successful");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Receipt.builder().id(generatedKeys.getLong(1));
                    } else {
                        log.error("Failed to create receipt, no obtained id");
                    }
                }
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return receipt;
    }

    @Override
    public void update(Receipt receipt, String[] params) {
        throw new IllegalArgumentException();
    }

    @Override
    public Receipt get(long id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_RECEIPT.query())) {
            statement.setLong(1, id);
            statement.execute();
            return getReceiptFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public void delete(Receipt receipt) {
        throw new IllegalArgumentException();
    }

    @Override
    public List<Receipt> getAll() {
        throw new IllegalArgumentException();
    }

    @Override
    public List<Receipt> getUserRecipes(long userId, int statusId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_USER_RECIPES.query())) {
            statement.setLong(1, userId);
            statement.setLong(2, statusId);
            statement.execute();
            return getRecipesFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Receipt> getRecipesByStatus(int statusId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_RECIPES_BY_STATUS.query())) {
            statement.setLong(1, statusId);
            statement.execute();
            return getRecipesFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public void receiptTransaction(long orderId, long userId, double cost, long statusId) {
        try (PreparedStatement paymentStatement =
                     connection.prepareStatement(QUERY.DO_PAYMENT.query());
             PreparedStatement statusStatement
                     = connection.prepareStatement(QUERY.SET_RECEIPT_STATUS.query())) {
            connection.setAutoCommit(false);
            paymentStatement.setDouble(1, cost);
            paymentStatement.setLong(2, userId);
            paymentStatement.execute();

            statusStatement.setLong(1, statusId);
            statusStatement.setLong(2, orderId);
            statusStatement.execute();
            connection.commit();
            log.info("Transaction was successful");
        } catch (SQLException exception) {
            try {
                log.error("Transaction was failed and did rollback");
                connection.rollback();
            } catch (SQLException e) {
                log.error("Transaction rollback was failed");
            }
        }
    }

//    @Override
//    public void repairPayment(long paymentId, long userId) {
//    }

    @Override
    public void closeOrder(long closeOrderId, long statusId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.SET_RECEIPT_STATUS.query())) {
            statement.setLong(1, statusId);
            statement.setLong(2, closeOrderId);
            statement.execute();
            log.info("Order closing was successful");
        } catch (SQLException exception) {
            log.error("Closing was failed");
        }
    }

    @Override
    public void returnReceipt(long returnId, int statusId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.SET_RECEIPT_STATUS.query())) {
            statement.setLong(1, statusId);
            statement.setLong(2, returnId);
            statement.execute();
            log.info("Returning was successful");
        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    @Override
    public void setRejectComment(long id, String comm, long statusId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.MAKE_ORDER_REJECTED.query())) {
            statement.setLong(1, statusId);
            statement.setString(2, comm);
            statement.setLong(3, id);
            statement.execute();
            log.info("Setting reject comment was successful");
        } catch (SQLException exception) {
            log.error("Setting reject comment was failed");
        }
    }

    @Override
    public void setActiveRepair(long orderId, double repairBill, long statusId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.MAKE_ORDER_REPAIR_ACTIVE.query())) {
            statement.setLong(1, statusId);
            statement.setDouble(2, repairBill);
            statement.setLong(3, orderId);
            statement.execute();
            log.info("Making order active for repair payment was successful");
        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    private List<Receipt> getRecipesFromResultSet(ResultSet resultSet) {
        List<Receipt> receipts = new ArrayList<>();
        try {
            while (resultSet.next()) {
                receipts.add(buildReceiptFromResultSet(resultSet));
                log.info("Receipt was found and packed in object");
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return receipts;
    }

    private Receipt getReceiptFromResultSet(ResultSet resultSet) {
        log.info("Getting receipt from result set");
        try {
            if (resultSet.next()) {
                return buildReceiptFromResultSet(resultSet);
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    private Receipt buildReceiptFromResultSet(ResultSet resultSet) throws SQLException {
        log.info("Building receipt object");
        return ReceiptUtil.createReceiptFromResultSet(resultSet);
    }
}
