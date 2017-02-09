public class Node {

   // Creating private variables
   private int totalBabyNames;
   private int rank;
   private int key;
   private int height;
   private String gender;
   private String name;
   private Node parent;
   private Node leftChild;
   private Node rightChild;
   private Node root;
   
   
   // Creates a constructor 
   public Node(String name, String gender, int key) {
   this.name = name;
   this.key = key;
   this.gender = gender;
   } // End of constructor 
   
   
   
 ////////////////////////////////////////////////////////////////             GETTERS             //////////////////////////////////////////////////////////
   
// Getter for Node Name
   public String name() {
      return name;
   }

// Getter for Node key
   public int key() {
      return key; 
   }
   
// Getter for Node leftChild   
   public Node leftChild() {
     return leftChild;
   }
   
// Getter for Node rightChild
   public Node rightChild () {
      return rightChild;
   }
   
// Getter for Node parent
   public Node parent() {
      return parent;
   }   
   
// Getter for gender 
   public String gender() {
      return gender;
   }
   
// Getter for height
   public int height() {
      return height;
   }   
  
 // Getter for rank
   public int rank() {
      return rank;
   }
   
// Getter for totalBabyNames
   public int totalBabyNames(){
      return totalBabyNames;
   }


////////////////////////////////////////////////////////////////////            SETTERS            ////////////////////////////////////////////////////////////////
  
// Setter method for parent
public void setParent(Node n) {
   parent = n;
}  

// Setter method for left child
public void setLeftChild(Node n) {
   leftChild = n;
}

public void setRightChild (Node n) {
   rightChild = n;
}

public void setHeight (int height) {
   this.height = height;
}

// Setter method for rank
public void setRank (int number) {
   rank = number;
}

// Setter method for totalBabyNames
public void setTotalBabyNames (int number) {
   totalBabyNames = number;

}




} // End of node class