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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.packages.database.DatabaseConnection;
import com.packages.objects.SliceInfo;
import com.packages.rdf.ExecuteSlice;
import com.packages.rdf.SparqlBuilder;

/**
 * Servlet implementation class ClassAnalyseServlet
 */
@WebServlet("/CreateClassSliceServlet")
public class CreateClassSliceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClassSliceServlet() {
        super();
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
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		String result="";
		HttpSession session = request.getSession();
		try{
			result = ExecuteSlice(FormQuery(request),session.getAttribute("dataSource").toString(), request);
			LogSlice(request);
			//FormQuery(request);
			//result="success";
		}
		catch(Exception e)
		{
			result = e.toString();
		}
		
		map.put("result", result);
		write(response, map);
	}

	private void write(HttpServletResponse response, Map<String, Object> map)
			throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
	private String FormQuery(HttpServletRequest request)
	{
		SparqlBuilder sb = new SparqlBuilder();
		return sb.FormClassQuery(request);
	}
	private String ExecuteSlice(String pattern, String filePath, HttpServletRequest request) throws IOException
	{
		ExecuteSlice es = new ExecuteSlice();
		return es.ExecuteSlice(pattern, filePath, request);
	}
	
	private void LogSlice(HttpServletRequest request) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		DatabaseConnection db = new DatabaseConnection();
		SliceInfo slice = new SliceInfo();
		slice = slice.CreateClassSlice(request);
		db.SliceInfoInsert(slice);
		
	}

}
