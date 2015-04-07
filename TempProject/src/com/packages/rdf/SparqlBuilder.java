package com.packages.rdf;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.packages.objects.RDFTriple;

public class SparqlBuilder {
	public String BuildPatternQuery(List<RDFTriple> list) {
		String query = "SELECT * WHERE { </br>";
		for (int i = 0; i < list.size(); i++) {
			RDFTriple element = list.get(i);
			query += element.getSubject() + ", " + element.getPredicate()
					+ ", " + element.getObject() + ".</br>";
		}
		query += "}";
		return query;
	}

	public String FormViewQuery(HttpServletRequest request) {
		
		int counter = Integer.parseInt(request.getParameter("hdnCounter"));
		String pattern = "Select * where { </br>";
		String subquery = "";
		String subject, predicate, object;
		int k = 0;
		int hdnCounter=0;
		int tempCtr=0;
		boolean temp = true;

		while(temp)
		{	
			if (request.getParameterMap().containsKey("hdnSelectCounter" + k)) {
				tempCtr=Integer.parseInt(request.getParameter("hdnSelectCounter"+k));
				temp = false;
			}
			k++;
		}
	
		for (int i = 0; i <= counter; i++) {
			if (request.getParameterMap().containsKey("txtSelectSubject" + i)) {
				
				hdnCounter = Integer.parseInt(request.getParameter("hdnSelectCounter"+i));				
				if(tempCtr != hdnCounter)
				{
					pattern += "{"+subquery+"} </br> UNION </br> ";
					subquery="";
					tempCtr=hdnCounter;
				}
				
				subject = CheckInstance(request.getParameter("txtSelectSubject"
						+ i));
				predicate = CheckInstance(request
						.getParameter("txtSelectPredicate" + i));
				object = CheckInstance(request.getParameter("txtSelectObject"
						+ i));
				subquery += subject + " " + predicate + " " + object + ". </br> ";				
			}
		}
		subquery = "{"+subquery+"}";
		pattern += subquery+"</br>}";
		return pattern;
	}
	public String FormClassQuery(HttpServletRequest request)
	{
		int counter = Integer.parseInt(request.getParameter("hdnClassCtr"));
		String pattern = "Select * where { ";
		String className;
		boolean temp = true;
		int k = counter;
		while(temp)
		{	
			
			if (request.getParameterMap().containsKey("txtClass" + k)) {
				temp = false;
			}
			else{k--;}
		}

		
		for (int i = 0; i <= counter; i++) {
			if (request.getParameterMap().containsKey("txtClass" + i)) {
				System.out.println("bu var ="+i);
				className = request.getParameter("txtClass"+i);
				pattern += "{ ?instance"+i+" a "+className+". "
						+ "?instance"+i+" ?p"+i+" ?o"+i+" } ";
				
				if(!((Integer)i).equals((Integer)k))
				{
					pattern += "UNION ";
				}
			}
		}
		pattern += " }";
		return pattern;
	}
	
	public String FormClassViewQuery(HttpServletRequest request)
	{
		int counter = Integer.parseInt(request.getParameter("hdnClassCtr"));
		String pattern = "Select * where {</br>";
		String className;
		boolean temp = true;
		int k = counter;
		while(temp)
		{	
			
			if (request.getParameterMap().containsKey("txtClass" + k)) {
				temp = false;
			}
			else{k--;}
		}
		
		for (int i = 0; i <= counter; i++) {
			if (request.getParameterMap().containsKey("txtClass" + i)) {
				className = request.getParameter("txtClass"+i);
				pattern += "{</br> ?instance"+i+" a "+className+".</br>"
						+ "?instance"+i+" ?p"+i+" ?o"+i+" </br>}</br>";
				if(!((Integer)i).equals((Integer)k))
				{
					pattern += "UNION</br>";
				}

			}
		}
		pattern += " }";
		return pattern;
	}

	public String FormPatternQuery(HttpServletRequest request) {
		
		int counter = Integer.parseInt(request.getParameter("hdnCounter"));
		String pattern = "Select * where { ";
		String subquery = "";
		String subject, predicate, object;
		int k = 0;
		int hdnCounter=0;
		int tempCtr=0;
		boolean temp = true;

		while(temp)
		{	
			
			if (request.getParameterMap().containsKey("hdnSelectCounter" + k)) {
				tempCtr=Integer.parseInt(request.getParameter("hdnSelectCounter"+k));
				temp = false;
			}
			
			k++;
		}
	
		for (int i = 0; i <= counter; i++) {
			if (request.getParameterMap().containsKey("txtSelectSubject" + i)) {
				
				hdnCounter = Integer.parseInt(request.getParameter("hdnSelectCounter"+i));				
				if(tempCtr != hdnCounter)
				{
					pattern += "{"+subquery+"} UNION ";
					subquery="";
					tempCtr=hdnCounter;
				}
				
				subject = CheckInstance(request.getParameter("txtSelectSubject"
						+ i));
				predicate = CheckInstance(request
						.getParameter("txtSelectPredicate" + i));
				object = CheckInstance(request.getParameter("txtSelectObject"
						+ i));
				subquery += subject + " " + predicate + " " + object + ". ";				
			}
		}
		subquery = "{"+subquery+"}";
		pattern += subquery+" }";
		return pattern;
	}

	
	public String FormPatternQuery2(HttpServletRequest request) {
		int counter = Integer.parseInt(request.getParameter("hdnCounter"));
		String pattern = "{ ";
		String subquery = "";
		String subject, predicate, object;
		
		for (int i = 0; i <= counter; i++) {
			if (request.getParameterMap().containsKey("txtSelectSubject" + i)) {
				
				subject = CheckInstance(request.getParameter("txtSelectSubject"
						+ i));
				predicate = CheckInstance(request
						.getParameter("txtSelectPredicate" + i));
				object = CheckInstance(request.getParameter("txtSelectObject"
						+ i));
				pattern += subject + " " + predicate + " " + object + ". ";
			}
		}
		pattern += " }";

		return pattern;
	}
	public String CheckInstance(String token) {
		if (token.startsWith("?") || token.startsWith("<")) {
			return token;
		} else {
			return "'" + token + "'";
		}
	}
}
