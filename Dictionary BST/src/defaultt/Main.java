package defaultt;
import java.util.*;

class node {
    String key;
    String meaning;
    node right;
    node left;
    node(String key, String meaning) {
        this.key = key;
        this.meaning = meaning;
        this.right = null;
        this.left = null;
    }
}

class binaryRoot {
    String key;
    String meaning;
    Scanner sc = new Scanner(System.in);
    node root;
    binaryRoot() {
        root = null;
    }

    void insert() {
        while (true) {
            System.out.print("Enter the Key : ");
            key = sc.next();
            if (!key.isEmpty() && key.length() < 50) {
                break;
            } else {
                System.out.println("Enter Correct length password ..");
                sc.nextLine();
            }
        }
        while (true) {
            System.out.print("Enter Meaning for Key : ");
            meaning = sc.next();
            if (!meaning.isEmpty() && meaning.length() < 50) {
                break;
            } else {
                System.out.println("Enter correct password");
                sc.nextLine();
            }
        }
        node newNode = new node(key, meaning);
        root = insertRec(root, newNode);
    }

    node insertRec(node root, node newNode) {
        if (root == null) {
            return newNode;
        }
        if (newNode.key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, newNode);
        } else {
            root.right = insertRec(root.right, newNode);
        }
        return root;
    }

    void create() {
        System.out.println("How many Words do you want to insert ? ");
        int n;
        while (true) {
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n > 0 && n < 40) {
                    break;
                } else {
                    System.out.println("Invalid input .. Enter number between (1-39)");
                    sc.next();
                }
            } else {
                System.out.println("Invalid Input .. Please Enter Numeric Data..");
                sc.next();
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Enter " + (i + 1) + " word");
            insert();
        }
        System.out.println("Data Successfully Entered !!! ");
    }

    void search() {
        System.out.println("Search Word by :- \n1)Key   2)Meaning");
        int k;
        while (true) {
            if (sc.hasNextInt()) {
                k = sc.nextInt();
                if (k == 1 || k == 2) {
                    break;
                } else {
                    System.out.println("Invalid input .. Choose correct option");
                    sc.next();
                }
            } else {
                System.out.println("Invalid Input .. Please Enter Numeric Data..");
                sc.next();
            }
        }
        sc.nextLine(); // clear buffer
        switch (k) {
            case 1 -> {
                boolean found = false;
                String searchKey;
                while (true) {
                    System.out.println("Enter the key which you want to search ");
                    searchKey = sc.next();
                    if (!searchKey.isEmpty() && searchKey.length() < 50) {
                        break;
                    } else {
                        System.out.println("Enter Correct length password ..");
                        sc.nextLine();
                    }
                }
                node ptr = root;
                while (ptr != null) {
                    if (searchKey.compareTo(ptr.key) < 0) {
                        ptr = ptr.left;
                    } else if (searchKey.compareTo(ptr.key) > 0) {
                        ptr = ptr.right;
                    } else {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println(" Key successfully found !!");
                    System.out.println(ptr.key + " : " + ptr.meaning);
                } else {
                    System.out.println("Data not found !!!");
                }
            }
            case 2 -> {
                boolean found = false;
                String searchMeaning;
                while (true) {
                    System.out.println("Enter the meaning which you want to search ");
                    searchMeaning = sc.next();
                    if (!searchMeaning.isEmpty() && searchMeaning.length() < 50) {
                        break;
                    } else {
                        System.out.println("Enter Correct length password ..");
                        sc.nextLine();
                    }
                }
                node result = searchByMeaning(root, searchMeaning);
                if (result != null) {
                    System.out.println(" Key with meaning '" + searchMeaning + "' successfully found !!");
                    System.out.println(result.key + " : " + result.meaning);
                } else {
                    System.out.println("Data not found !!!");
                }
            }
        }
    }

    node searchByMeaning(node root, String meaning) {
        if (root == null) return null;
        if (root.meaning.equals(meaning)) return root;
        node leftResult = searchByMeaning(root.left, meaning);
        if (leftResult != null) return leftResult;
        return searchByMeaning(root.right, meaning);
    }

    void update() {
        String searchKey;
        while (true) {
            System.out.println("Enter Key value whose meaning you want to update");
            searchKey = sc.next();
            if (!searchKey.isEmpty() && searchKey.length() < 50) {
                break;
            } else {
                System.out.println("Invalid Input.. Please Try Again..");
                sc.nextLine();
            }
        }
        node ptr = root;
        boolean found = false;
        while (ptr != null) {
            if (searchKey.compareTo(ptr.key) < 0) {
                ptr = ptr.left;
            } else if (searchKey.compareTo(ptr.key) > 0) {
                ptr = ptr.right;
            } else {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Key :" + searchKey + " successfully found !!");
            String newMeaning;
            while (true) {
                System.out.println("Enter New updated value for " + searchKey + " :");
                newMeaning = sc.next();
                if (!newMeaning.isEmpty() && newMeaning.length() < 50) {
                    break;
                } else {
                    System.out.println("Invalid Input.. Please try again later");
                    sc.nextLine();
                }
            }
            ptr.meaning = newMeaning;
            System.out.println("Meaning of " + searchKey + " Successfully updated !!");
        } else {
            System.out.println("Key not found!");
        }
    }

    node delete(node root, String delKey) {
        if (root == null) {
            System.out.println("No Such key Exists");
            return null;
        }
        if (delKey.compareTo(root.key) < 0) {
            root.left = delete(root.left, delKey);
        } else if (delKey.compareTo(root.key) > 0) {
            root.right = delete(root.right, delKey);
        } else {
            System.out.println("Key Successfully deleted !!!");
            root = helper(root);
        }
        return root;
    }

    public node helper(node root) {
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {
            node rightChild = root.right;
            node lastRight = findlastRight(root.left);
            lastRight.right = rightChild;
            return root.left;
        }
    }

    public node findlastRight(node root) {
        if (root.right == null) {
            return root;
        }
        return findlastRight(root.right);
    }

    void inorder(node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.key + " : " + root.meaning);
            inorder(root.right);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int trace = 1;
        binaryRoot obj = new binaryRoot();
        do {
            System.out.println("\n---  M E N U ---");
            System.out.println("1. Enter Word");
            System.out.println("2. Search Word");
            System.out.println("3. Update Word ");
            System.out.println("4. Delete Word ");
            System.out.println("5. Display Dictionary");

            int choice;
            while (true) {
                System.out.println("Enter Choice");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice > 0 && choice < 6) {
                        break;
                    } else {
                        System.out.println("Invalid option .. Please choose correct option . ");
                        sc.next();
                    }
                } else {
                    System.out.println("Invalid Input .. Please Enter Numeric Data..");
                    sc.next();
                }
            }
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> obj.create();
                case 2 -> obj.search();
                case 3 -> obj.update();
                case 4 -> {
                    String delKey;
                    while (true) {
                        System.out.print("Enter the Key : ");
                        delKey = sc.next();
                        if (!delKey.isEmpty() && delKey.length() < 50) {
                            break;
                        } else {
                            System.out.println("Enter Correct length password ..");
                            sc.nextLine();
                        }
                    }
                    obj.root = obj.delete(obj.root, delKey);
                }
                case 5 -> obj.inorder(obj.root);
            }
            System.out.println("Press 1-Continue , 0-Stop");
            while (true) {
                if (sc.hasNextInt()) {
                    trace = sc.nextInt();
                    if (trace == 1 || trace == 0) {
                        break;
                    } else {
                        System.out.println("Enter correct Input - (1/0)");
                        sc.nextLine();
                    }
                } else {
                    System.out.println("Enter Correct Input");
                    sc.next();
                }
            }
        } while (trace != 0);
        sc.close();
    }
}