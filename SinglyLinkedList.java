package trie;

public class SinglyLinkedList {
	private static class Node {
		Edge edge; 
		Node next;
		
		Node(Edge value){
			this.edge = value;
			this.next = null;
		}
	}
	protected Node head;
	
	public SinglyLinkedList () { 
		this.head = null;
	}
	
	public void insert(Edge edge) { 
		if (this.head == null) {
			this.head = new Node(edge);
			return;
		}
		Node newHead = new Node(edge);
		newHead.next = this.head;
		this.head = newHead;
	}
	
	public Edge getEdge (char c) {
	    Node tester = this.head;
	    while (tester != null) {
	    	if(tester.edge.label.charAt(0) == c)
	    		return tester.edge;
	    	tester = tester.next;
	    }
	    return null;
	}
	
	public String toString() {
		Node current = this.head;
		StringBuilder str = new StringBuilder();
		while (current != null) {
			str.append(current.edge + " -> ");
			current = current.next;
		}
		str.append("null");
		return str.toString();
	}
}
