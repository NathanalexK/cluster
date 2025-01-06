package com.project.demo.controller.haProxyInstance;

import com.project.demo.haproxy.HaProxy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/instances")
public class ListInstanceController extends HttpServlet {

    // Lister les instances HaProxy
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaProxy haProxy = HaProxy.getInstance();
        req.setAttribute("content", "instance/list");
        req.setAttribute("instances", haProxy.getInstances());
        req.getRequestDispatcher("layout.jsp").forward(req, resp);
    }
}
