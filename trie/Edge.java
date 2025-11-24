package trie;

public class Edge {
	private String label;
	private CompressedTrieNode child;
	private boolean occupied;
	
	public Edge (String label, CompressedTrieNode child) {
		this.label = label;
		this.child = child;
		this.occupied = false;
	}

	public String getLabel(){
		return new String(this.label);
	}

	public void setLabel(String label){
		this.label = label;
	}

	public CompressedTrieNode getChild(){
		return this.child;
	}

	public void setChild(CompressedTrieNode child){
		this.child = child;
	}

	public void setOccupied(boolean occupied){
		this.occupied = occupied;
	}

	public boolean isOccupied(){
		return occupied;
	}
}
