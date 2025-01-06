<%--
  Created by IntelliJ IDEA.
  User: nathanalex
  Date: 12/24/24
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>Instance HAPROXY</h2>
<h4>Ajouter une nouvelle instance</h4>

<form action="">
  <table>
    <tr>
      <td>Nom</td>
      <td><input type="text" name="name"/></td>
    </tr>
    <tr>
      <td>Mode</td>
      <td>
        <select name="mode" id="">
          <option value="http">HTTP
          </option>
          <option value="tcp">TCP</option>
        </select>
        <%--            <%=instance.getMode()%>--%>
      </td>
    </tr>

    <tr>
      <td>Load balancer</td>
      <td>
        <select name="loadBalancer">
          <option value="roundrobin">
            RoundRobin
          </option>
          <option value="leastconn">
            LeastConn
          </option>
        </select>
      </td>
    </tr>
  </table>
  <button type="submit">Ajouter</button>
</form>
