package com.packages.objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.UrlValidator;

import com.packages.rdf.SparqlBuilder;

public class SliceInfo extends InfoMain {
	private int sliceID;
	private String patterns;
	private String query;
	private String dataSource;
	private String dataSourceType;
	private String sliceDescription;
	private int createdBy;
	
	public int getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(int createdBy)
	{
		this.createdBy = createdBy;
	}
	public int getSliceID() {
		return sliceID;
	}
	public void setSliceID(int sliceID) {
		this.sliceID = sliceID;
	}
	public String getPatterns() {
		return patterns;
	}
	public void setPatterns(String patterns) {
		this.patterns = patterns;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public String getSliceDescription() {
		return sliceDescription;
	}
	public void setSliceDescription(String sliceDescription) {
		this.sliceDescription = sliceDescription;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	@SuppressWarnings("deprecation")
	public SliceInfo CreateSlice(HttpServletRequest request)
	{	
		UrlValidator valid = new UrlValidator();
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("userData");
		String data = session.getAttribute("dataSource").toString();
		SparqlBuilder sb = new SparqlBuilder();
		
		SliceInfo slice = new SliceInfo();
		if(request.getParameterMap().containsKey("txtSliceId"))
		{
			slice.setSliceID(Integer.parseInt(request.getParameter("txtSliceId")));
		}
		slice.setSliceDescription(request.getParameter("txtDescription"));
		slice.setPatterns(sb.FormPatternQuery(request));
		slice.setQuery(sb.FormViewQuery(request));
		slice.setDataSource(data);
		slice.setCreatedBy(user.getUserID());
		if(valid.isValid(data))
		{
			slice.setDataSourceType("url");
		}
		else{
			slice.setDataSourceType("file");
		}
		return slice;
	}
	@SuppressWarnings("deprecation")
	public SliceInfo CreateClassSlice(HttpServletRequest request)
	{	
		UrlValidator valid = new UrlValidator();
		HttpSession session = request.getSession();
		String data = session.getAttribute("dataSource").toString();
		SparqlBuilder sb = new SparqlBuilder();
		UserInfo user = (UserInfo) session.getAttribute("userData");
		SliceInfo slice = new SliceInfo();
		if(request.getParameterMap().containsKey("txtSliceId"))
		{
			slice.setSliceID(Integer.parseInt(request.getParameter("txtSliceId")));
		}
		slice.setSliceDescription(request.getParameter("txtClassDesc"));
		slice.setPatterns(sb.FormClassQuery(request));
		slice.setQuery(sb.FormClassViewQuery(request));
		slice.setDataSource(data);
		slice.setCreatedBy(user.getUserID());
		if(valid.isValid(data))
		{
			slice.setDataSourceType("url");
		}
		else{
			slice.setDataSourceType("file");
		}
		return slice;
	}
	
}
