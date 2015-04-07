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
import com.packages.business.SliceOps;
import com.packages.database.DatabaseConnection;
import com.packages.objects.SliceInfo;
import com.packages.rdf.ExecuteSlice;

/**
 * Servlet implementation class SliceInfo
 */
@WebServlet("/SliceInfoServlet")
public class SliceInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SliceInfoServlet() {
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
		String sliceResult="";
		SliceInfo slice = new SliceInfo();
		try {
			slice = GetSliceDetails(request);
			sliceResult = ExecuteUpdateSlice(slice);
			LogSlice(slice);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			sliceResult=e.toString();
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sliceResult", sliceResult);
		
		
		write(response, map);
	}

	private void write(HttpServletResponse response, Map<String, Object> map)
			throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
	private SliceInfo GetSliceDetails(HttpServletRequest request) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		SliceOps so = new SliceOps();
		SliceInfo slice = new SliceInfo();
		slice = so.GetSlice(request);
		return slice;
	}
	private String ExecuteUpdateSlice(SliceInfo slice) throws IOException
	{
		String result = "";
		ExecuteSlice es = new ExecuteSlice();
		
		try{
			es.ExecuteSlice(slice.getPatterns(),slice.getDataSource());
			result="success";
		}
		catch(Exception e)
		{
			result = e.toString();
		}
		return result;
	}
	private void LogSlice(SliceInfo slice) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		DatabaseConnection db = new DatabaseConnection();
		db.SliceInfoUpdate(slice);
		
	}

}
