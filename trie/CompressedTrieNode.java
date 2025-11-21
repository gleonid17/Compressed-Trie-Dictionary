package trie;

public class CompressedTrieNode {
	private RobinHoodHashtable hashtable;
	public boolean isEndOfWord;
	
	public CompressedTrieNode() {
		this.edgeList = new SinglyLinkedList();
		this.isEndOfWord = false;
	}
	
	public void insertEdge(Edge edge) {
		this.edgeList.insert(edge);
	}
	
	public Edge getEdgeByFirstChar(char c) {
		return this.edgeList.getEdge(c);
	}
	
	public SinglyLinkedList getAllEdges() {
		return this.hashtable.getAllEdges();
	}
}
