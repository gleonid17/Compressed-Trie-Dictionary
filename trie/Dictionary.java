package trie;

import java.io.File;
import java.util.Scanner;

public class Dictionary {
    private CompressedTrie dictionary;

    public Dictionary() {
        this.dictionary = new CompressedTrie();
    }  

    public void loadDictionary(String filename) {
        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);
            while(in.hasNext()) {
                String word = cleanWord(in.next());
                if(word.length() > 0)
                    this.dictionary.insert(word);
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); //En eimai sigouros an en swsto throw exception
            System.exit(1);
        }

    }

    public void updateImportance(String filename) {
        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);
            while(in.hasNext()) {
                String word = cleanWord(in.next());
                if(word.length() == 0)
                    continue;

                if (this.dictionary.search(word))
                    this.dictionary.findPrefixNode(word).node.incrementImportance();
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); //En eimai sigouros an en swsto throw exception
            System.exit(1);
        }
    }

    public CompressedTrie getTrie(){
        return dictionary;
    }

    public static String cleanWord(String word) {
        if (word == null || word.length() == 0) 
            return "";
        word = word.toLowerCase();
        int first = 0, last = 0;
        for (first = 0; first < word.length(); first++) {
            if (word.charAt(first) >= 'a' && word.charAt(first) <= 'z') 
                break;
        }
        for (last = word.length() - 1; last >= 0; last--) {
            if (word.charAt(last) >= 'a' && word.charAt(last) <= 'z') 
                break;
        }
        if (first > last || first >= word.length()) 
            return "";
        return word.substring(first, last + 1);
    }
}
