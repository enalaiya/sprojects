public class NGKey{
   public String name;
   public String gender;

   public NGKey(String name, String gender){
      this.name = name;
      this.gender = gender;
   }
   
   @Override//need to do this so that I can compare other keys to the keys in here and have them compare as the same thing.
   public boolean equals(Object object){
      if (!(object instanceof NGKey)){
         return false;
      }
      NGKey reference = (NGKey) object;
      return this.name.equals(reference.name) && this.gender.equals(reference.gender);    
      
   }
   //assists in the comparing.
   @Override
   public int hashCode(){
      return name.hashCode() ^ gender.hashCode();
   }
   
   
   public String getName(){
      return this.name;
   }
   
   public String getGender(){
      //System.out.println(this.gender);
      return this.gender;
   }
   

}