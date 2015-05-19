package com.packages.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mypack.test.ServletInfo;
import com.packages.database.DatabaseConnection;
import com.packages.rdf.FileAnalyse;

/**
 * Servlet implementation class myservlet
 */
@WebServlet("/myservlet")
public class myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ServletInfo servObj = new ServletInfo();
    /**
     * Default constructor. 
     */
    public myservlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		Map<String, Object> map = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		String filepath = request.getParameter("txtFilepath");
		DatabaseConnection conn = new DatabaseConnection();
		
			try {
				conn.ExecuteDbCommand("");
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
		map.put("filePath2", GetClasses(filepath));
		write(response, map);
	}

	private void write(HttpServletResponse response, Map<String, Object> map)
			throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
	private Map<String,Object> GetClasses(String path)
	{
		return servObj.FileAnalyseTransition(path);
	}
}
