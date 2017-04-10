import java.util.*;

/**
 * A class representing the FirstFit algorithm 
 * @author Karan Bhargava
 */
public class FirstFit extends AnyFit {

	/**
	 * A constructor to construct the algorithm
	 * @param p - the processes
	 */
	FirstFit(LinkedList<SwapProcesses> p) {
		super(p);
	}

	/**
	 * A method to find an empty block based on FirstFit requirements
	 * @param processSize - the size of the process
	 * @return endResult - an array with FirstFit empty blocks and size in memory
	 */
	public int[] findEmptyBlocks(int processSize) {
		int empty = 0;
		int currentSize = 0;
		int temporaryIndex = -1;
		int[] endResult = new int[2];
		boolean first = true;

		int emptyMemory[] = new int[MEM_SIZE];

		for (int i = 0; i < MEM_SIZE; i++) {
			if (totMemory[i] == '.') {
				currentSize++;
				if (first) {
					first = false;
					temporaryIndex = i;
				}
			} else {
				if (currentSize > 0) {
					emptyMemory[temporaryIndex] = currentSize;
				}
				currentSize = 0;
				first = true;
			}
		}

		if (currentSize > 0) {
			emptyMemory[temporaryIndex] = currentSize;
		}
		currentSize = 0;
		first = true;

		for (int i = 0; i < emptyMemory.length; i++) {
			if (emptyMemory[i] > 0) {
				empty++;
			}
		}

		int bSize[] = new int[empty];
		int bIndex[] = new int[empty];
		int centerPt = 0;
		for (int i = 0; i < emptyMemory.length; i++) {
			if (emptyMemory[i] > 0) {
				bSize[centerPt] = emptyMemory[i];
				bIndex[centerPt++] = i;
			}
		}
		emptyMemory = null;

		for (int i = 0; i < bSize.length; i++) {
			if (processSize <= bSize[i]) {
				endResult[0] = bSize[i];
				endResult[1] = bIndex[i];
				break;
			}
		}

		return endResult;
	}
}