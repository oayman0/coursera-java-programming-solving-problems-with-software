
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb){
        int firstIndex = stringb.indexOf(stringa);
        if(firstIndex == -1){return false;}
        int secondIndex = stringb.indexOf(stringa, firstIndex + stringa.length());    
        if(secondIndex == -1){return false;}
        return true;
    }
    public void testTwoOccurrences(){
        String a = "baa";
        String b = "babaanana";
        System.out.println(twoOccurrences(a, b));
        System.out.println(lastPart(a, b));
    }
    public String lastPart(String stringa, String stringb){
        int firstIndex = stringb.indexOf(stringa);
        if(firstIndex == -1){return stringb;}
        return stringb.substring(firstIndex + stringa.length());
    }
}
