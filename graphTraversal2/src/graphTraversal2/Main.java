package graphTraversal2;
import java.util.*;

class edge{
	int des;
	int weight;
	edge(int des,int weight){
		this.des = des;
		this.weight = weight;
	}
}

class graph{
	int v;
	ArrayList<ArrayList<edge>> adj;
	
	// initializing default null ArrayList 
	graph(int v){
		this.v = v;
		adj = new ArrayList<>();
		for(int i=0;i<v+1;i++) {
			adj.add(new ArrayList<>());
		}
	}
	
	//Method to add edge in this null ArrayList
	void addEdge (int src,int des , int weight) {
		adj.get(src).add(new edge(des,weight));
		adj.get(des).add(new edge(src,weight)); // Since graph is  undirected
	}
	
	//Method for displaying Adjacency List
	void display() {
		for(int i=1;i<v+1;i++) {
			System.out.print("Station "+i+" : ");
			for(edge e : adj.get(i)) {
				System.out.print("-> ( "+e.des+","+e.weight+" ) ");
			}
			System.out.println();
		}
	}
	
	//Method for recurssive call of dfs
	void dfsRecurssive(int vertex,boolean[] visited) {
		visited[vertex] = true;
		System.out.print(vertex + " ");
		for(edge i : adj.get(vertex)) {
			if(!visited[i.des]) {
				dfsRecurssive(i.des,visited);
			}
		}
	}
	
	//Method for DFS
	void dfs(int vertex) {
		boolean[] visited = new boolean[v+1];
        dfsRecurssive(vertex,visited);
	}
	
	// MST Using Prims Algorithm
	void mstPrims() {
		boolean[] visited = new boolean[v+1];
		PriorityQueue<edge> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.weight));
		//adding first null pair
		pq.add(new edge(1,0));
		int totalWeight =0;
		int  vertexCnt =0;
		
		while(!pq.isEmpty() && vertexCnt<v) {
			edge curr = pq.poll();
			int vertex = curr.des;
			if(visited[vertex]) {
				continue;
			}else {
				visited[vertex]=true;
				totalWeight = totalWeight +  curr.weight;
				vertexCnt++;
			}
			
			for(edge i : adj.get(vertex)) {
				if(!visited[i.des]) {
					pq.add(new edge(i.des,i.weight));
				}
			}
		}
		System.out.println("Minimum Spanning tree weight by Prims Algorithm is :"+ totalWeight);
	}
}
public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nCreate Graph before performing operations\n");
		// creating Graph
		int v;
		while(true) {
			System.out.print("Enter Number of Stations (vertices): ");
			if(sc.hasNextInt()) {
				v = sc.nextInt();
				sc.nextLine();
				if(v>0 && v<=25) {
					break;
				}else {
					System.out.println("Please Enter valid Input in Range (1-25) ");
				}
			}else {
				System.out.println("Enter Vaalid Numeri Input..");
				sc.next();
			}
		}
		
		graph g = new graph(v);		
		
		// Creating edges
		int k ;
		while(true) {
			System.out.print("How many edges do you want to add ? ");
			if(sc.hasNextInt()) {
				k = sc.nextInt();
				if(k>=v-1&& k<=(v*(v-1))/2) {
					break;
				}else {
					System.out.println("Invlid Input ....Edge must be between ("+(v-1)+"-"+(v*(v-1))/2+")... Try again..");
				}
			}else {
				System.out.println("Invlis Input.. Try again..");
				sc.next();
			}
		}
		
		for(int i=0;i<k;i++) {
			//Accepting start vertex of edge
			int src;
			while(true) {
				System.out.print("Enter start vertex number: ");
				if(sc.hasNextInt()) {
					src = sc.nextInt();
					sc.nextLine();
					if(src>0 && src<=v) {
						break;
					}else {
						System.out.println("Enter valid Station number");
					}
				}else {
					System.out.println("Enter valid Input..");
					sc.next();
				}
			}
			
			//Accepting ending vertex of edge
			int des;
			while(true) {
				System.out.print("Enter ending vertex number: ");
				if(sc.hasNextInt()) {
					des = sc.nextInt();
					sc.nextLine();
					if(des>0 && des<=v) {
						break;
					}else {
						System.out.println("Enter valid Station number");
					}
				}else {
					System.out.println("Enter valid Input..");
					sc.next();
				}
			}
			
			//Accepting weight of the edge
			int weight;
			while(true) {
				System.out.print("Enter bandwidth strength (weight) : ");
				if(sc.hasNextInt()) {
					weight = sc.nextInt();
					sc.nextLine();
					if(weight>0 && weight<=100000) {
						break;
					}else {
						System.out.println("Enter valid strength (1-100000). ");
					}
				}else {
					System.out.println("Enter valid Input..");
					sc.next();
				}
			}
			
			if(src==des) {
				System.out.println("Invaid input.. Please try again");
				i--;
				continue;
			}
			else {
				g.addEdge(src, des, weight);
			}
		}
		
		System.out.println("Edges Added Successfully..!!");	
		
		int x=1;
		do {
		System.out.println("\n------ MENU ------");
		System.out.println("1. Display Graph");
		System.out.println("2. Depth First Search of the graph");
		System.out.println("3. MST to find  Maximum Cost "); // By Prims Algorithm
		
		int choice;
		while(true) {
			System.out.print("Which option do you want to choose ? ");
			if(sc.hasNextInt()) {
				choice = sc.nextInt();
				sc.nextLine();
				if(choice>0 && choice<4) {
					break;
				}else {
					System.out.println("Enter in range (1-3): ");
				}
			}else {
				System.out.println("Invalid Input ..");
				sc.next();
			}
		}
		switch(choice) {
		case 1 -> { // Displaying graph
				g.display();
			}
		case 2 -> {//DFS of the graph
			System.out.print("\nEnter the starting station for DFS: ");
	        int startNode;
	        while (true) {
	            if (sc.hasNextInt()) {
	                startNode= sc.nextInt();
	                if (startNode > 0 && startNode <= v) {
	                    break;
	                } else {
	                    System.out.println("Invalid station. Enter a number between 1 and " + v);
	                }
	            } else {
	                System.out.println("Invalid input.");
	                sc.next();
	            }
	        }
	        System.out.println("\nDepth First Search of the Graph is -");
			g.dfs(startNode);
		}
		
		case 3 -> {// Minimum  Spanning tree by Prims Algorithm
			g.mstPrims();
		}
	}
		System.out.println();
		while(true) {
			System.out.print("Do you want to continue?");
			if(sc.hasNextInt()) {
				x = sc.nextInt();
				sc.nextLine();
				if(x>0 && x<=1) {
					break;
				}else {
					System.out.println("PLease Choose Correct option");
				}
			}else {
				System.out.println("Invalid Input Please try again..");	
				}
		}
	}while(x!=0);
		System.out.println("Program Executed Sucessfully !!");
  }
}
