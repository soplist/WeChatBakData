<%@ page language="java" import="java.util.*,org.wangkang.entity.Message" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <style>
    body {font-size:11px;}
  </style>
  <body>
    <h3>Love.Knowledge tree.Revelation Theology.</h3><br>
    <!-- judge app;ication -->
    <%
    if (application != null){
    %> 
    <!-- <c:out value="application is not null." /> -->
    <%
    }
    %>
    <!-- get message -->
    <%
    ServletContext context = this.getServletContext();
    ArrayList<Message> messageList = (ArrayList<Message>)application.getAttribute("msgs"); 
    %>
    <!-- judge message list -->
    <%
    if (messageList != null){
    %> 
    <!-- <c:out value="messageList is not null." /> -->
    <%
    }
    %>
    <!-- foreach message list-->
    <c:forEach items="<%=messageList%>" var="m">
    ${m.authorName}.${m.createTime}:${m.content}<br>
    </c:forEach>
  </body>
</html>
