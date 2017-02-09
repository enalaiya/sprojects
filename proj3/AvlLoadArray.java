import java.io.*; 
import java.util.*;



public class AvlLoadArray {

 //Create new ArrayList string to hold new Node objects


   static ArrayList<Node> nodeArray = new ArrayList<Node>(); 
   
   static int rankNum = 1;
   
   static int totalBabyNamesFemale = 0;
   
   static int totalBabyNamesMale = 0;
   
  static boolean genderSwap = false;
   
   
   
   
  

   public void add(String name, String gender, int key){  ////////////////////// METHOD ADD that creates new node objects and puts them in nodeArray

   Node nodeToAdd = new Node(name, gender, key);
   
   if ((nodeToAdd.gender().equals("F"))) {
      totalBabyNamesFemale += nodeToAdd.key();
   }
   
   if ((nodeToAdd.gender().equals("M"))) { // Condition that checks if new node's gender is Female than start the ranking at 1 again 
      if(genderSwap == false){
         rankNum = 1;
         genderSwap = true;
      }
      totalBabyNamesMale += nodeToAdd.key();
      
   
   }
   
   // Load up nodeArray one node at a time
   nodeArray.add(nodeToAdd); 
   
   // Set rank of node
   nodeToAdd.setRank(rankNum);
   

   
 
   
   rankNum ++;
  
   
   } // end of add method
   
    
    
    
///////////////////////////////////////////////////////////////// SEARCH NAME METHOD ///////////////////////////////////////////////////////

   public void avlSearchName(String searchName){    
   boolean found = false;
   int rank = -1;
   int femaleTotal = 0;
   int maleTotal = 0;
   int femaleRank = 0;
   int maleRank = 0;
   boolean femaleExists = false;
   boolean maleExists = false;
   
   for(Node n: nodeArray) {
      if (( n.name().equals(searchName)) && n.gender().equals("M")) {
         maleTotal = n.key();
         maleRank = n.rank();
         found = true;
         maleExists = true;
      }
      if (( n.name().equals(searchName)) && n.gender().equals("F")) {
         femaleTotal = n.key();
         femaleRank = n.rank();
         found = true;
         femaleExists = true;
      }
      

   } // end of for each loop
   
   
     
   // Case if name is not found
   if(found = false) {
      System.out.println(searchName + " is not found.");  
   } else {
      if (maleExists == true){
         System.out.println("Male occurrences:  " + maleTotal);
         System.out.println("Rank among male names:  " + maleRank);

      }
      if (femaleExists == true){
         System.out.println("Female occurrences:  " + femaleTotal);
         System.out.println("Rank among female names:  " + femaleRank);
      }
   
 }
   
    } // End of avlSearchName Method
    
    
    
    
///////////////////////////         UNIQUE NAME METHOD           ////////////////////////////////////////////////////////////////////////////////

   public void uniqueName() {
   
    //System.out.println("Entered AVL");

      int counter = 0;
      int counter2 = 0;
      for(Node n: nodeArray){
         if ((n.key() == 5) && ((n.gender().equals("F")))) {
             double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
             counter += 1;
             
             if(counter <=5) {
               System.out.println(n.name()  + " is a unique name. It occurs " + n.key() + " times.  Its frequency is " + frequency + " %");  
             }
         } // end of if
         
         
          if ((n.key() == 5) && ((n.gender().equals("M")))) {
             double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
             counter2 += 1;
             
             if(counter2 <=5) {
               System.out.println(n.name()  + " is a unique name. It occurs " + n.key() + " times.  Its frequency is " + frequency + " %");  
             }
         } // end of if
         
         
         
      
      } // end of for each loop









} // End of unique name methods



/////////////////////////////////                DISPLAY NAME METHOD         ///////////////////////////////////////////////////////////////////

   public void displayName() {
   StringBuilder s = new StringBuilder();
   ArrayList<String> nameArray = new ArrayList <String>();
   
   for(Node n: nodeArray) {
      if(!nameArray.contains(n)){
         String name = n.name();
         nameArray.add(name);
      }           
       
   } // end of for each loop
    
    Collections.sort(nameArray);
   int men = 0;
   int women = 0;
   boolean found = false;
   String percent;
   String tempName = "zzzz";
   for(String name : nameArray){
      if (!tempName.equals(name)){
         s.append(name);
         s.append(":  ");
      
      for(Node n: nodeArray){
         if(n.name().equals(name)){
            if (n.gender().equals("F")) {
               women = n.key();
               s.append("Females: ");
               s.append(women);
               s.append("  ");
            
            }
         if (n.gender().equals("M")) {
            men = n.key();
            s.append("Males: ");
            s.append(men);
            s.append("  ");
         }
            
         }
         
      }// end of for each
      
      percent = String.format( "%.2f", ((((double)men + women) / (totalBabyNamesFemale + totalBabyNamesMale)))*100);
         s.append("Babies with this name : ");
         s.append(percent);
         s.append("%");
         s.append("\n");
         tempName= name;
      }
    }
   System.out.println(s.toString());
   
   } // End of displayName method 





//////////////////////////      MOST POPULAR NAME   //////////////////////////////////////////////////////////////
   public void avlMostPopularName() {
   
   int[] rank = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
   Object[][] table = new String[11][6];

   //System.out.println(totalBabyNamesFemale);
    //System.out.println(totalBabyNamesMale);
   for(Node n : nodeArray) {
      for(int r : rank){
     // System.out.println(r);
         if(n.rank() == r && n.gender().equals("F")){
       //     System.out.println(r);
            table[r][0] = n.name();
            table[r][1] = Integer.toString(n.key());
            table[r][2] = String.format( "%.2f", ((double)n.key() / totalBabyNamesFemale)*100);
         }
         if(n.rank() == r && n.gender().equals("M")){
         //   System.out.println(r);
            table[r][3] = n.name();
            table[r][4] = Integer.toString(n.key());
            table[r][5] = String.format( "%.2f", ((double)n.key() / totalBabyNamesMale)*100);
         }
      }
      
      
      }
      table[0] = new String[]{ "Female Name", "Frequency", "%", "Male Name", "Frequency","%" };
   
      for (final Object[] row : table) {
            System.out.format("%15s%15s%15s%15s%15s%15s\n", row); //formats it like a table
      
      
      
      
      /*
      
      
      if ((n.rank() == 1) && ((n.gender().equals("F")))) {
         
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
      
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 2) && ((n.gender().equals("F"))))  {
         
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
        System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 3) && ((n.gender().equals("F"))))  {
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
        System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 4) && ((n.gender().equals("F"))))  {
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 5) && ((n.gender().equals("F"))))  {
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 6) && ((n.gender().equals("F"))))  {
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 7) && ((n.gender().equals("F"))))  {
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 8) && ((n.gender().equals("F"))))  {
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 9) && ((n.gender().equals("F"))))  {
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 10) && ((n.gender().equals("F"))))  {
         double frequency = (n.key()/(double)totalBabyNamesFemale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
      
   
     
      
      
       if ((n.rank() == 1) && ((n.gender().equals("M")))) {
         
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
      
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 2) && ((n.gender().equals("M"))))  {
         
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
        System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 3) && ((n.gender().equals("M"))))  {
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
        System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 4) && ((n.gender().equals("M"))))  {
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 5) && ((n.gender().equals("M"))))  {
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 6) && ((n.gender().equals("M"))))  {
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 7) && ((n.gender().equals("M"))))  {
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 8) && ((n.gender().equals("M"))))  {
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 9) && ((n.gender().equals("M"))))  {
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      
         if ((n.rank() == 10) && ((n.gender().equals("M"))))  {
         double frequency = (n.key()/(double)totalBabyNamesMale) * 100;
         System.out.println(n.name() + " occurs " + n.key() + " times.  The frequencey is " + String.format( "%.2f", frequency) + "%" );
      } // end of if
      

      */
      
      
 
   
   } // end of for each
   
   
   } // End of avlMostPopularName method
    
    
    
    


} // end of searchNameTree class