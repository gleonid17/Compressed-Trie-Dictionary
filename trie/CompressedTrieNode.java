package trie;

public class CompressedTrieNode {
	private RobinHoodHashtable hashtable;
	public boolean isEndOfWord;
	
	public CompressedTrieNode() {
		this.hashtable = new RobinHoodHashtable();
		this.isEndOfWord = false;
	}
	
	public void insertEdge(Edge edge) {
		this.hashtable.insert(edge);
	}
	
	public Edge getEdgeByFirstChar(char c) {
		return this.hashtable.search(c);
	}
	
	public SinglyLinkedList getAllEdges() {
		return this.hashtable.getAllEdges();
	}
}

//Just replased "edgelist" with "hashtable" and "getEdgeByFirstChar" with "search"