package job;
import java.util.*;
import java.util.PriorityQueue; 

//User defined data-type for ArrayList
class Job {
    String id;
    int deadline;
    float profit;
    
    Job(String id, int deadline, float profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSchedule { 

    public static void schedule(ArrayList<Job> obj) {
        // Step -1 Sort in ASCE for deadline
        obj.sort((a, b) -> a.deadline - b.deadline);
        
        // Step-2 Creating max-Heap Priority Queue
        PriorityQueue<Job> maxHeap = new PriorityQueue<>((a, b) -> Float.compare(b.profit, a.profit));
        ArrayList<Job> result = new ArrayList<>();
        
        // Step-3 Iterate Backwards towards job deadline
        for (int i = obj.size() - 1; i >= 0; i--) {
            int slots;
            if (i == 0) {
                slots = obj.get(i).deadline;
            } else {
                slots = obj.get(i).deadline - obj.get(i - 1).deadline;
            }

            // push element into heap
            maxHeap.add(obj.get(i));
            
            // fill available slots till heap becomes empty
            while (slots > 0 && !maxHeap.isEmpty()) {
                result.add(maxHeap.poll());
                slots--;
            }
        }
        
        // Step-4 Arranging the result
        result.sort((a, b) -> a.deadline - b.deadline);
        
        // Step-5 printing down results
        System.out.println();
        float maxProfit = 0;
        for (Job jobs : result) {
            System.out.print(jobs.id + " ");
            maxProfit = maxProfit + jobs.profit;
        }
        System.out.println("Maximum Profit : " + maxProfit);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Job> obj = new ArrayList<>();
        System.out.println("\n ~~~~~~ Welcome to Job Scheduler ~~~~~~~~ \n");
        
        // Accepting input dynamically
        int n = 0;
        while (true) {
            System.out.println("How many Job details do you want to add in the list?");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (n > 0 && n < 30) {
                    break;
                } else {
                    System.out.println("Enter Input in range (1-30) ONLY : ");
                }
            } else {
                System.out.println("Enter valid Input !! Try again..");
                sc.next(); // Clear invalid input
            }
        }

        for (int i = 0; i < n; i++) {
            // Accepting ID
            String id;
            while (true) {
                System.out.print("Enter Id for your Job : ");
                id = sc.next();
                if (!id.isEmpty() && id.length() < 10) {
                    break;
                } else {
                    System.out.println("Please Enter correct Length (between 1-10) : ");
                }
            }
            
            // Accepting deadline
            int deadline;
            while (true) {
                System.out.print("Enter deadline for " + id + " : ");
                if (sc.hasNextInt()) {
                    deadline = sc.nextInt();
                    if (deadline > 0 && deadline < 8) {
                        break;
                    } else {
                        System.out.println("Enter Input in range (1-8) ONLY : ");
                    }
                } else {
                    System.out.println("Enter valid Input !! Try again..");
                    sc.next(); // Clear invalid input
                }
            }
            
            // Accepting Profit
            float profit;
            while (true) {
                System.out.print("Enter Profit gained for " + id + " : ");
                if (sc.hasNextFloat()) {
                    profit = sc.nextFloat();
                    if (profit > 0 && profit < 100000) {
                        break;
                    } else {
                        System.out.println("Enter valid Profit (0-100000)..!!");
                    }
                } else {
                    System.out.println("Invalid Input..");
                    sc.next(); // Clear invalid input
                }
            }
            sc.nextLine(); // Consume newline
            
            // Adding new Job to the list
            obj.add(new Job(id, deadline, profit));
        }
        
        schedule(obj);
        sc.close();
        System.out.println("Program Successfully Executed .!!");
    }
}