package com.packages.rdf;

import javax.servlet.http.HttpServletRequest;

import com.packages.objects.RDFTriple;

public class TripleFormer {
	public RDFTriple CreateTriple(HttpServletRequest request) {
		RDFTriple triple = new RDFTriple();
		String subject = request.getParameter("radGroupSubj");
		switch (subject) {
		case "Variable":
			triple.setSubject("?" + request.getParameter("txtVariableSubj"));
			break;
		case "Resource":
			triple.setSubject(request.getParameter("ddlSubject"));
			break;
		default:
			break;
		}
		String predicate = request.getParameter("radGroupPred");
		switch (predicate) {
		case "Variable":
			triple.setPredicate("?" + request.getParameter("txtVariablePred"));
			break;
		case "Resource":
			triple.setPredicate(request.getParameter("ddlPredicate"));
			break;
		default:
			break;
		}
		String object = request.getParameter("radGroupObj");
		switch (object) {
		case "Variable":
			triple.setObject("?" + request.getParameter("txtVariableObj"));
			break;
		case "Resource":
			triple.setObject(request.getParameter("ddlObject"));
			break;
		case "Literal":
			triple.setObject("\"" + request.getParameter("txtLiteral") + "\"");
		default:
			break;
		}
		return triple;
	}
}
