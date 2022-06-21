package com.kvitnytskyi.electric_scooters.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletCommand {

    String execute(HttpServletRequest req, HttpServletResponse resp);
}
