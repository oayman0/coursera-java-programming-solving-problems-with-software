import edu.duke.*;
public class Part4 {
    public void urlLocator (){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : ur.words()) {
            int index = word.indexOf("youtube.com");
            if(index!= -1){
                int urlStart = word.indexOf("\"");
                int urlEnd = word.lastIndexOf("\"");
                System.out.println(word.substring(urlStart,urlEnd+1));
            }
            
        }
        
        
    }
}
