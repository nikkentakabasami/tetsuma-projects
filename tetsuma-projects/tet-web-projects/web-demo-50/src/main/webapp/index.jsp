<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="demos/include/header.jspf"%>


<div class="acc-2cols">

	<h1 class="acc-row">Демо веб-компонентов, классов и библиотек</h1>



	<div class="acc-anchor-list">
			<h4>сервлеты</h4>
	
  <a href="helloWorld1" target="jaxrs">HelloWorldServlet</a>
  <a href="test1" target="jaxrs">TestServlet1 - check jndi</a>
  <a href="test_js_libs.html" target="jaxrs">test_js_libs.html - check js libs</a>

	</div>
	
	
	
	

<%-- переменную demoFolders заполняет MainServletContextListener  --%>
	<c:forEach var="folder" items="${demoFolders}" varStatus="loop">

    	<div class="acc-anchor-list">
			<h5 style="color: gray;">${loop.index}- ${folder.name}</h5>
			<div>${folder.desc}</div>

			<c:forEach var="page" items="${folder.pages}">
				<a href="demos/${folder.name}/${page}" target="${folder.name}">${page}</a>
			</c:forEach>

		</div>


	</c:forEach>
<%--
 --%>



</div>




<%@ include file="demos/include/footer.jspf"%>



