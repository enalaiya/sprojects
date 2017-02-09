
public class RNValue{
   public int rank;
   public int number;
   public RNValue(int rank, int number){
      this.rank = rank;
      this.number = number;
   }
   
  /* @Override
   public boolean equals(Object object){
      if (!(object instanceof RNValue)){
         return false;
      }
      RNValue reference = (RNValue) object;
      return this.rank.equals(reference.rank) && this.number.equals(reference.number);    
      
   }
   
   @Override
   public int hashCode(){
      return rank.hashCode() ^ number.hashCode();
   }
   */
   public int getRank(){
      return this.rank;
   }

   public int getNumber(){
      return this.number;
   } 
}