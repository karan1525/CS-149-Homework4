import java.util.*;

/**
 * A class representing the FIFO paging algorithm
 * @author Karan Bhargava
 */
public class FIFO extends PagingAlgo {
	
	final int FRAME_SIZE = 4;
	private ArrayDeque<Character> memory = new ArrayDeque<>(FRAME_SIZE);
	
	FIFO() {
		super();
	}
	
	/**
	 * A method to run the simulation
	 */
	void runSimulation() {
		char currPage = ' ';
		char remPage = ' ';
		int currIndex = new Random().nextInt(DISK_FRAMES.length);
		
		int loopCounter = 1;
		
		while (loopCounter <= SIMULATION_TIME) {
			currPage = DISK_FRAMES[currIndex]; //gets the current page from the DISK_FRAME
			
			if(memory.contains(currPage)) {
				totalHitCount++;
			} else {
				if(memory.size() == FRAME_SIZE) {
					remPage = memory.removeFirst();
				}
				
				memory.addLast(currPage);
			}
			
		printReference(currPage, remPage, memory, currIndex); //print the stats every loop
		
		currIndex = nextReference(currIndex); //get the next reference for the loop
		remPage = ' '; //reset the page evicted for each loop
		
		loopCounter++;
		}
	}

}
