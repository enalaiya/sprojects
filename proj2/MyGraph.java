import java.util.*;
import java.io.*;
import java.util.Scanner;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 * Add your name here: Elizabeth Schoen
 */
public class MyGraph {

      public static Collection<String> vertices = new ArrayList<String>();  //i just straight up don't know how to implement this without global variables
   	public static Collection <String> edges = new ArrayList<String>();   //and it feels dirty.
      public static ArrayList<List<String>> AdjList = new ArrayList<List<String>>();
      public static int nodeCount;
      public static ArrayList<String> EDGELIST = new ArrayList<String>();
  
          
      public static void edgeMaker(){
         Iterator<String> iterator = edges.iterator(); 
         while (iterator.hasNext()){
            EDGELIST.add(iterator.next());
         }

      }
      // Loads and returns the collection of vertices of this graph
   	//Postcondition
      // - A Collection of vertices is returned 
   	public static Collection<String> loadVertices(String vertexfile) {
   
   	   // Your code here
          //Collection <String> vertices = new ArrayList<String>();
          Scanner input;
          try{
          input = new Scanner(new File(vertexfile));

               while(input.hasNext()){
               String vertex = input.next();
               vertices.add(vertex);
               //System.out.println(vertex);
               }
          }
          
          catch(FileNotFoundException e){
            e.printStackTrace();
          }
          
          return vertices;
   
   	}
   
   	// Loads and returns the collection of edges of this graph
   	//Postcondition
      // - A Collection of edges is returned 
   	public static Collection<String> loadEdges(String edgefile) {
   
   	   
        //  Collection <String> edges = new ArrayList<String>();
          Scanner input;
          try{
          input = new Scanner(new File(edgefile));

               while(input.hasNext()){
               String edge = input.next();
               edges.add(edge);
              // System.out.println(edge);
               }
          }
          
          catch(FileNotFoundException e){
            e.printStackTrace();
          }

          return  edges;

   
   	}
      
      // Displays the collection of vertices of this graph
      // Precondition
      //  - vertex is a collection of vertices 
   	public static void displayVertices(Collection<String> vertex) {
         
         for( String v : vertex){
            System.out.println(v);
         }
         System.out.println(); //i like an extra line at the bottom.

         return;
			   
   	}
   
      
      // Displays the collection of edges of this graph
      // Precondition
      //  - edge is a collection of edges 
   	public static void displayEdges(Collection<String> edge) {
         
         Iterator<String> iterator = edge.iterator();
         List<String> ppEdges = new ArrayList<String>(); 
         while (iterator.hasNext()){
            ppEdges.add(iterator.next());
            nodeCount++;
         }
         //there's probably a more elegant way to do this, but it works so....
         int count = 0;
         for(String i : ppEdges){
            if(count > 4){
               count = 0;
            }
            if(count == 0){
               System.out.print(i + " ==> ");
            }
            if (count == 1){
               System.out.print(i + ": (");
            }
            if (count == 2 || count == 3){
               System.out.print(i + ", ");
            }
            if (count == 4){
               System.out.println(i + ")");
            }
            count++;
         }
         System.out.println();
   	   
                 
         return;
   
   	}

      // Display graph
      // Precondition
      //  - vertex is a collection of vertices
      //  - edge is a collection of edges
      // Postcondition
      // Display an adjacency list or adjacency matrix of the graph 
   	public static void displayGraph(Collection<String> vertex, Collection<String> edge) {
   
   	   //forea
         //System.out.println(edge.size());
         Iterator<String> E = edge.iterator();
         Iterator<String> V = vertex.iterator();
         
         List<String> EList = new ArrayList<String>();
         List<String> VList = new ArrayList<String>();
         StringBuilder prettyprint = new StringBuilder();  //more asymptotically efficient than concatination i believe
          //while (iterator.hasNext()){
            //ppEdges.add(iterator.next());
         //}
         while(V.hasNext()){
            VList.add(V.next());
         }
         //System.out.println(VList);
         //System.out.println(VList.get(0));
         
         while(E.hasNext()){
            EList.add(E.next());
         }
         
            List<String> temp;// = new ArrayList<String>();
         for(int i=0; i < VList.size(); i++){
            temp = new ArrayList<String>();                    
                 //creates a temporary array where the 0th element is the origin airport and the 1st through
            temp.add(VList.get(i));                                   //nth elements are the airports adjacent to that airport
            for(int j=0; j < EList.size(); j++){
               if(VList.get(i).equals(EList.get(j)) && j%5 == 0){
                  temp.add(EList.get(j+1));
               }
            }

            AdjList.add(temp);

         }
         
                 // System.out.println(AdjList);
         List<String> inside = new ArrayList<String>();

         for(int n=0; n < AdjList.size(); n++){
            inside = AdjList.get(n);
            prettyprint.append(inside.get(0));
            prettyprint.append(" : ");
            for(int m = 1; m < inside.size(); m++){
               prettyprint.append(inside.get(m));
               prettyprint.append(" ");
            }
            prettyprint.append("\n");
         }
       
         System.out.println(prettyprint);
         return;
   
   	}

      
   
   	// Identifies advjacent vertices for a given vertex
   	// Precondition
      //  - input vertex, v is one of the vertices in the graph
   	// Postcondition
      //  - returns a collection of vertices adjacent to v in the graph
             	 
   	public static Collection<String> findAdjacentVertices(String vertex) {
   
   	   
         Collection <String> adjVertices = new ArrayList<String>();
         
         List<String> inside = new ArrayList<String>();
         
         for(int i=0; i < AdjList.size(); i++){
            inside = AdjList.get(i);
            if(inside.get(0).equals(vertex)){
               for(int j = 1; j < inside.size(); j++){                     //gotta make sure that weird thing insnt happening here too.
                  adjVertices.add(inside.get(j));
               }
            }
          }
          return adjVertices;
   
   	}
      
   	 
      // Checks whether vertex end_point is adjacent to vertex start_point (i.e. start_point -> end_point) in a directed graph.
      //Precondition
      // - Vertex a is the start_point
      // - Vertex b is the end_point 
      //Postcondition
      // - Returns an array which will contain distance, time_needed, and ticket_price of edge if there is a directed edge from start_point to end_point 
      // - Returns -1 otherwise.
      // - (Also, returns -1 if one of the two vertices does not exist.)
    
      
   	public static int[] checkIsAdjacent(String a, String b) {
          int[] returnData = new int[3];
          Iterator<String> E = edges.iterator();
          List<String> inside = new ArrayList<String>();
          boolean flag = false;
   	    for(int i=0; i < AdjList.size(); i++){
            inside = AdjList.get(i);
            if(inside.get(0).equals(a)){
               for (int j=1; j < inside.size(); j++){
                  if(inside.get(j).equals(b)){
                     flag = true;
                  }
               }
            }
          }
          List<String> EList = new ArrayList<String>();

          while(E.hasNext()){
            EList.add(E.next());
         }
          
         if(flag == true){                            //if is adjacent
            while(E.hasNext()){
               EList.add(E.next());
            }
            for(int i=0; i < EList.size(); i++){
               if(EList.get(i).equals(a)){
                  if(EList.get(i+1).equals(b)){
                     returnData[0] = i+2;
                     returnData[1] = i+3;
                     returnData[2] = i+4;
                     
                  }
               }
            }            
            return returnData;
         }
         else{
          
          
         int [] value = {-1, 0};
          
         return value;  //if not adjacent
         }
   	}
   
   	// Identifies the shortest route from start_point to end_point.
   	// Precondition
      //  - start_point is the starting vertex
      //  - end_point is the destination vertex
   	//  - route is a list in which the route will be stored, in order, beginning with the start_point and ending with the end_point
      //  - the list will be empty if no such route exists.  
      //Postcondition
   	//  - returns the length of the shortest route from start_point to end_point
      //  -1 if no such path exists.
      //  - if multiple such route exists, return only 1 route satisfying the criteria
   	
   	public static int findShortestRoute(String start_point, String end_point, List<String> route) {
         
   	    // YOUR CODE HERE
          //set up paths with weights
          //Iterator<String> verts = verticies.iterator();
          List<String> VertList = new ArrayList<String>();  //a list of all the verticies

          //while(verts.hasNext()){
           //    VertList.add(verts.next());
            //}
            
            
            
          return 0;
   
   	}
      
      
      
      
      
     // Identifies the shortest route from start_point to end_point.
   	// Precondition
      //  - start_point is the starting vertex
      //  - end_point is the destination vertex
   	//  - route is a list in which the route will be stored, in order, beginning with the start_point and ending with the end_point
      //  - the list will be empty if no such route exists.  
      //Postcondition
   	//  - returns the length of the shortest route from start_point to end_point
      //  -1 if no such path exists.
      //  - if multiple such route exists, return only 1 route satisfying the criteria

   	
   	public static int findCheapestRoute(String start_point, String end_point, List<String> route) {
   
   	    // YOUR CODE HERE
          
          
          return 0;
   
   	}
      
      // Identifies the fastest route from start_point to end_point.
   	// Precondition
      //  - start_point is the starting vertex
      //  - end_point is the destination vertex
   	//  - route is a list in which the route will be stored, in order, beginning with the start_point and ending with the end_point
      //  - the list will be empty if no such route exists.  
      //Postcondition
   	//  - returns the total_time of the fastest route from start_point to end_point
      //  -1 if no such path exists.
      //  - if multiple such route exists, return only 1 route satisfying the criteria
   	 
   	public static int findFastestRoute(String start_point, String end_point, List<String> route) {
   
   	    // YOUR CODE HERE
          return 0;
   
   	}

      //implement Dijkstras
         public static int[] ShortestPath(int start, int finish, int goal){ /*goal should be 2 for distance, 3 for time, 4 for price*/
         int[] distance = new int[nodeCount];               //[0, inf, inf]
         int[] predecessor = new int[nodeCount];
         int[] returnData= new int[2];
         //ArrayList<String> queue = new ArrayList<String>();
         for(int i=0; i < nodeCount; i++){
            distance[i] = Integer.MAX_VALUE;
            predecessor[i] = -999;
         }
         List<String> inside = new ArrayList<String>();

         distance[start] = 0;
         
         Iterator<String> iterator = vertices.iterator();
         ArrayList<String> queue = new ArrayList<String>(); 
         List<String> adjList = new ArrayList<String>(); 
         String location;
         while (iterator.hasNext()){
            queue.add(iterator.next());
         }
         for(int i = 0; i < nodeCount; i++){
            location = queue.get(i);
            adjList = AdjList.get(i);
            if(!location.equals(start)){ //if the destination doesnt equal the start
               for(String dest : adjList){  //look for it in the adjacency list
                  
               }
            }
         }
     
     
         
         return returnData;
      }
   
   public static int relaxDistance(ArrayList<String> queue, String predecessor, String finish, int adder){
      if (predecessor.equals(finish)){
         return adder;
      }
      else{
         for(String str : queue){
            relaxDistance(queue, str, finish, adder+distanceGivenEdge(predecessor,str));
         }
         
      }
      return 1;
   }
    public static int relaxTime(ArrayList<String> queue, String predecessor, String finish, int adder){
      if (predecessor.equals(finish)){
         return adder;
      }
      else{
         for(String str : queue){
            relaxTime(queue, str, finish, adder+timeGivenEdge(predecessor,str));
         }
         
      }
      return 1;
   }
   
    public static int relaxCost(ArrayList<String> queue, String predecessor, String finish, int adder){
      if (predecessor.equals(finish)){
         return adder;
      }
      else{
         for(String str : queue){
            relaxCost(queue, str, finish, adder+costGivenEdge(predecessor,str));
         }
         
      }
      return 1;
   }
   
   //returns 0 if doesnt exist
   public static int distanceGivenEdge(String start, String end){
      for(int i = 0; i < EDGELIST.size(); i++){
         if(EDGELIST.get(i) == start){
            if(EDGELIST.get(i+1) == end){
               return 1; //(EDGELIST.get(i+2)).parseInt();
            }
         }
      }
      
      return 0;
   }
   
    //returns 0 if doesnt exist
   public static int timeGivenEdge(String start, String end){
      for(int i = 0; i < EDGELIST.size(); i++){
         if(EDGELIST.get(i) == start){
            if(EDGELIST.get(i+1) == end){
               return 1;//EDGELIST.get(i+3);
            }
         }
      }
      
      return 0;
   }

    //returns 0 if doesnt exist
   public static int costGivenEdge(String start, String end){
      for(int i = 0; i < EDGELIST.size(); i++){
         if(EDGELIST.get(i) == start){
            if(EDGELIST.get(i+1) == end){
               return 1;// EDGELIST.get(i+4);
            }
         }
      }
      
      return 0;
   }


}
