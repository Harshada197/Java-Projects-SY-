import java.util.*;
// user defined data type
class Books {
    String BookName;
    String AuthorName;
    int ISBNNo;
    String publication;
    Double price;
    Books(String BookName,String AuthorName,int ISBNNo,String publication,Double price){
        this.BookName = BookName;
        this.AuthorName = AuthorName;
        this.ISBNNo = ISBNNo;
        this.publication=publication;
        this.price=price;
    }
}
class Library {
    static Scanner sc = new Scanner(System.in);
    Books[] theBooks = new Books[50];
    int count = 0;

    // Accept book details with validation
    void Accept_Book_Details() {
        System.out.print("How many books do you want to add? ");
        int n;
        int maxAllowed = 100;

        while (true) {
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine();
                if (n > maxAllowed) {
                    System.out.println("Number is too large. Try again.");
                } else if (n <= 0) {
                    System.out.println("Number must be positive. Try again.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
            }
        }

        String BookName, AuthorName, publication;
        int ISBNNo;
        double price;

        for (int i = count; i < count + n; i++) {
            System.out.println("\nEnter details for Book " + (i + 1));

            // Book Name
            while (true) {
                System.out.print("Enter Book Name: ");
                BookName = sc.nextLine();
                if (!BookName.trim().isEmpty() && BookName.length() <= 40 && BookName.matches("^[^\\d]+$")) {
                    break;
                } else {
                    System.out.println("Invalid Input. Try again!");
                }
            }

            // Author Name
            while (true) {
                System.out.print("Enter Author Name: ");
                AuthorName = sc.nextLine();
                if (!AuthorName.trim().isEmpty() && AuthorName.length() <= 40 && AuthorName.matches("^[^\\d]+$")) {
                    break;
                } else {
                    System.out.println("Invalid Input. Try again!");
                }
            }

        // ISBN Number
        while (true) {
        System.out.print("Enter ISBN No.: ");
        if (sc.hasNextInt()) {
            ISBNNo = sc.nextInt();
            sc.nextLine(); // clear the buffer
            if (ISBNNo <= 0 || ISBNNo > 10000) {
            System.out.println("ISBN must be between 1 and 10000.");
            } else if (isDuplicateISBN(ISBNNo)==true) {
            System.out.println("This ISBN already exists. Please enter a unique ISBN.");
            } else {
                break; // valid and unique
            }
        } else {
            System.out.println("Invalid input. Enter an integer.");
            sc.nextLine(); // consume invalid input
            }
        }
          
        // Publication
        while (true) {
            System.out.print("Enter Publication: ");
            publication = sc.nextLine();
            if (!publication.trim().isEmpty() && publication.length() <= 50 && publication.matches("^[^\\d]+$")) {
                break;
            } else {
                System.out.println("Invalid Input. Try again!");
            }
        }

        // Price
        while (true) {
            try {
                System.out.print("Enter Price: ");
                price = sc.nextDouble();
                sc.nextLine();
                if (price > 0 && price <= 1000000) {
                    break;
                } else {
                    System.out.println("Price should be between 0 and 1,000,000.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a numeric value.");
                sc.nextLine();
            }
        }

            theBooks[i] = new Books(BookName, AuthorName, ISBNNo, publication, price);
        }

        count += n;
    }
    // Check for duplicate ISBN
    boolean isDuplicateISBN(int isbn) {
        for (int i = 0; i < count; i++) {
                if (theBooks[i] != null && theBooks[i].ISBNNo == isbn) {
                return true;
                }
        }
        return false;
        }

    // Display original book list
    void Display_Book_Details() {
        if (count == 0) {
            System.out.println("No record found!");
        } else {
            displayBooks(theBooks);
        }
    }

    // Display any Books[] array
    void displayBooks(Books[] array) {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", "Book-Name", "Author-Name", "ISBNNo.", "Publication", "Price");
        for (Books book : array) {
            if (book != null)
                System.out.printf("%-20s %-20s %-20d %-20s %-20.2f\n",
                        book.BookName, book.AuthorName, book.ISBNNo, book.publication, book.price);
        }
    }

    // Search by ISBN using binary search
    void search_ISBN() {
        System.out.println("Enter ISBNNo. of book that you want to Search");
        int new_No;
        while (true) {
            try {
                new_No = sc.nextInt();
                if (new_No > 0 && new_No <= 10000) {
                    break;
                } else {
                    System.out.println("ISBNNo. should be positive. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input.. Please Try again!!");
                sc.next();
            }
        }

        Books[] tempSorted = Arrays.copyOf(theBooks, count);
        Arrays.sort(tempSorted, Comparator.comparingInt(b -> b.ISBNNo));
        int left = 0, right = count - 1;
        boolean found = false;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (tempSorted[mid].ISBNNo < new_No) {
                left = mid + 1;
            } else if (tempSorted[mid].ISBNNo > new_No) {
                right = mid - 1;
            } else {
                System.out.println("Book Found:");
                displayBooks(new Books[]{tempSorted[mid]});
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No Record Found.");
        }
    }

    // Search by author name using linear search
    void search_Author() {
        System.out.println("Enter Name of Author Whose book You want to Search");
        String search_Name;
        while (true) {
            search_Name = sc.next();
            if (!search_Name.trim().isEmpty() && search_Name.length() <= 40 && search_Name.matches("^[^\\d]+$")) {
                break;
            } else {
                System.out.println("Invalid Input .. Please Try Again !!");
            }
        }

        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (search_Name.equalsIgnoreCase(theBooks[i].AuthorName)) {
                System.out.println("Book Found:");
                displayBooks(new Books[]{theBooks[i]});
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Record Not Found");
        }
    }

    // Bubble Sort
    void sort_books_bubble() {
        Books[] tempSorted = Arrays.copyOf(theBooks, count);
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (tempSorted[j].ISBNNo > tempSorted[j + 1].ISBNNo) {
                    Books temp = tempSorted[j];
                    tempSorted[j] = tempSorted[j + 1];
                    tempSorted[j + 1] = temp;
                }
            }
        }
        System.out.println("Books Sorted using Bubble Sort:");
        displayBooks(tempSorted);
    }

    // Insertion Sort
    void sort_books_insertion() {
        Books[] tempSorted = Arrays.copyOf(theBooks, count);
        for (int i = 1; i < count; i++) {
            Books key = tempSorted[i];
            int j = i - 1;
            while (j >= 0 && tempSorted[j].ISBNNo > key.ISBNNo) {
                tempSorted[j + 1] = tempSorted[j];
                j--;
            }
            tempSorted[j + 1] = key;
        }
        System.out.println("Books Sorted using Insertion Sort:");
        displayBooks(tempSorted);
    }

    // Quick Sort
    void sort_books_quick() {
        Books[] tempSorted = Arrays.copyOf(theBooks, count);
        quickSort(tempSorted, 0, count - 1);
        System.out.println("Books Sorted using Quick Sort:");
        displayBooks(tempSorted);
    }

    void quickSort(Books[] arr, int start, int end) {
        if (start < end) {
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot - 1);
            quickSort(arr, pivot + 1, end);
        }
    }

    int partition(Books[] arr, int start, int end) {
        Books pivot = arr[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (arr[j].ISBNNo <= pivot.ISBNNo) {
                i++;
                Books temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Books temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;
        return i + 1;
    }
    // Merge Sort
    // Wrapper method
        void sortAndDisplayBooks(Books[] originalArr, int count) {
        Books[] tempArr = Arrays.copyOf(originalArr, count); // copy outside
        mergeSort(tempArr, 0, count - 1); // sort the copy
        displayBooks(tempArr); // display the sorted list
    }

    // Merge sort stays the same (no internal copy)
        void mergeSort(Books[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
             }
        }

        void merge(Books[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Books[] L = new Books[n1];
        Books[] R = new Books[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].ISBNNo <= R[j].ISBNNo) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Selection Sort
    void selection_sort(){
        Books[] tempSorted = Arrays.copyOf(theBooks, count);
        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < count; j++) {
                if (tempSorted[j].ISBNNo < tempSorted[minIndex].ISBNNo) {
                    minIndex = j;
                }
            }
            Books temp = tempSorted[minIndex];
            tempSorted[minIndex] = tempSorted[i];
            tempSorted[i] = temp;
        }
        System.out.println("Books Sorted using Selection Sort:");
        displayBooks(tempSorted);
    }
    
}
// main method
public class main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Library ob = new Library();
        System.out.println("\n\n----------- WELCOME TO LIBRARAY RECORD -----------\n");
        int choice=-1;
        
        do{
        System.out.println("\n---Menu---");
        System.out.println("1. Add Book to record");
        System.out.println("2. Display Book Record List");
        System.out.println("3. Search Book by ISBNNo");
        System.out.println("4. Search Book by Author name");
        System.out.println("5. Sort Book based on ISBNNo");
        System.out.print("Which operation do you wan to perform ?");
        while(true) {
            if(sc.hasNextInt()){
                choice = sc.nextInt();
                if(choice >= 1 && choice <= 5) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please choose correct option..");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // clear the invalid input
            }
        }
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
                System.out.println("\nSorting based on ISBNNo using : ");
                System.out.println("1. Insertion Sort");
                System.out.println("2. Bubble Sort");
                System.out.println("3. Quick Sort");    
                System.out.println("4. Merge Sort");
                System.out.println("5. Selection Sort");
                System.out.print("Which sorting method do you want to use ?");
                int sort_choice ;
                while(true) {
                    if(sc.hasNextInt()){
                        sort_choice = sc.nextInt();
                        if(sort_choice >= 1 && sort_choice <= 5) {
                            break;
                        } else {
                            System.out.println("Invalid choice. Please choose correct option..");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.next(); // clear the invalid input
                    }
                }
                switch(sort_choice) {
                    case 1:// Insertion Sort
                        ob.sort_books_insertion();
                        break;
                    case 2:// Bubble Sort
                        ob.sort_books_bubble(); 
                        break;
                    case 3:// Quick Sort
                        ob.sort_books_quick();
                        break;
                    case 4:// Merge Sort
                        ob.sortAndDisplayBooks(ob.theBooks,ob.count);
                        break;
                    case 5:// Selection Sort
                        ob.selection_sort();
                        break;
                }
                
        }
    
        System.out.print("\nDo You want to continue ?  (1/0)");
        while(true){
            choice = sc.nextInt();
            if(choice == 1 || choice == 0) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 to continue or 0 to exit.");
            }
        }
        }while (choice!=0);
    System.out.println("\n\n Thank You for using Library Record System.....");
    }
}


