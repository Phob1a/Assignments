import java.util.Collections;


/**
 * Average wait time = 4981.345
 * Average turnaround time = 5032.055
 */
public class Priority extends Scheduler {

    public Priority(Process[] pArray) {
        super(pArray);
    }

    public static int jud(Process process, Process arg0) {
        if(process.getPriority()>arg0.getPriority()) return 1;
        if(process.getPriority()<arg0.getPriority()) return -1;
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
        while(!readyQueue.isEmpty()){
            activeProcess=readyQueue.pop();
            System.out.println(activeProcess);
            runtime+=activeProcess.getBurstTime();
            activeProcess.setTurnaroundTime(runtime-activeProcess.getArrivalTime());
            turnAroundTime+=activeProcess.getTurnaroundTime();
            activeProcess.setWaitingTime(activeProcess.getTurnaroundTime()-activeProcess.getBurstTime());
            totalWaitTime+=activeProcess.getWaitingTime();
            terminatedProcesses.add(activeProcess);
            checkForArrivingProcesses(runtime);
            Collections.sort(readyQueue);
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
