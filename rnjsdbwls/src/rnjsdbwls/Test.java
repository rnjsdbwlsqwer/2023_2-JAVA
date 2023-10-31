package rnjsdbwls;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {

	public static void main(String[] args) throws IOException {
		
		File file1 = new File("obama.txt");
		File file2 = new File("output.txt");
		char CharCounter =  0;
		BufferedReader in = (new BufferedReader(new FileReader(file1)));
		PrintWriter out = (new PrintWriter(new FileWriter(file2)));
		
		int ch;
		
		String line = null;
		
		while ((line = in.readLine()) != null) {
			String line2 = line.toUpperCase();
		
			out.println(line2);
			
			
		}
				
				
				
		
		
		
		
		in.close();
		out.close();
		// TODO Auto-generated method stub

	}

}