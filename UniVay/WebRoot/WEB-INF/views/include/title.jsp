<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" %>
<%@ include file="/WEB-INF/views/include/pagePublic.jsp" %>
<title>
<c:choose>
	<c:when test="${not empty param.title}">${param.title }</c:when>
	<c:otherwise>����˼����Ϣ�������޹�˾</c:otherwise>
</c:choose>
</title>