package UC1366149_UC1367923;

import java.io.File;
import java.util.Scanner;

public class WordAnalyzer {
    
    private String filename;

    public WordAnalyzer(String filename){
        this.filename = filename;
    }

    public double[] getLetterProbability(){
        double[] probabilities = new double[26];
        try{
            File input = new File(filename);
            Scanner scan = new Scanner(input);

            int[] count =  new int[26];
            int totalCount = 0;

            while(scan.hasNext()){
                String word = scan.next();
                for(int i=0; i<word.length(); i++){
                    if(word.charAt(i) < 'a' || word.charAt(i) > 'z'){
                        System.out.println("Invalid word given: " + word);
                        continue;
                    }
                    count[word.charAt(i) - 'a'] ++;
                    totalCount++;
                }
            }

            for(int i=0; i<26; i++){
                probabilities[i] = (double)count[i] / totalCount;
            }

            scan.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return probabilities;
    }

    public double[] getLengthProbability(){
        int size = maxLength() + 1;
        double[] probability = new double[size];
        try{
            File input = new File(filename);
            Scanner scan = new Scanner(input);

            int[] count = new int[size];
            int totalWords = 0;

            while(scan.hasNext()){
                String word = scan.next();
                count[word.length()]++;
                totalWords ++;
            }

            for(int i=0; i<size; i++){
                probability[i] = (double)count[i] / totalWords;
            }

            scan.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        return probability;
    }

    public String generateWord(int length, double[] characterProb){
        String word = "";

        double[] cumulative = getCumulativeProbability(characterProb);

        for(int i=0; i<length; i++){
            word += randomChar(cumulative);
        }
        return word;
    }

    private char randomChar(double[] cumulativeCharProb){
        double random = Math.random();

        for(int i=0; i<cumulativeCharProb.length; i++){
            if(random < cumulativeCharProb[i])
                return (char)('a' + i);
        }

        return 'z';
    }

    private double[] getCumulativeProbability(double[] probability){
        double[] cumulative = new double[probability.length];
        cumulative[0] = probability[0];
        for(int i=1; i<probability.length; i++){
            cumulative[i] = probability[i] + cumulative[i-1];
        }
        return cumulative;
    }

    private int maxLength(){
        int maxLength = 0;
        try{
            File input = new File(filename);
            Scanner scan = new Scanner(input);

            while(scan.hasNext()){
                String word = scan.next();
                if(word.length() > maxLength)
                    maxLength = word.length();
            }

            scan.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return maxLength;
    }

    public static void main(String[] args){

        WordAnalyzer wa = new WordAnalyzer("words_alpha.txt");

        double[] charprobs = wa.getLetterProbability();
        // float total = 0;
        // for(int i=0; i<charprobs.length; i++){
        //     System.out.println((char)(i + 'a') + " : " + charprobs[i]);
        //     total += charprobs[i];
        // }
        // System.out.println("Total: " + total);

        // System.out.println("\n****************************************\n");

        // double[] lengthProb = wa.getLengthProbability();
        // total = 0;
        // for(int i=0; i<lengthProb.length; i++){
        //     System.out.println("Length of " + i + ": " + lengthProb[i]);
        //     total += lengthProb[i];
        // }
        // System.out.println("Total: " + total);

        // double[] cumulative = wa.getCumulativeProbability(charprobs);
        // for(int i=0; i<cumulative.length; i++){
        //     System.out.println(cumulative[i]);
        // }

        int i=1;
        while(true){
            if(wa.generateWord(5, charprobs).equals("louuu"))
                System.out.println("found louuu at trie " + i);
            i++;
        }
    }
}
