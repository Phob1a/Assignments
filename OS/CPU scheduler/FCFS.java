import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * Average wait time = 5054.19
 * Average turnaround time = 5104.9
 *
 */
public class FCFS extends Scheduler {

    public FCFS(Process[] p) {
        super(p);
    }

    @Override
    void runScheduler() {
        runtime=0;
        totalWaitTime=0;
        turnAroundTime=0;
        //PriorityQueue<Process>readyQueue=new PriorityQueue<>();
        checkForArrivingProcesses(0);

        while(!readyQueue.isEmpty()){
            activeProcess=readyQueue.pop();
            runtime+=activeProcess.getBurstTime();

            activeProcess.setTurnaroundTime(runtime-activeProcess.getArrivalTime());
            turnAroundTime+=activeProcess.getTurnaroundTime();
            activeProcess.setWaitingTime(activeProcess.getTurnaroundTime()-activeProcess.getBurstTime());
            totalWaitTime+=activeProcess.getWaitingTime();
            System.out.println(totalWaitTime);
            terminatedProcesses.add(activeProcess);
            checkForArrivingProcesses(runtime);
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
