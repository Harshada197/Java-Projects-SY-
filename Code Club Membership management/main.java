package n;
import java.util.*;
class member //create a class
{
	int member_id;
	String name;
	String address;
	String position; //position could be member,secretary/president
	member link;
	
	member (int id,String name,String address,String position)
	{   this.member_id = id;
		this.name = name;
		this.address = address;
		this.position = position;
		this.link = null;
	}
}
class Code_club //create class list
{   int member_id;
	String name;
	String address;
	String position;
	member head;
	Scanner sc=new Scanner (System.in);
	
	// inserting input in node with validations
	void create() {
	    // Input validation for member_id
            while(true) {
                try {
                    System.out.print("Enter Member Id : ");
                    member_id = sc.nextInt();
                    sc.nextLine(); // shifting to next line to avoid scanner buffer handeling errors
                    boolean found = isDuplicate(member_id);
                    if (found==true) {
                        System.out.println("Duplicate ID found. Please enter a different ID.");
                    } else {
                        break;
                    }
                } catch(InputMismatchException e) {
                    System.out.println("Invalid Input !! Try again");
                    sc.nextLine(); // clearing invalid input to accept next
                }
            }
            // validaion for accepting name 
            while(true){
                System.out.print("Enter Name : ");
                name = sc.nextLine();
                if(!name.trim().isEmpty() && name.length()<40 && name.matches("^[^\\d]+$")){
                    break;
                }
                else{
                    System.out.println("Envalid Input.. Please try again later..!!");
                }
            }
            // validation for member_address
            while(true){
                System.out.print("Enter Address :");
                address = sc.nextLine();
                if(!address.trim().isEmpty() && address.length()<=50 && address.matches("^[^\\d]+$")){
                    break;
                } 
                else{
                    System.out.println("Invalid OutPut ... Please try again later !!!");
                }
            }
			//validaton for accepting position of member 
            while(true){
                System.out.print("Enter Position : ");
                position = sc.nextLine();
                if(!position.trim().isEmpty() && position.length()<=50 && position.matches("^[^\\d]+$")){
                    break;
                } 
                else{
                    System.out.println("Invalid OutPut ... Please try again later !!!");
                }
            }
		}
    // Check for duplicate member_id    
    boolean isDuplicate(int id) {
      member current = head;
      while (current != null) {
         if (current.member_id == id) {
            return true; // Duplicate found
         }
      current = current.link;
      }
      return false; // No duplicate found
    }
    // creating node at end
    void accept(){
        System.out.println("How Many members do you want to add ?");
		int n;
        int maxAllowed = 80;
        // input validation for number of members
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
            }
        }
	for(int i=0;i<n;i++) {
		System.out.println("\nDetails for "+(i+1)+" member :");
        create();
        member newMember = new member(member_id,name,address,position);
		newMember.link = head;
		head = newMember;
       }
    }  
	//Displaying created LinkList
	void display(){
        if(head == null){
            System.out.println("No Record Entered ");
        }
		member temp = head;
		System.out.printf("%-20s %-20s %-20s %-20s\n", "ID", "Name", "Address", "Position");
		System.out.println("---------------------------------------------------------------");
		while(temp!=null) {
			System.out.printf("%-20d %-20s %-20s %-20s\n",temp.member_id, temp.name, temp.address, temp.position);
			temp = temp.link;
		}
	}
	// Inserting Node Data in the end
	void InsertEnd(){
		create();
		member newNode = new member(member_id,name,address,position);
		
		if(head==null) {
			head = newNode;
			return;
		}
		member currNode = head;
		while(currNode.link!=null) {
			currNode = currNode.link;
		}
		currNode.link = newNode;
		System.out.println("Details Successfully added ..");
	}
	//Inserting Detail at nth place;
	void insert_between(){
		System.out.print("Enter the position at which you want to place you details..");
		int m; // position of node to be placed
		while (true) {
            if (sc.hasNextInt()) {
                m = sc.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
            }
        }
        create();
		member newNode = new member(member_id,name,address,position);
		
		int cnt =0;
		member currNode = head;
		if(currNode == null) {
			System.out.println("Error out of bound .. Try again");
			return;
		}
		while(currNode.link!=null && cnt < m-2) {
			currNode = currNode.link;
			cnt++;
		}
		newNode.link = currNode.link;
		currNode.link = newNode;
		System.out.println("Details Successfully added ..");
	}
	// Inserting detail in middle
	void insert_middle() {
		member dummy = new member(-1,null,null,null);
		dummy.link= head;
		member slow = dummy;
		member fast = dummy;
		while( fast!=null && fast.link!=null) {
			slow = slow.link;
			fast = fast.link.link;
		}
		create();
		member newNode = new member(member_id,name,address,position);
		newNode.link = slow.link;
		slow.link = newNode;
	}
    // method to delete member from first
    void deleteFirst(){
        head = head.link;
        System.out.println("Member Deleted Successfully..!!");
    }
    // method to delete member from last
    void deleteLast(){
        member curr = head;
        while(curr.link.link!=null){
            curr = curr.link;
        }
        curr.link = null;
    }
    // method to delete member from nth position
    void deleteNth(){
        System.out.print("Enter the position of Member to delete data. ");
        int position;
        while(true){
            if(sc.hasNextInt()){
                position = sc.nextInt();
                break;
            }else{
                System.out.println("Invalid Input.. Try again..");
            }
        }
        member curr = head;
        while(curr!=null && position>2){
            curr = curr.link;
            position--;
        }
        curr.link = curr.link.link;
        System.out.println("Member Deleted Successfully..!!");

    }
    // method to delete member from middle
    void deleteMiddle(){
        member slow =head;
        member fast = head;
        while(slow.link!=null && fast.link!=null && slow!=null && fast!=null){
            slow = slow.link;
            fast = fast.link.link;
        }
        slow = slow.link;
        System.out.println("Member Deleted Successfully..!!");

    }
    // method to update details
    void updateDetails() {
    System.out.print("Enter ID of Member whose data you want to update: ");
    int new_id = -1;

    while (true) {
        if (sc.hasNextInt()) {
            new_id = sc.nextInt();
            sc.nextLine(); // consume leftover newline
            break;
        } else {
            System.out.println("Invalid Input.. Try again..!!");
            sc.next(); // consume invalid token
        }
    }

    member curr = head;
    boolean found = false;

    while (curr != null) {
        if (curr.member_id == new_id) {
            found = true;
            System.out.println("Which Detail Do you want to update?\n1) Name    2) Address    3) Position");
            int m;

            while (true) {
                if (sc.hasNextInt()) {
                    m = sc.nextInt();
                    sc.nextLine(); // consume newline
                    if (m >= 1 && m <= 3) break;
                    else System.out.println("Invalid choice..!!");
                } else {
                    System.out.println("Invalid Input.. Try again");
                    sc.next();
                }
            }

            switch (m) {
                case 1:
                    System.out.print("Enter New Updated Name: ");
                    while (true) {
                        String new_name = sc.nextLine();
                        if (!new_name.isEmpty() && new_name.length() <= 30 && new_name.matches("^[^\\d]+$")) {
                            curr.name = new_name;
                            System.out.println("Name of Member Successfully Updated !!");
                            break;
                        } else {
                            System.out.println("Invalid Input .. Please Try again..");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter New Updated Address: ");
                    while (true) {
                        String new_add = sc.nextLine();
                        if (!new_add.isEmpty() && new_add.length() <= 30 && new_add.matches("^[^\\d]+$")) {
                            curr.address = new_add;
                            System.out.println("Address of Member Successfully Updated !!");
                            break;
                        } else {
                            System.out.println("Invalid Input .. Please Try again..");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter New Updated Position: ");
                    while (true) {
                        String new_pos = sc.nextLine();
                        if (!new_pos.isEmpty() && new_pos.length() <= 30 && new_pos.matches("^[^\\d]+$")) {
                            curr.position = new_pos;
                            System.out.println("Position of Member Successfully Updated !!");
                            break;
                        } else {
                            System.out.println("Invalid Input .. Please Try again..");
                        }
                    }
                    break;
            }
            break; // exit loop after update
        }
        curr = curr.link;
    }

    if (!found) {
        System.out.println("Member with ID " + new_id + " not found.");
    }
    }
}
// Main Method
public class main{
	public static void main(String[] args){
        System.out.println("\n ~ ~ ~ ~ WELCOME TO CODE-CLUB MEMBERSHIP MANAGER ~ ~ ~ ~");
		Scanner sc = new Scanner(System.in);
		Code_club obj = new Code_club();
		int k=1;
		do{ //Make Menu Driven Program
			System.out.println("\n\n-----------Menu-----------\n");
			System.out.println("1.Create List of members");
			System.out.println("2.Display List");
			System.out.println("3.Insert Data of Member");
			System.out.println("4.Delete data of Member");
			System.out.println("5.Update data of Members");
			System.out.println("Which operation do you want to perform ?");
			int choice;
			while (true) {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                System.out.println();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
            }
        }
			
			switch(choice){
				case 1 -> // Creating LinkList (Inserting element in start)
							obj.accept();
				case 2 -> //Displaying current LinkList
							obj.display();
				case 3 -> {// Inserting Details at End
							System.out.println("Where Do you want to insert new member? \n1)Insert At beginning \n2)Insert At End \n3)Insert At Specific Position \n4)Insert At Middle Position");
							int h;
							while(true) {
								if(sc.hasNextInt()) {
									h=sc.nextInt();
									if(h>0 && h<=4) {
										break;
									}
									else {
										System.out.println("Please select Valid Option.");
									}
								}else {
									System.out.println("Please Inter valid Input..");
								}
							}
							switch(h) {
							case 1 -> {// Inserting data at beginning
									obj.accept();
									System.out.println("Data Successfully Inserted!!");
									}
							case 2 -> {// Inserting data at End
									obj.InsertEnd();
									System.out.println("Data Successfully Inserted!!");
									}
							case 3 -> {// Inserting data at nth position
									obj.insert_between();
									System.out.println("Data Successfully Inserted!!");
					  		  		}
							case 4 -> {// Inserting data at middle position
									obj.insert_middle();
									System.out.println("Data Successfully Inserted!!");		
									}
							}
					}
					
			    case 4 -> {// Deletion of Details of Member
							System.out.println("Which Member Do you wan to delete from the Record? \n1)Delete At beginning \n2)Delete At End \n3)Delete At Specific Position \n4)Delete At Middle Position");
							int h;
							while(true) {
								if(sc.hasNextInt()) {
									h=sc.nextInt();
									if(h>0 && h<=4) {
										break;
									}
									else {
										System.out.println("Please select Valid Option.");
									}
								}else {
									System.out.println("Please Inter valid Input..");
								}
							}
							switch(h) {
							case 1 -> {// Deleting data at beginning
								obj.deleteFirst();
								System.out.println("Data Successfully Deleted!!");
								}
							case 2 -> {// Deleting data at End
								obj.deleteLast();
								System.out.println("Data Successfully Deleted!!");
								}
							case 3 -> {// Deleting data at nth position
								obj.deleteNth();
								System.out.println("Data Successfully Deleted!!");
								}
							case 4 -> {// Inserting data at middle position
								obj.deleteMiddle();
								System.out.println("Data Successfully Deleted!!");		
								}
							}
					}
			   
                case 5 -> // Update details of member provided by id
                    	obj.updateDetails();
                 }
			System.out.print("Do you want to continue ? yes/No (1/0) ");
			while(true) {
				if(sc.hasNextInt()){
                    k = sc.nextInt();
                    if(k==1){
                        break;
                    }
                    else if(k==0){
                        break;
                    }
                else{
                    System.out.println("Enter valid Input.. \n 1-> continue  0-> to stop");
                    }
              }
		   }
		}while(k!=0);
        sc.close();
        System.out.println("Program Executed successfully !!!..");
	}
}