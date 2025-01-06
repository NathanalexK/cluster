package com.project.demo.controller.haProxy;

import com.project.demo.haproxy.HaProxy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/haproxy/servers")
public class ListServerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaProxy haProxy = HaProxy.getInstance();
        req.setAttribute("haproxy", haProxy);
        req.setAttribute("content", "haproxy/list-server");
        req.getRequestDispatcher("/layout.jsp").forward(req, resp);

    }
}
