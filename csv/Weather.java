
/**
 * Write a description of Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Weather {
    public CSVRecord hottest (CSVParser parser){
        CSVRecord largestSoFar = null;
        for(CSVRecord record : parser){
            largestSoFar = compareRecords(largestSoFar,record);
        }
        return largestSoFar;
    }
    public void testHottest(){
        FileResource fr = new FileResource();
        CSVRecord largest = hottest(fr.getCSVParser());
        String temp = largest.get("TemperatureF");
        String time = largest.get("TimeEST");
        System.out.println("largest is " + temp + " at" + time);
    }
    
    public CSVRecord hottestManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord largest = hottest(fr.getCSVParser());
            largestSoFar = compareRecords(largestSoFar,largest); 
        
        }
        return largestSoFar;
    }
     public void testHottestManyDays(){
        CSVRecord largest = hottestManyDays();
        String temp = largest.get("TemperatureF");
        
        String date = largest.get("DateUTC");
        System.out.println("largest ever is " + temp + " at" + date);
    }
    public CSVRecord compareRecords(CSVRecord largestSoFar, CSVRecord largest){
        if(largestSoFar == null){
                largestSoFar = largest;
        }else{
            double currTemp = Double.parseDouble(largest.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF")); 
            if (currTemp > largestTemp){
                largestSoFar = largest;
            }
        }
        return largestSoFar;
    }
}
