package UC1366149_UC1367923;

public class MemoryCounter{

    private WordGenerator generator;
    private CompressedTrie cTrie;
    private Trie trie;

    public MemoryCounter(String trainFile){
        generator = new WordGenerator(trainFile);
        cTrie = new CompressedTrie();
        trie = new Trie();
    }

    public int[] getSizes(int numOfWords, int wordLength, String filename){
        for(int i=0; i<numOfWords; i++){
            trie.insert();
        }
    }

    public int[] getSizes(int numOfWords, String filename){

    }
}