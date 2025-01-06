<%@ page import="java.util.List" %>
<%@ page import="com.project.demo.haproxy.HaProxyInstance" %><%--
  Created by IntelliJ IDEA.
  User: nathanalex
  Date: 12/23/24
  Time: 10:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<HaProxyInstance> instances = ((List<HaProxyInstance>) request.getAttribute("instances"));
%>

<h2>Instance HAPROXY</h2>
<h4>Liste Des Instances</h4>

<table>
  <thead>
  <tr>
    <th>Nom</th>
    <th>Port</th>
    <th>Mode</th>
    <th>Load Balancer</th>
    <th></th>
  </tr>
  </thead>

  <tbody>
  <% for(HaProxyInstance instance: instances) { %>
    <tr>
      <td><%=instance.getName()%></td>
      <td><%=instance.getPort()%></td>
      <td><%=instance.getMode()%></td>
      <td><%=instance.getLoadBalancer()%></td>
      <td><a href="${pageContext.request.contextPath}/instances/details?instance=<%=instance.getName()%>"><button>Voir Details</button></a></td>
    </tr>

  <% } %>
  </tbody>
</table>