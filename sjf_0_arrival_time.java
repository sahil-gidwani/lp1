import java.util.Scanner;

public class sjf_0_arrival_time {
    static void SJF(int pid[], int n, int bt[]) {
        int wt[] = new int[n];
        int tat[] = new int[n];

        float avgwt = 0, avgta = 0;

        int pos, temp;
        // sorting for burst times
        for (int i = 0; i < n; i++) {
            pos = i;
            for (int j = i + 1; j < n; j++) {
                if (bt[j] < bt[pos]) {
                    pos = j;
                }
            }

            temp = bt[i];
            bt[i] = bt[pos];
            bt[pos] = temp;

            temp = pid[i];
            pid[i] = pid[pos];
            pid[pos] = temp;
        }

        wt[0] = 0;

        // calculating waiting time
        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
            avgwt += wt[i];
        }
        System.out.println("\nProcess Burst Waiting TurnAround");
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
            avgta += tat[i];
            System.out.println(+pid[i] + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }

        System.out.println("Average Waiting Time : " + (avgwt / n));
        System.out.println("Average Turn Around Time : " + (avgta / n));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of process: ");
        int n = sc.nextInt();
        int pid[] = new int[n]; // process ids
        int bt[] = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter process " + (i + 1) + "burst time : ");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }
        sc.close();

        SJF(pid, n, bt);
    }
}