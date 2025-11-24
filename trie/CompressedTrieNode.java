package trie;

public class CompressedTrieNode {
	private RobinHoodHashtable hashtable;
	private boolean isEndOfWord;
	private int importance;
	
	public CompressedTrieNode() {
		this.hashtable = new RobinHoodHashtable();
		this.isEndOfWord = false;
		this.importance = 0;
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

	public boolean isEndOfWord(){
		return isEndOfWord;
	}

	public void setIsEndOfWord(boolean isEndOfWord){
		this.isEndOfWord = isEndOfWord;
	}

	public int getImportance(){
		return importance;
	}

	public void setImportance(int importance){
		this.importance = importance;
	}
}

//Just replased "edgelist" with "hashtable" and "getEdgeByFirstChar" with "search"