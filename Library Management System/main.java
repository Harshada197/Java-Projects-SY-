import java.util.*;
// user defined data type
class Books {
	String BookName;
	String AuthorName;
	int ISBNNo;
	String publication;
	int price;
	Books(String BookName,String AuthorName,int ISBNNo,String publication,int price){
		this.BookName = BookName;
		this.AuthorName = AuthorName;
		this.ISBNNo = ISBNNo;
		this.publication=publication;
		this.price=price;
	}
}
//class which contains all operations
class Library {
	static Scanner sc = new Scanner(System.in);
	Books theBooks[] = new Books[50]; // Array that stores 'book' Objects.
	int count =0;
	
	// Method for accepting input of books
	void Accept_Book_Details() {
		System.out.print("How Many books do you want to add ?");
		int n;
		while(true) {
			try {
				n=sc.nextInt();
				break;
			}catch(InputMismatchException e) {
				System.out.println("Please Enter valid Input");
				sc.next();
			}
		}
		String BookName;
		String AuthorName;
		int ISBNNo;
		String publication;
		int price;
	
		for(int i=count;i<n+count;i++) {
			while(true) {
				try {
					System.out.println();
					System.out.print("Enter Book-Name of "+(i+1) +" Book");
				    BookName = sc.next();
					System.out.print("Enter Author-Name of "+(i+1) +" Book");
					AuthorName = sc.next();
					System.out.print("Enter ISBNNo "+(i+1)+" Book");
					ISBNNo= sc.nextInt();
					System.out.print("Enter Publication of "+(i+1) +" Book");
					publication = sc.next();
					System.out.print("Enter Price of "+(i+1) +" Book");
					price = sc.nextInt();
					break;
				}catch(InputMismatchException e) {
					System.out.print("Please Enter valid Input");
					System.out.println();
					sc.next();
				}
			}
		  theBooks[i] = new Books(BookName , AuthorName , ISBNNo,publication,price);
		}
		count = count+n;
	}
	
	// method for displaying List of Books in Library
	void Display_Book_Details() {
		if(count==0) {
			System.out.println("No record found !!");
		}
		else {
			System.out.printf("%-20s %-20s %-20s %-20s %-20s", "Book-Name" , "Author-Name" , "ISBNNo." , "Publication" , "Price");
			System.out.println();
			for(int i=0;i<count;i++) {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s",theBooks[i].BookName ,theBooks[i].AuthorName ,theBooks[i].ISBNNo,theBooks[i].publication ,theBooks[i].price);
			    System.out.println();
			}
		}
	}

	//Method for Searching book Record by ISBNo. using binary search
	void search_ISBN() {
		int new_No;
		System.out.println("Enter ISBNNO. of book that you want to Search");
		while(true) {
			try {
				new_No = sc.nextInt();
				break;
			}catch(InputMismatchException e){
				System.out.println("Invalid input .. Try Again..");
				sc.next();
			}
			
		}
		
		Arrays.sort(theBooks,0,count,Comparator.comparing(Book -> Book.ISBNNo));
		
		int left =0;
		int right = count-1;
		boolean found = false;
		while(left<=right) {
			int mid = left + (right -left)/2;
			if(theBooks[mid].ISBNNo < new_No) {
				left = mid + 1;
			}
			else if(theBooks[mid].ISBNNo > new_No) {
				right = mid-1;
			}
			else {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s", "Book-Name" , "Author-Name" , "ISBNNo." , "Publication" , "Price");
				System.out.println();
				System.out.printf("%-20s %-20s %-20s %-20s %-20s",theBooks[mid].BookName ,theBooks[mid].AuthorName ,theBooks[mid].ISBNNo,theBooks[mid].publication ,theBooks[mid].price);
				found=true;
				break;
			}
		}
		if(!found) {
			System.out.println("No Record Found ");
		}
		System.out.println();
	}
	
	// Method for searching book record by author-name using linear search
	void search_Author() {
	   System.out.println("Enter Name of Author Whose book You want to Search");
	   String search_Name = sc.next();
	   boolean found =false;
	   for(int i=0;i<count;i++){
		if(search_Name.toLowerCase().equals(theBooks[i].AuthorName.toLowerCase())) {
			System.out.printf("%-20s %-20s %-20s %-20s %-20s", "Book-Name" , "Author-Name" , "ISBNNo." , "Publication" , "Price");
			System.out.println();
			System.out.printf("%-20s %-20s %-20s %-20s %-20s",theBooks[i].BookName ,theBooks[i].AuthorName ,theBooks[i].ISBNNo,theBooks[i].publication ,theBooks[i].price);
		    System.out.println();
			found= true;
			break;
		   }
	   }
	   if(!found) {
		   System.out.println("Recod Not Found");
	   }
	}

	//method for Sorting based on Insertion sort
	void sort_books_insertion() {
		for(int i=1;i<count;i++) {
			Books key = theBooks[i];
			int j = i-1;
			while(j>=0 && theBooks[j].ISBNNo > key.ISBNNo) {
				theBooks[j+1] = theBooks[j];
				j--;
			}
			theBooks[j+1] = key;
		}
		Display_Book_Details();
	}

	//method for Sorting books based on Bubble sort
	void sort_books_bubble() {
		for(int i=0;i<count-1;i++) {
			for(int j=0;j<count-i-1;j++) {
				if(theBooks[j].ISBNNo > theBooks[j+1].ISBNNo) {
					swap(j,j+1);
				}
			}
		}
		Display_Book_Details();
	}
	
	//method for Sorting books based on Quick sort
	void sort_books_quick() {
		quickSort(0,count-1);
		Display_Book_Details();
		
	}
	//Method for placing pivot element
	int partition(int start,int end) {
		int idx = start-1;
		int pivot = theBooks[count-1].ISBNNo;
		for(int j=start ; j<end ; j++) {
			if(theBooks[j].ISBNNo <= pivot) {
				idx++;
				swap(idx,j);
			}
		}
		idx++;
		swap(end,idx);
		return idx;
	}
	// method for swaping elements
	void swap(int i , int j) {
		Books temp = theBooks[i];
		theBooks[i] = theBooks[j];
		theBooks[j]= temp;	
	}
	//method for performing quick sort by recursion
	void quickSort(int start,int end) {
		if(start<end) {
			int pivot = partition(start ,end);
		    quickSort(start,pivot-1);
		    quickSort(pivot+1,end);
		}
	}
}
// main method
public class main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Library ob = new Library();
		System.out.println("----------- WELCOME TO LIBRARAY RECORD ------");
		System.out.println();
		int choice;
		
		do{
		System.out.println("---Menu---");
		System.out.println("1. Add Book to record");
		System.out.println("2. Display Book Record List");
		System.out.println("3. Search Book by ISBNNo");
		System.out.println("4. Search Book by Author name");
		System.out.println("5. Sort Book based on ISBNNo");
		System.out.print("Which operation do you wan to perform ?");
		choice = sc.nextInt();
		
		switch(choice){
			case 1://Acepting Book Details
				ob.Accept_Book_Details();
				break;
				
			case 2://Displaying Book Details
				System.out.println();
				ob.Display_Book_Details();
				break;
				
			case 3://Searching Book by ISBNNo
				System.out.println();
				ob.search_ISBN();
				break;

			case 4://Searching Book by Author name
				System.out.println();
				ob.search_Author();
				break;
				
			case 5://Sorting Book based on ISBNNo
				System.out.println();
				System.out.println("Sorting based on ISBNNo using : ");
				System.out.println("1. Insertion Sort");
				System.out.println("2. Bubble Sort");
				System.out.println("3. Quick Sort");	
				System.out.print("Which sorting method do you want to use ?");
				int sort_choice = sc.nextInt();	
				System.out.println();
				switch(sort_choice) {
					case 1:// Insertion Sort
						System.out.println("Sorting using Insertion Sort");
						ob.sort_books_insertion();
						break;
					case 2:// Bubble Sort
						System.out.println("Sorting using Bubble Sort");
						ob.sort_books_bubble();	
						break;
					case 3:// Quick Sort
						System.out.println("Sorting using Quick Sort");
						ob.sort_books_quick();
						break;
					default:
						System.out.println("Invalid choice");
				}
				
		}
	
		System.out.print("Do You want to continue ?  (1/0)");
		choice=sc.nextInt();
		System.out.println();
		}while (choice!=0);
	System.out.println("Thank You for using Library Record System");
	}
}
