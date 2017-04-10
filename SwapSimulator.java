import java.util.*;
import java.io.*;

/**
 * A class to simulate the swapping algorithms
 * @author Karan Bhargava
 */
public class SwapSimulator {

	static String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789" // 62
			+ "¡™£¢∞§¶•ªº–≠!@#$%^&*()+å∫ç∂´ƒ©˙ˆ∆˚¬µ˜øπœ®ß†¨√∑≈¥Ω{}:'<>?|/" // 58
			+ "ÅıÇÎ´Ï˝ÓˆÔÒÂ˜Ø∏Œ‰Íˇ¨◊„˛Á¸€‹›‡°·‚±ÚÆ˘¿"; // 38 + 58 + 62 = 158
	// possible processes;
	static char[] namesOfProc = string.toCharArray();
	final static int[] POSSIBLE_SIZES = { 5, 11, 17, 31 }; // possible sizes of a process

	/**
	 * A method to generate the processes for the swapping algorithms
	 * @return returnProcess - the linkedList with the generated processes
	 */
	static LinkedList<SwapProcesses> generateProcesses() {

		LinkedList<SwapProcesses> returnProcess = new LinkedList<SwapProcesses>();
		int count = 0;

		do{ 

			SwapProcesses proc = new SwapProcesses();
			int sizeOfProc = (int) (Math.random() * 4);
			int durationOfProc = (int) ((Math.random() * 5) + 1);

			proc.setSizeOfProc(POSSIBLE_SIZES[sizeOfProc]);
			proc.setDurationOfProc(durationOfProc);
			proc.setNameOfProc(namesOfProc[count++]);
			returnProcess.add(proc);

		} while (count < namesOfProc.length);


		return returnProcess;
	}

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream printStream = new PrintStream(
				new FileOutputStream(new File("SwapOutput.txt")));
		System.setOut(printStream);

		int firstFitSwappedIn = 0;

		for (int i = 0; i < 5; i++)
		{
			LinkedList<SwapProcesses> process = generateProcesses();

			System.out.println();
			System.out.println("+------------------------------+");
			System.out.format("| Swapping Simulation Run %d    |", i + 1);
			System.out.println();
			System.out.println("+------------------------------+");
			System.out.println();

			for (SwapProcesses p : process)
			{
				System.out.println(p);
			}

			LinkedList<SwapProcesses> firstFitProcesses = new LinkedList<SwapProcesses>();
			for (SwapProcesses p : process)
			{
				firstFitProcesses.add(p.clone());
			}
			LinkedList<SwapProcesses> nextFitProcesses = new LinkedList<SwapProcesses>();
			for (SwapProcesses p : process)
			{
				nextFitProcesses.add(p.clone());
			}
			LinkedList<SwapProcesses> bestFitProcesses = new LinkedList<SwapProcesses>();
			for (SwapProcesses p : process)
			{
				bestFitProcesses.add(p.clone());
			}

			System.out.println();
			System.out.println("-----"
							+ "-----");
			System.out.println();
			System.out.println("__FIRST FIT__");
			System.out.println();
			AnyFit firstFit = new FirstFit(firstFitProcesses);
			firstFit.run();
			firstFitSwappedIn += firstFit.SwapIn();

			System.out.println();
			System.out.println();
			System.out.println("-----"
							+ "-----");
			System.out.println();

			System.out.println("__STATS FOR PROCESSES__");
			System.out.println();
			System.out.println("FIRST FIT: " + (firstFitSwappedIn / 5.0));
		}

	}
}
