import java.util.Scanner;

class Song {
    int id;
    String name;
    String artist;
    Song next;
    Song prev;
    
    public Song(int id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.next = null;
        this.prev = null;
    }
}

class MusicPlayer {
    private Scanner sc = new Scanner(System.in);
    private Song head;
    private Song current;
    private int id;
    private String name;
    private String artist;

    // Check for duplicate song IDs
    private boolean isDuplicate(int id) {
        if (head == null) return false;
        
        Song temp = head;
        do {
            if (temp.id == id) return true;
            temp = temp.next;
        } while (temp != head);
        
        return false;
    }

    // Create a single song node with validation
    private void createSong() {
        // ID validation
        while (true) {
            System.out.print("Enter Song ID : ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                sc.nextLine();
                if (id <= 0 || id > 10000) {
                    System.out.println("Invalid ID. Please enter between 1 and 10000.");
                } else if (isDuplicate(id)) {
                    System.out.println("This ID already exists. Try another.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
        }
        
        // Name validation
        while (true) {
            System.out.print("Enter Song Name : ");
            name = sc.nextLine();
            if (!name.trim().isEmpty() && name.length() <= 40 && name.matches("^[^\\d]+$")) {
                break;
            }
            System.out.println("Invalid name. Must be non-empty, ≤40 chars, no numbers.");
        }
        
        // Artist validation
        while (true) {
            System.out.print("Enter Artist Name : ");
            artist = sc.nextLine();
            if (!artist.trim().isEmpty() && artist.length() <= 40 && artist.matches("^[^\\d]+$")) {
                break;
            }
            System.out.println("Invalid artist. Must be non-empty, ≤40 chars, no numbers.");
        }
    }

    // Create playlist with multiple songs
    public void createPlaylist() {
        System.out.print("How many songs to add? ");
        int n = getPositiveInt();
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Song " + (i + 1) + ":");
            createSong();
            Song newSong = new Song(id, name, artist);
            
            if (head == null) {
                head = newSong;
                head.next = head;
                head.prev = head;
                current = head;
            } else {
                Song tail = head.prev;
                newSong.next = head;
                newSong.prev = tail;
                tail.next = newSong;
                head.prev = newSong;
            }
        }
        System.out.println("\nPlaylist created with " + n + " songs.");
    }

    // Display all songs in playlist
    public void displayPlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        
        System.out.println("\nCurrent Playlist:");
        System.out.printf("%-10s %-30s %-30s%n", "Song ID", "Song Name", "Artist");
        System.out.println("------------------------------------------------------------");
        
        Song temp = head;
        do {
            System.out.printf("%-10d %-30s %-30s%n", temp.id, temp.name, temp.artist);
            temp = temp.next;
        } while (temp != head);
    }

    // Insert song at position
    public void insertSong() {
        if (head == null) {
            System.out.println("Playlist empty. Creating new playlist with this song.");
            createSong();
            head = new Song(id, name, artist);
            head.next = head;
            head.prev = head;
            current = head;
            return;
        }

        System.out.println("\nInsert at:");
        System.out.println("1. Beginning");
        System.out.println("2. End");
        System.out.println("3. Specific Position");
        System.out.print("Choice: ");
        
        int choice = getIntInRange(1, 3);
        createSong();
        Song newSong = new Song(id, name, artist);

        switch (choice) {
            case 1 -> insertAtBeginning(newSong);
            case 2 -> insertAtEnd(newSong);
            case 3 -> insertAtPosition(newSong);
        }
    }

    private void insertAtBeginning(Song newSong) {
        Song tail = head.prev;
        newSong.next = head;
        newSong.prev = tail;
        head.prev = newSong;
        tail.next = newSong;
        head = newSong;
        System.out.println("Song added at beginning.");
    }

    private void insertAtEnd(Song newSong) {
        Song tail = head.prev;
        newSong.next = head;
        newSong.prev = tail;
        tail.next = newSong;
        head.prev = newSong;
        System.out.println("Song added at end.");
    }

    private void insertAtPosition(Song newSong) {
        System.out.print("Enter position (1-" + (getLength() + 1) + "): ");
        int pos = getIntInRange(1, getLength() + 1);
        
        if (pos == 1) {
            insertAtBeginning(newSong);
            return;
        }
        
        Song temp = head;
        for (int i = 1; i < pos - 1; i++) {
            temp = temp.next;
        }
        
        newSong.next = temp.next;
        newSong.prev = temp;
        temp.next.prev = newSong;
        temp.next = newSong;
        System.out.println("Song added at position " + pos + ".");
    }

    // Delete song from playlist
    public void deleteSong() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        System.out.println("\nDelete from:");
        System.out.println("1. Beginning");
        System.out.println("2. End");
        System.out.println("3. By Song ID");
        System.out.print("Choice: ");
        
        int choice = getIntInRange(1, 3);
        
        switch (choice) {
            case 1 -> deleteFromBeginning();
            case 2 -> deleteFromEnd();
            case 3 -> deleteById();
        }
    }

    private void deleteFromBeginning() {
        if (head.next == head) {
            head = null;
            current = null;
        } else {
            Song tail = head.prev;
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        System.out.println("Deleted from beginning.");
    }

    private void deleteFromEnd() {
        if (head.next == head) {
            head = null;
            current = null;
        } else {
            Song tail = head.prev;
            Song newTail = tail.prev;
            newTail.next = head;
            head.prev = newTail;
        }
        System.out.println("Deleted from end.");
    }

    private void deleteById() {
        System.out.print("Enter Song ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        
        Song toDelete = null;
        Song temp = head;
        
        do {
            if (temp.id == id) {
                toDelete = temp;
                break;
            }
            temp = temp.next;
        } while (temp != head);
        
        if (toDelete == null) {
            System.out.println("Song not found.");
            return;
        }
        
        if (toDelete == head) {
            deleteFromBeginning();
        } else if (toDelete == head.prev) {
            deleteFromEnd();
        } else {
            Song prevNode = toDelete.prev;
            Song nextNode = toDelete.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            System.out.println("Song deleted.");
        }
    }

    // Display song navigation
    public void displaySong() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        
        if (current == null) {
            current = head;
        }
        
        System.out.println("\nSong Navigation:");
        System.out.println("1. Previous Song");
        System.out.println("2. Next Song");
        System.out.println("3. Current Song");
        System.out.print("Choice: ");
        
        int choice = getIntInRange(1, 3);
        
        switch (choice) {
            case 1 -> {
                current = current.prev;
                System.out.println("Playing previous song...");
            }
            case 2 -> {
                current = current.next;
                System.out.println("Playing next song...");
            }
            case 3 -> System.out.println("Playing current song...");
        }
        
        System.out.println("Now Playing:");
        System.out.printf("ID: %d\nName: %s\nArtist: %s\n", 
                        current.id, current.name, current.artist);
    }

    // Display playlist in reverse order
    public void displayReverse() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        
        System.out.println("\nPlaylist in Reverse Order:");
        System.out.printf("%-10s %-30s %-30s%n", "Song ID", "Song Name", "Artist");
        System.out.println("------------------------------------------------------------");
        
        Song temp = head.prev;
        do {
            System.out.printf("%-10d %-30s %-30s%n", temp.id, temp.name, temp.artist);
            temp = temp.prev;
        } while (temp != head.prev);
    }

    // Navigate through playlist
    public void navigatePlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        
        if (current == null) {
            current = head;
        }
        
        System.out.println("\nNow Playing: " + current.name + " by " + current.artist);
        System.out.println("Navigate:");
        System.out.println("1. Previous Song");
        System.out.println("2. Next Song");
        System.out.println("3. Back to Menu");
        System.out.print("Choice: ");
        
        int choice = getIntInRange(1, 3);
        
        if (choice == 1) {
            current = current.prev;
        } else if (choice == 2) {
            current = current.next;
        }
    }

    // Helper methods
    private int getLength() {
        if (head == null) return 0;
        
        int length = 0;
        Song temp = head;
        do {
            length++;
            temp = temp.next;
        } while (temp != head);
        
        return length;
    }

    private int getPositiveInt() {
        while (true) {
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                sc.nextLine();
                if (n > 0) return n;
                System.out.println("Please enter positive number.");
            } else {
                System.out.println("Invalid input. Enter a number.");
                sc.next();
            }
        }
    }

    private int getIntInRange(int min, int max) {
        while (true) {
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                sc.nextLine();
                if (n >= min && n <= max) return n;
                System.out.println("Please enter between " + min + " and " + max + ".");
            } else {
                System.out.println("Invalid input. Enter a number.");
                sc.next();
            }
        }
    }
}

public class main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MusicPlayer player = new MusicPlayer();
        
        System.out.println("\n===== Music Player =====");
        int choice;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Create Playlist");
            System.out.println("2. Display Playlist");
            System.out.println("3. Insert Song");
            System.out.println("4. Delete Song");
            System.out.println("5. Play Songs");
            System.out.println("6. View Reverse Playlist");
            System.out.println("7. Navigate Playlist");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            
            choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1 -> player.createPlaylist();
                case 2 -> player.displayPlaylist();
                case 3 -> player.insertSong();
                case 4 -> player.deleteSong();
                case 5 -> player.displaySong();
                case 6 -> player.displayReverse();
                case 7 -> player.navigatePlaylist();
                case 8 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);
        
        sc.close();
    }
}