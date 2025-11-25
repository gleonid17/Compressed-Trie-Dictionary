package trie;

public class PrefixAnalyzer {
    private CompressedTrie dictionary;

    public PrefixAnalyzer(CompressedTrie dictionary) {
        this.dictionary = dictionary;
    }

    public String[] topKFrequentWordsWithPrefix(String prefix, int k) {
        MinHeap heap = new MinHeap(k);

        CompressedTrie.PrefixResult result = dictionary.findPrefixNode(prefix);        if (result == null)
        if (result == null)    
            return new String[0];
        fillHeap(heap, result.node, prefix + result.suffix);
        return heap.heapSort();
    }

    private void fillHeap(MinHeap heap, CompressedTrieNode node, String word) {
        // Base Case: node is null
        if (node == null) {
            return;
        }

        // If node contains a word decide whether or not to add it in the heap
        if (node.isEndOfWord()) {
            if (!heap.isFull())
                heap.insert(word, node.getImportance());
            else if (node.getImportance() > heap.getTop().importance || (node.getImportance() == heap.getTop().importance && word.compareTo(heap.getTop().contents) < 0)) {
                heap.deleteMin();
                heap.insert(word, node.getImportance());
            }
        }

        // Recursively call for all child nodes
        SinglyLinkedList.Node current = node.getAllEdges().head;
        while (current != null) {
            fillHeap(heap, current.edge.getChild(), word + current.edge.getLabel());
            current = current.next;
        }
    }

    public float getAverageFrequencyOfPrefix(String prefix) {
        CompressedTrie.PrefixResult searchResult = dictionary.findPrefixNode(prefix);
        if (searchResult == null)
            return 0;

        int[] sumAndCount = getSumAndCount(searchResult.node);
        int sum = sumAndCount[0], count = sumAndCount[1];

        if (count == 0)
            return 0;

        return (float) sum / count;
    }

    private int[] getSumAndCount(CompressedTrieNode node) {
        // Base case
        if (node == null)
            return new int[] { 0, 0 };

        int sum = 0, count = 0;
        if (node.isEndOfWord()) {
            sum = node.getImportance();
            count = 1;
        }

        SinglyLinkedList.Node current = node.getAllEdges().head;
        while (current != null) {
            int[] result = getSumAndCount(current.edge.getChild());
            sum += result[0];
            count += result[1];
            current = current.next;
        }

        return new int[]{sum, count};
    }

    // private int getSumOfImportance(CompressedTrieNode node) {
    //     // Base case
    //     if (node == null)
    //         return 0;

    //     int sum = 0;
    //     if (node.isEndOfWord)
    //         sum = node.importance;

    //     SinglyLinkedList.Node current = node.getAllEdges().head;
    //     while (current != null) {
    //         sum += getSumOfImportance(current.edge.child);
    //         current = current.next;
    //     }

    //     return sum;
    // }

    // private int countWords(CompressedTrieNode node) {
    //     // Base case
    //     if (node == null)
    //         return 0;

    //     int count = 0;
    //     if (node.isEndOfWord)
    //         count = 1;

    //     SinglyLinkedList.Node current = node.getAllEdges().head;
    //     while (current != null) {
    //         count += countWords(current.edge.child);
    //         current = current.next;
    //     }

    //     return count;
    // }

    public char predictNextLetter(String prefix) throws Exception {
        CompressedTrie.PrefixResult searchResult = dictionary.findPrefixNode(prefix);
        if (searchResult == null)
            throw new Exception("Cannot predict next letter!");

        // If all words with this prefix share the next letter return it
        if (searchResult.suffix.length() > 0)
            return searchResult.suffix.charAt(0);

        // get average frequency of all subtrees
        SinglyLinkedList.Node current = searchResult.node.getAllEdges().head;
        if (current == null) // if node is leaf node
            throw new Exception("No words start with given prefix!");

        float frequency, maxFrequency = Float.NEGATIVE_INFINITY;
        char mostFrequentChar = '\0';

        while (current != null) {
            frequency = getAverageFrequencyOfPrefix(prefix + current.edge.getLabel().charAt(0));
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mostFrequentChar = current.edge.getLabel().charAt(0);
            }
            current = current.next;
        }

        if(mostFrequentChar == '\0')
            throw new Exception("Cannot predict next letter!");
        
        return mostFrequentChar;
    }
}
