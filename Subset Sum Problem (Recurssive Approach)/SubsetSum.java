// Solving Using Recurssive And Backtracking Approach
import java.util.*;
class SubsetSum {

    static void findSubset(int idx, int sum, int[] arr,ArrayList<Integer> pair, Set<ArrayList<Integer>> Subset, int n){
        
        // Base case , When sum equals Zero Pair is found
        if(sum ==0){
            Subset.add(new ArrayList<>(pair));
            return;
        }

        //Out of Bound case/ Base case .. When Sum becomes negetive
        if(idx>=n || sum<0){
            return;
        }

        // Including current element
        pair.add(arr[idx]);
        findSubset(idx+1,sum-arr[idx],arr,pair,Subset,n);
        pair.remove(pair.size()-1); // Backtraking step
        //Excluding Current Element
        findSubset(idx+1,sum,arr,pair,Subset,n);
    }

    //Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nFinding Subset with given Sum using Recurssion and Backtracking Approach");
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

        Set<ArrayList<Integer>> subset = new HashSet<>();
        ArrayList<Integer> pair = new ArrayList<>();
        findSubset(0,sum,arr,pair,subset,n);
        if(subset.isEmpty()){
            System.out.println("No Subset Found with the given Sum.");
        }else{
            System.out.println("Subsets with the given sum " + sum + ":");
            for(ArrayList<Integer> s : subset){
                System.out.print(s + " ");
            }
        }

    System.out.println("\nDo you want to continue ? (1-Yes/0-No) : ");
        while(true){
            if(sc.hasNextInt()){
                m = sc.nextInt();
                if(m==0 || m==1){
                    break;
                }else{
                    System.out.println("Please enter 1 for Yes or 0 for No:");
                }
            }else{
                System.out.println("Invalid Input. Enter Numeric Input..\n Please Try again..");
                sc.next(); // Clear the invalid input
            }
        }
        System.out.println();
    }while(m!=0);
    }
}
