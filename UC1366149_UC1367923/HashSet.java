package UC1366149_UC1367923;

public class HashSet {
    
    private String[] table;
    private int size;
    private int capacity;              
    private final double loadFactor = 0.7;

    public HashSet() {
        capacity = 16;
        table = new String[capacity];
        size = 0;
    }

    public HashSet(int initialCapacity) {
        capacity = nextPowerOfTwo(initialCapacity);
        table = new String[capacity];
        size = 0;
    }

    private int nextPowerOfTwo(int n) {
        int p = 1;
        while (p < n) p <<= 1;
        return p;
    }

    private int hash(String s) {
        long h = 1;
        long mod = 0x7fffffffL; // keep in 32-bit positive range

        for (int i = 0; i < s.length(); i++) {
            h = ((h << 1) * s.charAt(i)) & mod; 
        }

        return (int)h;
    }

    public int size() {
        return size;
    }

    public boolean contains(String s) {
        int index = hash(s) & (capacity - 1);

        while (table[index] != null) {
            if (table[index].equals(s)) return true;
            index = (index + 1) & (capacity - 1);
        }
        return false;
    }

    public boolean add(String s) {
        if (contains(s)) return false;

        if ((double)(size + 1) / capacity > loadFactor) {
            rehash();
        }

        int index = hash(s) & (capacity - 1);

        while (table[index] != null) {
            index = (index + 1) & (capacity - 1);
        }

        table[index] = s;
        size++;
        return true;
    }

    private void rehash() {
        String[] oldTable = table;

        capacity *= 2;
        table = new String[capacity];
        size = 0;

        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                add(oldTable[i]);
            }
        }
    }
}
