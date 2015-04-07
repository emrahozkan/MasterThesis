package com.packages.rdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteSlice {
	public String ExecuteRDFSlice(String pattern, String dataSource)
			throws IOException {
		System.out.println("yarrak afedersin");
		Process p = Runtime
				.getRuntime()
				.exec("java -jar /Users/emrahozkan/Documents/Thesis/rdfslice/rdfslice_1.6.jar "
						+ "-source /Users/emrahozkan/Documents/thesis/tutorials/LearningSPARQLExamples/ex054.ttl -patterns {?s ?p ?o}  "
						+ "-out /Users/emrahozkan/Documents/Thesis/rdfslice/standart.out -order S");
		String input = "/Users/emrahozkan/documents/thesis/rdfslice/test.nt";
		String output = "/Users/emrahozkan/Documents/Thesis/rdfslice/output.out";
		// String pattern = "{?s ?p ?o}";
		String[] list = { "source", input, "pattern", pattern, "out", output,
				"-order", "S" };
		// Process p = Runtime.getRuntime().exec(list);
		// create the process builder
		ProcessBuilder pb = new ProcessBuilder("java", "-jar",
				"rdfslice_1.6.jar", "-source",
				"/Users/emrahozkan/documents/thesis/rdfslice/test.nt",
				"-pattern", "{?s ?p ?o}", "-out",
				"/Users/emrahozkan/Documents/Thesis/rdfslice/output.out");
		pb.directory(new File("/Users/emrahozkan/Documents/Thesis/rdfslice"));
		// Process p = pb.start();
		// System.out.println(pb.command());

		try {
			// RDFSlice.main(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}
		String result = builder.toString();
		System.out.println(result);
		return result;
		// Process p =
				// Runtime.getRuntime().exec("java -jar /Users/emrahozkan/Documents/Thesis/rdfslice/rdfslice_1.6.jar "
				// +
				// "-source "+dataSource+" -patterns \""+pattern+"\" "
				// +
				// "-out /Users/emrahozkan/Documents/Thesis/rdfslice/emrah.out -order S");
				// Process p =
				// Runtime.getRuntime().exec("java -jar /Users/emrahozkan/Documents/Thesis/rdfslice/rdfslice_1.6.jar -source /users/emrahozkan/documents/thesis/rdfslice/mydata.nt -patterns {?s1 <http://www.europeana.eu/schemas/edm/isShownAt> ?o1.} -out /Users/emrahozkan/Documents/Thesis/rdfslice/dandik.out -order S");
//				Process p = Runtime
//						.getRuntime()
//						.exec("bash -c \"java -jar /Users/emrahozkan/Documents/Thesis/rdfslice/rdfslice_1.6.jar -source /users/emrahozkan/documents/thesis/rdfslice/mydata.nt -patterns {?s1 <http://www.europeana.eu/schemas/edm/isShownAt> ?o1.} -out /Users/emrahozkan/Documents/Thesis/rdfslice/sikortil.out -order S\"");
				
	}

	public String ExecuteSlice(String pattern, String dataSource)
			throws IOException {
		ProcessBuilder pb = new ProcessBuilder(
			    "java",
			    "-jar",
			    "/users/emrahozkan/documents/thesis/rdfslice/rdfslice_1.6.jar",
			    "-source",
			    dataSource,
			    "-patterns",
			    pattern,
			    "-out",
			    System.getProperty("user.home")+"/Desktop/output.out",
			    "-order",
			    "S");

		Process p = pb.inheritIO().start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			//builder.append(System.getProperty("line.separator"));
		}
		String result = builder.toString();
		System.out.println(reader.toString());
		

		
			// read the output from the command
			
			
		if(result.length() < 10)
		{
			return "success";
		}
		else{
			return result;
		}	
		
	}

}
