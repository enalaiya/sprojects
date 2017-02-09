import java.util.*;
import java.io.*;
public class driveTrendyNames{

   public static void main(String[] args){
      if(args.length != 5) {
			System.err.println("USAGE: java nameData <name_file>");
			System.exit(1);
		}
      
      ArrayList<String> years = new ArrayList<String>();
      for(String file : args){
         String[] date = file.split("");
         String year = date[3]+date[4]+date[5]+date[6];
         
      }
      
      
      
      
      
      
      Tree year1 = new Tree(args[0]);
      Tree year2 = new Tree(args[1]);
      Tree year3 = new Tree(args[2]);
      Tree year4 = new Tree(args[3]);
      Tree year5 = new Tree(args[4]);
      
      Tree[] forrest = {year1, year2, year3, year4, year5};
      
      StringBuilder s = new StringBuilder;
      
      for(Tree tree : forrest){
         tree.getMostPopularFemaleNames();
      }
      
      for(Tree tree : forrest){
         tree.getMostPopularMaleNames();
      }
      
      
      
   } 



}