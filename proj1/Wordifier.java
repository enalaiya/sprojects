/* 
 * Wordifier.java
 *
 * Implements methods for iteratively learning words from a 
 * character-segmented text file, and then evaluates how good they are
 *
 * Students may only use functionality provided in the packages
 *     java.lang
 *     java.util 
 *     java.io
 * 
 * Use of any additional Java Class Library components is not permitted 
 * 
 * Elizabeth Schoen
 * David Voye
 *
 */

//import java.util.LinkedList;
//import java.util.HashMap;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Wordifier {

    // loadSentences
    // Preconditions:
    //    - textFilename is the name of a plaintext input file
    // Postconditions:
    //  - A LinkedList<String> object is returned that contains
    //    all of the tokens in the input file, in order
    // Notes:
    //  - If opening any file throws a FileNotFoundException, print to standard error:
    //        "Error: Unable to open file " + textFilename
    //        (where textFilename contains the name of the problem file)
    //      and then exit with value 1 (i.e. System.exit(1))
	
      
 // -----------------------------    LOAD SENTENCES    --------------------------------------------------------------    
 
   public static LinkedList<String> loadSentences(String textFilename ){
   LinkedList<String> data = new LinkedList<String>();
   Scanner input;
   try{
      // Creates a new scanner and new file from loadStentences String input paramenter
      input = new Scanner(new File(textFilename));
 
      // Creates new empty linked list
     // LinkedList<String> data = new LinkedList<String>();
 
       // While loop that checks if text file has another token
      while(input.hasNext()){
         String word = input.next();
          data.add(word);
      } // End of while loop
   
  //System.out.println(data.toString());  
     
  }
  catch(FileNotFoundException e){
      e.printStackTrace();
      //System.out.println("Error: Unable to open file " + textFilename);
     // System.exit(1);
  }
  return data;
  } // End of loadSentences method

      
 // -----------------------------    FindNewWords METHOD    ---------------------------------------------------------------------        
      
      
      
		
	
    // findNewWords
    // Preconditions:
    //    - bigramCounts maps bigrams to the number of times the bigram appears in the data
    //    - scores maps bigrams to its bigram product score 
    //    - countThreshold is a threshold on the counts
    //    - probabilityThreshold is a threshold on the bigram product score 
    // Postconditions:
    //    - A HashSet is created and returned, containing all bigrams that meet the following criteria
    //        1) the bigram is a key in bigramCounts
    //        2) the count of the bigram is >= countThreshold
    //        3) the score of the bigram is >= probabilityThreshold
    //      Formatting note: keys in the returned HashSet should include a space between the two tokens in the bigram
    
    
	public static HashSet<String> findNewWords( HashMap<String,Integer> bigramCounts, HashMap<String,Double> scores, int countThreshold, double probabilityThreshold ) {
		HashSet<String> newWords = new HashSet<String>(); // Creates new HashSet called newWords
      for(String pair : bigramCounts.keySet()){  // Iterates through bigramCounts Keys             //System.out.println(pair);
           int occurances = bigramCounts.get(pair);  // Stores number of times bigram occures in int variable called occurances
           //System.out.println(scores.get(pair));
           double prob = scores.get(pair);  // Access's Scores HashSet and stores double value of probablity into prob variable 
           if (( occurances >= countThreshold) && (prob >= probabilityThreshold)) {  // Two condition if statment that checks if bigram is larger than or equal to int countThreshold and >= probabilityThreshold.  If so store the bigram into new HashSet
               newWords.add(pair);
           } // End of if 
      
      } // End of for each loop 
       
    // System.out.println(newWords);
      return newWords;
	} // End of findNewWords method

   
// ------------------------------    resegment METHOD      ---------------------------------   

    // resegment
    // Preconditions:
    //    - previousData is the LinkedList representation of the data
    //    - newWords is the HashSet containing the new words (after merging)
    // Postconditions:
    //    - A new LinkedList is returned, which contains the same information as
    //      previousData, but any pairs of words in the newWords set have been merged
    //      to a single entry (merge from left to right)
    //
    //      For example, if the previous linked list contained the following items:
    //         A B C D E F G H I
    //      and the newWords contained the entries "B C" and "G H", then the returned list would have 
    //         A BC D E F GH I
	public static LinkedList<String> resegment( LinkedList<String> previousData, HashSet<String> newWords ) {
   // Creates a new LinkedList called newLinkedList 
   LinkedList<String> newLinkedList = new LinkedList<String> ();  // Creates new linkedList to resegment new words found
      
   ListIterator <String> i = previousData.listIterator();  // New iterator to traverse throught LinkedList
   for(int j=0; j <= previousData.size(); j++){ // for loop that iterates throug the entire LinkedList called previousData
      if(i.hasNext() == true){
      String first = i.next(); 
      if (i.hasNext() == true){
          String second = i.next();
          String bigram = first + " " + second;
          if( newWords.contains(bigram)){ // if bigram is listed in HashSet newWords do......
               newLinkedList.add(bigram.replaceAll("\\s",""));  // Removes white space from bigram and inserts it into new LinkedList 
         }// End of if statement 
         else {
            newLinkedList.add(first);
           // newLinkedList.add(second);
          // System.out.println(newLinkedList);
            String position = i.previous(); 
         } // End of else 
      } // end of if statment checking if i.next has value
      
      else {
            newLinkedList.add(first);
      }
      }
     // Moves iterator back one spot Only want to go back to previous position when you dont add a bigram
                                    //dont want to if you do
                                       // if j = 1
	} // End of for loop 
   
   //System.out.println(newLinkedList);
   
   return newLinkedList;
} // End of resegment method 

 // -----------------------------    COMPUTE COUNTS    -------------------------------------------------------------- 
    // computeCounts
    // Preconditions:
    //    - data is the LinkedList representation of the data
    //    - bigramCounts is an empty HashMap that has already been created
    // Postconditions:
    //    - bigramCounts maps each bigram appearing in the data to the number of times it appears
	  
  
   
   public static void computeCounts(LinkedList<String> data, HashMap<String,Integer> bigramCounts ) {
   ListIterator <String> i = data.listIterator();
   for(int j=1; j < data.size(); j++){
      String first = i.next();
      String second = i.next();
      String bigram = first + " " + second;
      String position = i.previous();
         //if the bigram's value exists
      if (bigramCounts.containsKey(bigram)) {
         //increment the value associated with that bigram
         bigramCounts.put(bigram, bigramCounts.get(bigram) +1);
      }
      else{
         bigramCounts.put(bigram, 1);
         //System.out.println(bigram);
      }
      
     // incrementHashMap(bigramCounts, bigram, bigramCounts.get(bigram));
   
      } // End of for loop
     // System.out.println(bigramCounts); 
      } // End of computeCounts Method


 // ---------------------------      convertCountsToProbabilities METHOD    --------------------------------------------------------------------------------  

    // convertCountsToProbabilities 
    // Preconditions:
    //    - bigramCounts maps each bigram appearing in the data to the number of times it appears
    //    - bigramProbs is an empty HashMap that has already been created
    //    - leftUnigramProbs is an empty HashMap that has already been created
    //    - rightUnigramProbs is an empty HashMap that has already been created
    // Postconditions:
    //    - bigramProbs maps bigrams to their joint probability
    //        (where the joint probability of a bigram is the # times it appears over the total # bigrams)
    //    - leftUnigramProbs maps words in the first position to their "marginal probability"
    //    - rightUnigramProbs maps words in the second position to their "marginal probability"
	public static void convertCountsToProbabilities(HashMap<String,Integer> bigramCounts, HashMap<String,Double> bigramProbs, HashMap<String,Double> leftUnigramProbs, HashMap<String,Double> rightUnigramProbs ) {
		//compute total number of bigrams ????????
      double total = 0;
      for (int i : bigramCounts.values()){
         total += i;
      } 
      //iterating through all the bigrams, saving them as the keys in the bigramProbs hashmap, and using the bigram values and
      //total to calculate their probability
      for (String bigram : bigramCounts.keySet()){ //iterates through keyset
         //put new entry in bigram probs where the key is the bigram and the value is the bigram probability
         double prob = bigramCounts.get(bigram) / total;
         bigramProbs.put(bigram , prob); 
      }
      //splitting bigrams at the space and saving each letter into left and right hashmaps and recording the values as their number of occurances
      HashMap<String,Integer> leftCounts = new HashMap<String,Integer>();
      HashMap<String,Integer> rightCounts = new HashMap<String,Integer>();
      for (String bigram : bigramCounts.keySet()){
         String[] bigramSplit = bigram.split(" ");
         //if it alreaedy exists
         String leftUnigram = bigramSplit[0];
         String rightUnigram = bigramSplit[1];
         
         incrementHashMap(leftCounts, leftUnigram, bigramCounts.get(bigram));
         incrementHashMap(rightCounts, rightUnigram, bigramCounts.get(bigram));
         
         
         
         /*if(leftCounts.get(bigramSplit[0]) != null){ //contains       //increment hash counts !!!!
            leftCounts.put(bigramSplit[0], leftCounts.get(bigramSplit[0]) + bigramCounts.get(bigram));
         }
         //otherwise add a new one to the hashmap
         else{
            leftCounts.put(bigramSplit[0], bigramCounts.get(bigram));          
         }
         //do it to the right side
         if(rightCounts.get(bigramSplit[1]) != null){
            rightCounts.put(bigramSplit[1], rightCounts.get(bigramSplit[1]) + bigramCounts.get(bigram));
         }
         //otherwise add a new one to the hashmap
         else{
            rightCounts.put(bigramSplit[1], bigramCounts.get(bigram));          
         }
         */
     }
     
     //calculate left probabilities
     for (String word : leftCounts.keySet()){
         double leftprob = leftCounts.get(word) / total;
         leftUnigramProbs.put(word, leftprob);
     }
     //calculate right probabilities
     for (String word : rightCounts.keySet()){
         double rightprob = rightCounts.get(word) / total;
         rightUnigramProbs.put(word, rightprob);
     } 
     //System.out.print(bigramProbs);
     //System.out.print(leftCounts);
     //System.out.print(rightCounts); 
     return;
	}
   
// ------------------------------------ getScores METHOD -----------------------------------------   

    // getScores
    // Preconditions:
    //    - bigramProbs maps bigrams to to their joint probability
    //    - leftUnigramProbs maps words in the first position to their probability
    //    - rightUnigramProbs maps words in the first position to their probability
    // Postconditions:
    //    - A new HashMap is created and returned that maps bigrams to
    //      their "bigram product scores", defined to be P(w1|w2)P(w2|w1)
    //      The above product is equal to P(w1,w2)/sqrt(P_L(w1)*P_R(w2)), which 
    //      is the form you will want to use
	public static HashMap<String,Double> getScores( HashMap<String,Double> bigramProbs, HashMap<String,Double> leftUnigramProbs, HashMap<String,Double> rightUnigramProbs ) {
	   //create the new hashmap
      HashMap<String,Double> bigramProducts = new HashMap<String,Double>();
      
      for (String bigram : bigramProbs.keySet()){
           String[] bigramSplit = bigram.split(" ");
           String leftKey = bigramSplit[0];
           String rightKey = bigramSplit[1];
           
           double bigramProduct = (bigramProbs.get(bigram)) / (Math.sqrt(leftUnigramProbs.get(leftKey)*rightUnigramProbs.get(rightKey) ));
           bigramProducts.put(bigram, bigramProduct);
      } 
      //i think we're returning this hashmap?
   	return bigramProducts;
	}
// -------------------------------    getVocabulary METHOD ---------------------------
    // getVocabulary
    // Preconditions:
    //    - data is a LinkedList representation of the data
    // Postconditions:
    //    - A new HashMap is created and returned that maps words
    //      to the number of times they appear in the data
    
	public static HashMap<String,Integer> getVocabulary( LinkedList<String> data ) {
      HashMap<String,Integer> vocabulary = new HashMap<String, Integer>();
      ListIterator <String> position = data.listIterator();
      while(position.hasNext()){
         String word = position.next();
         incrementHashMap(vocabulary, word, 1);
      }
      //System.out.println(vocabulary);
		return vocabulary;
	}


 // -----------------------------    LOAD DICTIONARY    -------------------------------------------------------------- 
 
    // loadDictionary
    // Preconditions:
    //    - dictionaryFilename is the name of a dictionary file
    // Postconditions:
    //    - A new HashSet is created and returned that contains
    //      all unique words appearing in the dictionary

   
   // Method that loads dictionary text file into HashSet
   public static HashSet<String> loadDictionary( String dictionaryFilename ){
   
    // Creates new empty HashSet
   HashSet<String> dictionary = new HashSet<String>();
   try{
   //creates new scanner from dictionaryFilename
   Scanner inputDictionary = new Scanner(new File(dictionaryFilename));
   
  
   // While loop that checks if text file has another token
   while(inputDictionary.hasNext()){
      String word = inputDictionary.next();
      dictionary.add(word);
   } // End of while loop
    
   }catch(FileNotFoundException ex){
      ex.printStackTrace();
   }
   //System.out.println(dictionary.toString());
   //System.out.println(dictionary.getClass().getSimpleName());
   return dictionary;
   
      } // End of loadDictionary method


 // -----------------------------    INCREMENT HASHMAP    -------------------------------------------------------------- 
 
    // incrementHashMap
    // Preconditions:
    //  - map is a non-null HashMap 
    //  - key is a key that may or may not be in map
    //  - amount is the amount that you would like to increment key's value by
    // Postconditions:
    //  - If key was already in map, map.get(key) returns amount more than it did before
    //  - If key was not in map, map.get(key) returns amount
    // Notes:
    //  - This method has been provided for you 
	private static void incrementHashMap(HashMap<String,Integer> map,String key,int amount) {
		if( map.containsKey(key) ) {
			map.put(key,map.get(key)+amount);
		} else {
			map.put(key,amount);
		}
		return;
	}

    // printNumWordsDiscovered
    // Preconditions:
    //    - vocab maps words to the number of times they appear in the data
    //    - dictionary contains the words in the dictionary
    // Postconditions:
    //    - Prints each word in vocab that is also in dictionary, in sorted order (alphabetical, ascending) DONE
    //        Also prints the counts for how many times each such word occurs DONE
    //    - Prints the number of unique words in vocab that are also in dictionary 
    //    - Prints the total of words in vocab (weighted by their count) that are also in dictionary 
	// Notes:
    //    - See example output for formatting
	public static void printNumWordsDiscovered( HashMap<String,Integer> vocab, HashSet<String> dictionary ) {
      TreeMap<String,Integer> realWords = new TreeMap<String, Integer>();
      //goes through the dictionary and identifies words that match the hashmap vocab
      for (String word : dictionary){
         if(vocab.containsKey(word)){
            realWords.put(word, vocab.get(word));
         }
      }
      //prettyprints discovered words
      //System.out.println(realWords);
      for (String printWord : realWords.keySet()){
         System.out.println("Discovered " + printWord + " (count " + realWords.get(printWord) + ")");
      }
      
      //length of dictionary
      int dictLength = dictionary.size();
      int uniqueWords = realWords.size();
      double uniquePercent = uniqueWords * 1.0/ dictLength;
      uniquePercent = uniquePercent * 100;
      //System.out.println(uniquePercent);
      //prettyprint unique words
      System.out.println("Discovered " + uniqueWords + " actual (unique) words out of " + dictLength + " dictionary words"); 
      System.out.println("(" + String.format( "%.2f", uniquePercent) + "%)");
      
      //calculate total tokens
      int tokenCount = 0;
      int counts = 0;
      for (String word : vocab.keySet()){
         counts = vocab.get(word);
         tokenCount = tokenCount + counts;
      }
      
      //calculate actual token count
      int actualToken = 0;
      int counter = 0;
      for(String word : realWords.keySet()){
         counter = realWords.get(word);
         actualToken = actualToken + counter;
      }
      //System.out.println(actualToken);
      double tokenPercent = (actualToken * 1.0/ tokenCount) * 100;

      System.out.println("Discovered " + actualToken + " actual  words tokens out of " + tokenCount + " total tokens"); 
      System.out.println("(" + String.format( "%.2f", tokenPercent) + "%)");

      
		return;
	}

}
