---
# Huffman Coding Algorithm

Huffman coding is a popular method for **lossless data compression**. Developed by David A. Huffman in 1952, it is widely used in formats like ZIP, GZIP, JPEG, and MP3.

## Key Concepts
- **Lossless Compression:** No data is lost during encoding/decoding.
- **Prefix Code:** No code is a prefix of another, allowing unique decoding.
- **Greedy Algorithm:** Always merges the two least frequent symbols first.

## How Huffman Coding Works
1. **Count Frequency:** Calculate the frequency of each symbol in the input.
2. **Build Min-Heap:** Create a priority queue (min-heap) of nodes, each representing a symbol and its frequency.
3. **Build Tree:**
	- Remove the two nodes with the lowest frequency.
	- Merge them into a new internal node (sum of their frequencies).
	- Insert the new node back into the queue.
	- Repeat until only one node remains (the root of the tree).
4. **Assign Codes:** Traverse the tree:
	- Left edge = `0`, right edge = `1`.
	- Assign codes to each symbol based on the path from root to leaf.
	- More frequent symbols get shorter codes.

## Example
Suppose we want to encode the string: `ABBCDDDD`

| Symbol | Frequency |
|--------|-----------|
|   A    |     1     |
|   B    |     2     |
|   C    |     1     |
|   D    |     4     |

After building the Huffman tree, we might get codes like:

- `D`: 0
- `B`: 10
- `A`: 110
- `C`: 111

So, the encoded string is: `1101010111000000`

## Applications
- File compression (ZIP, GZIP)
- Multimedia (JPEG, MP3)
- Data transmission

---