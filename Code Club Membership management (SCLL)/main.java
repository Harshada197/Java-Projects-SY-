import java.util.*;

class Member {
    int member_id;
    String name;
    String address;
    String position;
    Member link;
    
    Member(int id, String name, String address, String position) {
        this.member_id = id;
        this.name = name;
        this.address = address;
        this.position = position;
        this.link = null;
    }
}

class CodeClub {
    private Member head;
    private Member tail;  // Maintain tail pointer for efficient operations
    private Scanner sc = new Scanner(System.in);
    
    // Input validation and node creation
    private Member createMember() {
        int member_id;
        String name, address, position;
        
        // Member ID validation
        while(true) {
            try {
                System.out.print("Enter Member Id: ");
                member_id = sc.nextInt();
                sc.nextLine();
                if (isDuplicate(member_id)) {
                    System.out.println("Duplicate ID found. Please enter a different ID.");
                } else {
                    break;
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid Input! Try again");
                sc.nextLine();
            }
        }
        
        // Name validation
        while(true) {
            System.out.print("Enter Name: ");
            name = sc.nextLine();
            if(!name.trim().isEmpty() && name.length() < 40 && name.matches("^[^\\d]+$")) {
                break;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
        
        // Address validation
        while(true) {
            System.out.print("Enter Address: ");
            address = sc.nextLine();
            if(!address.trim().isEmpty() && address.length() <= 50) {
                break;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
        
        // Position validation
        while(true) {
            System.out.print("Enter Position: ");
            position = sc.nextLine();
            if(!position.trim().isEmpty() && position.length() <= 50) {
                break;
            } else {
                System.out.println("Invalid Input! Please try again.");
            }
        }
        
        return new Member(member_id, name, address, position);
    }
    
    // Check for duplicate member_id
    boolean isDuplicate(int id) {
        if (head == null) return false;
        
        Member current = head;
        do {
            if (current.member_id == id) return true;
            current = current.link;
        } while (current != head);
        
        return false;
    }
    
    // Add multiple members
    void accept() {
        System.out.print("How many members do you want to add? ");
        int n;
        
        while (true) {
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine();
                if (n <= 0) {
                    System.out.println("Number must be positive. Try again.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
            }
        }
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nDetails for member " + (i+1) + ":");
            insertAtBeginning();
        }
    }
    
    // Insert at beginning
    void insertAtBeginning() {
        Member newMember = createMember();
        if (head == null) {
            head = newMember;
            tail = newMember;
            newMember.link = head;  // Circular reference
        } else {
            newMember.link = head;
            head = newMember;
            tail.link = head;  // Maintain circularity ///////
        }
        System.out.println("Member added successfully at beginning!");
    }
    
    // Insert at end
    void insertAtEnd() {
        Member newMember = createMember();
        
        if (head == null) {
            head = newMember;
            tail = newMember;
            newMember.link = head;
        } else {
            tail.link = newMember;
            newMember.link = head;
            tail = newMember;
        }
        System.out.println("Member added successfully at end!");
    }
    
    // Display all members
    void display() {
        if (head == null) {
            System.out.println("No members in the list.");
            return;
        }
        
        System.out.printf("%-10s %-20s %-30s %-20s\n", "ID", "Name", "Address", "Position");
        System.out.println("----------------------------------------------------------------------------");
        
        Member current = head;
        do {
            System.out.printf("%-10d %-20s %-30s %-20s\n", 
                current.member_id, current.name, current.address, current.position);
            current = current.link;
        } while (current != head);
    }
    
    // Get length of list
    private int length() {
        if (head == null) return 0;
        
        int count = 0;
        Member current = head;
        do {
            count++;
            current = current.link;
        } while (current != head);
        
        return count;
    }
    
    // Insert at specific position
    void insertAtPosition() {
        System.out.print("Enter position to insert at: ");
        int pos = sc.nextInt();
        sc.nextLine();
        
        int len = length();
        if (pos < 1 || pos > len + 1) {
            System.out.println("Invalid position. Valid positions are 1 to " + (len + 1));
            return;
        }
        
        if (pos == 1) {
            insertAtBeginning();
            return;
        }
        
        if (pos == len + 1) {
            insertAtEnd();
            return;
        }
        
        Member newMember = createMember();
        Member current = head;
        for (int i = 1; i < pos - 1; i++) {
            current = current.link;
        }
        
        newMember.link = current.link;
        current.link = newMember;
        System.out.println("Member added successfully at position " + pos + "!");
    }
    
    // Insert at middle
    void insertAtMiddle() {
        int len = length();
        if (len == 0) {
            insertAtBeginning();
            return;
        }
        
        int middle = len / 2;
        if (len % 2 != 0) middle++;
        
        System.out.println("Inserting at middle position: " + middle);
        insertAtPosition(middle);
    }
    
    // Helper method to insert at specific position
    private void insertAtPosition(int pos) {
        if (pos == 1) {
            insertAtBeginning();
            return;
        }
        
        Member newMember = createMember();
        Member current = head;
        for (int i = 1; i < pos - 1; i++) {
            current = current.link;
        }
        
        newMember.link = current.link;
        current.link = newMember;
        
        if (current == tail) {
            tail = newMember;
        }
    }
    
    // Delete from beginning
    void deleteFirst() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        
        if (head == tail) {  // Only one node
            head = null;
            tail = null;
        } else {
            head = head.link;
            tail.link = head;  // Maintain circularity
        }
        System.out.println("First member deleted successfully!");
    }
    
    // Delete from end
    void deleteLast() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        
        if (head == tail) {  // Only one node
            head = null;
            tail = null;
        } else {
            Member current = head;
            while (current.link != tail) {
                current = current.link;
            }
            current.link = head;
            tail = current;
        }
        System.out.println("Last member deleted successfully!");
    }
    
    // Delete from specific position
    void deleteAtPosition() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        
        System.out.print("Enter position to delete: ");
        int pos = sc.nextInt();
        sc.nextLine();
        
        int len = length();
        if (pos < 1 || pos > len) {
            System.out.println("Invalid position. Valid positions are 1 to " + len);
            return;
        }
        
        if (pos == 1) {
            deleteFirst();
            return;
        }
        
        if (pos == len) {
            deleteLast();
            return;
        }
        
        Member current = head;
        for (int i = 1; i < pos - 1; i++) {
            current = current.link;
        }
        
        current.link = current.link.link;
        System.out.println("Member at position " + pos + " deleted successfully!");
    }
    
    // Delete from middle
    void deleteMiddle() {
        int len = length();
        if (len == 0) {
            System.out.println("List is empty!");
            return;
        }
        
        int middle = len / 2;
        if (len % 2 == 0) middle++;
        
        System.out.println("Deleting from middle position: " + middle);
        deleteAtPosition(middle);
    }
    
    // Helper method to delete at specific position
    private void deleteAtPosition(int pos) {
        if (pos == 1) {
            deleteFirst();
            return;
        }
        
        if (pos == length()) {
            deleteLast();
            return;
        }
        
        Member current = head;
        for (int i = 1; i < pos - 1; i++) {
            current = current.link;
        }
        
        current.link = current.link.link;
    }
    
    // Update member details
    void updateDetails() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        
        System.out.print("Enter Member ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        Member current = head;
        boolean found = false;
        
        do {
            if (current.member_id == id) {
                found = true;
                break;
            }
            current = current.link;
        } while (current != head);
        
        if (!found) {
            System.out.println("Member with ID " + id + " not found.");
            return;
        }
        
        System.out.println("Which detail to update?\n1. Name\n2. Address\n3. Position");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                current.name = sc.nextLine();
                System.out.println("Name updated successfully!");
                break;
            case 2:
                System.out.print("Enter new address: ");
                current.address = sc.nextLine();
                System.out.println("Address updated successfully!");
                break;
            case 3:
                System.out.print("Enter new position: ");
                current.position = sc.nextLine();
                System.out.println("Position updated successfully!");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CodeClub club = new CodeClub();
        int choice;
        
        System.out.println("\n~ ~ ~ WELCOME TO CODE CLUB MEMBERSHIP MANAGER ~ ~ ~");
        
        do {
            System.out.println("\n----------- MENU -----------");
            System.out.println("1. Create list of members");
            System.out.println("2. Display all members");
            System.out.println("3. Insert new member");
            System.out.println("4. Delete member");
            System.out.println("5. Update member details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    club.accept();
                    break;
                case 2:
                    club.display();
                    break;
                case 3:
                    System.out.println("Where to insert?\n1. Beginning\n2. End\n3. Specific position\n4. Middle");
                    System.out.print("Enter choice: ");
                    int insertChoice = sc.nextInt();
                    sc.nextLine();
                    
                    switch (insertChoice) {
                        case 1:
                            club.insertAtBeginning();
                            break;
                        case 2:
                            club.insertAtEnd();
                            break;
                        case 3:
                            club.insertAtPosition();
                            break;
                        case 4:
                            club.insertAtMiddle();
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                    break;
                case 4:
                    System.out.println("Where to delete from?\n1. Beginning\n2. End\n3. Specific position\n4. Middle");
                    System.out.print("Enter choice: ");
                    int deleteChoice = sc.nextInt();
                    sc.nextLine();
                    
                    switch (deleteChoice) {
                        case 1:
                            club.deleteFirst();
                            break;
                        case 2:
                            club.deleteLast();
                            break;
                        case 3:
                            club.deleteAtPosition();
                            break;
                        case 4:
                            club.deleteMiddle();
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                    break;
                case 5:
                    club.updateDetails();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
        
        sc.close();
    }
}