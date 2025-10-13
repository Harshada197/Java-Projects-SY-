package jobSequence;
import java.util.*;

class Job {
	String id;
	int deadline ;
	double profit;
	// Constructors
	public Job(String id, int deadline, double profit){
		this.id = id;
		this.deadline = deadline;
		this.profit = profit;
	}
}
class JobSchedule{
	ArrayList<Job> list = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	
	String id;
	int deadline;
	double profit;
	
	void accept() {
	    // Enter ID
	    while (true) {
	        System.out.print("Enter Job Id : ");
	        id = sc.next();

	        if (id.isEmpty() || id.length() >= 10) {
	            System.out.println("Invalid Id. Please Try again");
	            continue;
	        }
	        if (isDuplicateId(id)) {
	            System.out.println("Id " + id + " already exists. Please try again.");
	            continue;
	        }
	        break;
	    }

	    // Enter Deadline
	    while (true) {
	        System.out.print("Enter Deadline for " + id + " job : ");
	        if (sc.hasNextInt()) {
	            deadline = sc.nextInt();
	            if (deadline > 0 && deadline < 20) {
	                break;
	            }
	            System.out.println("Enter deadline between 1–20");
	        } else {
	            System.out.println("Invalid Input.. Try again");
	            sc.next(); // clear invalid input
	        }
	    }

	    // Enter Profit
	    while (true) {
	        System.out.print("Enter Profit made for " + id + " job : ");
	        if (sc.hasNextDouble()) {
	            profit = sc.nextDouble();
	            if (profit > 0 && profit < 10000) {
	                break;
	            }
	            System.out.println("Enter profit between 0–10000");
	        } else {
	            System.out.println("Invalid Input");
	            sc.next(); // clear invalid input
	        }
	    }

	    // Add new Job
	    list.add(new Job(id, deadline, profit));
	    System.out.println("Job " + id + " added successfully!\n");
	}

	// Check duplicate ID
	boolean isDuplicateId(String id) {
	    for (Job jb : list) {
	        if (jb.id.equalsIgnoreCase(id)) {
	            return true;
	        }
	    }
	    return false;
	}

	//Displaying the list
	void display() {
		if(list.isEmpty()){
			System.out.println("\nPlease Create List to Display");
			System.out.println("Your Current List is Empty");
		}else{
			System.out.printf("\n%-20s %-20s %-20s","JOB ID" , "DEADLINE" , "PROFIT");
			System.out.println("\n------------------------------------------------");
			for(Job jb : list) {
				System.out.printf("%-20s %-20d %-20f\n",jb.id,jb.deadline,jb.profit);
			}
			System.out.println("\nList Successfully displayed");
		}
	}

	// method for scheduling the job
	void scheduleJob(){
		if(list.isEmpty()){
			System.out.println("Please Create List to Schedule");
			System.out.println("Your Current List is Empty");
		}
		else{
		//Step-1 : Sorting based on profit
		Collections.sort(list, (a, b) -> Double.compare(b.profit, a.profit));
		//Step-2 : Finding max deadLine
		int maxDeadLine =0;
		for(Job jb : list){
			if(jb.deadline>maxDeadLine){
				maxDeadLine = jb.deadline;
			}
		}
		//Step-3 : Creating result array
		String[] result = new String[maxDeadLine + 1]; // to record all the jobs
		boolean[] slot = new boolean[maxDeadLine + 1];  // to track availability of job
		double maxProfit = 0.00;
		//Step-4 : Alloting slots
		for(Job jb : list){
			for(int j=jb.deadline ; j>0 ; j--){
				if(!slot[j]){
					result[j]= jb.id;
					slot[j]=true;
					maxProfit += jb.profit;
					break;
				}
			}
		}
		//Step-5 Printing this list
		for(int i=0;i<maxDeadLine+1;i++) {
			if(slot[i]) {
				System.out.print(result[i]+" ");
			}
		}
		System.out.println("\nMaximum Profit gained after scheduling is : "+maxProfit);
		}
	}
}

class greedy{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		JobSchedule obj = new JobSchedule();
		
		System.out.println("\nWelcome to Job Scheduler\n");
		int k=1;
		do {
			System.out.println(" ---------- menu ---------");
			System.out.println("1. Create List of Job");
			System.out.println("2. Display List of Job");
			System.out.println("3. Schedule Job to maximize profit");
			System.out.print("\nWhich operation to perform ? ");
			int choice;
			while (true) {
			    if (sc.hasNextInt()) {
			        choice = sc.nextInt();
			        if (choice == 1 || choice == 2 || choice == 3) {
			            break;
			        } else {
			            System.out.println("Invalid Choice.. Try again");
			        }
			    } else {
			        System.out.println("Invalid Input.. Please enter a number (1-3)");
			        sc.next(); // clear invalid input
			    }
			}

			switch(choice) {
			case 1 -> {	// Creating ArrayList
					int n;
					while(true) {
					System.out.print("\nHow many jobs do you want to enter in the list : ");
						if(sc.hasNextInt()){
							n = sc.nextInt();
							if(n>0 && n<20) {
								break;
							}
						}else{
							System.out.println("Incorrect Count .. Please Try again");
							sc.next();
						}		
					}
					for(int i=0;i<n;i++) {
						System.out.println("\nEnter details of Job "+(i+1));
						obj.accept();
					}
					System.out.println("List Successfully created...!!!");
				}
			case 2 -> obj.display();// Displaying ArrayList

			case 3 -> obj.scheduleJob(); // Scheduling job
			}
			
			while(true) {
				System.out.println("Do you want to continue (yes-1 / No-0)");
				if(sc.hasNextInt()){
				k=sc.nextInt();
				if(k==0 || k==1) {
					break;
				}
				}else{
				System.out.println("Invalid Input.. Reenter your Choice");
				sc.next();
				}
			}
		}while(k!=0);
		System.out.println("Program Successfully Executed !!");
	}
}
