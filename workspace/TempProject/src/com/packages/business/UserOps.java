package com.packages.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import arq.rset;

import com.packages.database.DatabaseConnection;
import com.packages.database.QueryBase;
import com.packages.objects.UserInfo;

public class UserOps {
	public QueryBase queryObj;
	public DatabaseConnection dbCon = new DatabaseConnection();

	public Map<String,Object> CreateUser(HttpServletRequest request) {
		UserInfo user = new UserInfo();
		user.setName(request.getParameter("txtName"));
		user.setSurname(request.getParameter("txtSurname"));
		user.setUserName(request.getParameter("txtUserName"));
		user.setEmail(request.getParameter("txtEmail"));
		user.setPassword(request.getParameter("txtPassword"));
		user.setAdmin(request.getParameter("chkAdmin") != null);
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			dbCon.InsertUser(user);
			map.put("createResult", true);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("createResult", false);
			map.put("errorMessage", e.getMessage());
		}
		System.out.println(map.get("errorMessage"));
		return map;

	}

	public void UpdateUser(HttpServletRequest request) {
		UserInfo user = new UserInfo();
		user.setName(request.getParameter("txtName"));
		user.setSurname(request.getParameter("txtSurname"));
		user.setEmail(request.getParameter("txtEmail"));
		user.setPassword(request.getParameter("txtPassword"));
		user.setAdmin(request.getParameter("chkAdmin") != null);
		user.setUserID(Integer.parseInt(request.getParameter("hdnUserID")));
	}

	public Map<String, Object> UserLoginCheck(HttpServletRequest request) throws SQLException {
		UserInfo user = new UserInfo();
		user.setUserName(request.getParameter("txtUserName"));
		user.setPassword(request.getParameter("txtPassword"));
		ResultSet rs=null;
		boolean result=false;
		Map<String, Object> temp= new HashMap<String, Object>();
		try {
			rs = dbCon.UserLogin(user);
			System.out.println("I have reached here");
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if(rs.next())
		{
			user.setName((String)rs.getObject("NAME"));
			user.setSurname((String)rs.getObject("SURNAME"));
			user.setUserID((int)rs.getObject("USER_ID"));
			user.setUserName((String)rs.getObject("USERNAME"));
			user.setAdmin((boolean)rs.getObject("IS_ADMIN") );
			user.setEmail((String)rs.getObject("EMAIL"));
			temp.put("userData", user);
			temp.put("userName", rs.getObject("USERNAME"));
			temp.put("loginResult", true);
		}else{
			temp.put("loginResult",false);
		}
		return temp;
	}

}
