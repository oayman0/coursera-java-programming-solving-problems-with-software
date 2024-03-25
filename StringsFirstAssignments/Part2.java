
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String total = "";
        if(dna == dna.toUpperCase()){
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }else if(dna == dna.toLowerCase()){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1){return "Not Found";}
        int endIndex = dna.indexOf(stopCodon, startIndex + 3);
        if(endIndex == -1){return "Not Found";}
        if((endIndex - startIndex) % 3 != 0){return "Not Found";}
        total = dna.substring(startIndex, endIndex + 3);
        return total;
    }
    public void testSimpleGene(){
        String dna ="ADAATGHTTADADADDADTAA";
        String dna1 ="iiakatgomataa";
        String dna2 ="ADAATGHTTADADADDADTCAA";
        String dna3 ="ADAATGHTTADATDADDADTAA";
        
        System.out.println(findSimpleGene(dna,"ATG","TAA"));
        System.out.println(findSimpleGene(dna1,"ATG","TAA"));
        System.out.println(findSimpleGene(dna2,"ATG","TAA"));
        System.out.println(findSimpleGene(dna3,"ATG","TAA"));
    }
}
