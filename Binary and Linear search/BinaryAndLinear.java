import java.io.FileWriter;
import java.io.IOException;

public class BinaryAndLinear {
    public static void main(String[] args) throws IOException {

        int[] nums = new int[20000];
        for (int i = 0; i < nums.length; i++) { // multiples of 2
            nums[i] = 2 * i;
        }

        int[] sizes = {50, 100, 200, 300, 400, 500, 600, 800, 1000, 2000, 5000, 10000, 15000, 20000};
        int trials = 1000; // average over multiple runs

        FileWriter csvWriter = new FileWriter("results.csv");
        csvWriter.append("InputSize,KeyPosition,BinarySearchTime(ns),LinearSearchTime(ns)\n");

        for (int n : sizes) {
            int[] keys = { nums[0], nums[n / 2], nums[n - 1] };
            String[] keyLabels = { "Start", "Middle", "End" };

            for (int k = 0; k < keys.length; k++) {
                int key = keys[k];
                String pos = keyLabels[k];

                // Binary Search timing (averaged)
                long totalBinaryTime = 0;
                for (int i = 0; i < trials; i++) {
                    long start = System.nanoTime();
                    binarySearch(nums, key, n);
                    long end = System.nanoTime();
                    totalBinaryTime += (end - start);
                }
                long avgBinaryTime = totalBinaryTime / trials;

                // Linear Search timing (averaged)
                long totalLinearTime = 0;
                for (int i = 0; i < trials; i++) {
                    long start = System.nanoTime();
                    linearSearch(nums, key, n);
                    long end = System.nanoTime();
                    totalLinearTime += (end - start);
                }
                long avgLinearTime = totalLinearTime / trials;

                // Print results in console
                System.out.printf(
                    "Size=%d, Pos=%s, BinarySearch(avg)=%d ns, LinearSearch(avg)=%d ns\n",
                    n, pos, avgBinaryTime, avgLinearTime
                );

                // Write to CSV
                csvWriter.append(n + "," + pos + "," + avgBinaryTime + "," + avgLinearTime + "\n");
            }
        }

        csvWriter.flush();
        csvWriter.close();
        System.out.println("\nResults saved to results.csv");

        // Optionally, call Python script automatically
        try {
            String pythonExe = "python"; // or "python3" if needed
            String scriptPath = "C:/projects/DAALAB/Visualisation/graphPloting.py";
            String csvPath = "results.csv";

            ProcessBuilder pb = new ProcessBuilder(pythonExe, scriptPath, csvPath);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();

            System.out.println("Visualization completed.");
        } catch (Exception e) {
            System.out.println("Error running Python script: " + e.getMessage());
        }
    }

    private static int binarySearch(int[] nums, int key, int n) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > key) {
                right = mid - 1;
            } else if (nums[mid] < key) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static int linearSearch(int[] nums, int key, int n) {
        for (int i = 0; i < n; i++) {
            if (nums[i] == key)
                return i;
        }
        return -1;
    }
}
