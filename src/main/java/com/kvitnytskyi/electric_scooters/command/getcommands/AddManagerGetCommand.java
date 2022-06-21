package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddManagerGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AddManagerGetCommand.class);
    private final String addManagerPage;

    public AddManagerGetCommand(){
        MappingProperties properties = MappingProperties.getInstance();

        addManagerPage = properties.getProperty("addManager");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing add new manager GET command");
        return addManagerPage;
    }
}
