package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewScooterGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AddNewScooterGetCommand.class);
    private final String resultPage;

    public AddNewScooterGetCommand() {
        MappingProperties properties = MappingProperties.getInstance();
        resultPage = properties.getProperty("addNewScooter");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing additional new scooter by GET command");
        return resultPage;
    }
}
