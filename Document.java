/*
 * Document.java
 * Author: JiaoAng Dong 
 * Submission Date: 11/22/2019
 *
 * Purpose: Document.java represents a processed document with 
 * it's first constructor takes a single URL and uses it to initialize 
 * the reader instance variable, initializes the array of Word 
 * objects to a size of 10, and then calls methods that can process the 
 * document, shrink the words array, and sort the words array , 
 * strip the punctuation etc...
 * 
 *
 * Statement of Academic Honesty:
 *
 * The following code represents my own work. I have neither
 * received nor given inappropriate assistance. I have not copied
 * or modified code from any source other than the course webpage
 * or the course textbook. I recognize that any unauthorized
 * assistance or plagiarism will be handled in accordance with
 * the University of Georgia's Academic Honesty Policy and the
 * policies of this course. I recognize that my work is based
 * on a programming project created by the Department of
 * Computer Science at the University of Georgia. Any publishing
 * of source code for this project is strictly prohibited without
 * written consent from the Department of Computer Science.
 */
package project5.documentprocessing.data;

import project5.documentprocessing.reader.DocumentReader;


public class Document {
	
	// initiate instance variables and arrays.

	Word[] words;			// An array of Word objects representing the words in this document.
	String[] ignore;		// An array of Strings that should be ignored when this document is processed.
	DocumentReader reader;	// A DocumentReader from which to read the lines of the document.
	int uniqueWords;		// An integer which counts the number of unique words seen.
	int lines;		        // An integer that stores the numbers of lines in the document.


	// This constructor takes String URL parameter and uses it to initialize reader
 	public Document(String url) {

		//put your constructor definition here
		
		reader = new DocumentReader (url) ; 
		
		words = new Word[10];
		
		for(int i = 0 ; i < words.length ; i++ ) {
			words[i] = new Word("");
			
		}
		ignore = new String[0];
		
		processDocument();
		shrink();
		sort();
	}
 	
 	// Similar to the first constructor, but at the same time takes in
 	// the ignore array used for words to ignore
 	
	public Document(String url, String[] ignore) {

		//put your constructor definition here
		
		//assigning the url to the reader using documentreader
		reader = new DocumentReader (url);
		words = new Word[10];
		for(int i = 0 ; i < words.length ; i++ ) {
			words[i] = new Word("");
			
		}
		//assigning the ignore to the this.ignore
		
		// new copy for the ignore words list
		String[] ignorecopy  = new String[ignore.length];
		for (int i = 0 ; i < ignore.length; i++) {
			ignorecopy[i] = ignore[i];
		}
		
		// modify the instance variable 
		this.ignore= ignorecopy;
		
		processDocument();
		shrink();
		sort();
	}

	// return the length of the word
	
	public int getWordsSize() {

		//put your method definition here

		return words.length;
	}

	// returns how many words there are
	
	public int getWordCount() {

		//put your method definition here

		return uniqueWords;
	}

	// return the number of lines 
	public int getLineCount() {

		//put your method definition here

		return lines;
	}

	// Creates a newarray having twice the current word array's size 
	// copies old array into the new one. 
	// the words array instance variable is updated. 
	
	private void expandWords() {

		//put your method definition here

		Word[] newarray = new Word[this.words.length * 2];
		for (int i = 0 ; i < words.length ; i++) {
			newarray[i] = words[i];
		}
		words = newarray ;

	}
	
	//Shrinks the words array down to exactly the length needed to store all of the
	//words seen in the document.
	
	private void shrink()  {

		//put your method definition here
		Word[] randomWords = new Word[uniqueWords];

		int j = 0;
		for (int i=0; i < words.length;i++)
		{
			if (!(words[i].getWordValue().equalsIgnoreCase(""))) {
				randomWords[j]=words[i];
				j++;


			}
		}
		words=randomWords;
	}

	// returns a "stripped" string with all of he non-letter or digit characters removed from w.
	
	private String stripPunctuation(String w) {

		//put your method definition here
		String newstring = "";
		for (int i = 0 ; i < w.length() ; i++) {
			if (Character.isLetterOrDigit(w.charAt(i)) || Character.isWhitespace(w.charAt(i))) {
				newstring += w.charAt(i);
			}
		}
		return newstring;
	}
	
	//Returns true if a Word object for w is already in the array
	
	public boolean seenWord(String w) {


		//put your method definition here
		boolean value = false;
		for ( int i = 0 ; i < words.length ; i ++) {
			if( words[i].getWordValue().equalsIgnoreCase(w)) {
				value = true;
			}
		}
		return value;
	}

	//calls shrink() and then sorts the Word objects in the words array according to the ascending
	//lexicographical ordering of the strings they store. This method must be called by the constructor.


	private void sort() {
		int minIdx;
		Word temp;
		this.shrink();
		for (int i = 0; i < this.words.length; i++) {
			minIdx = i;
			for (int j = i+1; j < this.words.length; j++) {
				if (this.words[j].compareTo(this.words[minIdx]) < 0) {
					minIdx = j ;
				}
			}
			temp = this.words[minIdx];
			this.words[minIdx] = this.words[i];
			this.words[i] = temp;
		}
	}
	
	//Returns the index in the words array of the Word object
	//representing the word string parameter w. If it is not 
	//in the words array, the method returns -1.

	private int findWord(String w) {

		//put your method definition here
		int index = -1;	
		for ( int i = 0 ; i < words.length ; i ++) {
			if( words[i].getWordValue().equalsIgnoreCase(w) ) {
				index = i;
			}
			
		}
		return index;

	}
	
	//Returns the index in the strings array of w. 
    //If w is not in the array, the method returns -1.
	private int findString(String w, String[] strings) {

		int index = -1;	
		for ( int i = 0 ; i < strings.length ; i ++) {
			if( strings[i].equalsIgnoreCase(w) ) {
				index = i; 
			}
			
			
		}
		return index;


	}
	
	//This method adds a new word object to the words array 
	//if it does not exist in the words array.
	
	private void addWord(String w) {

		Word[] newarray = new Word[words.length + 1];
		Word newWord = new Word(w);
		boolean booleanIgnored = false;
		
		//This for loop checks to see if w needs to be ignored
		for(int i = 0; i < ignore.length; i++)
		{
			if(w.equalsIgnoreCase(ignore[i]))
				booleanIgnored = true;
		}
		
		//This for loop copies every word from the words array to newarray
		for(int i = 0; i < words.length; i++)
		{
			newarray[i]=words[i];
		}
		
		//If String w is already stored in the words array
		if (seenWord(w)) {
			words[findWord(w)].incrementFrequency();
		}
		//If  w is not stored in the words array
		else
		{
			if (booleanIgnored == false)
			{
				uniqueWords++;
				newarray[newarray.length - 1] = newWord;
				words = newarray;
			}
		}
		
		
		
		
		
	}
	//Reads all of the lines from the DocumentReader and parses out each word, 
	//adding each word to the words array. Importantly, strings contained in the 
	//ignore list are not added as words to the words array.
	private void processDocument() {

		//put your method definition here

    
    String newstring = "";
	String[] stringarray;
	
	//While there is a line in the document
	while (reader.hasLines() == true)
	{
		newstring += reader.getLine() + " ";
		lines++;
	}
	
	// Removes the non-letter and digit characters
	newstring = stripPunctuation(newstring);
	// Extracts all of the words from a line by using  split 
	stringarray = newstring.split(" ");
	// This for loop adds all of the words that were parsed
	for ( int i = 0; i < stringarray.length; i++)
		addWord(stringarray[i].toLowerCase());
	}
	

	// Returns a string with the document line count and word count on the first line 
	// and then the word string and frequency for each unique word found in the document 
	// then prints Words size.
	
	public String toString() {

		//put your method definition here
		String Output = "";
		Output += "Line Count: " + getLineCount() + " Word Count: " + getWordCount();
		
		for (int i = 0; i < words.length; i++)
		{
			Output += "\nWord: " + words[i].getWordValue() + " Frequency: " + words[i].getFrequency();
		}
		Output += "\nWords Size: " + getWordsSize();
		
		return Output;
		
		
	}

}