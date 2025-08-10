import java.util.*;

//class to create Node
class Song {
    int id;
    String name;
    String artist;
    Song next;
    Song prev;
    
    Song(int id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.next = null;
        this.prev = null;
    }
}

class Operations {
    Scanner sc = new Scanner(System.in);
    int id;
    String name;
    String artist;
    Song head;
    Song curr;

    //to check duplicacy of song id
    boolean isDuplicate(int curr_id) {
        Song temp = head;
        while(temp != null) {
            if(temp.id == curr_id) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    //creating single node of song
    void createSong() {
        //validation for id 
        while(true) {
            System.out.print("Enter Song Id: ");
            if(sc.hasNextInt()) {
                id = sc.nextInt();
                sc.nextLine();
                if(id <= 0 || id > 10000) {
                    System.out.println("Wrong Input. Please enter a number between 1 and 10000.");
                } else if(isDuplicate(id)) {
                    System.out.println("This Song_id already exists. Please try again with a new Song.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input for ID. Please enter a valid integer.");
                sc.next(); // clear the invalid input
            }
        }
        
        // validation for name
        while(true) {
            System.out.print("Enter Name of the Song: ");
            name = sc.nextLine();
            if(!name.trim().isEmpty() && name.length() <= 40 && name.matches("^[^\\d]+$")) {
                break;
            } else {
                System.out.println("Invalid input. Name should be non-empty, max 40 characters, and contain no digits.");
            }
        }
        
        // validation for Artist
        while(true) {
            System.out.print("Enter Name of the Artist: ");
            artist = sc.nextLine();
            if(!artist.trim().isEmpty() && artist.length() <= 40 && artist.matches("^[^\\d]+$")) {
                break;
            } else {
                System.out.println("Invalid input. Artist should be non-empty, max 40 characters, and contain no digits.");
            }
        }
    }

    void createPlaylist() {
        System.out.println("How many Songs do you want to add in the playlist?");
        int n;

        while(true) {
            if(sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine(); // consume newline
                if(n > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // clear the invalid input
            }
        }

        for(int i = 0; i < n; i++) {
            System.out.println("\nEnter details of song " + (i+1) + ":");
            createSong();
            Song newSong = new Song(id, name, artist);
            
            if(head == null) {
                head = newSong;
                curr = head; // set current to head when adding first song
            } else {
                newSong.next = head;
                head.prev = newSong;
                head = newSong;
            }
        }
        System.out.println("\nPlaylist created successfully with " + n + " songs.");
    }

    //Displaying Complete PlayList
    void displayList() {
        if(head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        Song temp = head;
        System.out.println("\nSongs in the playlist:");
        System.out.printf("%-10s %-30s %-30s%n", "Song ID", "Song Name", "Artist Name");
        System.out.println("------------------------------------------------------------------");

        while(temp != null) {
            System.out.printf("%-10d %-30s %-30s%n", temp.id, temp.name, temp.artist);
            temp = temp.next;
        }   
    }

    // Insertion operations
    void insertSong() {
        if(head == null) {
            System.out.println("Playlist is empty. Creating new playlist with this song.");
            createSong();
            head = new Song(id, name, artist);
            curr = head;
            return;
        }

        System.out.println("\nWhere do you want to insert the song?");
        System.out.println("1. At the beginning");
        System.out.println("2. At the end");
        System.out.println("3. At a specific position");
        System.out.print("Enter your choice: ");
        
        int choice;
        while(true) {
            if(sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
                if(choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // clear the invalid input
            }
        }

        createSong();
        Song newSong = new Song(id, name, artist);

        switch(choice) {
            case 1: // Insert at beginning
                newSong.next = head;
                head.prev = newSong;
                head = newSong;
                System.out.println("Song added at the beginning of the playlist.");
                break;
                
            case 2: // Insert at end
                Song temp = head;
                while(temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newSong;
                newSong.prev = temp;
                System.out.println("Song added at the end of the playlist.");
                break;
                
            case 3: // Insert at specific position
                System.out.print("Enter the position where you want to insert the song: ");
                int k;
                int len = getLength();
                while(true) {
                    if(sc.hasNextInt()) {
                        k = sc.nextInt();
                        sc.nextLine(); // consume newline
                        if(k > 0 && k <= len + 1) {
                            break;
                        } else {
                            System.out.println("Please enter a valid position between 1 and " + (len + 1) + ".");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        sc.next(); // clear the invalid input
                    }
                }
                
                if(k == 1) {
                    newSong.next = head;
                    head.prev = newSong;
                    head = newSong;
                } else {
                    Song temp2 = head;
                    for(int i = 0; i < k - 2; i++) {
                        temp2 = temp2.next;
                    }
                    newSong.next = temp2.next;
                    newSong.prev = temp2;
                    if(temp2.next != null) {
                        temp2.next.prev = newSong;
                    }
                    temp2.next = newSong;
                }
                System.out.println("Song added at position " + k + " in the playlist.");
                break;
        }
    }

    // Deletion operations
    void deleteSong() {
        if(head == null) {
            System.out.println("Playlist is empty. Nothing to delete.");
            return;
        }

        System.out.println("\nWhere do you want to delete the song from?");
        System.out.println("1. From the beginning");
        System.out.println("2. From the end");
        System.out.println("3. By Song ID");
        System.out.print("Enter your choice: ");
        
        int choice;
        while(true) {
            if(sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
                if(choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // clear the invalid input
            }
        }

        switch(choice) {
            case 1: // Delete from beginning
                if(head.next == null) {
                    head = null;
                } else {
                    head = head.next;
                    head.prev = null;
                }
                System.out.println("Song deleted from the beginning of the playlist.");
                break;
                
            case 2: // Delete from end
                if(head.next == null) {
                    head = null;
                } else {
                    Song temp = head;
                    while(temp.next.next != null) {
                        temp = temp.next;
                    }
                    temp.next = null;
                }
                System.out.println("Song deleted from the end of the playlist.");
                break;
                
            case 3: // Delete by ID
                System.out.print("Enter the ID of the song to delete: ");
                int deleteId;
                while(true) {
                    if(sc.hasNextInt()) {
                        deleteId = sc.nextInt();
                        sc.nextLine(); // consume newline
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        sc.next(); // clear the invalid input
                    }
                }
                
                Song temp = head;
                Song prev = null;
                boolean found = false;
                
                while(temp != null) {
                    if(temp.id == deleteId) {
                        found = true;
                        if(prev == null) { // deleting head
                            head = temp.next;
                            if(head != null) {
                                head.prev = null;
                            }
                        } else {
                            prev.next = temp.next;
                            if(temp.next != null) {
                                temp.next.prev = prev;
                            }
                        }
                        System.out.println("Song with ID " + deleteId + " deleted successfully.");
                        break;
                    }
                    prev = temp;
                    temp = temp.next;
                }
                
                if(!found) {
                    System.out.println("Song with ID " + deleteId + " not found in the playlist.");
                }
                break;
        }
    }

    //method which will always consider and give length of the list
    int getLength() {
        int length = 0;
        Song temp = head;
        while(temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
    
    void displaySong() {
        if(curr == null) {
            System.out.println("No song is currently selected.");
            return;
        }

        System.out.println("\nWhich Song Details do you want to display?");
        System.out.println("1) Previous Song");
        System.out.println("2) Next Song");
        System.out.println("3) Current Song");

        int choice;
        while(true) {
            if(sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
                if(choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // clear the invalid input
            }
        }

        switch(choice) {
            case 1: // Previous song
                if(curr.prev != null) {
                    curr = curr.prev;
                    System.out.println("\nPlaying previous song:");
                } else {
                    System.out.println("\nNo previous song available.");
                }
                break;
            case 2: // Next song
                if(curr.next != null) {
                    curr = curr.next;
                    System.out.println("\nPlaying next song:");
                } else {
                    System.out.println("\nNo next song available.");
                }
                break;
            case 3: // Current song
                System.out.println("\nPlaying current song:");
                break;
        }
        
        // Display song info
        System.out.printf("Song ID: %d\nSong Name: %s\nArtist: %s\n", curr.id, curr.name, curr.artist);
    }

    void displayReverse() {
    if (head == null) {
        System.out.println("Playlist is empty.");
        return;
    }

    System.out.println("\nPlaylist in reverse order:");
    System.out.printf("%-10s %-30s %-30s%n", "Song ID", "Song Name", "Artist Name");
    System.out.println("------------------------------------------------------------------");

    // First, find the last node
    Song last = head;
    while (last.next != null) {
        last = last.next;
    }

    // Now traverse backward using prev pointers
    Song current = last;
    while (current != null) {
        System.out.printf("%-10d %-30s %-30s%n", current.id, current.name, current.artist);
        current = current.prev;
        }
    }
    
    void navigateSong() {
        if(curr == null) {
            System.out.println("No song is currently selected.");
            return;
        }

        System.out.println("\nNavigate to:");
        System.out.println("1. Previous Song");
        System.out.println("2. Next Song");
        System.out.println("3. Current Song");
        System.out.print("Enter your choice: ");

        int choice;
        while(true) {
            if(sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
                if(choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // clear the invalid input
            }
        }

        switch(choice) {
            case 1: // Move to previous song
                if(curr.prev != null) {
                    curr = curr.prev;
                    System.out.println("\nMoved to previous song:");
                } else {
                    System.out.println("\nNo previous song available.");
                    return;
                }
                break;
            case 2: // Move to next song
                if(curr.next != null) {
                    curr = curr.next;
                    System.out.println("\nMoved to next song:");
                } else {
                    System.out.println("\nNo next song available.");
                    return;
                }
                break;
            case 3: // Stay on current song
                System.out.println("\nCurrent song:");
                break;
        }

        // Display song info
        System.out.printf("Song ID: %d\nSong Name: %s\nArtist: %s\n", curr.id, curr.name, curr.artist);
    }
}

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Operations obj = new Operations();
        System.out.println("\n------- Welcome to Music Player ------");
        
        int k = 1;
        do {
            System.out.println("\n`` MENU ``");
            System.out.println("1. Create Playlist");
            System.out.println("2. Display Playlist");
            System.out.println("3. Insert Songs");
            System.out.println("4. Delete Songs");
            System.out.println("5. Display Current/Next/Previous Song");
            System.out.println("6. Reverse Playlist");
            System.out.println("7. Navigate Through Playlist");
            System.out.println("8. Exit");
            System.out.print("Which operation do you want to perform? ");
            
            int n;
            while(true) {
                if(sc.hasNextInt()) {
                    n = sc.nextInt();
                    sc.nextLine(); // consume newline
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    sc.next(); // clear the invalid input
                }
            }
            
            switch(n) {
                case 1: // creating playlist
                    obj.createPlaylist();
                    break;
                case 2: // displaying playlist
                    obj.displayList();
                    break;
                case 3: // inserting songs
                    obj.insertSong();
                    break;
                case 4: // deleting songs
                    obj.deleteSong();
                    break;
                case 5: // display specific song
                    obj.displaySong();
                    break;
                case 6: // reverse playlist
                    obj.displayReverse();
                    break;
                case 7: // navigate playlist
                    obj.navigateSong();
                    break;
                case 8: // exit
                    k = 0;
                    break;
                default: 
                    System.out.println("Invalid Input. Please try again.");
            }
            
            if(k != 0) {
                System.out.print("\nDo you want to continue (1/0)? ");
                while(true) {
                    if(sc.hasNextInt()) {
                        k = sc.nextInt();
                        sc.nextLine(); // consume newline
                        if(k == 0 || k == 1) {
                            break;
                        } else {
                            System.out.println("Invalid input! Please enter only 1 or 0.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        sc.next(); // clear the invalid input
                    }
                }
            }
        } while(k != 0);
        
        System.out.println("\nProgram Executed Successfully \nExiting program..");
        sc.close();
    }
}