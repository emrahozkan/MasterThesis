package com.packages.objects;

public class RDFTriple {
	private String Subject;
	private String Predicate;
	private String Object;
	private boolean Literal;
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getPredicate() {
		return Predicate;
	}
	public void setPredicate(String predicate) {
		Predicate = predicate;
	}
	public String getObject() {
		return Object;
	}
	public void setObject(String object) {
		Object = object;
	}
	public boolean isLiteral() {
		return Literal;
	}
	public void setLiteral(boolean literal) {
		Literal = literal;
	}
}
