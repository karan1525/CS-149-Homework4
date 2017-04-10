import java.util.*;

/**
 * An abstract class representing all the fit algorithms
 * @author Karan Bhargava
 */
public abstract class AnyFit {

	//instance variables for all the fit algorithms
	private ArrayList<SwapProcesses> currentProc;
	private LinkedList<SwapProcesses> allProc;
	boolean first = true, second;
	private final int MINIMUM_SIZE = 5;
	protected final int MEM_SIZE = 100;
	protected final char[] totMemory = new char[MEM_SIZE];
	protected int free, swap, stop = 0;
	private final int SECONDS = 60;

	/**
	 * An abstract method to implement the swapping algorithms
	 * @param procSize - the process size for which we need to find empty blocks
	 * @return an array representing the size and index of empty blocks
	 */
	abstract int[] findEmptyBlocks(int procSize);

	/**
	 * A constructor to construct the fit algorithm
	 * @param processes - the processes we're working with
	 */
	AnyFit(LinkedList<SwapProcesses> processes) {
		allProc = processes;
		free = MEM_SIZE;
		swap = 0;
		currentProc = new ArrayList<SwapProcesses>();

		for(int i = 0; i < MEM_SIZE; i++) {
			totMemory[i] =  '.';
		}	
	}

	/**
	 * A method to swap the process out of memory
	 * @param proc - the process we need to swap
	 */
	void swap(SwapProcesses proc) {
		int sizeOfProc = proc.getSizeOfProc();
		char nameOfProc = proc.getNameOfProc();
		int i = 0;

		while (i < MEM_SIZE) {
			if (totMemory[i] == nameOfProc) {
				break;
			}
			i++;
		}
		sizeOfProc += i;
		while ( i < sizeOfProc && i < MEM_SIZE) {
			totMemory[i] = '.';
			i++;
		}
	}

	/**
	 * A method to return the amount swapped in processes
	 * @return swap - the swapped in processes
	 */
	int SwapIn() {
		return swap;
	}

	/**
	 * A method to run the simulation for the swapping processes
	 */
	void run() {
		for (int i = 0; i < SECONDS; i++) {
			int j = 0; 
			int temporaryDur = currentProc.size();

			while (j < temporaryDur) {
				temporaryDur = currentProc.get(j).getDurationOfProc()- 1;
				currentProc.get(j).setDurationOfProc(temporaryDur);

				if (temporaryDur != 0) {
					j++;
				} else {
					SwapProcesses proc = currentProc.remove(j);
					free += proc.getSizeOfProc();
					System.out.println(String.format("time %2d: Swapped Out Process %s", i,proc.getNameOfProc()));
					swap(proc);
					print();

				}
			}
			j = 0;
			while (free >= MINIMUM_SIZE && j < allProc.size()) {
				int[] blockToFit = findEmptyBlocks(allProc.get(j).getSizeOfProc());
				int counter = blockToFit[1];

				if (blockToFit[0] >= allProc.get(j).getSizeOfProc()) {
					SwapProcesses p = allProc.remove(j);

					for (int x = 0; x < p.getSizeOfProc(); x++) {
						totMemory[counter] = p.getNameOfProc();
						counter++;

						free -= p.getSizeOfProc();
						currentProc.add(p);
						swap++;

						System.out.println(String.format("time %2d: Swapped In Process %s Size is %2d Duration is %d", i, p.getNameOfProc(), p.getSizeOfProc(), p.getDurationOfProc()));
						print();
					}
				} else {
					j++;
				}
			}
		}
	}
	
	/**
	 * A method to print the current processes in memory
	 */
	void print() {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < MEM_SIZE; i++) {
			output.append(totMemory[i]);
		}
		
		System.out.println(output.toString());
	}
	
	
}
