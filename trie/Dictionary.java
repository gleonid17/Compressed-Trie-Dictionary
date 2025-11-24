package trie;

import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    private CompressedTrie Dictionary;

    public Dictionary() {
        this.Dictionary = new CompressedTrie();
    }  

    // The word doesn't need to be trimmed, just lowercased right?
    public void loadDictionary(String filename) {
        try (FileReader in = new FileReader(filename)) {
            StringBuilder word = new StringBuilder();
            int character;
            while ((character = in.read()) != -1) {
                if (character >= 'A' && character <= 'Z') {
                    character = character - 'A' + 'a';
                }
                if (character == '\n' || character == ' ' || character == '\r' || character == '\t') {
                    this.Dictionary.insert(word.toString());
                    word.setLength(0);
                }
                else {
                    word.append(character);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateImportance(String filename) {
        try (FileReader in = new FileReader(filename)) {
            StringBuilder word = new StringBuilder();
            int character;
            while ((character = in.read()) != -1) {
                if (character >= 'A' && character <= 'Z') {
                    character = character - 'A' + 'a';
                }
                if (character == '\n' || character == ' ' || character == '\r' || character == '\t') {
                    // Only word trim, validation check, search and update importance left but feeling sleepy (insert croissant emoji here)
                    word.setLength(0);
                }
                else {
                    word.append(character);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
