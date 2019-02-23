import java.util.LinkedList;

public abstract class Scheduler {
	
	/**
	 * Relevant data structure and variables common to all schedulers.
	 */
	int runtime;
	int totalWaitTime;
	int turnAroundTime;
	int f=0;
	int n;
	protected Process[] p;
	protected LinkedList<Process> readyQueue, terminatedProcesses;
	protected Process activeProcess;
	
	/**
	 * Each class extending Scheduler should make a call to this super constructor from their own constructors.
	 * @param pArray
	 */
	public Scheduler(Process[] pArray) {
		readyQueue = new LinkedList<Process>();
		terminatedProcesses = new LinkedList<Process>();
		p = pArray;
		n = p.length;
	}


	//public static abstract int jud(Process process, Process arg0);

	/**
	 * This method is specific to a given scheduler.
	 * (Hint: each scheduler will have a slightly different way of running)
	 */
	abstract void runScheduler();

	/**
	 * This method is specific to a given scheduler.
	 * (Hint: this has common elements, but will differ slightly for some scheduling algorithms)
	 */
	abstract void checkForArrivingProcesses(int t);
	
	/**
	 * Calculates the average waiting time of all processes
	 * (Hint: this is common to all schedulers)
	 * @return
	 */
	public double calculateAverageWaitTime() {
		//double avgWT = 0.0;
		// complete code in this class
		return totalWaitTime*1.0/n;
	}
	
	/**
	 * Calculates the average turnaround time of all processes
	 * (Hint: this is common to all schedulers)
	 * @return
	 */
	public double calculateAverageTurnaroundTime() {
		double avgTT = 0.0;
		//complete code in this class
		avgTT=turnAroundTime*1.0/n;
		return avgTT;
	}
	
	/**
	 * Return total run time of all processes
	 * @return
	 */
	public int getRuntime() {
		return 0;
	}
	
	/**
	 * Calculates how busy a CPU is over a given time period (efficiency)
	 * @param minutes
	 * @return
	 */

	public double getProcessorUsage(double minutes) {
		double sec=minutes*60;
		double t=0;
		for(Process p:terminatedProcesses){
			t+=p.getBurstTime()*1.0;
		}
		t/=1000;
		return t/sec;
	}
	

}
