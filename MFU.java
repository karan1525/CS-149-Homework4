import java.util.ArrayList;
import java.util.Random;


public class MFU extends PagingAlgo
{
   //4 page frames, can be changed depending on user preferred frame size
   final static int FRAME_SIZE = 4;
   
   //ArrayDeque to hold the memory
   private ArrayList<Character> memory = new ArrayList<Character>(FRAME_SIZE);
   
   //ArrayList to keep track of most used page counter
   private int[] pageBuffer = new int[FRAME_SIZE];
   
   public MFU()
   {
      super();
   }
   
   /**
    * This method is an override of the parent class PagingAlgo,
    * and will handle the simulation for running MLU
    */
   @Override
   void runSimulation()
   {
      // TODO Auto-generated method stub
      char currPage = ' ';
      char removedPage = ' ';
      Integer index = new Random().nextInt(DISK_FRAMES.length);
      int maxIndex;
      int j = 1; //index for simulation
      
      while( j < SIMULATION_TIME)
      {
         currPage = DISK_FRAMES[index];
         //page fault occurred
         if(!memory.contains(currPage))
         {
            if(memory.size() == FRAME_SIZE)
            {
               maxIndex = -1;
               int maxCount = Integer.MIN_VALUE;
               
               for(int n = 0; n < FRAME_SIZE; n++)
               {
                  if(pageBuffer[n] > maxCount)
                  {
                     maxCount = pageBuffer[n];
                     maxIndex = n;
                  }
               }
               
               //find unnecessary pages 
               if(maxIndex >= 0)
               {
                  removedPage = memory.set(maxIndex, currPage);
                  pageBuffer[maxIndex] = 1;
               }
            }
            //empty page frame
            else 
            {
               memory.add(currPage);
               pageBuffer[memory.indexOf(currPage)] += 1;
            }
         }
         //the page is in memory already
         else
         {
            pageBuffer[memory.indexOf(currPage)]++;
            totalHitCount = totalHitCount + 1;
         }
         
         printReference(currPage, removedPage, memory, index);
         index = nextReference(index);
         removedPage = ' ';
         j++;
      }
   }
}
