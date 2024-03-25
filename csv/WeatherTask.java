
/**
 * Write a description of WeatherTask here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class WeatherTask {
    public CSVRecord coldestHourInFile (CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord record : parser){
            if(!record.get("TemperatureF").contains("-9999")){
                if(coldestSoFar == null){
                    coldestSoFar = record;
                }else{
                    double currTemp = Double.parseDouble(record.get("TemperatureF"));
                    double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                    if(currTemp < coldestTemp){
                        coldestSoFar = record;
                    }
                }
            }
        }
        return coldestSoFar;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord record = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temp: " + record.get("TemperatureF") + " At: " + record.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature (){
        String name = null;
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord record = coldestHourInFile(fr.getCSVParser());
            if(record != null){
                if(coldestSoFar == null){
                    coldestSoFar = record;
                    name = f.getName();
                }else{
                    double currTemp = Double.parseDouble(record.get("TemperatureF"));
                    double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                    if(currTemp < coldestTemp){
                        coldestSoFar = record;
                        name = f.getName();
                    }
                }
            }
        }
        return name;    
    }
    public void testFileWithColdestTemperature(){
        String name = fileWithColdestTemperature();
        FileResource fr = new FileResource("G:\\courses\\Java programming\\nc_weather\\nc_weather\\2013\\" + name);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser); 
        
        System.out.println("Coldest day was in file: " + name);
        System.out.println("Coldest Temperature that day was: " + record.get("TemperatureF"));
        System.out.println("All the Temperature in that day were:");
        parser = fr.getCSVParser();
        for(CSVRecord currRecord : parser){
            System.out.println(currRecord.get("DateUTC") +" "+ currRecord.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for(CSVRecord record : parser){
            if(!record.get("Humidity").contains("N/A")){
                if(lowestSoFar == null){
                    lowestSoFar = record;
                }else{
                    double currH = Double.parseDouble(record.get("Humidity"));
                    double lowestH = Double.parseDouble(lowestSoFar.get("Humidity"));
                    if(currH < lowestH){
                        lowestSoFar = record;
                    }
                }
            }
        }
        return lowestSoFar;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
        
    }
    
    public CSVRecord  lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = lowestHumidityInFile(parser);
            if(record != null){
                if(lowestSoFar == null){
                    lowestSoFar = record;
                }else{
                    double currH = Double.parseDouble(record.get("Humidity"));
                    double lowestH = Double.parseDouble(lowestSoFar.get("Humidity"));
                    if(currH < lowestH){
                        lowestSoFar = record;
                    }
                }
            }
            
        }
        return lowestSoFar;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
        
    }
    
    public double  averageTemperatureInFile (CSVParser parser){
        double total = 0;
        int count = 0 ;
       
        for(CSVRecord record : parser){
            if(!record.get("TemperatureF").contains("-9999")){
                total += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        return total/count;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avg);
    }
    
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value){
        double total = 0;
        int count = 0 ;
        for(CSVRecord record : parser){
            if((!record.get("TemperatureF").contains("-9999")) && (!record.get("Humidity").contains("N/A")) && (Double.parseDouble(record.get("Humidity")) >= (double)value ) ){
                total += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        if(count > 0){
            return total/count;
        }else{
            return 0;
        }
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(avg != 0){
            System.out.println("Average Temp when high Humidity is " + avg);
        }else{
            System.out.println("No temperatures with that humidity");
        }
    }
    
}
