<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<body>

Session ID : ${user.sessionid}<br/>
<br/>

SandBox<br/>
<a href="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc&SessID=${user.sessionid}"><s:text name="add new account"/></a><br/>

<br />

Production<br/>
<a href="https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-dd83-4-kafers&SessID=${user.sessionid}"><s:text name="add new account"/></a><br/>


</body>
</html>
