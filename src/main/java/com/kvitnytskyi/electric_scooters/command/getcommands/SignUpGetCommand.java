package com.kvitnytskyi.electric_scooters.command.getcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(SignUpGetCommand.class);
    private final String signupPage;
    private final String mainPage;

    public SignUpGetCommand() {
        MappingProperties properties = MappingProperties.getInstance();

        signupPage = properties.getProperty("signupPage");
        mainPage = properties.getProperty("mainPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing signup get command");

        Boolean authorized = (Boolean) req.getSession().getAttribute("authorized");
        if (authorized != null && authorized) {
            return mainPage;
        }
        return signupPage;
    }
}
