import java.util.AbstractCollection;
import java.util.Random;

/**
 * An abstract class that all the Paging Algorithms can use
 * @author Karan Bhargava
 */
public abstract class PagingAlgo {

	int totalHitCount;
	static final char[] DISK_FRAMES = {'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'};
	static final int SIMULATION_TIME = 100;

	/**
	 * A constructor to construct a paging algorithm
	 */
	PagingAlgo() {
		totalHitCount = 0;
	}

	/**
	 * A method to calculates the index of the next 
	 * page reference based on locality of reference. 
	 * @param currentReference - the current page reference
	 * @return index of the new page reference
	 */
	int nextReference(int currentReference) {
		Random random = new Random();
		int c = 0;

		// check locality of reference
		if (!(random.nextInt(10) < 7)) { //30% chance of a matching reference
			c = random.nextInt(7) + 2; 
		} else {
			c = random.nextInt(3) - 1; //70% chance of a matching reference
		}
		return ((c + currentReference) % 10 + 10) % 10;	
	}

	/**
	 * A method to get the hit count of an algorithm
	 * @return totalHitCount - the total hit count
	 */
	int getTotalHitCount() {
		return totalHitCount;
	}

	/**
	 * An abstract method to run the simulation
	 */
	abstract void runSimulation();

	/**
	 * A method to print the results of each page 
	 * @param pageIn - the current page in
	 * @param pageEvicted - the page that was taken out
	 * @param memory - the pages in memory
	 * @param referenceNumber - the reference number of the page
	 */
	void printReference(char pageIn, char pageEvicted, AbstractCollection<?> memory, int referenceNumber) {
		System.out.println("\nPage In: " + pageIn);
		System.out.println("Evicted: " + pageEvicted);
		System.out.println("Reference Number: " + referenceNumber);
		System.out.println("Memory: " + memory);


	}


}