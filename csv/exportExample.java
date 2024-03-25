
/**
 * Write a description of exportExample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class exportExample {

    public void test(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println(countryInfo(parser, "Nauru"));
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
        
        
    }
    public String countryInfo (CSVParser parser, String country){
        for (CSVRecord record : parser){
            if(record.get("Country").equalsIgnoreCase(country)){
                return (country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        }
        return "Not Found";
    }
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2) ){
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters (CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            if(record.get("Value (dollars)").length() > amount.length()){
                 System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }        
    }
     
}
