package com.packages.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.http.HttpSession;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Driver;
import com.packages.objects.SliceInfo;
import com.packages.objects.UserInfo;

public class DatabaseConnection extends DbInfo {

	public void TestConnect() throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		DriverManager.getConnection("jdbc:mysql://localhost:3306/test?root");

	}

	public void ExecuteDbCommand(String query) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(CLASS_NAME).newInstance();
		Connection con = DriverManager.getConnection(CONNECTION_STRING
				+ DB_NAME + USER_NAME);
		PreparedStatement ps = con
				.prepareStatement("insert into emrah (Name, Surname) values (?, ?)");
		ps.setString(1, "deneme1");
		ps.setString(2, "deneme2");
		ResultSet result = null;
		ps.executeUpdate();
		while (result.next()) {
			System.out.println(result.getString(1) + result.getString(2));
		}

	}

	// to obtain output parameter from database use java.sql.CallableStatement
	// java.sql.CallableStatement ps =
	// con.prepareCall("{call USER_INS(?, ?, ?, ?, ?, ?, ?)}") ;
	// ps.setString(1, user.getName());
	// ps.setString(2, user.getSurname());
	// ps.setString(3, user.getUserName());
	// ps.setString(4, user.getEmail());
	// ps.setString(5, user.getPassword());
	// ps.setBoolean(6, user.isAdmin());
	// ps.registerOutParameter(7, Types.INTEGER);
	public void InsertUser(UserInfo user) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("reached businessbase");
		Class.forName(CLASS_NAME).newInstance();

		Connection con = DriverManager.getConnection(CONNECTION_STRING
				+ DB_NAME, USER_NAME, PASSWORD);
		PreparedStatement ps = con
				.prepareCall("{call USER_INS(?, ?, ?, ?, ?, ?)}");
		ps.setString(1, user.getName());
		ps.setString(2, user.getSurname());
		ps.setString(3, user.getUserName());
		ps.setString(4, user.getEmail());
		ps.setString(5, user.getPassword());
		ps.setBoolean(6, user.isAdmin());
		ps.executeQuery();

	}

	public void UpdateUserData(UserInfo user) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("reached businessbase");
		Class.forName(CLASS_NAME).newInstance();

		Connection con = DriverManager.getConnection(CONNECTION_STRING
				+ DB_NAME, USER_NAME, PASSWORD);
		PreparedStatement ps = con
				.prepareCall("{call USER_UPD(?, ?, ?, ?, ?, ?, ?, ?)}");
		ps.setString(1, user.getName());
		ps.setString(2, user.getSurname());
		ps.setString(3, user.getUserName());
		ps.setString(4, user.getEmail());
		ps.setString(5, user.getPassword());
		ps.setBoolean(6, user.isAdmin());
		ps.setInt(7, user.getUserID());
		ps.executeQuery();

	}

	public ResultSet UserLogin(UserInfo user) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {

		Class.forName(CLASS_NAME).newInstance();

		ResultSet rs = null;
		Connection con = DriverManager.getConnection(CONNECTION_STRING
				+ DB_NAME, USER_NAME, PASSWORD);
		PreparedStatement ps = con.prepareCall("{call USER_LOGIN(?, ?)}");
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getPassword());
		rs = ps.executeQuery();

		return rs;
	}
	
	public ResultSet SliceInfoGet(SliceInfo slice, int userID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(CLASS_NAME).newInstance();
		Connection con = DriverManager.getConnection(CONNECTION_STRING
				+ DB_NAME, USER_NAME, PASSWORD);
		ResultSet rs = null;
		PreparedStatement ps = con.prepareCall("{call SLICE_GET(?,?)}");
		if(slice.getSliceID()!=0)
		{
		ps.setInt(1, slice.getSliceID());
		ps.setInt(2, userID);
		}
		else
			ps.setNull(1, Types.INTEGER);
			ps.setInt(2, userID);
		rs=ps.executeQuery();
		return rs;
	}
	
	public void SliceInfoInsert(SliceInfo slice) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		System.out.println("I made it here");
		Class.forName(CLASS_NAME).newInstance();

		Connection con = DriverManager.getConnection(CONNECTION_STRING
				+ DB_NAME, USER_NAME, PASSWORD);
		PreparedStatement ps = con
				.prepareCall("{call SLICE_INS(?, ?, ?, ?, ?, ?)}");
		ps.setString(1, slice.getSliceDescription());
		ps.setString(2, slice.getPatterns());
		ps.setString(3, slice.getQuery());
		ps.setString(4, slice.getDataSource());
		ps.setString(5, slice.getDataSourceType());
		ps.setInt(6, slice.getCreatedBy());
		System.out.println(123132);
		ps.executeQuery();
	}
	public void SliceInfoUpdate(SliceInfo slice) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(CLASS_NAME).newInstance();

		Connection con = DriverManager.getConnection(CONNECTION_STRING
				+ DB_NAME, USER_NAME, PASSWORD);
		PreparedStatement ps = con
				.prepareCall("{call SLICE_UPD(?, ?, ?, ?, ?, ?)}");
		ps.setInt(1,slice.getSliceID());
		ps.setString(2, slice.getSliceDescription());
		ps.setString(3, slice.getPatterns());
		ps.setString(4, slice.getQuery());
		ps.setString(5, slice.getDataSource());
		ps.setString(6, slice.getDataSourceType());
		ps.executeQuery();
	}
	public void PublishSlice(int sliceID, boolean isPublic) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(CLASS_NAME).newInstance();

		Connection con = DriverManager.getConnection(CONNECTION_STRING
				+ DB_NAME, USER_NAME, PASSWORD);
		PreparedStatement ps = con
				.prepareCall("{call PUBLISH_SLICE(?, ?)}");
		ps.setBoolean(1,isPublic);
		ps.setInt(2, sliceID);
		ps.executeQuery();
	}
	
}
