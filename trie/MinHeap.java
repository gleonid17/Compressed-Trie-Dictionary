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
        this.maxSize = maxSize;
        this.currentlyStored = 0;
        this.contents = new HeapNode[maxSize + 1];
    }

    public boolean isFull(){
        return this.currentlyStored >= this.maxSize;
    }

    public void insert(String contents, int importance){
        if (isFull()) 
            throw new IllegalStateException("Heap is full");

        HeapNode newNode = new HeapNode(contents, importance);
        this.currentlyStored++;
        this.contents[currentlyStored] = newNode;
        percolateUp(this.currentlyStored);
    }

    
    public void insert(HeapNode node){
        if (isFull()) 
            throw new IllegalStateException("Heap is full");

        this.currentlyStored++;
        this.contents[currentlyStored] = node;
        percolateUp(this.currentlyStored);
    }
    

    private boolean isEmpty(){
        return this.currentlyStored == 0;
    }   

    public void deleteMin(){
        if (isEmpty()) {
            return;
        }
        contents[1] = contents[currentlyStored]; // switch last and first node
        currentlyStored--;
        percolateDown(1);
    }

    public HeapNode getTop(){
        return this.contents[1];
    }

    private void percolateUp(int index){
        HeapNode temp = this.contents[index];
        while (index > 1 && isHigherPriority(temp, contents[index/2])) {
            this.contents[index] = this.contents[index / 2];
            index = index / 2; 
        }
        this.contents[index] = temp;
    }

    private void percolateDown(int index){
        HeapNode k = this.contents[index];
        int j;
        while (2 * index <= this.currentlyStored) {
            j = 2 * index;
            if (j + 1 <= this.currentlyStored && isHigherPriority(contents[j+1], contents[j])) {
                j++;
            }
            if (isHigherPriority(this.contents[j], k)) {
                this.contents[index] = this.contents[j];
                index = j;
            } else {
                break;
            }
        }
        this.contents[index] = k;
    }

    private boolean isHigherPriority(HeapNode node1, HeapNode node2) {
    if (node1.importance < node2.importance) {
        return true;
    }
    if (node1.importance == node2.importance && 
        node1.contents.compareTo(node2.contents) > 0) {
        return true;
    }
    return false;
}

    /*private void buildHeap(){
        for (int i = this.currentlyStored / 2; i >= 1; i--) {
            percolateDown(i);
        }
    }
    */
    //Leo Sort
    //The following method keeps the curent heap as is
    /*public String[] sort(){ 
        HeapNode[] tempContents = new HeapNode[this.currentlyStored + 1];
        for (int i = 1; i <= this.currentlyStored; i++)
            tempContents[i] = this.contents[i];
        int tempSize = this.currentlyStored;
        String[] sortedArray = new String[tempSize];
        for (int i = 0; i < tempSize; i++){
            sortedArray[i] = getTop().contents;
            deleteMin();
        }
        this.contents = tempContents;
        this.currentlyStored = tempSize;
        return sortedArray;
    }
    */

    public String[] heapSort(){
        String[] sortedArray = new String[this.currentlyStored];
            for (int i = this.currentlyStored; i >= 1; i--){
            sortedArray[i-1] = getTop().contents;
            deleteMin();
        }
        return sortedArray;
    }

    //The folloing method empties the current heap
    /*public String[] sort(){
        String[] sortedArray = new String[this.currentlyStored];
        for (int i = 0; i < sortedArray.length; i++){
            sortedArray[i] = getTop().contents;
            deleteMin();
            //sortedArray[i] = deleteMin().contents; // if deleteMin() returns HeapNode
        }
        return sortedArray;
    }*/
}