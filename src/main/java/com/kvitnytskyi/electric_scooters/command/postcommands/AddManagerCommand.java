package com.kvitnytskyi.electric_scooters.command.postcommands;

import com.kvitnytskyi.electric_scooters.command.ServletCommand;
import com.kvitnytskyi.electric_scooters.dao.user.UserDaoImpl;
import com.kvitnytskyi.electric_scooters.model.user.User;
import com.kvitnytskyi.electric_scooters.service.user.UserService;
import com.kvitnytskyi.electric_scooters.util.MappingProperties;
import com.kvitnytskyi.electric_scooters.util.UserUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddManagerCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AddManagerCommand.class);
    private final String adminPage;
    private final String addManagerPage;
    private final UserService userService;

    public AddManagerCommand() {
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminPagePost");
        addManagerPage = properties.getProperty("addManagerPost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing add manager POST command");
        String resultPage = addManagerPage;
        String[] registrationFields = UserUtil.getRegistrationFields(req);

        log.info("New manager registration");
        if (isaNewManager(registrationFields)){
            User manager = UserUtil.createManger(registrationFields);
            if (userService.signUp(manager)){
                log.info("Manger registration was successful");
                resultPage = adminPage;
            }
        }
        return resultPage;
    }

    private boolean isaNewManager(String[] newUser) {
        return newUser[0] != null && newUser[1] != null
                && newUser[2] != null && newUser[3] != null
                && newUser[4] != null && newUser[5] != null
                && newUser[5].equals(newUser[4]);
    }
}
