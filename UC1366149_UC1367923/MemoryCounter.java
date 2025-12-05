package UC1366149_UC1367923;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class MemoryCounter{

    public static long getSizeTrie(String filename){
        Trie trie = new Trie();
        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                trie.insert(in.next());
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return trie.getSize();
    }

    public static long getSizeCompressedTrie(String filename) {
        CompressedTrie cTrie = new CompressedTrie();
        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                cTrie.insert(in.next());
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return cTrie.getSize();
    }

    public static void main(String[] args) {
        String dir = "dictionaries/";
        try{
            PrintWriter outputCT = new PrintWriter("compressed-trie-memory.txt");
            PrintWriter outputT = new PrintWriter("trie-memory.txt");
            /*
            System.out.println("Computing for random lengths...\n");

            outputCT.print("RANDOM LENGTH:\n");
            outputT.print("RANDOM LENGTH:\n");

            outputCT.print(getSizeCompressedTrie(dir + "random-length_10000.txt"));
            outputCT.print('\t');
            outputCT.print(getSizeCompressedTrie(dir + "random-length_100000.txt"));
            outputCT.print('\t');
            outputCT.print(getSizeCompressedTrie(dir + "random-length_500000.txt"));
            outputCT.print('\t');
            outputCT.print(getSizeCompressedTrie(dir + "random-length_1000000.txt"));
            outputCT.print('\t');
            outputCT.println();
            outputCT.println();
            
            outputT.print(getSizeTrie(dir + "random-length_10000.txt"));
            outputT.print('\t');
            outputT.print(getSizeTrie(dir + "random-length_100000.txt"));
            outputT.print('\t');
            outputT.print(getSizeTrie(dir + "random-length_500000.txt"));
            outputT.print('\t');
            outputT.print(getSizeTrie(dir + "random-length_1000000.txt"));
            outputT.print('\t');
            outputT.println();
            outputT.println();
            */

            int[] sizes = { 5000, 10000, 15000, 17576 };
            int[] lengths = { 3 };

            for (int L : lengths) {
                System.out.println("Computing for length " + L + "...\n");

                outputCT.println("LENGTH " + L + ":");
                outputT.println("LENGTH " + L + ":");

                String filename = "length" + L + "_";

                long maxPossible = (long) Math.pow(26, L);

                for (int size : sizes) {

                    // Safety check: impossible request → skip
                    if (maxPossible < size) {
                        continue;
                    }

                    outputCT.print(getSizeCompressedTrie(dir + filename + size + ".txt"));
                    outputCT.print('\t');
                    outputT.print(getSizeTrie(dir + filename + size + ".txt"));
                    outputT.print('\t');
                }
                outputCT.println();
                outputT.println();
                outputCT.println();
                outputT.println();
            }

            outputCT.close();
            outputT.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}