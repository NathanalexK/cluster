package com.project.demo.controller.Master;

import com.project.demo.master.InstancePc;
import com.project.demo.master.MasterConf;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/master")
public class MasterController extends HttpServlet {

    public static String url = "jdbc:mysql://localhost:3306/pokemon";
    String user = "replicator";
    String password = "root";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        InstancePc myPc = new InstancePc("192.168.88.27", "root", "root");
        InstancePc remotePc = new InstancePc("172.10.191.78", "root", "root");

        MasterConf ms = new MasterConf(remotePc);
        try (Connection con = DriverManager.getConnection(ms.jdbcUrl(), user, password)) {

            String query = "show master status";
            try (Statement statement = con.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                rs.next();

                ms.setMasterLogFile(rs.getString("File"));
                ms.setPosition(rs.getString("Position"));
                out.print(ms.getMasterLogFile());
                out.print(ms.generateMasterQuery());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
