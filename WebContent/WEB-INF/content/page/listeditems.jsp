<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<body>
  
  <h1>Recently listed items from ListersIn</h1>
  
  <ul>
    <s:iterator value="blogfeed.rows">
      <li>
        <a href="${url}">
          ${title}
        </a>
      </li>
	  </s:iterator>
  </ul>
  
</body>
</html>
