<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<body>

<s:url action="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn" var="ebayurl">
  <s:param name="runame">Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc</s:param>
  <s:param name="SessID">${user.sessionid}</s:param>
</s:url>

<s:a href="%{ebayurl}"><s:text name="add new account"/></s:a><br>

${user.sessionid}<br>

<a href="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc&SessID=${user.sessionid}"><s:text name="add new account"/></a><br>

</body>
</html>
