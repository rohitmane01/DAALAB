import java.util.*;

public class KnapSackFractional {
    public static void main(String[] args){
        int []w = {10,15,25,30,45,35,12}; // weight
        int []p = {30,40,20,10,15,50,19}; // profit

        int n = w.length;
        double [][]combo = new double[n][2]; // [profit per weight, index]

        for(int i=0; i<n; i++){
            combo[i][0] = (double)p[i] / w[i];
            combo[i][1] = i;
        }

        // sort by profit/weight descending
        Arrays.sort(combo, (a, b) -> Double.compare(b[0], a[0]));

        int cap = 80;
        double profit = 0;

        for(int i=0; i<n; i++){
            int index = (int) combo[i][1];
            if(w[index] <= cap){
                cap -= w[index];
                profit += p[index];
                System.out.println("Took full item " + index + " (w=" + w[index] + ", p=" + p[index] + ")");
            } else {
                profit += combo[i][0] * cap;
                System.out.println("Took " + ((double)cap / w[index]) * 100 
                                   + "% of item " + index + " (w=" + w[index] + ", p=" + p[index] + ")");
                break;
            }
        }

        System.out.printf("Maximum Profit = %.2f%n", profit);
    }
}
