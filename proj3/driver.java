   //CSCI 241 
   // Lab 3
   // Lizzy Schoen & David Voye

   // Java Imports
   import java.io.*; 
   import java.util.*;
   
   // Driver program 
   public class driver {
   
      // Main Class
      public static void main(String[] args) throws FileNotFoundException {
      
         // Check to see if correct number of arguments are passed
         if(args.length != 1) {
            System.err.println("Enter 1 argument as yob2014.txt file");
            System.exit(1);
         }
         
         // Assigns nameFile as inputfile argument
         String nameFile = args[0];  
         
         // Creates new Scanner named input and passes in textfile
         Scanner input = new Scanner(new File(nameFile));
         
   
      
         
         // While loop that iterates line by line throught text file
         while (input.hasNextLine()) {
         
            String babyNameInfo = input.nextLine();
            
            String[] myList = new String[3];
            
             myList = babyNameInfo.split(",");
             String name = myList[0];
             String gender = myList[1];
             int key = Integer.parseInt(myList[2]);
             
             BinaryTree tree = new BinaryTree();
             
             tree.add(name, gender, key);
             
             AvlLoadArray avl = new AvlLoadArray();
             
             // Accessing static array from AvlLoadArray
             //System.out.println(AvlLoadArray.nodeArray);
             
             avl.add(name, gender, key);
             
             
           
              
             
         }// End of while loop
         
         

         
         
         
         
///////////////////    Returns Unique Name for Binary Tree /////////////////////////////////
  //BinaryTree tree1 = new BinaryTree();
  //tree1.searchUniqueName();  
        
         
         
/////////////////     Returns Search name for Binary Tree //////////////////////////////////////////
   
   //inaryTree tree2 = new BinaryTree();
   //tree2.searchName("Zena");

         

///////////////   Returns Most Popular Name for Binary Tree    ///////////////////////////////////

   //BinaryTree tree3 = new BinaryTree();
   //tree3.mostPopularName();    
   
   
   
/////////////////   Returns Display Name for Binary Tree     ///////////////////////////////////////
   //BinaryTree tree4 = new BinaryTree();
   //tree4.displayNameOfChild();
         
         
////////////////////////        Returns  Display Name for ArrayList         //////////////////////////////////// 

      
         //AvlLoadArray newInstance = new AvlLoadArray();
         //newInstance.displayName();      
         
         
         
         
///////////////////////        Returns Search Name for ArrayList        ////////////////////////////////

         //AvlLoadArray newInstance1 = new AvlLoadArray();
         //newInstance1.avlSearchName("Emma");
         
         
         
         
//////////////////////       Returns Most Popular Name for ArrayList     //////////////////////////////////////
      
      //AvlLoadArray newInstance2 = new AvlLoadArray();
      //newInstance2.avlMostPopularName();
       
       
       
       
       
         
///////////////////////        Returns Unique Name for ArrayList         ////////////////////////////////

        AvlLoadArray newInstance3 = new AvlLoadArray();
         newInstance3.uniqueName();
                
       
       
       
         
        
   
      } // End of main
      
      
      
   
   } // End of driver class
   
   