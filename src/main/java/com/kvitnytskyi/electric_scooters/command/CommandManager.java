package com.kvitnytskyi.electric_scooters.command;

import com.kvitnytskyi.electric_scooters.command.admin.AdminPage;
import com.kvitnytskyi.electric_scooters.command.admin.ChangeUserStatus;
import com.kvitnytskyi.electric_scooters.command.admin.DeleteScooter;
import com.kvitnytskyi.electric_scooters.command.getcommands.*;
import com.kvitnytskyi.electric_scooters.command.manager.CheckReceipt;
import com.kvitnytskyi.electric_scooters.command.manager.ManagerPage;
import com.kvitnytskyi.electric_scooters.command.postcommands.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    private final static Logger log = Logger.getLogger(CommandManager.class);
    private final Map<String, ServletCommand> getCommands;
    private final Map<String, ServletCommand> postCommands;

    public CommandManager() {
        getCommands = new ConcurrentHashMap<>();
        postCommands = new ConcurrentHashMap<>();

        log.info("GET commands are loaded");
        getCommands.put("/login", new LoginGetCommand());
        getCommands.put("/signup", new SignUpGetCommand());
        getCommands.put("/admin", new AdminPage());
        getCommands.put("/manager", new ManagerPage());
        getCommands.put("/logout", new LogoutGetCommand());
        getCommands.put("/getScooterByMark", new GetScooterByMark());
        getCommands.put("/getScooterByClass", new GetScooterByClass());
        getCommands.put("/getScooterByNameAZ", new GetScooterByNameAZ());
        getCommands.put("/getScooterByNameZA", new GetScooterByNameZA());
        getCommands.put("/getScooterByCostEC", new GetScooterByCostEC());
        getCommands.put("/getScooterByCostCE", new GetScooterByCostCE());
        getCommands.put("/addNewScooter", new AddNewScooterGetCommand());
        getCommands.put("/editScooter", new EditScooterGetCommand());
        getCommands.put("/addManager", new AddManagerGetCommand());
        getCommands.put("/takeOrder", new TakeOrderGetCommand());
        getCommands.put("/cabinet", new GoToUserCabinet());
        getCommands.put("/topUp", new TopUpGetCommand());
        getCommands.put("/checkComment", new CheckCommentCommand());
        getCommands.put("/rejectReceipt", new RejectReceiptCommand());
        getCommands.put("/verifyReceipt", new VerifyReceiptCommand());

        log.info("POST commands are loaded");
        postCommands.put("/login", new LoginCommand());
        postCommands.put("/signup", new SignUpCommand());
        postCommands.put("/addNewScooter", new AddNewScooterCommand());
        postCommands.put("/editScooter", new EditScooterCommand());
        postCommands.put("/deleteScooter", new DeleteScooter());
        postCommands.put("/addManager", new AddManagerCommand());
        postCommands.put("/takeOrder", new TakeOrderCommand());
        postCommands.put("/topUp", new TopUpCommand());
        postCommands.put("/changeStatus", new ChangeUserStatus());
        postCommands.put("/receiptAction", new ReceiptAction());
        postCommands.put("/checkReceipt", new CheckReceipt());
        postCommands.put("/rejectReceipt", new RejectReceipt());
        postCommands.put("/verifyReceipt", new VerifyReceipt());
    }

    public ServletCommand getGetCommand(HttpServletRequest req) {
        String command = getMapping(req);
        if (getCommands.get(command) == null) {
            return getCommands.get("/");
        }
        return getCommands.get(command);
    }

    public ServletCommand getPostCommand(HttpServletRequest req) {
        String command = getMapping(req);
        if (postCommands.get(command) == null) {
            return postCommands.get("/");
        }
        return postCommands.get(command);
    }

    private String getMapping(HttpServletRequest req) {
        String mapping = req.getRequestURI().substring(req.getContextPath().length());
        if (mapping.endsWith("/")) {
            mapping = mapping.substring(0, mapping.length() - 1);
        }
        return mapping;
    }
}
