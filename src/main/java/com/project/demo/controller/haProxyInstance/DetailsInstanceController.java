package com.project.demo.controller.haProxyInstance;

import com.project.demo.haproxy.HaProxy;
import com.project.demo.haproxy.HaProxyInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/instances/details")
public class DetailsInstanceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaProxy haProxy = HaProxy.getInstance();
        String instanceName = req.getParameter("instance");
        HaProxyInstance haProxyInstance = haProxy.findInstanceByName(instanceName);
        req.setAttribute("instance", haProxyInstance);
        req.setAttribute("content", "instance/details");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }
}
