package com.mypack.test;

import java.util.HashMap;
import java.util.Map;

import com.packages.rdf.FileAnalyse;

public class ServletInfo {
	public Map<String,Object> FileAnalyseTransition(String fileLoc)
	{
		FileAnalyse tempObj = new FileAnalyse();
		return tempObj.GetClasses(fileLoc);
	}
	
}
