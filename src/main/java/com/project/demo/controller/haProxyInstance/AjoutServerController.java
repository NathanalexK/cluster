package com.project.demo.controller.haProxyInstance;

import com.project.demo.haproxy.HaProxy;
import com.project.demo.haproxy.HaProxyInstance;
import com.project.demo.haproxy.Server;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/instance/add")
public class AjoutServerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("content", "instance/form");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaProxy haProxy = HaProxy.getInstance();
        HaProxyInstance haProxyInstance = haProxy.findInstanceByName(req.getParameter("instance"));
        Server server = new Server();
        server.setName(req.getParameter("name"));
        server.setIpAdress(req.getParameter("ip"));
        server.setPort(Integer.parseInt(req.getParameter("port")));
        haProxyInstance.addServer(server);
        haProxy.rewrite();
        resp.sendRedirect(req.getContextPath() + "/instances/details?instance=" + haProxyInstance.getName());
    }
}
