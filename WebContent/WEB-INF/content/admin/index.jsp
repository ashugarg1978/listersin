<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<title>admin</title>
<link rel="stylesheet" type="text/css" href="/css/admin.css">
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/admin.js"></script>
</head>
<body>
	
	<ul>
		<li><a href="https://listers.in/admin/">Production</a></li>
		<li><a href="http://sandbox.listers.in/admin/">Sandbox</a></li>
		<li><a href="/admin/updatesummary">Update summary</a></li>
    <li><a href="http://forum.listers.in/" target="_blank">Forum</a></li>
    <li><a href="http://listers.in/blog/wp-admin/" target="_blank">Blog</a></li>
    <li><a href="http://zabbix.devweb.in/" target="_blank">Zabbix</a></li>
    <li><a href="http://www.tapatalk.com/forum_owner.php" target="_blank">Tapatalk</a></li>
	</ul>
  
  <table>
    <thead>
      <tr>
        <th rowspan="2">Email</th>
        <th rowspan="2">Created</th>
        <th rowspan="2">Last used</th>
        <th rowspan="2">Action</th>
        <th rowspan="2">eBay ID</th>
        <th colspan="2">Active</th>
        <th colspan="2">Cmpltd</th>
        <th colspan="2">Saved</th>
      </tr>
      <tr>
        <th>P</th>
        <th>F</th>
        <th>P</th>
        <th>F</th>
        <th>P</th>
        <th>F</th>
      </tr>
    </thead>
    <tbody>
	    <s:iterator var="user" value="users">
				<s:if test="userids2 == null || userids2.size() == 0">
					<tr>
						<td>
							<a href="/admin/signin?email=${email}" target="usersignin">${email}</a>
							<s:if test="status != 'free trial'">
                <div>${status}</div>
							</s:if>
						</td>
						<td>${created_local}</td>
						<td>${lastused_local}</td>
						<td>
							<a class="delete" href="#" data-id="${_id}">delete</a>
						</td>
						<td>-</td>
						<td align="right">-</td>
						<td align="right">-</td>
						<td align="right">-</td>
						<td align="right">-</td>
						<td align="right">-</td>
						<td align="right">-</td>
					</tr>
				</s:if>
				<s:else>
					
	        <s:iterator var="userid" value="#user.userids2" status="status">
						<tr>
							<s:if test="#status.first == true">
								<td rowspan="${userids2.size()}">
									<a href="/admin/signin?email=${email}" target="usersignin">${email}</a>
							    <s:if test="status != 'free trial'">
                    <div>${status}</div>
							    </s:if>
								</td>
								<td rowspan="${userids2.size()}">${created_local}</td>
								<td rowspan="${userids2.size()}">${lastused_local}</td>
								<td rowspan="${userids2.size()}">
							    <a class="delete" href="#" data-id="${_id}">delete</a>
								</td>
							</s:if>
							
							<td>
                <s:property value="username"/>
                <s:if test="TokenStatus.Status != 'Active'">
                  <br/>(<s:property value="TokenStatus.Status"/>)
                </s:if>
              </td>
							<td align="right"><s:property value="summary.Active.truez"/></td>
							<td align="right"><s:property value="summary.Active.falsez"/></td>
							<td align="right"><s:property value="summary.Completed.truez"/></td>
							<td align="right"><s:property value="summary.Completed.falsez"/></td>
							<td align="right"><s:property value="summary.empty.truez"/></td>
							<td align="right"><s:property value="summary.empty.falsez"/></td>
						</tr>
	        </s:iterator>
					
				</s:else>
	    </s:iterator>
    </tbody>
  </table>
  
</body>
</html>
