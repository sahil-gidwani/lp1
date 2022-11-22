import java.util.*;

public class next_fit {
    // Function to allocate memory to blocks as per Next fit algorithm
    static void NextFit(int blockSize[], int m, int processSize[], int n) {
        // Stores block id of the block allocated to a process
        int allocation[] = new int[n], j = 0;

        // Initially no block is assigned to any process
        Arrays.fill(allocation, -1);

        // pick each process and find suitable blocks according to its size ad assign to
        // it
        for (int i = 0; i < n; i++) {
            int count = 0;

            while (count < m) {
                // makes sure that for every process we traverse through entire array maximum
                // once only.This avoids the problem of going into infinite loop if memory is
                // not available
                count++;

                if (blockSize[j] >= processSize[i]) {

                    // allocate block j to p[i] process
                    allocation[i] = j;

                    // Reduce available memory in this block.
                    blockSize[j] -= processSize[i];

                    break;
                }

                // mod m will help in traversing the blocks from starting block after we reach
                // the end.
                j = (j + 1) % m;
            }
        }

        System.out.print("\nProcess No.\tProcess Size\tBlock no.\n");
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + "\t\t" + processSize[i]
                    + "\t\t");
            if (allocation[i] != -1) {
                System.out.print(allocation[i] + 1);
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println("");
        }
    }

    static public void main(String[] args) {
        int blockSize[] = { 100, 500, 200, 300, 600 };
        int processSize[] = { 212, 417, 112, 426 };
        int m = blockSize.length;
        int n = processSize.length;

        NextFit(blockSize, m, processSize, n);
    }
}
