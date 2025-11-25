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
                String word = in.next().toLowerCase();
                while(1) {
                    int first = 0, last = word.length() - 1;
                    boolean foundFirst = false, foundLast = false;
                    
                }
            }
        }
    }

    // The word doesn't need to be trimmed, just lowercased right?
    /*public void loadDictionary(String filename) {
        try (FileReader in = new FileReader(filename)) {
            StringBuilder word = new StringBuilder();
            int character;
            while ((character = in.read()) != -1) {
                if (character >= 'A' && character <= 'Z') {
                    character = character - 'A' + 'a';
                }
                if (character == '\n' || character == ' ' || character == '\r' || character == '\t') {
                    this.dictionary.insert(word.toString());
                    word.setLength(0);
                }
                else {
                    word.append(character);
                }
            }
        }catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }*/

    public void updateImportance(String filename) {
        try (FileReader in = new FileReader(filename)) {
            StringBuilder word = new StringBuilder();
            int character;
            while ((character = in.read()) != -1) {
                if (character >= 'A' && character <= 'Z') {
                    character = character - 'A' + 'a';
                }
                if (character == '\n' || character == ' ' || character == '\r' || character == '\t') {
                    int letters = 0;

                    word.setLength(0);
                }
                else {
                    word.append(character);
                }
            }
        }catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    public CompressedTrie getTrie(){
        return dictionary;
    }
}
