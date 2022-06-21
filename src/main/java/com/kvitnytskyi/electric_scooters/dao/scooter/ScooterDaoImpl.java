package com.kvitnytskyi.electric_scooters.dao.scooter;

import com.kvitnytskyi.electric_scooters.connection.BasicConnectionPool;
import com.kvitnytskyi.electric_scooters.connection.ConnectionCreator;
import com.kvitnytskyi.electric_scooters.dao.QUERY;
import com.kvitnytskyi.electric_scooters.model.scooter.Scooter;
import com.kvitnytskyi.electric_scooters.model.scooter.ScooterClass;
import com.kvitnytskyi.electric_scooters.model.scooter.ScooterMark;
import com.kvitnytskyi.electric_scooters.util.ScooterUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScooterDaoImpl implements ScooterDao{

    private static final Logger log = Logger.getLogger(ScooterDaoImpl.class);
    private static ScooterDaoImpl instance;
    private Connection connection;
    private BasicConnectionPool connectionPool;

    private ScooterDaoImpl() {
        try {
            connectionPool = BasicConnectionPool.create();
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public static ScooterDaoImpl getInstance() {
        if (instance == null) {
            instance = new ScooterDaoImpl();
        }
        return instance;
    }

    @Override
    public Scooter save(Scooter scooter) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CREATE_SCOOTER.query()
                             , Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, scooter.getName());
            statement.setLong(2, scooter.getScooterClass().ordinal());
            statement.setLong(3, scooter.getScooterMark().ordinal());
            statement.setBoolean(4, scooter.isStatus());
            statement.setDouble(5, scooter.getCost());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.error("Scooter wasn't created");
            } else {
                log.info("Scooter creation was successful");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Scooter.builder().id(generatedKeys.getLong(1));
                    } else {
                        log.error("Failed to create scooter, no obtained id");
                    }
                }
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return scooter;
    }

    @Override
    public void update(Scooter scooter, String[] params) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.UPDATE_SCOOTER_INFO.query())) {

            statement.setString(1, params[0]);
            statement.setLong(2, ScooterClass.valueOf(params[1]).ordinal());
            statement.setLong(3, ScooterMark.valueOf(params[2]).ordinal());
            statement.setDouble(4, Double.parseDouble(params[3]));
            statement.setLong(5, scooter.getId());
            statement.execute();

        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    @Override
    public Scooter get(long id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SCOOTER.query())) {
            statement.setLong(1, id);
            statement.execute();
            return getScooterFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public void delete(Scooter scooter) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.DELETE_SCOOTER.query())) {
            statement.setLong(1, scooter.getId());
            statement.execute();
        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    @Override
    public List<Scooter> getAll() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(QUERY.GET_ALL_SCOOTER.query());
            return getScootersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    private List<Scooter> getScootersFromResultSet(ResultSet resultSet) {
        List<Scooter> scooterList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                scooterList.add(buildScooterFromResultSet(resultSet));
                log.info("Scooter was found and packed in object");
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return scooterList;
    }

    private Scooter getScooterFromResultSet(ResultSet resultSet) {
        log.info("Getting scooter from result set");
        try {
            if (resultSet.next()) {
                return buildScooterFromResultSet(resultSet);
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        log.info("No scooter was found");
        return null;
    }

    private Scooter buildScooterFromResultSet(ResultSet resultSet) throws SQLException {
        log.info("Building scooter object");
        return ScooterUtil.createScooterFromResultSet(resultSet);
    }

    @Override
    public List<Scooter> getScootersByMark(Long markId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SCOOTER_BY_MARK.query())) {
            statement.setLong(1, markId);
            statement.execute();
            return getScootersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Scooter> getScootersByClass(long classId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SCOOTER_BY_CLASS.query())) {
            statement.setLong(1, classId);
            statement.execute();
            return getScootersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Scooter> getScootersByNameAZ() {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SORTED_SCOOTER_BY_NAME_A_Z.query())){
            statement.execute();
            return getScootersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Scooter> getScootersByNameZA() {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SORTED_SCOOTER_BY_NAME_Z_A.query())){
            statement.execute();
            return getScootersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Scooter> getScootersByCostEC() {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SORTED_SCOOTER_BY_COST_E_C.query())){
            statement.execute();
            return getScootersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Scooter> getScootersByCostCE() {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.GET_SORTED_SCOOTER_BY_COST_C_E.query())){
            statement.execute();
            return getScootersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Scooter> getAllAvailableScooters() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(QUERY.GET_ALL_AVAILABLE_SCOOTER.query());
            return getScootersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }
}
