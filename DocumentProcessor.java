package project5.documentprocessing;

import project5.documentprocessing.data.Document;

public class DocumentProcessor {

	static String[] ignore =  {};
	
	public static void main(String[] args) {
		if (args.length == 1) {
			Document d = new Document(args[0], ignore);					
			System.out.println(d);
		}
		else {
			System.out.println("Must provide an url as command-line argument.");
		}

	}

}