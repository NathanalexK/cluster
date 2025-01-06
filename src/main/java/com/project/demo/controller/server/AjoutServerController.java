package com.project.demo.controller.server;

import com.project.demo.haproxy.HaProxy;
import com.project.demo.haproxy.HaProxyInstance;
import com.project.demo.haproxy.Server;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/server/add")
public class AjoutServerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaProxy haProxy = HaProxy.getInstance();
        String instanceName = req.getParameter("instance");
        String serverName = req.getParameter("serverName");
        if(instanceName != null && serverName != null) {
            Instance instance
            Server server = haProxy.findInstanceByName(instanceName).getServerByName(serverName).orElse(null);
            req.setAttribute("instance", );
            req.setAttribute("server", server);

        }

        req.setAttribute("instances", haProxy.getInstances());
        req.setAttribute("content", "server/saisie");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaProxy haProxy = HaProxy.getInstance();
        if(req.getParameter("server") != null) {

        } else {


            HaProxyInstance haProxyInstance = haProxy.findInstanceByName(req.getParameter("instance"));
            Server server = new Server();
            server.setName(req.getParameter("name"));
            server.setIpAdress(req.getParameter("ip"));
            server.setPort(Integer.parseInt(req.getParameter("port")));
            haProxyInstance.addServer(server);
            haProxy.rewrite();
            resp.sendRedirect("/");
        }
    }
}
