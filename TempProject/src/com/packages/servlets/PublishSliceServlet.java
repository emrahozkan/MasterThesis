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
import com.packages.database.DatabaseConnection;
import com.packages.objects.SliceInfo;

/**
 * Servlet implementation class PublishSliceServlet
 */
@WebServlet("/PublishSliceServlet")
public class PublishSliceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishSliceServlet() {
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
		System.out.println(request.getParameter("btnPublishSlice"));
		String sliceResult="";
		sliceResult = PublishSlice(request);
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
	private String PublishSlice(HttpServletRequest request)
	{
		String result = "";
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.PublishSlice(Integer.parseInt(request.getParameter("hdnSliceID")), true);
			result = "success";
		} catch (NumberFormatException | InstantiationException
				| IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}

}
