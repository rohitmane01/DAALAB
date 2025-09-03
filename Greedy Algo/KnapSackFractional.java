import java.util.*;

public class KnapSackFractional {
    public static void main(String[] args){
        
        int []weight = {10,15,25,30,45,35,12}; // weight
        int []price = {30,40,20,10,15,50,19}; // profit


        int n = weight.length;
        double [][]combo = new double[n][2]; // [profit per weight, index]

       
        for(int i=0; i<n; i++){
            
            combo[i][0] = (double)price[i] / weight[i]; //Ratio Profit per Kg (unit of weight)
            
            combo[i][1] = i; //Curresponding index to that profit
        }

        // sort by profit/weight descending
        Arrays.sort(combo, (a, b) -> Double.compare(b[0], a[0]));

        int cap = 80; //Initial / Total Capacity of KnapSack
        double currentProfit = 0;  //Profit that we get by adding items

        
        for(int i=0; i<n; i++){ //iterate over the all the items

            int index = (int) combo[i][1]; //getting index of item which have high profit per wieght(kg ) ration

            
            if(weight[index] <= cap){ //as KnapScak have the capacity to take the whole object therefore put the whole object into KanpScak
                
                cap -= weight[index]; //Update capacity as w[index] is occupied by the (index)th object 
                
                currentProfit += price[index]; //add profit
                
                System.out.println("Took full item " + index + " (w=" + weight[index] + ", p=" + price[index] + ")");
            } else {
                
                currentProfit += combo[i][0] * cap; //profit added for capacity of this object that we can include in KnapScak.
                
                System.out.println("Took " + ((double)cap / weight[index]) * 100 
                                   + "% of item " + index + " (w=" + weight[index] + ", p=" + price[index] + ")");
                
                
                                   break; //break as KnapSack is Full 
            }
        }

        System.out.printf("Maximum Profit = %.2f%n", currentProfit);
    }
}
