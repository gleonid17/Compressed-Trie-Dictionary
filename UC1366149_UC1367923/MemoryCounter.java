package UC1366149_UC1367923;

import java.util.Scanner;
import java.io.File;

public class MemoryCounter{

    public static int getSizeTrie(String filename){
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

    public static int getSizeCompressedTrie(String filename) {
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
        System.out.println(getSizeCompressedTrie("dictionaries/length3_10000.txt"));
    }
}