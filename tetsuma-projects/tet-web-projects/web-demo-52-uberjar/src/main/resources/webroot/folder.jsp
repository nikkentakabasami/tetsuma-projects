<%@page import="ru.tet.beans.DemoFolder"%>
<%@page import="ru.tet.demos.aux.DemosScanner"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="demos/include/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/index.js"></script>


<div class="acc-2cols">



	<%
	DemosScanner scanner = DemosScanner.getInstance();
	String folderName = request.getParameter("name");
	request.setAttribute("folder", scanner.findFolder(folderName));	
	
	%>

	<h1 class="acc-row">Демо из папки ${folder.name}</h1>

  <a href="demoscan/refreshDemoList" id="demoscan">DemosServlet - refreshDemoList</a>
  <br>

	<div class="acc-anchor-list">
		<h4>${folder.desc}<sup class="header-sup">${folder.name}</sup>
		</h4>

		<c:forEach var="page" items="${folder.pages}">
			<a href="demos/${folder.name}/${page.name}" target="${page.id}">${page.desc}</a>
		</c:forEach>

	</div>






</div>




<%@ include file="demos/include/footer.jspf"%>



