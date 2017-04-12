import java.util.ArrayDeque;
import java.util.Random;

public class LRU extends PagingAlgo {

	final int FRAME_SIZE = 4;
	private ArrayDeque<Character> memory = new ArrayDeque<>(FRAME_SIZE);

	LRU() {
		super();
	}

	@Override
	void runSimulation() {
		int currentIndex = new Random().nextInt(DISK_FRAMES.length);
        char currentPage = ' ';
        char removed = ' ';
        for (int i = 1; i <= SIMULATION_TIME; i++)
        {
            currentPage = DISK_FRAMES[currentIndex];
            if(!memory.contains(currentPage)) // page fault
            {
                if(memory.size() == 4)
                {
                    removed = memory.removeFirst();
                }
                memory.addLast(currentPage);
            }
            else // page already in memory
            {
                memory.remove(currentPage);
                memory.addLast(currentPage);
                totalHitCount++;
            }
            
            printReference(currentPage, removed, memory,i);
                   
            // set currentIndex to next reference
            currentIndex = nextReference(currentIndex);
            removed = ' ';
        }
	}
}
