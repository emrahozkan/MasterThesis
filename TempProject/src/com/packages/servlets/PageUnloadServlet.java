package com.packages.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PageUnloadServlet
 */
@WebServlet("/PageUnloadServlet")
public class PageUnloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageUnloadServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("subjectFilePath") != null) {
			File file = new File(session.getAttribute("subjectFilePath").toString());
			file.delete();
			file = new File(session.getAttribute("predicateFilePath").toString());
			file.delete();
			file = new File(session.getAttribute("objectFilePath").toString());
			file.delete();
			session.removeAttribute("subjectFilePath");
			session.removeAttribute("predicateFilePath");
			session.removeAttribute("objectFilePath");
			
		}
	}

}
