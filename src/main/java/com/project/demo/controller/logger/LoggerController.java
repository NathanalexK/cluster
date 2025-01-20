package com.project.demo.controller.logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

@WebServlet("/logger")
public class LoggerController extends HttpServlet {
    private static final Logger logger = (Logger) LogManager.getLogger(LoggerController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Ceci est un message de debug");
        logger.info("Ceci est un message de debug");
        logger.fatal("Ceci est un message de debug");
    }
}
