import java.util.Collections;

public class SJF extends Scheduler {


    /**
     * Each class extending Scheduler should make a call to this super constructor from their own constructors.
     *
         Average wait time = 3457.16
         Average turnaround time = 3507.87
     *
     *
     * @param pArray
     */
    public SJF(Process[] pArray) {
        super(pArray);
    }

    public static int jud(Process process, Process arg0) {
        if(process.getBurstRemaining()<arg0.getBurstRemaining()) return 1;
        if(process.getBurstRemaining()>arg0.getBurstRemaining()) return -1;
        if(process.getProcessID()<arg0.getProcessID()) return 1;
        return -1;
    }

    @Override
    void runScheduler() {
        runtime = 0;
        totalWaitTime = 0;
        turnAroundTime = 0;
        checkForArrivingProcesses(0);
        Collections.sort(readyQueue);
        while (!readyQueue.isEmpty()) {
            activeProcess = readyQueue.pop();
            while (activeProcess.getBurstRemaining() != 0) {
                activeProcess.reduceBurstRemainingTime();
                runtime++;
                checkForArrivingProcesses(runtime);
                Collections.sort(readyQueue);
                if (activeProcess.getBurstRemaining() == 0) {
                    terminatedProcesses.add(activeProcess);
                    activeProcess.setTurnaroundTime(runtime-activeProcess.getArrivalTime());
                    turnAroundTime += activeProcess.getTurnaroundTime();
                    activeProcess.setWaitingTime(activeProcess.getTurnaroundTime()-activeProcess.getBurstTime());
                    totalWaitTime+=activeProcess.getWaitingTime();
                } else {
                    if (!readyQueue.isEmpty() && activeProcess.compareTo(readyQueue.getFirst()) !=-1) {
                        readyQueue.add(activeProcess);
                        Collections.sort(readyQueue);
                        break;
                    }
                }
            }
        }

    }

    @Override
    void checkForArrivingProcesses(int t) {
        while(f<p.length){
            if(p[f].getArrivalTime()<=t){
                readyQueue.add(p[f]);
                f++;
                continue;
            }
            break;
        }
    }

}
