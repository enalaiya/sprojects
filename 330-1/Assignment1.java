/*Assignment 1 - Computer Science 330 Databases
 *Elizabeth Schoen
 *This program parses a Stockmarket input file for crazy days and stock splits.
 *Due 1/26/2017
 *
 *
 */


import java.io.*;
import java.util.*;
import java.lang.*;

public class Assignment1{
   public static void main(String[] args){
      //takes one argument - the input file provided.
      if (args.length != 1){
         System.err.println("Error: Wrong number of arguments");
			System.exit(1);  
      }
      
      String inputFile = args[0];
      Scanner input;
      
      //make a 2D array to store all the data in
      //Database here is an array of string arrays, each string array is a line of the file with each item saved as an item.
      ArrayList<ArrayList<String>> database = new ArrayList<ArrayList<String>>();
      ArrayList<String> line = new ArrayList<String>();
      
      int row = 0;
      try{
         input = new Scanner(new File(inputFile));
         while(input.hasNext()){
            if(row == 0){
               line = new ArrayList<String>();
               line.add(input.next());
               row++;
            }
            else if(row == 8){
               database.add(line);
               row = 0;
            }
            else{
               line.add(input.next());
               row++;
            }
         }
         
         
      }
      catch(FileNotFoundException e){
          e.printStackTrace();
      }
      
      //this ends up being where all the work is done.  If I'd been more thoughtful in my original design, and I wasn't so down to the wire
      //finishing it, I would've made a more elegant object oriented solution.  
      //As I'm sure the purpose of this assignment is to show us how difficult database-type interactions are without a database, I think
      //it turned out okay :)
      printCrazyDays(database);
      
      
      
  // System.out.println(database);
   }//end of main
   
   
   //the original idea was to have this function parse the file for the crazy days only.  
   //However as I made more and more poor design choices, I backed myself into a corner.
   //this function prints all the output.
   public static void printCrazyDays(ArrayList<ArrayList<String>> database){
      String company = "nil";
      String date;
      double tomorrowOpening = 0.0;
      double openingPrice;
      double highPrice;
      double lowPrice;
      double closingPrice;
      int sharesTraded;
      double adjPrice;
      double crazy = 0.0;
      String[] addTo = new String[2];
      ArrayList<String[]> crazyArray = new ArrayList<String[]>();
      int crazyCount = 0;
      String[] craziest = {" ", "0.0"};
      double day;
      int c = 0;
      ArrayList<String> stockSplitList = new ArrayList<String>();
      String split;
      
      
      //for every line in the database
      for (ArrayList<String> line : database){
      
         //if this is the first time we're seeing this company, there are a lot of things that need to happen
         if(!line.get(0).equals(company)){
         //firstly, if there was a company before this, we need to print all the data from that previous company.
            if(c != 0){
               System.out.println();
               System.out.println("Total crazy days = " + crazyCount);            
            }
            
            if (!craziest[0].equals(" ")){
               System.out.println();
               System.out.printf("The craziest day:  " + craziest[0] + "  " + "    %.2f%%%n", 100*Double.parseDouble(craziest[1]));
            }
            System.out.println();
            for(String s : stockSplitList){
               System.out.println(s);
            }
            System.out.println();
            if(c != 0){
              System.out.println("Total number of splits: " + stockSplitList.size());
            }
            c++;
            System.out.println("\n \n \n");
            
            //now, we need to reinitialize all the variables for next company
            stockSplitList = new ArrayList<String>();
            company = line.get(0);
            printHeader(company);
            crazyCount = 0;
            craziest[0] =  " ";
            craziest[1] = "0.0";
            stockSplitList = new ArrayList<String>();
            tomorrowOpening = 0.0;
         }         
         
         
         //get all the data out of the line
         date = line.get(1);
         openingPrice = Double.parseDouble(line.get(2));
         highPrice = Double.parseDouble(line.get(3));
         lowPrice = Double.parseDouble(line.get(4));
         closingPrice = Double.parseDouble(line.get(5));
         sharesTraded = Integer.parseInt(line.get(6));
         adjPrice = Double.parseDouble(line.get(7));
         
         
         //if a day is crazy        
         crazy = calculateCrazy(highPrice, lowPrice);
         if(crazy!= 0.0){
            System.out.printf("Crazy day: "+ date + "    %.2f%%%n", 100*crazy);
           if( crazy > Double.parseDouble(craziest[1]) ){
               craziest[0] = date;
               craziest[1] = Double.toString(crazy);
            }
            crazyCount ++;
            
         } 
         
         
         //calculate stock splits here
         if(tomorrowOpening != 0.0){
            split = calculateStockSplit(tomorrowOpening, closingPrice, date);
            if(!split.equals(" ")){
               stockSplitList.add(split);
            }
         }
         
         
         //saving this days opening price to use for stock splits
         tomorrowOpening = openingPrice;

         
      }
      /////////here we print all the things for the last company, since we've run out of text and broken out of the last loop
      //catching the last number of crazy days
      System.out.println();
      System.out.println("Total crazy days = " + crazyCount);
      //catching the last craziest day.
      if (!craziest[0].equals(" ")){
               System.out.println();
               System.out.printf("The craziest day:  " + craziest[0] + "  " + "    %.2f%%%n", 100*Double.parseDouble(craziest[1]));
            }
      //print splits(if they exist) for the last company
      System.out.println();
            for(String s : stockSplitList){
               System.out.println(s);               
            }
      System.out.println();
      System.out.println("Total number of splits: " + stockSplitList.size());
      
      
      //returns nothing, everything's already printed.
      return;
   }
   
   
   //this function calculates the stock splits
   public static String calculateStockSplit(double openingPrice, double closingPrice, String date){
      //check order of operations here if getting weird output
      String returnString = " ";
      
      if(Math.abs( closingPrice / openingPrice - 2.0) < 0.05){     
         returnString =  "2:1 split on " + date + "  " + closingPrice + " --> " + openingPrice;
      }
      if(Math.abs( (closingPrice / openingPrice) - 3.0) < 0.05){        
         returnString =  "3:1 split on " + date + "  " + closingPrice + " --> " + openingPrice;
      }
      if(Math.abs( (closingPrice / openingPrice) - 1.5) < 0.05){        
         returnString =  "3:2 split on " + date + "  " + closingPrice + " --> " + openingPrice;
      }
      return returnString;
   }
   
   
   //this function just prints a header for a company.  
   //might seem very superfluous (especially looking at the garbage above)
   //but it had a place in the beginning of this process....
   public static void printHeader(String company){
      System.out.println("Processing " + company);
      System.out.println("=============================");
      return;
   }
   
   //this function calculates crazy days.  if theyre crazy, it returns the
   //percent, if theyre not it returns 0.0, which whoever's calling it should know what to do with.
   public static double calculateCrazy(double high, double low){
      double percent = (high-low)/high;
      if(percent >= .15){
         return percent;
      }
      else{
         return 0.0;
      }
   }
   
   
   //this function does nothing anymore, but was origionally going to go 
   //though a list of all the crazy days and pick out the craziest one.  I think
   //it was having variable issues so I stopped trying to use it.
   public static String[] findCrazziest(ArrayList<String[]> dateCrazy){
      Double biggest = 0.0;
      String[] returnS = {" ", " "};
      for(String[] crazy : dateCrazy){
      System.out.println(crazy[0] + " " + crazy[1]);
         if(Double.parseDouble(crazy[1]) > biggest){
            returnS[0] = crazy[0];
            returnS[1] = crazy[1];
         }
      }
      return returnS;
   }



}
