# Compressed Trie Autocomplete System

A Java implementation of a compressed trie (radix tree) for efficient dictionary storage, prefix-based search, autocomplete suggestions, and frequency analysis.

Built as part of a university project investigating the memory and performance trade-offs between standard and compressed trie structures. Includes custom implementations of a Robin Hood hash table, min-heap, singly linked list, and hash set — no external data structure libraries used.

## Features

-   **Top-K Autocomplete** — retrieve the K most frequent words matching a given prefix
-   **Average Frequency Analysis** — calculate average corpus frequency across all words sharing a prefix
-   **Next Character Prediction** — predict the most probable character following a prefix
-   **Memory Comparison** — benchmark memory usage between standard and compressed trie structures

## How It Works

The system loads two input files:

-   **Dictionary** (`words.txt`) — the set of valid words inserted into the trie
-   **Corpus** (`corpus.txt`) — a text file used to compute word frequencies

Words are inserted into the compressed trie, then the corpus is scanned to assign frequency values. Autocomplete queries collect all words matching a prefix and return the top-K results ranked by frequency, maintained efficiently using a min-heap.

A compressed trie merges chains of single-child nodes into single edges, reducing node count and memory usage significantly compared to a standard character-per-node trie — an advantage that grows with dictionary size.


## Data Structures

| Class | Role |
|---|---|
| `CompressedTrie` | Primary trie structure — insert, search, prefix traversal |
| `CompressedTrieNode` | Trie node storing edges and metadata |
| `Edge` | Compressed edge label and child reference |
| `RobinHoodHashtable` | Edge storage with Robin Hood open addressing |
| `MinHeap` | Maintains top-K results during prefix queries |
| `SinglyLinkedList` | Internal utility list |
| `Trie` | Standard trie, used for memory comparison only |
| `HashSet` | Deduplication for the word generator |

## Project Structure

```
├── src/
│   ├── CompressedTrie.java
│   ├── CompressedTrieNode.java
│   ├── Dictionary.java
│   ├── Edge.java
│   ├── HashSet.java
│   ├── MemoryCounter.java
│   ├── Menu.java
│   ├── MinHeap.java
│   ├── PrefixAnalyzer.java
│   ├── RobinHoodHashtable.java
│   ├── SinglyLinkedList.java
│   ├── Trie.java
│   └── WordGenerator.java
│
├── data/
│   ├── words.txt
│   └── corpus.txt
│
├── README.md
├── LICENSE
└── .gitignore
```

## Requirements

Java 8 or later. Verify with:

```bash
java -version
javac -version

```

### Installation

**Windows** — Download the JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/) and run the installer.

**macOS**

```bash
brew install openjdk
```

**Ubuntu / Debian**

```bash
sudo apt update && sudo apt install default-jdk
```

**Fedora**

```bash
sudo dnf install java-latest-openjdk-devel
```

**Arch Linux**

```bash
sudo pacman -S jdk-openjdk
```

## Build & Run

```bash
# Compile
javac src/*.java

# Run
java -cp src Menu data/words.txt data/corpus.txt

```

## Menu

```
1. Find k most used words after prefix
2. Average Frequency
3. Find most probable character
4. Exit
```

## Notes

Generated datasets and experiment output files from the original memory evaluation have been omitted to keep the repository lightweight. Provide your own `words.txt` and `corpus.txt` to experiment with different dictionary sizes.

## Authors

George Leonidou  
Nikolas Pomiloridis

## License

MIT — see [LICENSE](https://claude.ai/chat/LICENSE) for details.