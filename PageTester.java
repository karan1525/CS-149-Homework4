/**
 * A tester class to test the paging algorithms
 * @author Karan Bhargava
 */
public class PageTester {
	
	final static int NUMBER_OF_SIMULATIONS = 5;
	
	public static void main (String[] args) {
		//FIFO
		System.out.println("-----Start FIFO-----");
        int hitsForFIFO = runAlgo(new FIFO());
        System.out.println("-----End FIFO-----");
        
        System.out.println("\n -----The Paging Algortihm Hit Rate Averages-----");
        System.out.println("FIFO: " + hitsForFIFO);
        
        //LRU
		System.out.println("-----Start LRU-----");
        int hitsForLRU = runAlgo(new LRU());
        System.out.println("-----End LRU-----");
        
        System.out.println("\n -----The Paging Algortihm Hit Rate Averages-----");
        System.out.println("LRU: " + hitsForLRU);
        
        //LFU
    	System.out.println("-----Start LFU-----");
        int hitsForLFU = runAlgo(new LFU());
        System.out.println("-----End LFU-----");
        
        System.out.println("\n -----The Paging Algortihm Hit Rate Averages-----");
        System.out.println("LFU: " + hitsForLFU);
	}
	
	/**
	 * A helper method to run the paging algorithm
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
