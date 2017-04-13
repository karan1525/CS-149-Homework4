import java.util.Timer;

/**
 * A tester class to test the paging algorithms
 *
 * @author Karan Bhargava
 */
public class PageTester {

	final static int NUMBER_OF_SIMULATIONS = 5;

	public static void main(String[] args) {
		// FIFO
		System.out.println("-----Start FIFO-----");
		int hitsForFIFO = runAlgo(new FIFO());
		System.out.println("-----End FIFO-----");

		// LRU
		System.out.println("-----Start LRU-----");
		int hitsForLRU = runAlgo(new LRU());
		System.out.println("-----End LRU-----");

		// LFU
		System.out.println("-----Start LFU-----");
		int hitsForLFU = runAlgo(new LFU());
		System.out.println("-----End LFU-----");

		System.out.println("-----Start MFU-----");
		int hitsForMFU = runAlgo(new MFU());
		System.out.println("-----End MFU-----");

		System.out.println("-----Start Random Pick-----");
		int hitsForRandom = runAlgo(new RandomPick());
		System.out.println("-----End Random Pick-----");

		System.out.println("\n -----The Paging Algortihm Hit Rate Averages-----");
		System.out.println("FIFO: " + hitsForFIFO / NUMBER_OF_SIMULATIONS);
		System.out.println("LRU: " + hitsForLRU / NUMBER_OF_SIMULATIONS);
		System.out.println("LFU: " + hitsForLFU / NUMBER_OF_SIMULATIONS);
		System.out.println("MFU: " + hitsForMFU / NUMBER_OF_SIMULATIONS);
		System.out.println("Random Pick: " + hitsForRandom / NUMBER_OF_SIMULATIONS);
	}

	/**
	 * A helper method to run the paging algorithm
	 *
	 * @param algoToRun - the algorithm to run
	 * @return the total hit count for a paging algorithm
	 */
	static int runAlgo(PagingAlgo algoToRun) {
		int loopCounter = 0;

		while (loopCounter < NUMBER_OF_SIMULATIONS) {
			algoToRun.runSimulation();
			loopCounter++;
		}

		return algoToRun.getTotalHitCount();
	}

}
