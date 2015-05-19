package com.packages.rdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.simple.*;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class FileAnalyse {
	private static final String[] REPLACEMENT_CHARS;
	private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
	static {
	    REPLACEMENT_CHARS = new String[128];
	    for (int i = 0; i <= 0x1f; i++) {
	      REPLACEMENT_CHARS[i] = String.format("\\u%04x", (int) i);
	    }
	    REPLACEMENT_CHARS['"'] = "\\\"";
	    REPLACEMENT_CHARS['\\'] = "\\\\";
	    REPLACEMENT_CHARS['\t'] = "\\t";
	    REPLACEMENT_CHARS['\b'] = "\\b";
	    REPLACEMENT_CHARS['\n'] = "\\n";
	    REPLACEMENT_CHARS['\r'] = "\\r";
	    REPLACEMENT_CHARS['\f'] = "\\f";
	    HTML_SAFE_REPLACEMENT_CHARS = REPLACEMENT_CHARS.clone();
	    HTML_SAFE_REPLACEMENT_CHARS['<'] = "\\u003c";
	    HTML_SAFE_REPLACEMENT_CHARS['>'] = "\\u003e";
	    HTML_SAFE_REPLACEMENT_CHARS['&'] = "\\u0026";
	    HTML_SAFE_REPLACEMENT_CHARS['='] = "\\u003d";
	    HTML_SAFE_REPLACEMENT_CHARS['\''] = "\\u0027";
	  }
	private Byte[] getActual(String value) throws IOException {
	    Byte[] temp = null; 
	    
	    return temp;
	    
	  }
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
		Map<String, Object> classMap = new HashMap<String, Object>();
		String data = request.getParameter("txtDataSource");
		HttpSession session = request.getSession();
		session.setAttribute("dataSource", data);	
		
		
		ServletContext servletContext = request.getServletContext();
		String contextPath = servletContext.getRealPath("/data");
		String tdbPath = servletContext.getRealPath("/tdb");
		File dir = new File(contextPath);
		
		//JsonWriter oStream;
		File subjectFile = File.createTempFile("subjects", ".json", dir); //burası var
		File predicateFile = File.createTempFile("predicates", ".json", dir); 
		File objectFile = File.createTempFile("objects", ".json", dir);
		
	
		FileWriter subjectStream = new FileWriter(subjectFile,true);
		FileWriter pStream = new FileWriter(predicateFile,true);
		FileWriter oStream = new FileWriter(objectFile,true); 
		//oStream = new JsonWriter(new FileWriter(objectFile,true));
		
		System.out.println(subjectFile.getAbsolutePath());
		subjectStream.write("{\"subjects\" : [ ");
		pStream.write( "{\"predicates\" : [ ");
		oStream.write( "{\"objects\" : [ ");
		System.out.println(contextPath);
		System.out.println(tdbPath);
		Dataset dataset = TDBFactory.createDataset(tdbPath);
		Model model = dataset.getDefaultModel();
		System.out.println(1);
		InputStream str = FileManager.get().open(data);
		if(data.substring(data.lastIndexOf('.')).equals(".gz"))
		{
			str = new GZIPInputStream(str);
		}
		System.out.println(2);
		String fileType = "";
		fileType = data.substring(data.substring(0, data.lastIndexOf('.')).lastIndexOf('.'),data.lastIndexOf('.'));
		System.out.println(fileType);
		if(fileType.equals(".nt"))
		{
			model.read(str,null, "N-TRIPLES");	
		}
		else{
			model.read(str,null);	
		}
		System.out.println(dir.getAbsolutePath());
		StmtIterator iter = model.listStatements();
		Resource subject;
		Property predicate;
		RDFNode object;
		int i = 0;
//		JSONObject subJson = new JSONObject();   
//		JSONObject predJson = new JSONObject(); 
//		JSONObject objJson = new JSONObject(); 
//        
//        JSONArray objArray = new JSONArray();
//        JSONArray predArray = new JSONArray();
//        JSONArray subjArray = new JSONArray();
        Set<String> usedObjKeys = new HashSet<String>();
        Set<String> usedSubjKeys = new HashSet<String>();
        Set<String> usedPredKeys = new HashSet<String>();
//        oStream.name("objects");
//        oStream.beginArray();
        JSONObject gson = new JSONObject();
		while (iter.hasNext()) {
			JSONObject objJson = new JSONObject();
			JSONObject subJson = new JSONObject();   
			JSONObject predJson = new JSONObject(); 
			//Map<String,String>objMap = new HashMap<String, String>();
			//Map<String,String>predMap = new HashMap<String, String>();
			//Map<String,String>subjMap = new HashMap<String, String>();
			Statement stmt = iter.nextStatement();
			subject = stmt.getSubject();
			predicate = stmt.getPredicate();
			object = stmt.getObject();
			String subjKey, predKey, objKey;
			String oKey, oValue, pKey, pValue, sKey, sValue;
			
			subjKey = subject.getLocalName();
			predKey=predicate.getLocalName();

//			subjMap.put("key", subject.getLocalName());
//			subjMap.put("value",subject.getURI());
			
			
			pKey = predicate.getLocalName();
			pValue = (predicate.isLiteral() ? predicate.getLocalName() : "<"+predicate.getURI()+">");
			sKey = subject.getLocalName();
			sValue = subject.getURI();
//			predMap.put("key", predicate.getLocalName());
//			predMap.put("value",(predicate.isLiteral() ? predicate.getLocalName() : "<"+predicate.getURI()+">"));
			
			if(object.isResource())
			{
				objKey = object.asResource().getLocalName();
				oKey = object.asResource().getLocalName();
				oValue = "<"+object.asResource().getURI()+">";	
//				objMap.put("key", object.asResource().getLocalName());
//		        objMap.put("value", "<"+object.asResource().getURI()+">");
			}
			else{
				objKey = object.toString();	
				oKey = object.toString();
				oValue = object.toString();
//				objMap.put("key", object.toString());
//				objMap.put("value", object.toString());
			}
			i++;
			System.out.println(i);
			if(!usedObjKeys.contains(objKey))
			{			
				
//				oStream.name("key").value(oKey);
//				oStream.name("value").value(oValue);
				//oStream.write(("{\"value\":\""+ +"\",\"key\" : \""+ new JSONObject(oKey).toString() +"\" }, ").getBytes());
				objJson.put("key", oKey);
				objJson.put("value", oValue);
				oStream.write(objJson.toJSONString()+" , ");
				usedObjKeys.add(objKey);
				//objArray.add(objMap);
			}
			if(!usedSubjKeys.contains(subjKey))
			{
				subJson.put("key", sKey);
				subJson.put("value", sValue);
				subjectStream.write(subJson.toJSONString()+ " , ");
				//subjectStream.write(("{\"value\":\""+subject.getURI()+"\",\"key\" : \""+ subject.getLocalName() +"\" }, ").getBytes());
				usedSubjKeys.add(subjKey);
//				subjArray.add(subjMap);
			}
			if(!usedPredKeys.contains(predKey))
			{
				predJson.put("key", pKey);
				predJson.put("value", pValue);
				pStream.write(predJson.toJSONString()+" , ");
				//pStream.write(("{\"value\":\""+pValue+"\",\"key\" : \""+ pKey +"\" }, ").getBytes());
				usedPredKeys.add(predKey);
				//predArray.add(predMap);
			}
		}
		subjectStream.write(("{\"value\":\"\",\"key\" : \"\" }]} "));
		subjectStream.flush();
		subjectStream.close();
		pStream.write(("{\"value\":\"\",\"key\" : \"\" }]} "));
		pStream.flush();
		pStream.close();
		oStream.write(("{\"value\":\"\",\"key\" : \"\" }]} "));
//		oStream.endArray();
//		oStream.endObject();
		oStream.flush();
		oStream.close();
		
		usedPredKeys = null;
		usedSubjKeys = null;
		usedObjKeys = null;
		
//		objJson.put("objects", objArray);
//		subJson.put("subjects", subjArray);
//		predJson.put("predicates",predArray);
		
	
		//File subjectFile = File.createTempFile("subjects", ".json", dir); 
//		File predicateFile = File.createTempFile("predicates", ".json", dir); 
//		File objectFile = File.createTempFile("objects", ".json", dir); 
		
//        //FileWriter fileSubject = new FileWriter(subjectFile.getAbsolutePath());
//        FileWriter filePredicate = new FileWriter(predicateFile.getAbsolutePath());
//        FileWriter fileObject = new FileWriter(objectFile.getAbsolutePath());
        

        //fileSubject.write(subJson.toJSONString());
//        filePredicate.write(predJson.toJSONString());
//        fileObject.write(objJson.toJSONString());
        
//        subJson = null;
//        predJson= null;
//        objJson = null;
        
        //fileSubject.flush();
        //fileSubject.close();
//        filePredicate.flush();
//        filePredicate.close();
//        fileObject.flush();
//        fileObject.close();
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
