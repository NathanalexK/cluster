<%@ page import="com.project.demo.haproxy.HaProxyInstance" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.demo.haproxy.Server" %><%--
  Created by IntelliJ IDEA.
  User: nathanalex
  Date: 12/25/24
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<HaProxyInstance> instances = ((List<HaProxyInstance>) request.getAttribute("instances"));
    Server server = ((Server) request.getAttribute("server"));
%>

<h2>Serveur</h2>
<h4>Ajout Serveur HAPROXY</h4>
<form action="${pageContext.request.contextPath}/server/add" method="post">
    <table>
        <tr>
            <td>Instance HAPROXY</td>

            <input type="hidden" name="server" value="<%=server != null ? server.getName() : ""%>">
            <input type="hidden" name="instance" value="<%=server != null ? ser%>"

            <%
                if(server == null) {
            %>
            <td>
                <select name="instance">
                    <% for(HaProxyInstance instance: instances) {%>
                        <option value="<%=instance.getName()%>">
                            <%=instance.getName()%>  (Mode: <%=instance.getMode()%>, Port: <%=instance.getPort()%>)
                        </option>
                    <% } %>
                </select>
            </td>
            <%
                }
            %>
        </tr>
        <tr>
            <td>Nom du Server</td>
            <td><input type="text" name="name" placeholder="Server 1" value="<%=server != null ? server.getName() : ""%>"></td>
        </tr>

        <tr>
            <td>Adresse IP</td>
            <td><input type="text" name="ip" placeholder="192.168.10.24" value="<%=server != null ? server.getIpAdress() : ""%>"></td>
        </tr>

        <tr>
            <td>Port</td>
            <td><input type="text" name="port" placeholder="80" value="<%=server != null ? server.getPort() : ""%>"></td>
        </tr>


    </table>
    <div>
        <button type="submit"><%=server != null ? "Modifier" : "Ajouter"%></button>
    </div>
</form>
