<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%-- ���ع���ҳͷ�ļ� ��ʼ --%>
<%@include file="/WEB-INF/views/include/pagePublic.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- ���ع���ҳͷ�ļ� ���� --%>

<%-- ����ҳͷhtml��ǩ ��ʼ --%>
<jsp:include flush="true" page="/WEB-INF/views/include/pageDeclare.jsp" />
<%-- ����ҳͷhtml��ǩ ���� --%>

	<head>
		<%-- ����ҳͷtitle ��ʼ --%>
		<jsp:include page="/WEB-INF/views/include/title.jsp" flush="true">
			<jsp:param name="title" value="ǰ̨-����" />
		</jsp:include>
		<%-- ����ҳͷtitle��ǩ ��ʼ --%>
		<%-- ����ҳͷmeta��ǩ ��ʼ --%>
		<jsp:include flush="true" page="/WEB-INF/views/include/meta.jsp" >
			<jsp:param name="keywords" value="ǰ̨����,ǰ̨,����" />
			<jsp:param name="description" value="ǰ̨����DEMO"  />
		</jsp:include>
		<%-- ����ҳͷmeta��ǩ ���� --%>
		<%-- ����ҳͷjs��css��ǩ ��ʼ --%>
		<jsp:include flush="true" page="/WEB-INF/views/include/jsAndCss.jsp" />
		<%-- ����ҳͷjs��css��ǩ ��ʼ --%>
		
		<f>
	
<script type="text/javascript">
//ҳ��Ƕ������ͳ�ƽű�
/*
		var _aq = _aq || [];
		_aq.push(['_setSiteId', 10000]);
		_aq.push(['_trackPageview']);
		
		(function() {
		    var ga = document.createElement( 'script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src='http://localhost:8081/stat/stat.js';
		    var s = document.getElementsByTagName( 'script')[0]; s.parentNode.insertBefore(ga, s);
		  })();
  
		$(document).ready(function(){
		});
*/
</script>

		
	</head>
	<body>
	 ����˹�´�Ұ�·�Ͽ�����������˹�ӵ绰�������ʦ�ĸ��ã������ݿ���õ���������滮
		
	</body>
	<script type="text/javascript"> 
	
		function test(flag){
		 
			var forwardUrl ="/servlet/Test?function=test&flag="+flag;
			
			ajaxLoad("info",forwardUrl,"post");
			
		}
		
	</script>
</html>
