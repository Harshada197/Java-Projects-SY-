// Using Recurssion Approach
import java.util.*;
class SubsetSum {
    public static boolean isSubsetSum(int[]arr , int target){
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][target+1];
        //sum zero is always possible  - therefore initializing it
        for(int i=0;i<=n;i++){
            dp[i][0] = true;
        }
        //filling remaining dp matrix
        for(int i=1;i<=n;i++){
            for(int j=1;j<=target;j++){
                if(arr[i-1]>j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
                }
            }
        }
        return dp[n][target];
    }
   
    //Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nFinding Subset with given Sum using Dynamic Programming Approach");
        System.out.println("-----------------------------------------------------------------------------------\n");
        int m=1;
        do{
        System.out.print("How many numbers do you want to enter in your set? ");
        int n; 
        while(true){
            if(sc.hasNextInt()){
                n = sc.nextInt();
                if(n>0 && n<50){
                    break;
                }else{
                    System.out.println("Please enter a valid number of elements (1-49):");
                    sc.next(); // Clear the invalid input
                }
            }else{
                System.out.println("Invalid Input.Enter Numeric Input..\n Please Try again..");
                sc.next(); // Clear the invalid input
            }
        }
        // creating Array to store the elements
        int[] arr = new int[n];
        System.out.println("Enter the elements of the set:");
        for(int i = 0; i < n; i++) {
            System.out.print("Enter "+(i+1)+" element: ");
            while(true){ 
                if(sc.hasNextInt()){
                    arr[i] = sc.nextInt();
                    if(arr[i]>=0 && arr[i]<=100){
                        break;
                    }else{
                        System.out.println("Please enter Number between 0 and 100.");
                        sc.next(); // clear invalid input
                    }
                }else{
                    System.out.println("Invalid Input. Enter Numeric Input..\n Please Try again..");
                }
            }
        }
        //Sum you want to find ..
        System.out.println("Enter the sum you want to find:");
        int sum;
        while (true) { 
            if (sc.hasNextInt()) {
                sum = sc.nextInt();
                if (sum >= 0 && sum <= 1000) {
                    break;
                } else {
                    System.out.println("Please enter a valid sum between 0 and 1000:");
                    sc.next(); // Clear the invalid input
                }
            } else {
                System.out.println("Invalid Input. Enter Numeric Input..\n Please Try again..");
                sc.next(); // Clear the invalid input
            }
        }
        //calling out function
        boolean result = isSubsetSum(arr, sum);
        if(result){
            System.out.println("Yes, there exists a subset with the given sum: " + sum);
        }else{
            System.out.println("No, there is no subset with the given sum: " + sum);
        }

        System.out.println("Do you want to continue? (1-Yes, 0-No)");
        while(true){
            if(sc.hasNextInt()){
                m = sc.nextInt();
                if(m==0 || m==1){
                    break;
                }else{
                    System.out.println("Please enter 1 to continue or 0 to exit:");
                    sc.next(); // Clear the invalid input
                }
            }else{
                System.out.println("Invalid Input. Enter Numeric Input..\n Please Try again..");
                sc.next(); // Clear the invalid input
            }
        }
    }while(m==1);
        System.out.println("Thank you for using the program!");
        sc.close();
    }
}


