package trie;

import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {

        if(args.length != 2){
            System.err.println("Wrong number of arguments given!\nCorrenct execution command: java Menu wordFile.txt textFile.txt"); // Enter execution command
            System.exit(1);
        }

        String wordFile = args[0];
        String importanceFile = args[1];
        if(wordFile == null || importanceFile == null){
            System.err.println("Parameter entered is null!");
            System.exit(1);
        }

        System.out.println("Loading Dictionary...");
        Dictionary dictionary = new Dictionary();
        dictionary.loadDictionary(wordFile);

        System.out.println("Updating Word Importance...");
        dictionary.updateImportance(importanceFile);

        CompressedTrie trie = dictionary.getTrie();
        PrefixAnalyzer analyzer = new PrefixAnalyzer(trie);

        int option;

        Scanner in = new Scanner(System.in);
        do {
            System.out.println("1.\tFind k most used words after prefix");
            System.out.println("2.\tAverage Frequency");
            System.out.println("3.\tFind most probable character");
            System.out.println("4.\tExit");
            System.out.println("Give me the option you want to run (1 - 3), give 4 to stop");
            option = in.nextInt();
            while (option < 1 || option > 4){
                System.out.println("Wrong input, give option again");
                option = in.nextInt();
            }
            String prefix;
            int first = 0, last = 0;
            boolean foundFirst = false, foundLast = false;
            switch(option){
                case 1:
                    System.out.println("Give the number of words you need");
                    int k = in.nextInt();
                    while(k <= 0){
                        System.out.println("Wrong input, give number greater than 0");
                        k = in.nextInt();
                    }
                    System.out.println("Give the prefix");
                    prefix = in.next();
                    prefix = Dictionary.cleanWord(prefix);
                    if (prefix.length() > 0) {
                        String[] results = analyzer.topKFrequentWordsWithPrefix(prefix, k);
                        System.out.println("The top " + k + " words with prefix " + prefix + " are:");
                        for (int j = 0; j < results.length; j++) {
                            System.out.println(results[j]);
                        }
                    }
                    else {
                        System.out.println("No valid characters in prefix!");
                    }
                    break;
                case 2:
                    System.out.println("Give the prefix");
                    prefix = Dictionary.cleanWord(in.next());
                    if (prefix.length() > 0) {
                        float average = analyzer.getAverageFrequencyOfPrefix(prefix);
                        System.out.println("The average frequency of words with prefix " + prefix + " is: " + average);
                    }
                    else {
                        System.out.println("No valid characters in prefix!");
                    }
                    break;
                case 3:
                    System.out.println("Give the prefix");
                    prefix = Dictionary.cleanWord(in.next());
                    if (prefix.length() > 0) {
                        char character;
                        try{
                            character = analyzer.predictNextLetter(prefix);
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                            break;
                        }
                        System.out.println("The most probable character after prefix " + prefix + " is: " + character);
                    }
                    else {
                        System.out.println("No valid characters in prefix!");
                    }
                    break;
                case 4:
                    break;
            }
            System.out.println();
        }while(option!=4);

        in.close();
    }
}