import java.util.*;
import java.io.*;
import java.util.Scanner;


//Elizabeth Schoen && David Voye
//Driver file, run with a text file formatted ***YYYY.txt as a parameter

//this class calls the various other classes to create data structures as requested
//by the user and provides the data - a driver program
public class nameData {
   

   public static void main(String[] args){
      if(args.length != 1) {
			System.err.println("USAGE: java nameData <name_file>");
			System.exit(1);
		}
      
      //adding this for infinite looping
      int infinite = 1;
      
      Scanner s = new Scanner(System.in);
      nameHash hash;
      String nameFile = args[0];
      
     // int dataStructure = Integer.parseInt(args[1]);//change this for final program
      
      
      
      LinkedList<String> dataLL = new LinkedList<String>();
      Scanner input;
      
      
     // System.out.println(nameFile);
     //parse the file name for the year
      String[] date = nameFile.split("");
      String year = date[3]+date[4]+date[5]+date[6];
      //System.out.println(year);
      
      
      try{
       // Creates a new scanner  
       
         input = new Scanner(new File(nameFile));

       // Puts all the data into a linked list
       while(input.hasNext()){
         String word = input.next();
          dataLL.add(word);
       } 
       }catch(FileNotFoundException e){
          e.printStackTrace();
         //System.out.println("Error: Unable to open file " + nameFile);
         // System.exit(1);
       }
      
      for(String babyNameInfo : dataLL){
               String[] myList = new String[3];
            
               myList = babyNameInfo.split(",");
               String Name = myList[0];
               String gender = myList[1];
               int key = Integer.parseInt(myList[2]);
             
               BinaryTree tree = new BinaryTree();
             
               tree.add(Name, gender, key);
             
               AvlLoadArray avl = new AvlLoadArray();
             
               // Accessing static array from AvlLoadArray
               //System.out.println(AvlLoadArray.nodeArray);
             
               avl.add(Name, gender, key);
             
             
           
              
             
         }// End of while loop
   while(infinite == 1){
      System.out.println("Enter the number for the data structure you'd like to use (1 = tree, 2 = hashmap, 3 = linkedlist) : ");
      int dataStructure = Integer.parseInt(s.next());
      int request;
      System.out.println("What information would you like to know? (1 = Search for a name, 2 = Most popular name, 3 = Unique name, 4 = Display Name)");
      request = Integer.parseInt(s.next()); // might have to parse to an int
         
         
      //while(infinite == 1){
      
      
      //now all the data is in a linked list yay
      
      //add something here to get the loop to continue indefinetely
      
      
      //if dataStructure is not a 1 2 or 3
      if(dataStructure != 1 && dataStructure != 2 && dataStructure != 3){
         System.out.println("Please enter a valid data structure (1:tree, 2:hashmap, 3:linkedlist)");
         System.exit(1); //??
      }
      //create the dataStructure here
      else{
      
         //
         //AVL TREE
         //
         if(dataStructure == 1){
            System.out.println("Selected Data Structure: Tree");
            
       
         // While loop that iterates line by line throught text file
         /*for(String babyNameInfo : dataLL){
               String[] myList = new String[3];
            
               myList = babyNameInfo.split(",");
               String Name = myList[0];
               String gender = myList[1];
               int key = Integer.parseInt(myList[2]);
             
               BinaryTree tree = new BinaryTree();
             
               tree.add(Name, gender, key);
             
               AvlLoadArray avl = new AvlLoadArray();
             
               // Accessing static array from AvlLoadArray
               //System.out.println(AvlLoadArray.nodeArray);
             
               avl.add(Name, gender, key);
             
             
           
              
             
         }// End of while loop
*/
                  
            
                    
            //check for requested data
            if(request != 1 && request != 2 && request != 3 && request != 4){
               System.out.println("please enter a valid request (1:search, 2:popular, 3:unique, 4:display)");
               System.exit(1);//??
            }
            //call the functions below
            else{
         
               if(request == 1){
               String name1;
               System.out.println("Please enter a name you would like to know about");
               name1 = s.next();
               System.out.println("Selected name: " + name1);
               System.out.println("Year: " + year);
               BinaryTree tree1 = new BinaryTree();
               tree1.searchName(name1);
               
               //call functions here
            
               }
               if(request == 2){
                  BinaryTree tree2 = new BinaryTree();
                  tree2.mostPopularName(); 
                  
               }
               if(request == 3){
                  BinaryTree tree3 = new BinaryTree();
                  tree3.searchUniqueName();
               }
               if(request == 4){
                  BinaryTree tree4 = new BinaryTree();
                  tree4.displayNameOfChild();
               }
            }
         }
         
         
         
         //             MAYBE ADD A LARGEST RANK SO IF THERE'S
                      //NO NAMES WITH 5 YOU CAN STILL HAVE DATA
                      //IN THAT TABLE? JUST A REACH GOAL
         //HASH MAP
         //
         if(dataStructure == 2){
            System.out.println("Selected Data Structure: HashMap");
            hash = new nameHash(dataLL);
            //nameHash.createMap(dataLL);
            
             //get the data requested here
            //if it's
            if(request != 1 && request != 2 && request != 3 && request != 4){
            System.out.println("please enter a valid request (1:search, 2:popular, 3:unique, 4:display)");
            System.exit(1);//??
            }
            //call the functions below
            else{
         
               if(request == 1){
                  String name;
                  System.out.println("Please enter a name you would like to know about");
                  name = s.next();
                  System.out.println("Selected name: " + name);
                  System.out.println("Year: " + year);
          
                  //call functions here
                  hash.searchName(name); 
            
               }
               if(request == 2){
                  System.out.println("Year: " + year);
                  //call functions here
                  hash.mostPopularName();
               }
               if(request == 3){
                  System.out.println("Year: " + year);
                  hash.uniqueName();
               }
               if(request == 4){
                  System.out.println("Year: " +year);
                  hash.displayName();
               }
            }

         }
         
         
         //
         //LINKED LIST 
         //
         if(dataStructure == 3){
            System.out.println("Selected Data Structure: LinkedList");
            //System.out.println("not yet implemented");
            
             //get the data requested here
            if(request != 1 && request != 2 && request != 3 && request != 4){
               System.out.println("please enter a valid request (1:search, 2:popular, 3:unique, 4:display)");
               System.exit(1);//??
            }
            //call the functions below
            else{
         
               if(request == 1){
               String name2;
               System.out.println("Please enter a name you would like to know about");
               name2 = s.next();
               System.out.println("Selected name: " + name2);
               System.out.println("Year: " + year);
               //call functions here
               AvlLoadArray newInstance1 = new AvlLoadArray();
               newInstance1.avlSearchName(name2);
            
               }
               if(request == 2){
                  AvlLoadArray newInstance2 = new AvlLoadArray();
                  newInstance2.avlMostPopularName();
               }
               if(request == 3){
                  AvlLoadArray newInstance3 = new AvlLoadArray();
                  newInstance3.uniqueName();
               }
               if(request == 4){
                  AvlLoadArray newInstance4 = new AvlLoadArray();
                  newInstance4.displayName();  ;
               }
            }

         }
      }
      
 }

   


}//attatched to while loop for infinite

}

