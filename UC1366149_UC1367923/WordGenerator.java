package UC1366149_UC1367923;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class WordGenerator {
    
    private final static int alphabetSize = 26;
    private String filename;
    private double[] charCDF;
    private double[] lengthCDF;
    private double[] charProb;
    private double[] lengthProb;

    public WordGenerator(String filename){
        this.filename = filename;

        calculateProbabilities();
        calculateCDFs();
    }

    public double[] getCharProb(){
        return charProb;
    }

    public double[] getLengthProb(){
        return lengthProb;
    }

    private void calculateProbabilities(){
        int size = maxLength() + 1;
        lengthProb = new double[size];
        charProb = new double[alphabetSize];

        try{
            // Create input stream
            File input = new File(filename);
            Scanner scan = new Scanner(input);

            int[] lengthCount = new int[size];
            int[] charCount = new int[alphabetSize];
            int totalWords = 0;
            int totalChars = 0;

            while(scan.hasNext()){
                String word = scan.next();

                // Increase measurements for length
                lengthCount[word.length()]++;
                totalWords ++;

                // Increase measurements for characters
                for(int i=0; i<word.length(); i++){
                    if(word.charAt(i) < 'a' || word.charAt(i) > 'z'){
                        System.out.println("Invalid word given: " + word);
                        continue;
                    }
                    charCount[word.charAt(i) - 'a'] ++;
                    totalChars++;
                }
            }

            for(int i=0; i<size; i++){
                lengthProb[i] = (double)lengthCount[i] / totalWords;
            }

            for(int i=0; i<alphabetSize; i++){
                charProb[i] = (double)charCount[i] / totalChars;
            }

            scan.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

    }

    private void calculateCDFs(){
        charCDF = new double[alphabetSize];
        lengthCDF = new double[lengthProb.length];

        lengthCDF[0] = lengthProb[0];
        for(int i=1; i<lengthProb.length; i++){
            lengthCDF[i] = lengthProb[i] + lengthCDF[i-1];
        }

        charCDF[0] = charProb[0];
        for(int i=1; i<charProb.length; i++){
            charCDF[i] = charProb[i] + charCDF[i-1];
        }
    }

    public String generateWord(){
        StringBuilder word = new StringBuilder();

        for(int i=0; i<randomLength(); i++){
            word.append(randomChar());
        }
        return word.toString();
    }

    public String generateWord(int length){
        StringBuilder word = new StringBuilder();

        for(int i=0; i<length; i++){
            word.append( randomChar());
        }
        return word.toString();
    }

    public void generateDictionary(int size){
        try{
            FileWriter output = new FileWriter("random-length_" + size +".txt");
            
            output.write(generateWord());
            for(int i=1; i<size; i++){
                output.write('\n');
                output.write(generateWord());
            }

            output.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.err.println(e.getStackTrace());
            System.exit(1);
        }
    }

    public void generateDictionary(int size, int wordLength){
        try{
            FileWriter output = new FileWriter("length" + wordLength + "_" + size +".txt");
            
            output.write(generateWord(wordLength));
            for(int i=1; i<size; i++){
                output.write('\n');
                output.write(generateWord(wordLength));
            }

            output.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.err.println(e.getStackTrace());
            System.exit(1);
        }
    }

    private char randomChar(){
        double random = Math.random();

        for(int i=0; i<charCDF.length; i++){
            if(random < charCDF[i])
                return (char)('a' + i);
        }

        return 'z';
    }

    private int randomLength(){
        double random = Math.random();

        for(int i=1; i<lengthCDF.length; i++){
            if(random < lengthCDF[i])
                return i;
        }

        return 0;
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
        WordGenerator wg = new WordGenerator("words_alpha.txt");

        wg.generateDictionary(1000000);
        wg.generateDictionary(1000000, 5);

        // for(int i=0; i<100; i++){
        //     System.out.println(wg.generateWord());
        // }

        // System.out.println("\n****************************************\n");

        // double[] length =  wg.getLengthProb();
        // double total = 0;
        // for(int i=0; i<length.length; i++){
        //     System.out.println("Length " + i +": " + length[i]);
        //     total += length[i];
        // }
        // System.out.println("Total: " + total);

        // System.out.println("\n****************************************\n");

        // total = 0;
        // double[] characters =  wg.getCharProb();
        // for(int i=0; i<characters.length; i++){
        //     System.out.println((char)('a' + i)  +": " + characters[i]);
        //     total += characters[i];
        // }
        // System.out.println("Total: " + total);

    }
}
