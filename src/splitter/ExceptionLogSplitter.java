package splitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ExceptionLogSplitter {
//	private File sourceFolder = new File(System.getProperty("user.dir"));
	private String targetFileName = "ExceptionLog"; 
	private File sourceFile = new File(targetFileName + ".txt");
	
	InputStream is;
	private String date = "";
	
	private static Pattern p = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}).{0,200}");
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		 
		
		ExceptionLogSplitter es = new ExceptionLogSplitter();
		
		es.parse();
		

	}
	
	public void parse() throws IOException {
		System.out.println(sourceFile);
		is = new FileInputStream(sourceFile);
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = "";
		LinkedList<String> listOfMessages = new LinkedList<String>();
		
//		line = reader.readLine();
//		Matcher m = p.matcher(line);
		
		System.out.println("date " + date);
		
		while ((line = reader.readLine()) != null) {
			
//			m = p.matcher(line);
			Matcher m = p.matcher(line);
			while(m.find()) {
			if (date.equals("")) {
				date = m.group(1);
			}
			
			if ( !(date.equals("")) && !(m.group(1).equals(date)) ) {
				write(listOfMessages, date);
				date = m.group(1);
				listOfMessages = new LinkedList<String>();
			}
			
			listOfMessages.add(line);
			
			}
			}
	
	}
	
	
	private void write(List<String> msg, String date) throws IOException {
		File targetFile = new File(targetFileName + "_" + date + ".txt");
			
		PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(targetFile)));
		System.out.println("I write output to "
				+ targetFile.getAbsolutePath());
		
		for (String s : msg) {
			out1.println(s);
		}
		out1.close();
		
		
	}
}
