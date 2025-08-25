// Implementation of Stack Using Linklist
package defautl;
import java.util.*;
class Node {
    String data;
    Node next;
    Node(String data) {
        this.data = data;
        this.next = null;
    }
}
class Stack {
    Node head;
    Stack() {
        this.head = null;
    }
    boolean isEmpty() {
        return head == null;
    }
    void push(String new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }
    void pop() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
    }
    void peek() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return;
        }
        System.out.println(head.data);
    }
    int size() {
        Node curr = head;
        int cnt = 0;
        while (curr != null) {
            cnt++;
            curr = curr.next;
        }
        return cnt;
    }
    // Reverse a sentence (words order)
    void reverseSen(Scanner sc) {
        System.out.println("Enter the String that you want to Reverse");
        String string;
        while (true) {
            string = sc.nextLine();
            String check = string.trim();
            if (check.isEmpty()) {
                System.out.println("Invalid Input..");
            }else if (check.length() > 100) {
                System.out.println("Length to input too long..");
            } else {
                break;
            }
        }
        String[] words = string.trim().split("\\s+");
        Stack tempStack = new Stack();
        for (String word : words) {
            tempStack.push(word);
        }
        System.out.println("Reversed String :");
        while (!tempStack.isEmpty()) {
            System.out.print(tempStack.head.data + " ");
            tempStack.pop();
        }
        System.out.println();
    }
    // Reverse a word (characters order)
    void reverseWord(String word) {
        Stack st = new Stack();
        for (int i = 0; i < word.length(); i++) {
            st.push(String.valueOf(word.charAt(i)));
        }
        StringBuilder revString = new StringBuilder();
        while (!st.isEmpty()) {
            revString.append(st.head.data);
            st.pop();
        }
        System.out.println("Reversed String is : " + revString);
    }
	void evaluatePostfix(String exp){
		Scanner sc = new Scanner(System.in);
		Stack st = new Stack();
		for(int i=0;i<exp.length();i++){
			char ch = exp.charAt(i);
			if(Character.isDigit(ch)){
				st.push(String.valueOf(ch));
			}else{
				int left_operend = Integer.parseInt(st.head.data);
				st.pop();
				int right_operand = Integer.parseInt(st.head.data);
				st.pop();
				switch(ch){
					case '+' -> st.push(String.valueOf(right_operand + left_operend));
					case '-'-> st.push(String.valueOf(right_operand - left_operend));
					case '*'-> st.push(String.valueOf(right_operand * left_operend));
					case '/'-> {	if(left_operend ==0){
									System.out.println("Can't divide by zero");
									return;
									}
								st.push(String.valueOf(right_operand / left_operend));
							}
				}
			}
		}
		if(st.size()>1){
			System.out.println("Invalid Postfix Expression");
			return;
		}
		System.out.println("Result of Postfix Exprression is : " + st.head.data);
	}
}

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack st = new Stack();
		int k=1;
		System.out.println("\n----- Stack Implementation using Linklist ---------\n");
		do{
        System.out.println("```` MENU ````");
        System.out.println("1.Reverse String"); // 1>sentence 2>words
        System.out.println("2.Evaluate Postfix Expression");
		System.out.print("choose option : ");
		int choice;
        while (true) {
            choice = sc.nextInt();
            if (choice == 1 || choice == 2) {
                break;
            } else {
                System.out.println("Please choose appropriate option");
            }
        }
        sc.nextLine(); // consume leftover newline
        switch (choice) {
            case 1 -> {
                System.out.print("\nWhich String to reverse -\n1.Reverse Sentence \n2.Reverse Word");
				System.out.println("Enter Choice : ");
                int m;
                while (true) {
                    try {
                        m = sc.nextInt();
                        if (m == 1 || m == 2) {
                            break;
                        } else {
                            System.out.println("Please choose appropriate option");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter only 1 or 2:");
                        sc.next(); // clear the invalid input
                    }
                }
                sc.nextLine(); // consume leftover newline
                switch (m) {
                    case 1 -> st.reverseSen(sc);
                    case 2 -> {
                        String word;
                        while (true) {
                            System.out.println("Enter the word which you want to reverse");
                            word = sc.nextLine();
                            if (!word.isEmpty() && word.matches("^[^\\d]+$") && word.length() < 15) {
                                break;
                            } else {
                                System.out.println("Please enter correct word");
                            }
                        }
                        st.reverseWord(word);
                    }
                }
            }
            case 2 -> {
				System.out.println("Enter Postfix Expression that you want to Evaluate");
				String exp;
				while(true){
					exp = sc.nextLine();
					if(!exp.isEmpty() && exp.length()<20 && exp.matches("^[0-9+\\-*/]+$")){
						break;
					}else{
						System.out.println("Please enter correct Postfix Expression");
					}
				}
				st.evaluatePostfix(exp);
				}
        }
		System.out.println("Do you want to continue ? 1.Yes  0.No");
		k = sc.nextInt();
	}while(k!=0);
        sc.close();
    }
}
