package assignmnet9;
import java.util.*;

class contact{
	String num ;
	String name;
	contact(String num , String name){
		this.num = num;
		this.name= name;
	}
}

class operation{
	Scanner sc = new Scanner(System.in);
	int size;
	int capacity;
	contact[] hashtable;
	String num;
	String name;
	
	operation(int capacity){
		this.capacity = capacity;
		hashtable = new contact[capacity];
		for(int i=0;i<capacity;i++){
			hashtable[i]=null;
		}
	}
	
	int h1(String phone) {
		int idx  = Integer.parseInt(phone)%capacity;
		return idx;
	}
	
	int h2(String phone) {
		int idx = 7 -(Integer.parseInt(phone) % 7);
		return idx;
	}
	
	void insert() {
		System.out.println("How many record do you want to insert? ");
		int n = sc.nextInt();
		for(int i=0;i<n;i++) {
			System.out.print("Enter Phone number: ");
			num = sc.next();
			sc.nextLine();
			System.out.print("Enter Name: ");
			name = sc.nextLine();
			
			contact c = new contact(num,name);
			
			//now allocate it space
			int l=0;
			while(l<capacity) {
				int newIdx = (h1(num)+l*h2(num))%capacity;
				if(hashtable[newIdx]==null ) {
					hashtable[newIdx]=c;
					break;
				}
			l++;
			}
			
			if(l==capacity) {
				System.out.println("Hashtable is full..");
			}
		}
	}
	
	void search() {
		System.out.println("Enter the Number which you want to search ?");
		String num = sc.next();
		int l=0;
		boolean found = false;
		while(l<capacity) {
			int newIdx = (h1(num)+l*h2(num))%capacity;
			if(hashtable[newIdx]!=null && hashtable[newIdx].num.equals(num) ) {
				System.out.println("Contact found");
				found = true;
				System.out.println(hashtable[newIdx].num+" : "+hashtable[newIdx].name);
				return;
			}
			 if(hashtable[newIdx]==null) {
				System.out.println("No record Available");
				return;
			}
			l++;
		}
		if(found==false) {
			System.out.println("No contact found !!");
			return;
		}
	}
	
	void delete() {
		System.out.println("Enter the Number which you want to delete ?");
		String num = sc.next();
		int l=0;
		boolean found = false;
		while(l<capacity) {
			int newIdx = (h1(num)+l*h2(num))%capacity;
			if(hashtable[newIdx]!=null && hashtable[newIdx].num.equals(num) ) {
				System.out.println("Contact Successfully deleted");
				found = true;
				hashtable[newIdx] = null;
				return;
			}
			if(hashtable[newIdx]==null) {
				System.out.println("No record available...");
				return;
			}
			l++;
		}
		if(found==false) {
			System.out.println("No contact found !!");
			return;
		}
	}
	void display() {
		int i=0;
		for(contact c : hashtable) {
			if(c!=null) {
				System.out.println(i+"->"+c.num+" : "+c.name);
			}else {
				System.out.println(i+"->"+" - "+" : "+" - ");
			}
			i++;
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Create Hash table....");
		System.out.println("Size you want to you hash table..");
		int capacity = sc.nextInt();
		operation obj = new operation(capacity);
		int d =1;
		do {
		System.out.println("======== Menu ======");
		System.out.println("1.Insert Record");
		System.out.println("2.Display Record");
		System.out.println("3.Search Record");
		System.out.println("4.Delete record");
		int choice = sc.nextInt();
		switch(choice) {
		case 1->{obj.insert();}
		case 2->{obj.display();}
		case 3->{obj.search();}
		case 4->{obj.delete();}
		}
		System.out.println("COntinue? yes-1/0-no");
		d = sc.nextInt();
		}while(d!=0);
	}
}
