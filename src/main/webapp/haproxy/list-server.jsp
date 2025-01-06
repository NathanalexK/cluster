<%@ page import="com.project.demo.haproxy.HaProxy" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.project.demo.haproxy.HaProxyInstance" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.demo.haproxy.Server" %><%--
  Created by IntelliJ IDEA.
  User: nathanalex
  Date: 12/24/24
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HaProxy haProxy = ((HaProxy) request.getAttribute("haproxy"));
%>


<h2>Details HAPROXY</h2>
<h4>Liste Serveurs</h4>

<table>
    <thead>
    <tr>
        <th>Instance Hote</th>
        <th>Mode</th>
        <th>Nom Serveur</th>
        <th>Adresse IP</th>
        <th>Port</th>
        <th></th>
    </tr>
    </thead>

    <tbody>
    <%
        for(Map.Entry<HaProxyInstance, List<Server>> entry : haProxy.getAllSevers().entrySet()) {
            for(Server server: entry.getValue()) {
    %>
        <tr>
            <td><%=entry.getKey().getName()%></td>
            <td><%=entry.getKey().getMode()%></td>
            <td><%=server.getName()%></td>
            <td><%=server.getIpAdress()%></td>
            <td><%=server.getPort()%></td>
            <td>
                <button onclick="tester(this, 'http://<%=server.getIpAdress() + ":" + server.getPort()%>')">Tester</button>
                <a href=""><button>Modifier</button></a>
                <a href=""><button>Supprimer</button></a>
            </td>
        </tr>

    <%
            }
        }
    %>
    </tbody>
</table>

<script>
    async function tester(btn, lien) {
        timeout = 3000;
        btn.innerHTML = "Test en Cours"

        const timeoutId = setTimeout(() => {
            btn.innerHTML = "Test Error";
        }, timeout)

        try {
            await fetch(lien, {
                method: 'HEAD',
                mode: 'no-cors'
            });
            clearTimeout(timeoutId);
            btn.innerHTML = "Test OK";
            // console.log("ok")
        } catch (error) {
            console.log("no")
        }
        // img = new Image();
        // img.onload = () => {
        //     console.log("sascac")
        //     resolve({});
        // }
        // console.log(btn);
        // const xhr = new XMLHttpRequest();
        // xhr.withCredentials = false;
        //
        // xhr.addEventListener('loadend', () => {
        //     // if(xhr.readyState < 4) return;
        //     console.log(xhr.status);
        //     console.log("scaac")
        //
        //     if(xhr.status === 200) {
        //         console.log("OK");
        //         btn.innerHtml = 'Test OK';
        //     }
        //
        //     else {
        //         console.log("error");
        //         btn.innerHtml = "Test Error";
        //     }
        // })
        //
        // xhr.addEventListener('error', () => {
        //     console.log("sdadva")
        // })
        //
        // xhr.open('GET', lien, true);
        // xhr.send();
    }
</script>
