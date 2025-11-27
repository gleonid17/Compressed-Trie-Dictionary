package trie;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Wrong number of arguments given!\nCorrenct execution command: java Menu wordFile.txt textFile.txt");
            System.exit(1);
        }

        String wordFile = args[0];
        String importanceFile = args[1];
        if (wordFile == null || importanceFile == null) {
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

        int option = 0;

        Scanner in = new Scanner(System.in);
        do {
            System.out.println("1.\tFind k most used words after prefix");
            System.out.println("2.\tAverage Frequency");
            System.out.println("3.\tFind most probable character");
            System.out.println("4.\tExit");
            System.out.println("Enter option (1 - 4): ");

            // Ensure correct input is given
            boolean correctInputRead = false;
            while (!correctInputRead) {
                try {
                    option = in.nextInt();
                    while (option < 1 || option > 4) {
                        System.out.println("Wrong input, give option again (1 - 4): ");
                        option = in.nextInt();
                    }
                    correctInputRead = true;
                } catch (InputMismatchException e) {
                    // Clean buffer and print error message
                    in.next();
                    System.err.println("Invalid input given. Please enter an integer: ");
                    correctInputRead = false;
                }
            }

            String prefix;
            switch (option) {
                case 1:
                    // Read number of words (k)
                    System.out.println("Give the number of words you need: ");
                    correctInputRead = false;
                    int k = 0;
                    while (!correctInputRead) {
                        try {
                            k = in.nextInt();
                            while (k <= 0) {
                                System.out.println("Wrong input, give number greater than 0: ");
                                k = in.nextInt();
                            }
                            correctInputRead = true;
                        } catch (InputMismatchException e) {
                            // Clean buffer and print error message
                            in.next();
                            System.err.println("Invalid input given. Please enter an integer: ");
                            correctInputRead = false;
                        }
                    }

                    // Read prefix
                    prefix = readPrefixSafely(in);
                    String[] results = analyzer.topKFrequentWordsWithPrefix(prefix, k);
                    System.out.println("The top " + k + " words with prefix " + prefix + " are:");
                    for (int j = 0; j < results.length; j++) {
                        System.out.println(results[j]);
                    }   
                    break;

                case 2:
                    prefix = readPrefixSafely(in);
                    float average = analyzer.getAverageFrequencyOfPrefix(prefix);
                    System.out.println("The average frequency of words with prefix " + prefix + " is: " + average);

                    System.out.println("Give the prefix: ");
                    correctInputRead = false;
                    break;

                case 3:

                    prefix = readPrefixSafely(in);
                    char character;
                    try {
                        character = analyzer.predictNextLetter(prefix);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("The most probable character after prefix " + prefix + " is: " + character);
                    break;

                case 4:
                    break;
            }

            System.out.println();
        }while(option!=4);

    in.close();
    }

    private static String readPrefixSafely(Scanner inputStream) {
        System.out.println("Give the prefix: ");
        String prefix;

        do{
            prefix = Dictionary.cleanWord(inputStream.next());
            if(prefix.length() > 0 && !Dictionary.hasSpecial(prefix))
                return prefix;
            else{
                System.out.println("Invalid prefix! Enter prefix again: ");              
            }
        }while(true);
    }
}