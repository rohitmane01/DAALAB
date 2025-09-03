public class fact {
    public static void main(String [] args){
        int [] data = {1,3,33,333,3333,333333,333333,3333333,3333333,999999999,4000};
        int n = data.length;

        for(int i =0; i<n ;i++){
            
            iterate(data[i]);
            System.out.print(iterate(data[i]));

            System.out.print(recurse(data[i]));
        }
    }

    private static int iterate(int num){
        int fact =0;
        while(num>0){
            fact *=num--;
        }

        return fact;
    }
    
    private static int recurse(int num){
        if(num==1)return 1;
        

        
        return num*recurse(num-1);

     
    


    }
}