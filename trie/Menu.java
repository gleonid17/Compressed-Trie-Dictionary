package trie;

import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {

        if(args.length != 2){
            System.err.println("Wrong number of arguments given!\nCorrenct execution command: "); // Enter execution command
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
            switch(option){
                case 1:
                    System.out.println("Give the number of words you need");
                    int k = in.nextInt();
                    while(k <= 0){
                        System.out.println("Wrong input, give number greater than 0");
                        k = in.nextInt();
                    }
                    System.out.println("Give the prefix");
                    String prefix = in.next();
                    prefix = prefix.toLowerCase();
                    for (int i = 0; i < prefix.length(); i++){
                        if (! (prefix.charAt(i) >= 'a' && prefix.charAt(i) <= 'z')){
                            //needs teleiwma
                        }
                    }

            }

        }while(option!=4);

        in.close();
    }
}