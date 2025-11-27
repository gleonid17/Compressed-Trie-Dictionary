package trie;

public class RobinHoodHashtable {
    private Edge[] table;
    private int maxProbeLength;
    private int capacity;
    private int size;

    public RobinHoodHashtable() {
        this(3);
    }

    private RobinHoodHashtable(int capacity) {
        size = 0;
        this.capacity = capacity;
        maxProbeLength = 0;
        table = new Edge[capacity];
    }

    public void insert(Edge edge) {
        if (edge == null)
            return;

        // If adding element will cause load factor to be >= 90% rehash
        if ((size + 1) / (float) capacity >= 0.9)
            rehash();

        int index = hash(edge.getLabel().charAt(0));
        insertHelper(edge, index, 0);
        size++;
    }

    private void insertHelper(Edge edge, int index, int probeLength) {
        if (table[index] == null || !table[index].isOccupied()) {
            table[index] = edge;
            table[index].setOccupied(true);

            if (probeLength > maxProbeLength) {
                maxProbeLength = probeLength;
            }
            return;
        }

        // Check whether to swap elements
        int key = hash(table[index].getLabel().charAt(0));
        int existingProbeLength = index - key;

        if (existingProbeLength < 0)
            existingProbeLength = capacity - key + index;

        if (probeLength <= existingProbeLength) {
            // Insert the same edge at the next index
            insertHelper(edge, (index + 1) % capacity, probeLength + 1);
        } else {
            // Swap elements and insert temp at next index
            Edge temp = table[index];
            table[index] = edge;

            if (probeLength > maxProbeLength) {
                maxProbeLength = probeLength;
            }

            insertHelper(temp, (index + 1) % capacity, existingProbeLength + 1);
        }
    }

    public Edge search(char firstChar) {
        int key = hash(firstChar);
        
        for (int i = 0; i <= maxProbeLength; i++) {
            int index = (key + i) % capacity;

            if(table[index] == null || table[index].isOccupied() == false)
                return null;

            else if (table[index].getLabel().charAt(0) == firstChar)
                return table[index];
        }

        return null;
    }

    public void rehash() {
        int newSize;
        switch(size){
            case 3: newSize = 7; break;
            case 7: newSize = 11; break;
            case 11: newSize = 17; break;
            case 17: newSize = 23; break;
            case 23: newSize = 29; break;
            default: newSize = 29; break;
        }

        RobinHoodHashtable ht = new RobinHoodHashtable(newSize);

        for (int i = 0; i < this.capacity; i++) {
            if (table[i]!= null && table[i].isOccupied())
                ht.insert(this.table[i]);
        }

        copy(ht);
    }

    private void copy(RobinHoodHashtable ht) {
        this.capacity = ht.capacity;
        this.maxProbeLength = ht.maxProbeLength;
        this.size = ht.size;

        this.table = new Edge[ht.capacity];

        for (int i = 0; i < ht.capacity; i++) {
            this.table[i] = ht.table[i];
        }
    }

    private int hash(char firstChar) {
        if (firstChar >= 'A' && firstChar <= 'Z')
            return (firstChar - 'A') % capacity;
        else
            return (firstChar - 'a') % capacity;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < capacity; i++) {
            if (table[i] == null || !table[i].isOccupied())
                sb.append("_ ");
            else
                sb.append(table[i].getLabel() + ' ');
        }

        return sb.toString();
    }

    public SinglyLinkedList getAllEdges(){
        SinglyLinkedList list = new SinglyLinkedList();

        for(int i=0; i<capacity; i++){
            if(table[i] != null && table[i].isOccupied())
                list.insert(table[i]);
        }

        return list;
    }

    public static void main(String[] args) {
        System.out.println("=== ROBIN HOOD HASHING TEST (Single Characters) ===\n");

        RobinHoodHashtable ht = new RobinHoodHashtable();

        // Letters chosen so that: (letter - 'a') % 3 == 0 → collision chain
        String[] strongCollisions = {
                "a", // 0 % 3 = 0
                "d", // 3 % 3 = 0
                "g", // 6 % 3 = 0
                "j", // 9 % 3 = 0
                "m", // 12 % 3 = 0
                "p", // 15 % 3 = 0
                "s", // 18 % 3 = 0
                "v", // 21 % 3 = 0
                "y" // 24 % 3 = 0
        };

        System.out.println("Inserting characters that all hash to the same bucket...\n");

        for (String s : strongCollisions) {
            System.out.println("Insert: " + s);
            ht.insert(new Edge(s, null));
            System.out.println("Table: " + ht);
            System.out.println("Current maxProbeLength = " + ht.maxProbeLength);
            System.out.println("---------------------------------------------");
        }

        System.out.println("\n=== TEST SEARCH ===");
        for (String s : strongCollisions) {
            char c = s.charAt(0);
            Edge e = ht.search(c);
            System.out.println("Search '" + c + "' → " +
                    (e != null ? e.getLabel() : "NOT FOUND"));
        }

        System.out.println("\n=== INSERT MORE LETTERS (triggers rehash) ===");

        // now use letters that map to other buckets
        String[] moreLetters = { "b", "c", "e", "f", "h", "i" };

        for (String s : moreLetters) {
            System.out.println("Insert: " + s);
            ht.insert(new Edge(s, null));
            System.out.println("Table: " + ht);
            System.out.println("Current maxProbeLength = " + ht.maxProbeLength);
            System.out.println("---------------------------------------------");
        }

        System.out.println("\n=== FINAL TABLE ===");
        System.out.println(ht);
        System.out.println("Final maxProbeLength = " + ht.maxProbeLength);

        for (String s : strongCollisions) {
            char c = s.charAt(0);
            Edge e = ht.search(c);
            System.out.println("Search '" + c + "' → " +
                    (e != null ? e.getLabel() : "NOT FOUND"));
        }
    }

}