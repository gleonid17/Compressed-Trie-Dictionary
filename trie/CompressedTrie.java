package trie;

public class CompressedTrie {
	CompressedTrieNode root;

	public CompressedTrie() {
		this.root = new CompressedTrieNode();
	}

	public void insert(String word) {
		if (this.root == null) {
			this.root = new CompressedTrieNode();
		}
		insertHelper(this.root, word);
	}

	private void insertHelper(CompressedTrieNode node, String word) {
		// If word is
		if (word.length() == 0) {
			node.setIsEndOfWord(true);
			return;
		}
		Edge common = node.getEdgeByFirstChar(word.charAt(0));
		// If there's not an edge with word.charAt[0]
		if (common == null) {
			CompressedTrieNode newNode = new CompressedTrieNode();
			newNode.setIsEndOfWord(true);
			node.insertEdge(new Edge(word, newNode));
			return;
		}
		// Find prefix
		int i = 0;
		String prefix;
		while (i < word.length() && i < common.getLabel().length() && word.charAt(i) == common.getLabel().charAt(i)) {
			i++;
		}
		prefix = word.substring(0, i);
		String edgeRest = common.getLabel().substring(i);
		String wordRest = word.substring(i);
		if (edgeRest.length() == 0) {
			insertHelper(common.getChild(), wordRest);
			return;
		}
		CompressedTrieNode splitNode = new CompressedTrieNode();
		splitNode.insertEdge(new Edge(edgeRest, common.getChild()));
		common.setChild(splitNode);
		common.setLabel(prefix);
		//insertHelper(splitNode, wordRest);
        if (wordRest.length() == 0)
            splitNode.setIsEndOfWord(true);          // The split node represents a complete word
        else
            insertHelper(splitNode, wordRest);
	}

	public boolean search(String word) {
		if(this.root == null)
			return false;
		return searchHelper(this.root, word);
	}
	
	private boolean searchHelper(CompressedTrieNode node, String word) {
		if(word.length() == 0)
			return node.isEndOfWord();
		Edge common = node.getEdgeByFirstChar(word.charAt(0));
		// If there's not an edge with word.charAt[0]
		if (common == null)
			return false;
		// Find prefix
		if(!word.startsWith(common.getLabel()))
			return false;
		String wordRest = word.substring(common.getLabel().length());
		return searchHelper(common.getChild(), wordRest);
	}	
	
   public static void main(String[] args) {
        CompressedTrie trie = new CompressedTrie();
        // Insert words
        trie.insert("car");
        trie.insert("ca");
        trie.insert("cat");
        trie.insert("dog");
        trie.insert("door");
        trie.insert("do");
        // Words to test
        String[] tests = {
                "car",
                "ca",
                "cat",
                "dog",
                "do",
                "door",
                "cart",
                "c",
                "doll",
                "doe"
        };
        // Run searches
        for (String word : tests) {
            boolean found = trie.search(word);
            System.out.println("search(\"" + word + "\")\t->\t" + found);
        }
    }
}
