import java.util.*;

public class LFU extends PagingAlgo {
	ArrayList<Character> arrivalInOrder = new ArrayList<Character>();
	Map<Character, Integer> frequencies = new HashMap<Character, Integer>();
	// private ArrayDeque<Character> memory = new ArrayDeque<>(4);
	// page frame buffer = 4;
	Character[] pageBuffer = new Character[4];

	LFU() {
		super();
		for (int i = 0; i < DISK_FRAMES.length; i++) {
			frequencies.put(DISK_FRAMES[i], 0);
		}
	}

	@Override
	void runSimulation() {
		// TODO Auto-generated method stub
		char currPage = ' ';
		char removed = ' ';
		int currIndex = new Random().nextInt(DISK_FRAMES.length);
		int counter = 0;

		while (counter < SIMULATION_TIME) { // SIMULATION_TIME
			boolean pageFault = true;
			currPage = DISK_FRAMES[currIndex];
			pageFault = findPageFault(pageBuffer, currPage);
			if (pageFault == false) // No Page Fault
			{
				int loc = getFirstFreeSpace(pageBuffer);
				if (loc != -1 && notInBuffer(pageBuffer, currPage) == true) { // pageBuffer
																				// is
																				// full
					pageBuffer[loc] = currPage;
				}
				int value = frequencies.get(currPage) + 1;
				frequencies.put(currPage, value);
				totalHitCount++;
//				System.out.println("Got In Here");
				if (inListing(arrivalInOrder, currPage) == false)
					arrivalInOrder.add(currPage);
			} else { // There Is A Page Fault
//				System.out.println("else");
				Character chosen = getLeastFrequency(frequencies, arrivalInOrder);
				removed = chosen;
				// Handle Removal
				removeChar(chosen, currPage, pageBuffer);
				// Add into ArrivalInOrder
				arrivalInOrder.add(currPage);
			}
			printRef2(currPage, removed, pageBuffer, counter);
			// set currentIndex to next reference
			currIndex = nextReference(currIndex);
			removed = ' ';
			counter++;
		}
	}

	/**
	 * Checks if it's in the page buffer or not
	 * 
	 * @param pageBuffer2
	 * @param currPage
	 * @return true if not in buffer, false otherwise
	 */
	private boolean notInBuffer(Character[] list, char currPage) {
		// TODO Auto-generated method stub
		ArrayList<Character> checking = new ArrayList<Character>();
		for (int i = 0; i < list.length; i++) {
			checking.add(list[i]);
		}
		if (!checking.contains(currPage))
			return true;
		return false;
	}

	/**
	 * Remove Handling and putting in correctly
	 */
	private void removeChar(Character chosen, Character currPage, Character[] list) {
		// TODO Auto-generated method stub

		int index = 0;
		if (getFirstFreeSpace(list) == -1) {
			index = findPage(pageBuffer, chosen);
			// remove char from arrival order
			for (int i = 0; i < arrivalInOrder.size(); i++) {
				if (arrivalInOrder.get(i).equals(chosen))
					arrivalInOrder.remove(i);
			}
			// Change Frequencies
			frequencies.put(chosen, 0);
		} else {
			index = getFirstFreeSpace(list);
		}
		int value = 0;
		value = frequencies.get(currPage) + 1; // make sure it's Zero
		frequencies.put(currPage, value);
		// Change Page Buffer
		if (notInBuffer(list, currPage) == true)
			pageBuffer[index] = currPage;
	}

	/**
	 * Used to find whether or not x is already in list
	 * 
	 * @param list
	 *            ArrayList of Characters in order
	 * @param x
	 *            element being checked
	 * @return true if already in, false if not
	 */
	private boolean inListing(ArrayList<Character> list, char x) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(x))
				return true;
		}
		return false;
	}

	/**
	 * Finds the Page
	 * 
	 * @param list
	 * @param x
	 * @return Finds Page within Page bufer
	 */
	private int findPage(Character[] list, char x) {
		// TODO Auto-generated method stub
		int val = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i].equals(x))
				val = i;
		}
		return val;
	}

	/**
	 * Finds Page Fault
	 * 
	 * @param list
	 * @param currPage
	 * @return false if no page fault, true if there is
	 */
	private boolean findPageFault(Character[] list, char currPage) {
		// TODO Auto-generated method stub
		ArrayList<Character> temp = new ArrayList<Character>();
		for (int i = 0; i < list.length; i++) {
			temp.add(list[i]);
		}
		if (temp.contains(currPage))
			return false;
		return true;
	}

	/**
	 * Finds First Free space
	 * 
	 * @param list
	 * @return First instance of Free Space
	 */
	private int getFirstFreeSpace(Character[] list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.length; i++) {
			if (list[i] == null)
				return i;
		}
		return -1;
	}

	/**
	 * Find spot with least frequencies, DOES NOT HANDLE ANY REMOVE
	 * 
	 * @param frequencies
	 * @param list
	 * @return Least Frequency Location
	 */
	public char getLeastFrequency(Map<Character, Integer> frequencies, ArrayList<Character> list) {
		Integer[] values = frequencies.values().toArray(new Integer[frequencies.size()]);
		int firstNonZ = 0;
		int min = 0;
		int multiple = 0;
		Character temp = ' ';
		ArrayList<Character> MinDup = new ArrayList<Character>();
		ArrayList<Integer> numHolder = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			if (firstNonZ == 0 && values[i] != 0) {
				min = values[i];
				firstNonZ = 1;
			} else {
				if (values[i] != 0 && (values[i]) < min) {
					min = values[i];
					multiple = 0;
				} else if (values[i] != 0 && (values[i]) == min) {
					multiple++;
				}
			}
		}
		// Get's Key with the Min frequency
		for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
			Character key = entry.getKey();
			int val = entry.getValue();
			if (val == min) {
				temp = key;
				MinDup.add(key); // if Multiple Keys with Min Frequency, add
									// them to MinDup
			}
		}
		// If there are multiples of the least frequencies handle like FIFO
		if (multiple != 0) {
			int index = 0;
			// Find Index of Least Frequencies
			for (int i = 0; i < MinDup.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (MinDup.get(i).equals(list.get(j)))
						numHolder.add(j); // add location in list
				}
			}
			index = numHolder.get(0);
			// Iterate through numHolder to get which one has smallest index
			for (int i = 1; i < numHolder.size(); i++) {
				if (numHolder.get(i) < index)
					index = numHolder.get(i);
			}
			// With that index now return that charater from list
			temp = list.get(index);
		}
		return temp;
	}
}
