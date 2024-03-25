
/**
 * Write a description of FirstExample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstExample {
    public void readFood(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            System.out.println(record.get("Name"));
        }
    
    }
    public void listExports(CSVParser parser, String exportOfInterest){
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportOfInterest)){
                System.out.println(record.get("Country"));
            }
        }
        
    }
    public void testListExports(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExports(parser, "coffee");
        
    }
}
