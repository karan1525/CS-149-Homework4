/**
 * A class representing a process
 * @author Karan Bhargava
 */
public class SwapProcesses {
	
	//instance variables
	char nameOfProc;
	int sizeOfProc, durationOfProc;
	
	/**
	 * A method to return the processes' information in String format
	 */
	public String toString() {
		
		return String.format("Process: Name: %c Size: %2d Duration %2d", nameOfProc, durationOfProc, sizeOfProc);
	}
	
	/**
	 * A method to clone the current process and return it
	 * @return newProc - the cloned process
	 */
	public SwapProcesses clone() {
		SwapProcesses newProc = new SwapProcesses();
		
		newProc.setDurationOfProc(durationOfProc);
		newProc.setNameOfProc(nameOfProc);
		newProc.setSizeOfProc(sizeOfProc);
		
		return newProc;
	}
	
	/**
	 * A method to set the duration of the process
	 * @param duration - the duration to set
	 */
	void setDurationOfProc(int duration) {
		durationOfProc = duration;
	}
	
	/**
	 * A method to get the duration of the process
	 * @return durationOfProc - the duration of the process
	 */
	int getDurationOfProc() {
		return durationOfProc;
	}
	
	/**
	 * A method to set the name of the process
	 * @param name - the name to set
	 */
	void setNameOfProc(char name) {
		nameOfProc = name;
	}
	
	/**
	 * A method to get the name of the process
	 * @return nameOfProc - the name of the process
	 */
	char getNameOfProc() {
		return nameOfProc;
	}
	
	/**
	 * A method to set the size of the process
	 * @param size - the size to set
	 */
	void setSizeOfProc(int size) {
		sizeOfProc = size;
	}
	
	/**
	 * A method to get the size of the process
	 * @return sizeOfProc - the size of the process
	 */
	int getSizeOfProc() {
		return sizeOfProc;
	}
	
	

}
