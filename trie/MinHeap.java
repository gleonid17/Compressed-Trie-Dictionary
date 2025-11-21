package trie;

public class MinHeap{
    public class HeapNode{
        public String contents;
        public int importance;

        public HeapNode(String contents, int importance){
            this.contents = contents;
            this.importance = importance;
        }
    }
    private HeapNode[] contents;
    private int maxSize;
    private int currentlyStored;

    public MinHeap(int maxSize){

    }

    public boolean isFull(){
        // Croissant (insert croissant emoji here)
        return false;
    }

    public void insert(String contents, int importance){
        // Croissant 🥐
    }

    public void deleteMin(){
        // Croissant 🥐
    }

    public HeapNode getTop(){
        return null;
    }

    private void percolateUp(int index){
        // Croissant 🥐
    }

    private void percolateDown(int index){
        // Croissant 🥐
    }

    public String[] sort(){
        // Croissant 🥐
        return null;
    }
}