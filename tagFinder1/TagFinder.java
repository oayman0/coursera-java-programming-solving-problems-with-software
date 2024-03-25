/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    public String findGene(String dna){
        String total = "";
        int start = dna.indexOf("ATG"); 
        if(start == -1){return "Not Found";}
        int end = dna.indexOf("TAA", start+3);
        if(end == -1){return "Not Found";}        
        if((end - start) % 3 == 0){
        total = dna.substring(start, end+3);
        return total;
        }else{
        return "Not Found";
        }
    }
    public void testFindGene(){
        String dna = "AGAGATGOMATAAKHALAF";
          String dna2 = "AGAGATGOMARTAKHALaFTAA";
        String result = findGene(dna);
        String result2 = findGene(dna2);    
        System.out.println(result);
        System.out.println(result2);
    }
    public String findProtein(String dna) {
        int start = dna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public void testing() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }
}
