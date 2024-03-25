
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord record : fr.getCSVParser(false)){
            if(Integer.parseInt(record.get(2)) <= 100){
                System.out.println("Name " + record.get(0) +
                " Gender " + record.get(1) +
                " Num Born " + record.get(2)
                );
            }
            }
    }
    public void totalBirths (FileResource fr){
        int totalBoys = 0;
        int totalGirls = 0;
        int boysNamesCount = 0;
        int girlsNamesCount = 0;
        for(CSVRecord record : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            if(record.get(1).contains("M")){
                totalBoys += numBorn;
                boysNamesCount++;
            }else{
                totalGirls += numBorn;
                girlsNamesCount++;
            }
            
        }
        int totalBirths = totalBoys + totalGirls;
        int totalNames = boysNamesCount+ girlsNamesCount;
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Total Boys Births = " + totalBoys);
        System.out.println("Total Girls Births = " + totalGirls);
        System.out.println("Total Names Count in file were " + totalNames);
        System.out.println("Boys Names Count = " + boysNamesCount);
        System.out.println("Girls Names Count = " + girlsNamesCount);
        
    }
    public void testTotalBirths (){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
              
        FileResource fr = new FileResource(
        "G:\\courses\\Java programming\\us_babynames\\us_babynames_by_year\\yob"
        + year + ".csv");
        
        int rank = 1;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                if(record.get(0).equals(name)){
                    return rank;
                }else{
                    rank++;
                }
            }
        }
        return -1;
    }
    public void testGetRank(){
        System.out.println("Rank is " + getRank(1971, "Frank", "M"));
    }
    
    public String getName(int year, int rank, String gender){
        
        FileResource fr = new FileResource(
        "G:\\courses\\Java programming\\us_babynames\\us_babynames_by_year\\yob"
        + year + ".csv");
        int currRank = 1;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                if(rank == currRank){
                    return record.get(0);
                }else{
                    currRank++;
                }
            }
        
        }
        return "NO NAME";      
    }
    public void testGetName(){
        String name = getName(1982, 450, "M");
        System.out.println(name);
    } 
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
            int rank = getRank(year, name, gender);
            String newName = getName(newYear, rank, gender);
            return newName;
    } 
    public void testWhatIsNameInYear(){

        String name = whatIsNameInYear("Owen", 1974, 2014, "M");
        System.out.println("New name would be: " + name);
    }
    
    public int yearOfHighestRank (String name, String gender){
        int highestRank = -1;
        int highestYear = -1;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int currYear = Integer.parseInt(f.getName().substring(3,7));
            int currRank = getRank(currYear, name, gender);
            if(currRank != -1){
                if(highestRank == -1){
                    highestRank = currRank;
                    highestYear = currYear;
                }else if(currRank < highestRank){
                        highestRank = currRank;
                        highestYear = currYear;
                }
                
            }
        }
        return highestYear;
    }
    public void testYearOfHighestRank(){
        int year = yearOfHighestRank("Mich","M");
        System.out.println(year);
    }
    
    public double getAverageRank(String name, String gender){
        double totalRank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int currYear = Integer.parseInt(f.getName().substring(3,7));
            int currRank = getRank(currYear, name, gender);
            if(currRank != -1){
                totalRank += currRank;
                count++;
            }
        }
        if(count != 0){
            return totalRank / count;        
        }else{
            return -1.0;
        }
    }
    public void testGetAverageRank(){
        double avg = getAverageRank("Robert", "M");
        System.out.println(avg);
    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        int rank = getRank(year, name, gender);
        int total = 0;
        if(rank != -1){
            FileResource fr = new FileResource(
            "G:\\courses\\Java programming\\us_babynames\\us_babynames_by_year\\yob"
            + year + ".csv");
            for(CSVRecord record : fr.getCSVParser(false)){
                if(record.get(1).equals(gender)){
                    if(!record.get(0).equals(name)){
                        total += Integer.parseInt(record.get(2)); 
                    }else{
                        return total;
                    }
                }
            }
        }
        return 0;
    }
    public void testGetTotalBirthsRankedHigher(){
        int total = getTotalBirthsRankedHigher(1990,"Emily","F");
         System.out.println(total);
    }
}
