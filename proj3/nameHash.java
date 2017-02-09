import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.lang.*;

public class nameHash {
   public static int totalF;
   public static int totalM;
   public static int total;
   public static LinkedList<String> nameFile;
   public static HashMap<NGKey,RNValue> map = new HashMap<NGKey,RNValue>();
   public static int lowestRankM;
   public static int lowestRankF;
   
   public nameHash(LinkedList<String> nameFile){
      this.nameFile = nameFile;
      //System.out.println("?????");
       
      //System.out.println("made it to constructor");
      createMap();
      
   }

   /*createMap function creates a map with a "Name Gender Key" as the key and
   /*a "Rank Number Value" as the value. it then calls the function to 
   /*calculate the total number of babies in original file.
   */
   public static void createMap(){
      Iterator<String> iterator =  nameFile.listIterator(0);
      NGKey key;
      RNValue value;
      String[] datas;
      String name;
      String gender;
      int rankCounter = 1;
      boolean genderChange = false;  //used by the rank counter to know when to switch genders
      
      while(iterator.hasNext()){
         datas = iterator.next().split(",");
         name = datas[0];
         gender = datas[1];
         if(gender.equals("M") && genderChange == false){
            lowestRankF = rankCounter;
            rankCounter = 1;
            genderChange = true;
         }
         value = new RNValue(rankCounter, Integer.parseInt(datas[2]));
         key = new NGKey(name, gender);
         map.put(key, value);
         rankCounter++;
         //System.out.println(name);
         //System.out.println(gender);
         //System.out.println(value);
         
      }     
      lowestRankM = rankCounter;
      //System.out.println(lowestRankM);
      //System.out.println(lowestRankF);
      totalBabies();
      
      return;
   }
   //finds the total numbers of babies and saves them as totalM and totalF and total
   public static void totalBabies(){
      Set<NGKey> keySet = map.keySet();
      for (NGKey key : keySet){
      //System.out.println(key.getGender());
         if(key.getGender().equals("F")){
            totalF = totalF + map.get(key).getNumber();
            total = total + map.get(key).getNumber();
         }
         if(key.getGender().equals("M")){
            totalM = totalM + map.get(key).getNumber();
            total = total + map.get(key).getNumber();
         }
      }
      //System.out.println( total + totalM + totalF);
      //totalF = 0;
   }
   
   //public static String getKeys(){
     // Set<NGKey> keys map.keySet();
     // for (RNValue value : map){
         
     // }
  // }
   
   
   
   public static HashMap<NGKey,RNValue> getMap(){
      return map;
   }
   
   public static void searchName(String name){
      NGKey maleKey = new NGKey(name, "M");
      NGKey femaleKey = new NGKey(name, "F");

      int maleCount = 0;
      int femaleCount = 0;
      int maleRank = 0;
      int femaleRank = 0;
      boolean maleExists = false;
      boolean femaleExists = false;
      
      if (map.containsKey(maleKey)){         
         maleCount = map.get(maleKey).getNumber();
         maleRank = map.get(maleKey).getRank();
         maleExists = true;
      }
      if (map.containsKey(femaleKey)){
         femaleCount = map.get(femaleKey).getNumber();
         femaleRank = map.get(femaleKey).getRank();
         femaleExists = true;
      } 
      if (maleExists == true){
         System.out.println("Male occurrences:  " + maleCount);
         System.out.println("Rank among male names:  " + maleRank);

      }
      if (femaleExists == true){
         System.out.println("Female occurrences:  " + femaleCount);
         System.out.println("Rank among female names:  " + femaleRank);
      }
      if(femaleExists == false && maleExists == false){
         System.out.println("Name not found :( ");
      }   
   
      return;
   
   }
   
   public static void mostPopularName(){
      Set<NGKey> keySet = map.keySet();
      RNValue val;
      Object[][] table = new String[11][6];
      int[] rankArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      for (NGKey key : keySet){
         
         val = map.get(key);
         //System.out.println(val.getRank());
         for(int rankCompare : rankArray){
            if(val.getRank() == rankCompare && key.getGender().equals("F")){
               table[rankCompare][0] = key.getName();
               table[rankCompare][1] = Integer.toString(val.getNumber());
               table[rankCompare][2] = String.format( "%.2f", ((double)val.getNumber() / totalF)*100);
 
            }
            if(map.get(key).getRank() == rankCompare && key.getGender().equals("M")){
               table[rankCompare][3] = key.getName();
               table[rankCompare][4] = Integer.toString(val.getNumber());
               table[rankCompare][5] = String.format( "%.2f", ((double)val.getNumber() / totalM)*100);
            }
          }
       }                       //String.format( "%.2f", double )
        
        //set up header
        table[0] = new String[]{ "Female Name", "Frequency", "%", "Male Name", "Frequency","%" };
   
         for (final Object[] row : table) {
            System.out.format("%15s%15s%15s%15s%15s%15s\n", row); //formats it like a table
            }
   }
   
   public static void uniqueName(){
      int Fcount = 0;
      int Mcount = 0;
      RNValue val;
      
      Set<NGKey> keySet = map.keySet();
      Object[][] table = new String[6][6];
      for(NGKey key : keySet){
          val = map.get(key);
          //System.out.println(val.getNumber());
          //System.out.println("fcount : " + Fcount);
          //System.out.println("mcount : " + Mcount);
         
          if(val.getNumber() == 5 && key.getGender().equals("F") && Fcount < 6){
               //System.out.println("found a female name " + key.getName() +" Fcount is now " + Fcount); 
               table[Fcount][0] = key.getName();
               table[Fcount][1] = Integer.toString(val.getNumber());
               table[Fcount][2] = String.format( "%.7f", ((double)val.getNumber() / totalF)*100);
               Fcount++;                     //I incresed the number of decimal places visable so the % wouldn't
                                             //be just 0.00%
          }
          if(val.getNumber() == 5 && key.getGender().equals("M") && Mcount < 6){
               //System.out.println("found a male name " + key.getName() +" Mcount is now " + Mcount); 

               table[Mcount][3] = key.getName();
               table[Mcount][4] = Integer.toString(val.getNumber());
               table[Mcount][5] = String.format( "%.7f", ((double)val.getNumber() / totalM)*100);
               Mcount++;                     //I incresed the number of decimal places visable so the % wouldn't
                                             //be just 0.00%
          }
      }
   
      //set up header
        table[0] = new String[]{ "Female Name", "Frequency", "%", "Male Name", "Frequency","%" };
   
         for (final Object[] row : table) {
            System.out.format("%15s%15s%15s%15s%15s%15s\n", row); //formats it like a table
            }
   }


   public static void displayName(){
      Set<NGKey> set = map.keySet();
      ArrayList<String> nameList = getNamesFromKeySet(set);
      //not 100% we're allowed to use this but geeze, how complex do you want this to be?
      Collections.sort(nameList);  //is a set - names that apply to both genders DO NOT appear twice
      System.out.println(nameList);
      
      //string builder is more asymptotically efficeient than concatination i believe
      StringBuilder s = new StringBuilder();
      NGKey maleKey;
      NGKey femaleKey;
      int men = 0;
      int women = 0;
      String percent;
      
      for(String name : nameList){
         maleKey = new NGKey(name, "M");
         femaleKey = new NGKey(name, "F");
         s.append(name);
         s.append(" : ");
         if(map.containsKey(maleKey)){
            
            men = map.get(maleKey).getNumber();
            s.append("Males: ");
            s.append(men);
            s.append("  ");
         }
         else{
            s.append("Males: 0  ");
         }
         if(map.containsKey(femaleKey)){
            women = map.get(femaleKey).getNumber();
            s.append("Females: ");
            s.append(women);
            s.append("  ");
         }
         else{
            s.append("Females: 0  ");
            
         }
         percent = String.format( "%.2f", (((double)men + women) / total)*100);
         s.append("Babies with this name : ");
         s.append(percent);
         s.append("%");
         s.append("\n");
         
      }
      System.out.println(s.toString());
       
      
      
      
   }
      
      //helper function
      //when 
   public static ArrayList<String> getNamesFromKeySet(Set<NGKey> set){
      ArrayList<String> names = new ArrayList<String>();
      for(NGKey key : set){
      //check to see if name already exists
         if(names.contains(key.getName()) == false){
            names.add(key.getName());
            }
         //System.out.println(key.getName());
      }
      return names;
   }
      
      


}
