// Class that has 2 methods
// 1st Method is add which creates a new node given values of name, gender and key are passed is a paramaters
// 2nd Method is Inserting the newley created node in the previous add method into the correct spot using the addNodeToTree method which is recursive

public class BinaryTree {

   Node root;
   static int rankNum = 1;
   static int totalBabyNames = 0;
   
//////////////////////////// ADD METHOD /////////////////////////////// CREATES A NEW NODE OBJECT AND CALLS TRAVERSE AND ADD NODE METHOD BELOW /////////   
   
   public void add(String name, String gender, int key){

   Node nodeToInsert = new Node(name, gender, key);
   totalBabyNames += nodeToInsert.key();
   
    if ((nodeToInsert.gender().equals("M"))) { // Condition that checks if new node's gender is Male than start the ranking at 1 again 
      rankNum = 1;
   
   }
   

   
   // Want to make newly created node's height 0
   nodeToInsert.setHeight(0);
   nodeToInsert.setLeftChild(null);
   nodeToInsert.setRightChild(null);
   
   // Set rank of node
   nodeToInsert.setRank(rankNum);
   
   
  
    
   
   if(root == null){
      root = nodeToInsert;
      root.setRank(1);
   }
   
   addNodeToTree(root, nodeToInsert);
   
    rankNum ++;
   } // End of add method 


////////////////////////////////// TRAVERSE AND ADD METHOD PLACING NODE IT CORRECT POSITION //////////////////////////////////



   private Node addNodeToTree (Node node, Node nodeToInsert) {
      
      
      
      
      
      if (nodeToInsert.key() < node.key()) { // Checking to see if node to add's key value is less than current node
      
         if(node.leftChild() == null){ // Checking to see if current node does not have a left child.  If left child is null then insert NodetoAdd as current node's left child
           
           // This is what I want --> node.leftChild() = nodeToInsert;
          
          node.setLeftChild(nodeToInsert);
          nodeToInsert.setParent(node);
          int increaseHeight = ((nodeToInsert.parent()).height() + 1);
          nodeToInsert.setHeight(increaseHeight);
          
           
            
         } else {
         
         addNodeToTree(node.leftChild(), nodeToInsert);
         
         } // End of else
   

   
      } else if (nodeToInsert.key() > node.key()) { // Then node to add's value is greater than current node and we must traverse the right child of current node
         
         if (node.rightChild() == null){
         
            node.setRightChild(nodeToInsert);
            nodeToInsert.setParent(node);
            int increaseHeight = ((nodeToInsert.parent()).height() + 1);
            nodeToInsert.setHeight(increaseHeight);

            
             // Checking to see if current node does not have right child.  If true then insert NodeToAdd as node.rightChild
         
         } else {
      
         
         addNodeToTree(node.rightChild(), nodeToInsert);
         
         } // End of else
      
      } // End of else if
      
      
      
      // assigning height of current node to max height of child + 1 for the newly inserted node
      //node.setHeight(nodeMaxHeight);
      int balance = balance(node);
      
      
      
      if (( balance > 1) && (node.key() > nodeToInsert.key())) { // left left heavy 
         rotateRight(node.parent());
      } // End of left left heavy
      
      
      if (( balance < -1) && (node.key() < nodeToInsert.key())) { // right right heavy
         rotateLeft(node.parent());
      } // End of right right heavy
      
      if ((balance > 1) && (node.key() < nodeToInsert.key())) { // left right heavy
         doubleRotateRight(node.parent());
      } // End of left right heavy aka double rotate right 
      
      if (( balance < -1) && (node.key() > nodeToInsert.key())) { // right left heavy
         doubleRotateLeft(node.parent());
      } // End of right left heavy aka double rotate left  
      
      
      
return node;  
   } // End of addNodeToTree Method 

// Method that returns max Node
public int max(int a, int b){
   if(a > b) {
      return a;
   } else {
      return b;
   }

} // End of max method






// Method that returns balance of node (Height of left child - Height of right child)
public int balance(Node n) {
   Node placeholder = new Node("Noname", "N", 0);
   
   if ((n.leftChild() == null) && (n.rightChild() == null)) {
      return 0;
   }
   if ((n.leftChild() == null) && (n.rightChild() != null)){
      placeholder.setHeight(0);
      Node rChild = n.rightChild();
      return (placeholder.height() - rChild.height());  
      }
   if ((n.rightChild() == null) && (n.leftChild() != null)) {
      placeholder.setHeight(0);
      Node lChild = n.leftChild();
      return (lChild.height() - placeholder.height());
      
   }else { 
   
      Node leftChild = n.leftChild();
      Node rightChild = n.rightChild();
      return ((leftChild.height()) - (rightChild.height())); 
       
    } // End of else


} // End of balance method


/////////////////////////////        ROTATE LEFT          //////////////////////////////

public void rotateLeft(Node n){
   Node old = n;
   Node rightC = n.rightChild();
   Node rightGrandChild = rightC.rightChild();
   n = rightC;
   n.setLeftChild(old);
   n.setRightChild(rightGrandChild);
   
    // Adjust height of nodes 
   rightC.setHeight(rightC.height() - 1);
   old.setHeight(old.height() + 1);
   rightGrandChild.setHeight(rightGrandChild.height() - 1);
   
   //Adjust parent of nodes
   rightC.setParent(old.parent());
   rightC.setLeftChild(old);
   
   
} // End of rotate left method


//////////////////////////////       ROTATE RIGHT       //////////////////////////////

public void rotateRight(Node n){
   Node old = n;
   Node leftC = n.leftChild();
   Node leftGrandChild = leftC.leftChild();
   n = leftC;
   
   n.setRightChild(old);
   n.setLeftChild(leftGrandChild);
   
   // Adjust height of nodes 
   leftC.setHeight(leftC.height() - 1);
   old.setHeight(old.height() + 1);
   leftGrandChild.setHeight(leftGrandChild.height() - 1);
   
   //Adjust parent of nodes
   leftC.setParent(old.parent());
   leftC.setRightChild(old);
   
} // End of rotate right Method

//////////////////////////// Double Rotate Left Method /////////////////////////////

public void doubleRotateLeft(Node n){
   Node old = n;
   Node rightC = n.rightChild();
   Node rightGrandChild = rightC.leftChild();
   
   // First staighten out tree and swap child and grandchild
   Node rightCtemp = rightC;
   rightC = rightGrandChild;
   rightC.setLeftChild(null);
   rightC.setRightChild(rightCtemp);
   
   // Then rotate left as usual
   n = rightC;
   n.setLeftChild(old);
   n.setRightChild(rightC.rightChild());
   
      // Adjust height of nodes 
   n.setHeight(n.height() - 2);
   old.setHeight(old.height() + 1);
   
   //Adjust parent of nodes
   rightC.setParent(old.parent());
   rightC.setRightChild(rightCtemp);
   rightC.setLeftChild(old);
   old.setRightChild(null);
   rightCtemp.setLeftChild(null);

} // End of double Rotate Left Method 

//////////////////////////    Double Rotate Right method  /////////////////////////////////

public void doubleRotateRight(Node n){
   Node old = n;
   Node leftC = n.leftChild();
   Node leftGrandChild = leftC.rightChild();
   
   // First staighten out tree and swap child and grandchild
   Node leftCtemp = leftC;
   leftC = leftGrandChild;
   leftC.setRightChild(null);
   leftC.setLeftChild(leftCtemp);
   
   // Then rotate left as usual
   n =leftC;
   n.setRightChild(old);
   n.setLeftChild(leftC.leftChild()); 
   
    // Adjust height of nodes 
   n.setHeight(n.height() - 2);
   old.setHeight(old.height() + 1);
   
   //Adjust parent of nodes
   leftC.setParent(old.parent());
   leftC.setRightChild(old);
   leftC.setLeftChild(leftCtemp);
   old.setLeftChild(null);
   leftCtemp.setRightChild(null);
   

return;

} // End of double Rotate Right Method 


////////////////////////////////////////// Method  that searches name in ArrayList  /////////////////////////////////////////////

public void searchName( String name){
         AvlLoadArray newInstance1 = new AvlLoadArray();
         newInstance1.avlSearchName(name);       
}



//////////////////////////////////      Method that searchs unique name in ArrayList ////////////////////////////////////

public void searchUniqueName(){
         AvlLoadArray newInstance2 = new AvlLoadArray();
         newInstance2.uniqueName();


}


///////////////////////////////////        Method Most Popular Name  in ArrayList    ///////////////////////////


public void mostPopularName(){
         AvlLoadArray newInstance3 = new AvlLoadArray();
         newInstance3.avlMostPopularName();


}


///////////////////////////////////        Method Display Name    in ArrayList       //////////////////////////////////
public void displayNameOfChild(){
         AvlLoadArray newInstance4 = new AvlLoadArray();
         newInstance4.displayName();
}












} // End of BinaryTree class