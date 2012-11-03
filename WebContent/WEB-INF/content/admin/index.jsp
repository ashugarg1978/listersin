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
    <thead>
      <tr>
        <th>Email</th>
        <th>Status</th>
        <th>Created</th>
        <th>Last used</th>
        <th>Action</th>
        <th>eBay IDs</th>
        <th>User agent</th>
      </tr>
    </thead>
    <tbody>
	    <s:iterator var="user" value="users">
		    <tr id="${_id}">
          <td>
						<a href="/admin/signin?email=${email}" target="usersignin">${email}</a>
					</td>
          <td>${status}</td>
          <td>${created_local}</td>
          <td>${lastused_local}</td>
          <td>
            <a class="delete" href="#">delete</a>
          </td>
          <td>
            <ul>
	            <s:iterator var="userid" value="#user.userids">
                <li><s:property value="key"/></li>
	            </s:iterator>
            </ul>
          </td>
          <td>${useragent}</td>
		    </tr>
	    </s:iterator>
    </tbody>
  </table>
  
</body>
</html>
