package UC1366149_UC1367923;

import java.io.File;
import java.util.Scanner;

public class WordAnalyzer {
    
    private String filename;

    public WordAnalyzer(String filename){
        this.filename = filename;
    }

    public float[] getLetterProbability(){
        float[] probabilities = new float[26];
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
                probabilities[i] = (float)count[i] / totalCount;
            }

            scan.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return probabilities;
    }

    public float[] getLengthProbability(){
        int size = maxLength() + 1;
        float[] probability = new float[size];
        try{
            File input = new File(filename);
            Scanner scan = new Scanner(input);

            float[] count = new float[size];
            int totalWords = 0;

            while(scan.hasNext()){
                String word = scan.next();
                count[word.length()]++;
                totalWords ++;
            }

            for(int i=0; i<size; i++){
                probability[i] = (float)count[i] / totalWords;
            }

            scan.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        return probability;
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

        WordAnalyzer wa = new WordAnalyzer("all-words-sum.txt");

        float[] charprobs = wa.getLetterProbability();
        float total = 0;
        for(int i=0; i<charprobs.length; i++){
            System.out.println((char)(i + 'a') + " : " + charprobs[i]);
            total += charprobs[i];
        }
        System.out.println("Total: " + total);

        System.out.println("\n****************************************\n");

        float[] lengthProb = wa.getLengthProbability();
        total = 0;
        for(int i=0; i<lengthProb.length; i++){
            System.out.println("Length of " + i + ": " + lengthProb[i]);
            total += lengthProb[i];
        }
        System.out.println("Total: " + total);
    }
}
