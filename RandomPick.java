import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class RandomPick extends PagingAlgo {
	final int FRAME_SIZE = 4;

	private ArrayDeque<Character> memory = new ArrayDeque<>(FRAME_SIZE);
	private Character[] temp = new Character[FRAME_SIZE];

	RandomPick() {
		super();
	}

	/**
	 * A method to run the simulation
	 */
	void runSimulation() {
		char currPage = ' ';
		char remPage = ' ';
		// Getting a index between DISK FRAMES and length
		int currIndex = new Random().nextInt(DISK_FRAMES.length);
		int randomEvict = new Random().nextInt(FRAME_SIZE);

		int loopCounter = 1;

		while (loopCounter <= SIMULATION_TIME) {
			currPage = DISK_FRAMES[currIndex]; // gets the current page from the
												// DISK_FRAME

			if (!(memory.contains(currPage))) { // there is a page fault
				if (memory.size() == 4) {
					remPage = replacePage(randomEvict, currPage);
				} else {
					memory.addFirst(currPage);
				}
				totalHitCount++;
			}

			printReference(currPage, remPage, memory, currIndex); // print the
																	// stats
																	// every
																	// loop

			currIndex = nextReference(currIndex); // get the next reference for
													// the loop
			remPage = ' '; // reset the page evicted for each loop

			loopCounter++;
		}
	}

	private char replacePage(int currIndex, char currPage) {
		// Dequeue the page to modify the page
		for (int i = 0; i < temp.length; i++) {
			temp[i] = memory.pop();
		}
		char removePage = temp[currIndex];
		temp[currIndex] = currPage;
		for (int i = 0; i < temp.length; i++) {
			memory.addFirst(temp[i]);
		}
		return removePage;
	}
}
