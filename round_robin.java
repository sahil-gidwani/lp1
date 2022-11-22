import java.util.*;

class Process {
    int processID;
    int arrivalTime;
    int burstTime;

    Process(int processID, int arrivalTime, int burstTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class round_robin {

    public static void findWatingTime(Process proc[], int noofProcesses, int watingTime[], int turnAroundTime[]) {
        for (int i = 0; i < noofProcesses; i++) {
            watingTime[i] = turnAroundTime[i] - proc[i].burstTime;
        }
    }

    public static void findTurnaroundTime(Process proc[], int noofProcesses, int turnAroundTime[],
            int completionTime[]) {
        for (int i = 0; i < noofProcesses; i++) {
            turnAroundTime[i] = completionTime[i] - proc[i].arrivalTime;
        }
    }

    public static void findCompletionTime(Process proc[], int noofProcesses, int completionTime[], int tq) {
        int remainingbursTime[] = new int[noofProcesses];
        int tempArrivalTime[] = new int[noofProcesses];

        ArrayList<Integer> readyQueue = new ArrayList<Integer>(200);
        // ASSUME PROCESS ARE SORTED ACCORDING TO ARRIVAL TIME (IF NOT ADD A SORT
        // FUNCTION)
        readyQueue.add(0);

        for (int i = 0; i < noofProcesses; i++) {
            remainingbursTime[i] = proc[i].burstTime;
            tempArrivalTime[i] = proc[i].arrivalTime;
        }

        int counter = 0;

        while (true) {
            boolean done = true;

            // SELECT PROCESS
            for (int j = 0; j < readyQueue.size(); j++) {

                int i = readyQueue.get(j);

                if (remainingbursTime[i] > 0) {

                    done = false;

                    if (remainingbursTime[i] == 0) {
                        counter++;
                        continue;
                    }

                    if (remainingbursTime[i] > tq) {
                        counter = counter + tq;

                        // INSERT ARRIVED PROCESS IN READY_QUEUE
                        for (int k = 1; k < noofProcesses; k++) {
                            if (tempArrivalTime[k] <= counter) {
                                readyQueue.add(k);
                                // ONCE THE PROCESS IS ARRIVED MARK IT WITH MAX INTEGER VALUE SO IT DOES GO IN
                                // THE READY QUEUE AGAIN
                                tempArrivalTime[k] = Integer.MAX_VALUE;
                            }
                        }

                        remainingbursTime[i] = remainingbursTime[i] - tq;
                        // ADD INCOMPLETE PROCESS IN READY_QUEUE
                        readyQueue.add(i);
                    } else {
                        counter = counter + remainingbursTime[i];
                        completionTime[i] = counter;
                        remainingbursTime[i] = 0;

                    }
                }
            }

            if (done == true) {
                break;
            }
        }

        // DISPLAYING THE READY QUEUE
        System.out.print("\nReady Queue: ");
        System.out.print("[");
        for (int i = 0; i < readyQueue.size(); i++) {
            System.out.print("Process" + (readyQueue.get(i) + 1) + ", ");
        }
        System.out.println("]");

    }

    public static void findAverage(Process proc[], int noofProcesses, int tq) {
        int completionTime[] = new int[noofProcesses];
        int watingTime[] = new int[noofProcesses];
        int turnAroundTime[] = new int[noofProcesses];
        int average_WT = 0, average_TT = 0;

        findCompletionTime(proc, noofProcesses, completionTime, tq);

        findTurnaroundTime(proc, noofProcesses, turnAroundTime, completionTime);

        findWatingTime(proc, noofProcesses, watingTime, turnAroundTime);

        // DISPLAY TABLE
        System.out.println("-----------------------------");
        System.out.println("Total number of processes: " + noofProcesses);
        System.out.println("-----------------------------");
        System.out.println(
                "Process SR. \t Arrival Time \t Burst Time \t Completion Time \t Turnaround Time \t Wating Time");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < noofProcesses; i++) {
            average_TT = average_TT + turnAroundTime[i];
            average_WT = average_WT + watingTime[i];
            System.out
                    .println("Process " + proc[i].processID + "\t\t" + proc[i].arrivalTime + "\t\t" + proc[i].burstTime
                            + "\t\t" + completionTime[i] + "\t\t\t" + turnAroundTime[i] + "\t\t\t" + watingTime[i]);

        }
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------");
        // DISPLAY AVERAGE TURNAROUND AND WATING TIME

        System.out.println("AVERAGE WATING TIME: " + (average_WT / (float) noofProcesses) + " units");
        System.out.println("AVERAGE TURNAROUND TIME: " + (average_TT / (float) noofProcesses) + " units");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Process proc[] = { new Process(1, 0, 5), new Process(2, 1, 4), new Process(3, 2, 2), new Process(4, 3, 1) };

        // ENTER THE TIME QUANTUM
        System.out.print("Enter the Time Quantum: ");
        int tq = scan.nextInt();

        findAverage(proc, proc.length, tq);

        scan.close();
    }
}
