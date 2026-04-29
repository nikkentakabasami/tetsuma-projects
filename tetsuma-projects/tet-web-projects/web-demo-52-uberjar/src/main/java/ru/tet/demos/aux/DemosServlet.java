package ru.tet.demos.aux;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.tet.beans.DemoFolder;

@WebServlet("/demoscan/*")
public class DemosServlet extends HttpServlet {

	public static final String SIBLINGS_URL = "/siblingPages";
	public static final String REFRESH_DEMOS_URL = "/refreshDemoList";
	public static final String DEMO_FOLDERS_URL = "/demoFolders";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String pathInfo = request.getPathInfo();

		//возврат json-объекта
		if (pathInfo.startsWith(SIBLINGS_URL)) {
			
			String pageName = request.getParameter("pageName");
			String[] siblingPages = DemosScanner.getInstance().findSiblingPages(pageName);

			ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json");
			mapper.writeValue(response.getOutputStream(), siblingPages);
			return;
		}
		
		if (pathInfo.startsWith(REFRESH_DEMOS_URL)) {

			List<DemoFolder> demoFolders = DemosScanner.getInstance().scanDemos();
			request.getServletContext().setAttribute("demoFolders", demoFolders);
			response.getWriter().write("ok");

			return;
		}
		
		if (pathInfo.startsWith(DEMO_FOLDERS_URL)) {

			List<DemoFolder> demoFolders = DemosScanner.getInstance().getDemoFolders();

			ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json");
			mapper.writeValue(response.getOutputStream(), demoFolders);
			return;
		}
		

	}


}