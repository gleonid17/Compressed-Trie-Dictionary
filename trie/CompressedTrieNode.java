package trie;

public class CompressedTrieNode {
	private SinglyLinkedList edgeList;
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
}
