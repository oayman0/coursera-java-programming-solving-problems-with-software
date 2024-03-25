
/**
 * Write a description of gene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class gene {
    public void findMultipleGenesz(String dna){
        int start = 0;
        int minIndex = 0;
        while(true){
        int startIndex = dna.indexOf("ATG", start);
        if(startIndex == -1){
            System.out.println("NotFound");
            break;
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex) ){
            minIndex = tagIndex;
        }
         if(minIndex == -1){
            System.out.println("NotFound");
            break;
        }
        System.out.println( dna.substring(startIndex, minIndex + 3));
        start = minIndex;
    }
   }
   
    public void findMultipleGenes(String dna){
        int startIndex = 0;
        while (true){
           String currGene = findGene(dna, startIndex);
           if(currGene.isEmpty()){
                break;
            }
           System.out.println(currGene);
           startIndex = currGene.length() + dna.indexOf(currGene, startIndex);
           
        }
        
   }
    public int findStopCodon(String dna, int startIndex, String stopCodon){
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
    public void testFindMultipleGenes(){
        String dna = "DDATGAAATAGJJATGBBBTGAKKKATGCCCTAAKKATGOOOTAA";
        findMultipleGenesz(dna);
    }
    public void testFindStopCodon(){
        int x = findStopCodon("AAATAG",0,"TAG");
        System.out.println(x);
    }
}
