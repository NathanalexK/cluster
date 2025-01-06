<%@ page import="com.project.demo.haproxy.HaProxyInstance" %>
<%@ page import="com.project.demo.haproxy.Server" %><%--
  Created by IntelliJ IDEA.
  User: nathanalex
  Date: 12/22/24
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HaProxyInstance instance = ((HaProxyInstance) request.getAttribute("instance"));
%>
<h2>Details de l'Instance</h2>

<h4>Configuration de l'Instance</h4>
<form action="/instance/update">
    <input type="hidden" name="originalName" value="<%=instance.getName()%>">
    <table>
        <tr>
            <td>Nom</td>
            <td><input type="text" name="name" value="<%=instance.getName()%>"/></td>
        </tr>
        <tr>
            <td>Mode</td>
            <td>
                <select name="mode" id="">
                    <option value="http" <%=instance.getMode().equalsIgnoreCase("http") ? "selected" : ""%>>HTTP
                    </option>
                    <option value="tcp" <%=instance.getMode().equalsIgnoreCase("tcp") ? "selected" : ""%>>TCP</option>
                </select>
                <small> (Valeur: <%=instance.getMode()%>) </small>
                <%--            <%=instance.getMode()%>--%>
            </td>
        </tr>

        <tr>
            <td>Load balancer</td>
            <td>
                <select name="loadBalancer" id="">
                    <option value="roundrobin" <%=instance.getLoadBalancer().equalsIgnoreCase("roundrobin") ? "selected" : ""%>>
                        RoundRobin
                    </option>
                    <option value="leastconn" <%=instance.getLoadBalancer().equalsIgnoreCase("leastconn") ? "selected" : ""%>>
                        LeastConn
                    </option>
                </select>
                <small>(Valeur: <%=instance.getLoadBalancer()%>)</small>
                <%--            <%=instance.getLoadBalancer()%>--%>
            </td>
        </tr>
    </table>
    <button type="submit">Mettre a Jour</button>
</form>

<h4>Ajout de Server</h4>
<form action="add" method="post">
    <input type="hidden" name="instance" value="<%=instance.getName()%>">
    <table>
        <tr>
            <td>Nom du Server</td>
            <td><input type="text" name="name" placeholder="Server 1"></td>
        </tr>

        <tr>
            <td>Adresse IP</td>
            <td><input type="text" name="ip" placeholder="192.168.10.24"></td>
        </tr>

        <tr>
            <td>Port</td>
            <td><input type="text" name="port" placeholder="80"></td>
        </tr>


    </table>
    <div>
        <button type="submit">Ajouter</button>
    </div>
</form>

<h4>Liste Des Serveurs</h4>
<table>
    <thead>
    <tr>
        <th>Nom</th>
        <th>Adresse IP</th>
        <th>Port</th>
        <th></th>
    </tr>
    </thead>

    <tbody>
    <% for(Server server: instance.getServerList()) {%>
        <tr>
            <td><%=server.getName()%></td>
            <td><%=server.getIpAdress()%></td>
            <td><%=server.getPort()%></td>
            <td>
                <a href=""><button>Modifier</button></a>
                <a href=""><button>Supprimer</button></a>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>

