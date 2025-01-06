package com.project.demo.controller.haProxyInstance;

import com.project.demo.haproxy.HaProxy;
import com.project.demo.haproxy.HaProxyInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/instance/update")
public class UpdateInstanceController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaProxy haProxy = HaProxy.getInstance();
        HaProxyInstance instance = haProxy.findInstanceByName(req.getParameter("originalName"));
        if(instance != null) {
            instance.setName(req.getParameter("name"));
            instance.setMode(req.getParameter("mode"));
            instance.setPort(Integer.parseInt(req.getParameter("port")));
            haProxy.rewrite();
            resp.sendRedirect(req.getContextPath() + "/instances/details?instance=" + instance.getName());
        }

    }
}
