package UC1366149_UC1367923;

public class Trie {

	private final static int alphabetSize = 26;
	
	
	private class TrieNode{
		private TrieNode children[];
		private boolean isEndOfWord;
		
		public TrieNode() {
			children = new TrieNode[alphabetSize];
			isEndOfWord = false;
		}

		public int getSize(){
			// 8 bytes for pointer to table and 1 byte for boolean
			int bytes = 8 + 1;

			// size of table contents
			if(children != null)
				for(int i=0; i< children.length; i++){
					bytes += 8; // pointer to TrieNode
					
					// If not null add the size of the child node
					if(children[i] != null)
						bytes += children[i].getSize();
				}

			return bytes;
		}
	}
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	
	public boolean search(String word) {
		int i=0;
		TrieNode current = root;

		while(current != null && i < word.length()) {
			current = current.children[word.charAt(i) - 'a'];
			i++;
		}
		
		if(i == word.length() && current.isEndOfWord)
			return true;
		
		return false;		
	}
	
	public void insert(String word) {
		TrieNode current = root;
		
		for(int i=0; i < word.length(); i++) {
			if(current.children[word.charAt(i) - 'a'] == null) {
				TrieNode newNode = new TrieNode();
				current.children[word.charAt(i) - 'a'] = newNode;
			}
			
			current = current.children[word.charAt(i) - 'a'];
		}
		
		current.isEndOfWord = true;
	}
	
	public void display() {
		displayHelper(root, "");
	}
	
	private static void displayHelper(TrieNode node, String word) {
		if(node == null)
			return;
		if(node.isEndOfWord)
			System.out.println(word);
		
		for(int i=0; i< alphabetSize; i++) {
			displayHelper(node.children[i], word.concat((char)(i + 'a') + ""));
		}
	}
	
	public boolean delete(String key) {
		return deleteHelper(root, key, 0);
	}
	
	private boolean deleteHelper(TrieNode node, String key, int depth) {
		if(node == null)
			return false;
		
		if(depth == key.length()) {
			if(!node.isEndOfWord)
				return false;
			
			node.isEndOfWord = false;
			
			for(int i=0; i< alphabetSize; i++) {
				if(node.children[i] != null) {
					return false;
				}
			}
			
			return true;
		}
		
		boolean shouldDeleteChild = deleteHelper(node.children[key.charAt(depth) - 'a'], key, depth + 1);
		
		if(shouldDeleteChild) {
			node.children[key.charAt(depth) - 'a'] = null;
			
			if(node.isEndOfWord)
				return false;
			
			
			for(int i=0; i< alphabetSize; i++) {
				if(node.children[i] != null) {
					return false;
				}
			}
			return true;
		}
		
		return false;
		
	}

	public int getSize(){
		// 4 bytes for alphabetsize and 8 bytes for root pointer
		int bytes = 4 + 8;
		bytes += root.getSize();
		return bytes;
	}
	
	public static void main(String args[]) {
		
		Trie t = new Trie();
		
		t.insert("cat");
		t.insert("dog");
		
		System.out.println(t.search("dog"));
		System.out.println(t.search("cat"));
		System.out.println(t.search("do"));
		
		t.insert("do");
		t.insert("rat");
		
		System.out.println(t.search("do"));
		System.out.println(t.search("rat"));
		
		System.out.println();
		t.display();
		
		System.out.println();		System.out.println();
		t.delete("do");
		t.display();
		
	}
	
}
