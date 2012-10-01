<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<title>admin</title>
<link rel="stylesheet" type="text/css" href="/css/admin.css">
<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/admin.js"></script>
</head>
<body>
  
  <table>
    <tbody>
	    <s:iterator var="user" value="users">
		    <tr id="${_id}">
          <td>${email}</td>
          <td>${status}</td>
          <td>${created_local}</td>
          <td>
            <a href="#">delete</a>
          </td>
          <td>
	          <s:iterator var="userid" value="#user.userids">
              <s:property value="key"/>
	          </s:iterator>
          </td>
		    </tr>
	    </s:iterator>
    </tbody>
  </table>
  
</body>
</html>
