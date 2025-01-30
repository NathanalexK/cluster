<%--
  Created by IntelliJ IDEA.
  User: andyr
  Date: 29/01/2025
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2>Master</h2>
<h4>Ajout Serveur Master</h4>
<form action="" method="post">
    <table>
        <thead>
        <th>Master</th>
        <th>Info</th>
        <th>Slave</th>
        <th>Info</th>
        </thead>

        <tbody>
            <tr>
                <td>Port</td>
                <td><input type="number" name="masterPort" placeholder="000.000.000.000"></td>
                <td>Port</td>
                <td><input type="number" name="slavePort" placeholder="000.000.000.000"></td>
            </tr>

            <tr>
                <td>User</td>
                <td><input type="text" name="masterUser" placeholder="replicator"></td>
                <td>User</td>
                <td><input type="text" name="slaveUser" placeholder="replicator"></td>
            </tr>

            <tr>
                <td>Password</td>
                <td><input type="password" name="masterPass" placeholder=""></td>
                <td>Password</td>
                <td><input type="password" name="slavePass" placeholder=""></td>
            </tr>

        </tbody>
    </table>
    <div>
        <button type="submit">Ajouter</button>
    </div>
</form>
