<%--
  Created by IntelliJ IDEA.
  User: nathanalex
  Date: 12/22/24
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String content = (String) request.getAttribute("content") + ".jsp";
%>
<html>
<head>
    <title>Cluster Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>
<div class="container">
    <div class="sidebar">
        <div class="title">Cluster ADMIN </div>
        <div class="list">
            <div class="list-head">Instance</div>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/instances">Lister Instances</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/instance/add">Saisir Instance</a>
                </li>

            </ul>
        </div>

        <div class="list">
            <div class="list-head">Serveurs</div>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/haproxy/servers">Lister Serveurs</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/server/add">Saisir Serveur</a>
                </li>
            </ul>
        </div>

        <div class="list">
            <div class="list-head">Base de Donnee</div>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/haproxy/servers">Lister Serveurs</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/server/add">Saisir Serveur</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="content">
        <jsp:include page="<%=content%>"/>
    </div>
</div>

</body>
</html>
