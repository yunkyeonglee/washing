package com.yk.myserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hello extends HttpServlet {
	private String message;
	ArrayList<String> names = new ArrayList<String>();

	  public void init() throws ServletException
	  {
	      // Do required initialization
	      message = "Hello World.....";
	      DBConnectionManager manager = null;
	      try {
			manager = new DBConnectionManager("jdbc:mysql://localhost:3306/myserver", "root", "root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      Connection conn = manager.getConnection();
	      
	      try {
			PreparedStatement stmt = conn.prepareStatement("select * from user");
			
			ResultSet set = stmt.executeQuery();
			
			
			
			
			while( set.next() ){
				names.add(set.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      
	      Thread t = new Thread();
	      t.start();
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
	      // Set response content type
	      response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<h1>" + message + "</h1>");
	      out.println("<h1>" + message + "</h1>");
	      out.println("<h1>" + message + "</h1>");
	      out.println("<h1>" + message + "</h1>");
	      out.println("<img src='https://yoxos.eclipsesource.com/yoxos/doc/org.eclipse.jst.server_ui.feature.feature.group/logo.png'/>");
	      
	  }
	  
	  public String h100(){
		  StringBuffer sb = new StringBuffer();
		  for(int i = 0 ; i < names.size() ; i++){ 
			sb.append("<h1>for java index=" + names.get(i) + "</h1>");
		  }
		  return sb.toString();
	  }
	  public void destroy()
	  {
	      // do nothing.
	  }
	  
	  public String get(){
		  StringBuffer out = new StringBuffer();
		  out.append("<h1>" + message + "</h1>");
	      out.append("<h1>" + message + "</h1>");
	      out.append("<h1>" + message + "</h1>");
	      out.append("<h1>" + message + "</h1>");
	      
		  return out.toString();
	  }
}
