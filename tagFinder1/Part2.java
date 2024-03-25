
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany (String stringa , String stringb){
        int count = 0;
        int startIndex = stringb.indexOf(stringa);
        while (true){
            
            if (startIndex == -1){
                break;
            }else{
                count++;
                startIndex = stringb.indexOf(stringa, startIndex + stringa.length());
            }
        }
        return count;
        
    }
    public void testHowMany (){
        int x = howMany("G","ABBFBBfBB");
        System.out.println(x);
        
    }
}
