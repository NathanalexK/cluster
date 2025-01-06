package com.project.demo.controller.haProxyInstance;

import com.project.demo.haproxy.HaProxy;
import com.project.demo.haproxy.HaProxyInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/instances/delete")
public class DeleteServerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaProxy haProxy = HaProxy.getInstance();
        HaProxyInstance instance = haProxy.findInstanceByName(req.getParameter("name"));
        instance.deleteServer(req.getParameter("serverName"));
        haProxy.rewrite();
        resp.sendRedirect(req.getContextPath() + "/instances/details?instance="+instance.getName());
    }
}
