package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(LogoutGetCommand.class);
    private final String loginPage;

    public LogoutGetCommand() {
        MappingProperties properties = MappingProperties.getInstance();

        loginPage = properties.getProperty("loginPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing logout command");
        req.getSession().invalidate();
        return loginPage;
    }
}
