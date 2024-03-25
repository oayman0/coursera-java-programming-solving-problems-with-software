
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.lang.Math;
public class Part1 {
    public StorageResource findMultipleGenes(String dna){
        dna = dna.toUpperCase();
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while (true){
           String currGene = findGene(dna, startIndex);
           if(currGene.isEmpty()){
                break;
            }
           sr.add(currGene);
           startIndex = currGene.length() + dna.indexOf(currGene, startIndex);
           
        }
        return sr;
   }
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        dna = dna.toUpperCase();
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1){
            if((currIndex - startIndex) %3 != 0){
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }else{
            return currIndex;
            }       
        }
        return currIndex;
    }
    public String findGene(String dna,int where){
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex) ){
            minIndex = tagIndex;
        }
        if(minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    public double cgRatio (String dna){
        int count = 0;
        for (int i = 0; i < dna.length(); i++){
           if( dna.substring(i, i + 1).equals("C") || dna.substring(i, i + 1).equals("G")){
                count++;
            }   
        }
        return (double) count / (double) dna.length();
    }
    public int countCTG (String dna){
        int count = 0;
        int startIndex = 0;
        while(true){
            int currIndex = dna.indexOf("CTG", startIndex);
            if(currIndex == -1){
                return count;
            }else{
                count++;
                startIndex = currIndex + 3;  
            }
        }
    }
    public void processGenez (StorageResource sr){
        for(String s : sr.data()){
            System.out.println(s);
        }
    }
    public void processGenes (StorageResource sr){
        int count = 0;
        int cgCount = 0;
        int longest = 0;
        int countTotal = 0;
        for(String s : sr.data()){
            System.out.println(s);
            if(s.length() > 60){
                System.out.println("Length greater than 60: " + s);
                count++;
            }
            if(cgRatio(s) > 0.35){
                System.out.println("cgRatio higher than 0.35: " + s);
                cgCount++;
            }
            if(s.length() > longest){
                longest = s.length();
            }
            countTotal++;
        }
        System.out.println("## Number of strings > 60  is: " + count);
        System.out.println("## Number of cgRatio > 0.35 is: " + cgCount);
        System.out.println("## Length of longest gene is: " + longest);
        System.out.println("## Total Count of genes is: " + countTotal);
    }
    public void testFindMultipleGenes(){
        String dna = "SSSATGAAATAAGHJATGBBBTGAHHHATGJTAAATGCCTAGAG";
        StorageResource sr = findMultipleGenes(dna);
        for(String s : sr.data()){
            System.out.println(s);
        }
    }
    public void testCgRatio(){
        double ratio = cgRatio("ASDCCFGGH");
        System.out.println(ratio);
    }
     public void testCountCTG(){
        int count = countCTG("ASDCCTGFGCTGGHCTGJK");
        System.out.println(count);
    }
   public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource geneList = findMultipleGenes(dna);
        processGenes(geneList);
        int count = countCTG(dna);
        System.out.println(count);
    }
}
