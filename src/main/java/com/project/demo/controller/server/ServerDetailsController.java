package com.project.demo.controller.server;

import com.project.demo.haproxy.HaProxy;
import com.project.demo.haproxy.HaProxyInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/server/details")
public class ServerDetailsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaProxy haProxy = HaProxy.getInstance();
        String instanceName = req.getParameter("instance");
        HaProxyInstance haProxyInstance = haProxy.findInstanceByName(instanceName);
        req.setAttribute("instance", haProxyInstance);
        req.setAttribute("content", "server/details");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }
}
