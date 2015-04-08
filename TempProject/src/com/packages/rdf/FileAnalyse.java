package com.packages.rdf;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.rulesys.Node_RuleVariable;
import com.hp.hpl.jena.sparql.util.StringUtils;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class FileAnalyse {
	public Map<String, Object> GetClasses(String fileLoc) {
		Map<String, Object> fileMap = new HashMap<String, Object>();
		System.out.println("I am awake");
		OntModel inf = ModelFactory.createOntologyModel();
		InputStream in = FileManager.get().open(fileLoc);

		inf.read(in, "");
		if (in == null) {
			System.out.println("File not found");
			throw new IllegalArgumentException("File not found");

		}
		ExtendedIterator<OntClass> classes = inf.listClasses();
		int tempctr = 0;
		while (classes.hasNext()) {
			tempctr++;
			OntClass essaClasse = classes.next();
			String vClasse = essaClasse.getLocalName().toString();
			fileMap.put("class" + tempctr, vClasse);
		}

		return fileMap;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, Object>> GetFileComponents(HttpServletRequest request) throws IOException {
		Map<String, Map<String, Object>> patternMap = new HashMap<String, Map<String, Object>>();
//		Map<String, Object> subjectMap = new HashMap<String, Object>();
//		Map<String, Object> predicateMap = new HashMap<String, Object>();
//		Map<String, Object> objectMap = new HashMap<String, Object>();
		Map<String, Object> classMap = new HashMap<String, Object>();
		String data = request.getParameter("txtDataSource");
		HttpSession session = request.getSession();
		session.setAttribute("dataSource", data);	
		
		
		//Model model = ModelFactory.createDefaultModel();
		ServletContext servletContext = request.getServletContext();
		String contextPath = servletContext.getRealPath("/data");
		String tdbPath = servletContext.getRealPath("/tdb");
		File dir = new File(contextPath);
		Dataset dataset = TDBFactory.createDataset(tdbPath);
		Model model = dataset.getDefaultModel();
		FileManager.get().readModel( model, data );
		
		System.out.println(dir.getAbsolutePath());
		//model.read(data);
		StmtIterator iter = model.listStatements();
		Resource subject;
		Property predicate;
		RDFNode object;
		int i = 0;
		JSONObject subJson = new JSONObject();   
		JSONObject predJson = new JSONObject(); 
		JSONObject objJson = new JSONObject(); 
        
        JSONArray objArray = new JSONArray();
        JSONArray predArray = new JSONArray();
        JSONArray subjArray = new JSONArray();
        Set<String> usedObjKeys = new HashSet<String>();
        Set<String> usedSubjKeys = new HashSet<String>();
        Set<String> usedPredKeys = new HashSet<String>();
		while (iter.hasNext()) {
			Map<String,String>objMap = new HashMap<String, String>();
			Map<String,String>predMap = new HashMap<String, String>();
			Map<String,String>subjMap = new HashMap<String, String>();
			Statement stmt = iter.nextStatement();
			subject = stmt.getSubject();
			predicate = stmt.getPredicate();
			object = stmt.getObject();
			String subjKey, predKey, objKey;
			subjKey = subject.getLocalName();
			predKey=predicate.getLocalName();

//			subjectMap.put(subject.getLocalName(),"<"+subject.getURI()+">" );
//			predicateMap.put(predicate.getLocalName(),(predicate.isLiteral() ? predicate.getLocalName() : "<"+predicate.getURI()+">"));
			subjMap.put("key", subject.getLocalName());
			subjMap.put("value",subject.getURI());
			predMap.put("key", predicate.getLocalName());
			predMap.put("value",(predicate.isLiteral() ? predicate.getLocalName() : "<"+predicate.getURI()+">"));
			
			if(object.isResource())
			{
				objKey = object.asResource().getLocalName();
//				objectMap.put(object.asResource().getLocalName(), "<"+object.asResource().getURI()+">");
				objMap.put("key", object.asResource().getLocalName());
		        objMap.put("value", "<"+object.asResource().getURI()+">");
			}
			else{
				objKey = object.toString();	
//				objectMap.put(object.toString(), object.toString());
				objMap.put("key", object.toString());
				objMap.put("value", object.toString());
			}
			i++;
			System.out.println(i);
			if(!usedObjKeys.contains(objKey))
			{
				usedObjKeys.add(objKey);
				objArray.add(objMap);
			}
			if(!usedSubjKeys.contains(subjKey))
			{
				usedSubjKeys.add(subjKey);
				subjArray.add(subjMap);
			}
			if(!usedPredKeys.contains(predKey))
			{
				usedPredKeys.add(predKey);
				predArray.add(predMap);
			}
		}

		usedPredKeys = null;
		usedSubjKeys = null;
		usedObjKeys = null;
		objJson.put("objects", objArray);
		subJson.put("subjects", subjArray);
		predJson.put("predicates",predArray);
		
	
		File subjectFile = File.createTempFile("subjects", ".json", dir); 
		File predicateFile = File.createTempFile("predicates", ".json", dir); 
		File objectFile = File.createTempFile("objects", ".json", dir); 
		
        FileWriter fileSubject = new FileWriter(subjectFile.getAbsolutePath());
        FileWriter filePredicate = new FileWriter(predicateFile.getAbsolutePath());
        FileWriter fileObject = new FileWriter(objectFile.getAbsolutePath());
        

        fileSubject.write(subJson.toJSONString());
        filePredicate.write(predJson.toJSONString());
        fileObject.write(objJson.toJSONString());
        
        subJson = null;
        predJson= null;
        objJson = null;
        
        fileSubject.flush();
        fileSubject.close();
        filePredicate.flush();
        filePredicate.close();
        fileObject.flush();
        fileObject.close();
        subjectFile.deleteOnExit();
        predicateFile.deleteOnExit();
        objectFile.deleteOnExit();
        
        session.setAttribute("subjectFile", subjectFile.getName());
        session.setAttribute("predicateFile",predicateFile.getName());
        session.setAttribute("objectFile", objectFile.getName());
        session.setAttribute("subjectFilePath", subjectFile.getAbsolutePath());
        session.setAttribute("predicateFilePath",predicateFile.getAbsolutePath());
        session.setAttribute("objectFilePath", objectFile.getAbsolutePath());
        System.out.println(objectFile.getName());
        
		String sparql = "select distinct ?Concept where {[] a ?Concept}";
		Query query = QueryFactory.create(sparql);
		QueryExecution qe = QueryExecutionFactory.create(query,model);
		com.hp.hpl.jena.query.ResultSet rs = qe.execSelect();
		model = rs.getResourceModel();
		while (rs.hasNext()) {
		    QuerySolution row= rs.next();
		    RDFNode thing= row.get("Concept");
		    classMap.put(thing.asResource().getLocalName(), thing.asResource().getURI());
		    System.out.println(thing.asResource().getLocalName());
		}
		
		FileUtils.cleanDirectory(new File(tdbPath)); 
		//patternMap.put("Subjects", subjectMap);
		//patternMap.put("Predicates", predicateMap);
		//patternMap.put("Objects",objectMap);
		patternMap.put("Classes",classMap);
		return patternMap;
	}
	public void ListClasses(HttpServletRequest request)
	{
		String data = request.getParameter("txtDataSource");
		Model model = ModelFactory.createOntologyModel();
		model.read(data);
		String sparql = "select distinct ?Concept where {[] a ?Concept}";
		Query query = QueryFactory.create(sparql);
		QueryExecution qe = QueryExecutionFactory.create(query,model);
		com.hp.hpl.jena.query.ResultSet rs = qe.execSelect();
		model = rs.getResourceModel();
		while (rs.hasNext()) {
		    QuerySolution row= rs.next();
		    RDFNode thing= row.get("Concept");
		    String a = thing.asResource().getLocalName()+" / "+thing.asResource().getURI().toString();
		    System.out.println(a);
		}		
	}
	
	
}
