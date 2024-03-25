
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){return "Not Found";}
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if(endIndex == -1){return "Not Found";}
        if((endIndex - startIndex) % 3 != 0){return "Not Found";}
        String total = dna.substring(startIndex, endIndex + 3);
        return total;
    }
    public void testSimpleGene(){
        String dna ="ADAATGHTTADADADDADTAA";
        String dna1 ="ADTGHTTADADADDADTAA";
        String dna2 ="ADAATGHTTADADADDADTCAA";
        String dna3 ="ADAATGHTTADATDADDADTAA";
        
        System.out.println(findSimpleGene(dna));
        System.out.println(findSimpleGene(dna1));
        System.out.println(findSimpleGene(dna2));
        System.out.println(findSimpleGene(dna3));
    }
}
