package com.packages.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import sun.tools.jar.CommandLine;

import com.google.gson.Gson;
import com.packages.database.DatabaseConnection;
import com.packages.objects.RDFTriple;
import com.packages.rdf.FileAnalyse;
import com.packages.rdf.SparqlBuilder;
import com.packages.rdf.TripleFormer;

/**
 * Servlet implementation class CreatePatternServlet
 */
@WebServlet("/CreatePatternServlet")
public class CreatePatternServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatePatternServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do get create pattern");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();
		 System.out.println(request.getParameter("radGroupQueryType"));
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Map<String, Object>> tempMap = new HashMap<String, Map<String, Object>>();
		try {
			tempMap = GetStatements(request);
			
			map.put("subjectFile", session.getAttribute("subjectFile"));
			map.put("objectFile", session.getAttribute("objectFile"));
			map.put("predicateFile", session.getAttribute("predicateFile"));
			map.put("Classes", tempMap.get("Classes"));
			map.put("analyseResult", true);
			System.out.println("herşey tamam");
		} catch (Exception e) {
			map.put("analyseResult", e.toString());
		}
		write(response, map);
		
		 
 	   	
 	   	
        
	}

	private void write(HttpServletResponse response, Map<String, Object> map)
			throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}

	protected String FormQuery(HttpServletRequest request) {
		SparqlBuilder obj = new SparqlBuilder();
		return obj.FormPatternQuery(request);
	}

	protected Map<String, Map<String, Object>> GetStatements(
			HttpServletRequest request) {
		FileAnalyse fa = new FileAnalyse();
		Map<String, Map<String,Object>>result = new HashMap<String,Map<String,Object>>();
		try{
			result = fa.GetFileComponents(request);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return result;
	}

}
