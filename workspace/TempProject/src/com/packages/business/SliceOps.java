package com.packages.business;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.packages.database.DatabaseConnection;
import com.packages.objects.SliceInfo;
import com.packages.objects.UserInfo;
import com.packages.rdf.SparqlBuilder;

public class SliceOps {
	public SliceInfo GetSlice(HttpServletRequest request) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		DatabaseConnection db = new DatabaseConnection();
		SliceInfo slice = new SliceInfo();
		slice.setSliceID(Integer.parseInt(request.getParameter("hdnSliceID")));
		ResultSet rs = null;
		HttpSession session = request.getSession();
		UserInfo user = new UserInfo();
		user = (UserInfo)session.getAttribute("userData");
		rs = db.SliceInfoGet(slice,user.getUserID());
		
		while(rs.next())
		{
			slice.setDataSource(rs.getString("DATA_SOURCE"));
			slice.setQuery(rs.getString("QUERY"));
			slice.setDataSourceType(rs.getString("DATA_SOURCE_TYPE"));
			slice.setPatterns(rs.getString("PATTERN"));
			slice.setSliceDescription(rs.getString("SLICE_DESC"));
		}
		return slice;
	}
}
