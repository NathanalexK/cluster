package com.project.demo.controller.Master;

import com.project.demo.master.MasterStatus;
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
    String user = "root";
    String password = "root";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String ip1 = req.getParameter("ip1");
        String ip2 = req.getParameter("ip2");

        MasterStatus ms = new MasterStatus("localhost",user, password);
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String query = "show master status";
            try (Statement statement = con.createStatement()) {
                ResultSet rs = statement.executeQuery(query);

                ms.setMasterLogFile(rs.getString("File"));
                ms.setPosition(rs.getString("Position"));
                out.print(ms.getMasterLogFile());
                out.print(ms.getPosition());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
