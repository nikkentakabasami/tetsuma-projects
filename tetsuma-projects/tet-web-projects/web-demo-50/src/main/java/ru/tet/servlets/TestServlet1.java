package ru.tet.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.tet.MiscWebServices;
import ru.tet.beans.UserDTO;

/**
 * Показывает jndi-ресурсы.
 * 
 */
@WebServlet(name = "TestServlet1", urlPatterns = "/test1")
public class TestServlet1 extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("tws initiated");
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		
		String testStringValue1 = MiscWebServices.getInstance().getJNDIVal("testStringValue1",String.class);
		Integer testNumberValue1 = MiscWebServices.getInstance().getJNDIVal("testNumberValue1",Integer.class);
		
		UserDTO user = MiscWebServices.getInstance().getJNDIVal("ttt/testUser1",UserDTO.class);
		
		
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>TestServlet1.</h1>");
		out.println("<h3>system jndi resources:</h3>");
		out.format("testStringValue1=%s, <br>testNumberValue1=%d<br>", testStringValue1,testNumberValue1);
		out.format("user=%s%n", user.toString());
		
		
		
	}
	

	
	
}