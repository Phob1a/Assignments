public class RoundRobin extends Scheduler {

    int q=20;
    public RoundRobin(Process[] pArray) {
        super(pArray);
    }

    /**
     * Average wait time = 6728.25
     * Average turnaround time = 6778.96
     */
    @Override
    void runScheduler() {
        runtime=0;
        totalWaitTime=0;
        turnAroundTime=0;
        //PriorityQueue<Process>readyQueue=new PriorityQueue<>();
        checkForArrivingProcesses(0);

        while(!readyQueue.isEmpty()){
            activeProcess=readyQueue.pop();
            //System.out.println(activeProcess.getProcessID());
            if(q>=activeProcess.getBurstRemaining()){
                runtime+=activeProcess.getBurstRemaining();
                while(activeProcess.getBurstRemaining()>0){
                    activeProcess.reduceBurstRemainingTime();
                }
                activeProcess.setTurnaroundTime(runtime-activeProcess.getArrivalTime());
                turnAroundTime+=activeProcess.getTurnaroundTime();
                activeProcess.setWaitingTime(activeProcess.getTurnaroundTime()-activeProcess.getBurstTime());
                totalWaitTime+=activeProcess.getWaitingTime();
            }
            else{
                int n=q;
                while(n>0){
                    activeProcess.reduceBurstRemainingTime();
                    n--;
                }
                runtime+=q;
            }
            checkForArrivingProcesses(runtime);
            if(activeProcess.getBurstRemaining()!=0){
                readyQueue.add(activeProcess);
            }
            else{
                terminatedProcesses.add(activeProcess);
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
