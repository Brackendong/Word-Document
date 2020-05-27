/*
 * Word.java
 * Author: JiaoAng Dong 
 * Submission Date: 11/22/2019
 *
 * Purpose: Word.java serves the basics for document.java
 * It have methods that include finding the string value of the 
 * of the word array. Methods that finds out the frequency and 
 * equals for word.
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

public class Word implements Comparable<Word> {

	
	// create constructors and initiate instance variables
	   private String wordValue;
	   private int frequency;
	   
	   public Word (String s) {
           wordValue = s;
           frequency = 1;
		   
	   }
	   // Add the constructor and other instance methods

	   // return string wordValue
	   
	   public String getWordValue() {
	       return wordValue;
	   }

	   // return int frequency
	   
	   public int getFrequency() {
	       return frequency;
	   }
	   
	   // increment frequency
	   
	   public void incrementFrequency() {
	          frequency++;
	   }
	   
	   // compares wordvalue with word w, returns boolean
	   
	   public boolean equals(Word w) {
	       
		   return this.wordValue.equalsIgnoreCase(w.getWordValue());
	   }

       // compare word w with wordvalue return int of compare method
	   
	   public int compareTo(Word w) {
	       return this.wordValue.compareTo(w.getWordValue());
	   }
	   
}
