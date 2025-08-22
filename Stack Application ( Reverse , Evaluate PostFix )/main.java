//create stack ADT using array/linklist - 1) reverse the string 2) evaluate postfix expression 
import java.util.*;

class Stack {
    private int size;
    int top;
    int[] stk;

    Stack(int Max_size) {
        this.size = Max_size;
        this.top = -1;
        this.stk = new int[Max_size];
    }

    boolean isFull() {
        return top == size - 1;
    }

    boolean isEmpty() {
        return top == -1;
    }

    void push(int x) {
        if (isFull()) {
            System.out.println("Stack Overflow !!");
        } else {
            top++;
            stk[top] = x;
        }
    }

    void pop() {
        if (isEmpty()) {
            System.out.println("Stack UnderFlow !!!");
        } else {
            top--;
        }
    }

    // Static method for postfix evaluation
    static void evaluate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Postfix Expression: ");
        String exp;
        while (true) {
            exp = sc.next().trim();
            if (exp.length() > 0 && exp.matches("[0-9+\\-*/^]+")) {
                break;
            }
            System.out.println("Invalid Expression.. Please Enter Again");
        }
        Stack st = new Stack(exp.length());
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isDigit(ch)) {
                st.push(ch - '0');
            } else {
                if (st.top < 1) {
                    System.out.println("Not Enough Operand Found in stack..");
                    return;
                }
                int val1 = st.stk[st.top];
                st.pop();
                int val2 = st.stk[st.top];
                st.pop();
                switch (ch) {
                    case '+' -> st.push(val2 + val1);
                    case '-' -> st.push(val2 - val1);
                    case '*' -> st.push(val2 * val1);
                    case '/' -> {
                        if (val1 == 0) {
                            System.out.println("Math Error : Cannot divide by Zero");
                            return;
                        }
                        st.push(val2 / val1);
                    }
                    case '^' -> st.push((int) Math.pow(val2, val1));
                    default -> {
                        System.out.println("Invalid Operator");
                        return;
                    }
                }
            }
        }
        if (st.top != 0) {
            System.out.println("Invalid Expression.. Too Many operands Still remaining in Stack");
            return;
        }
        System.out.println("Result: " + st.stk[st.top]);
    }

    // Static method for string reversal
    static void reverse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String Which you want to reverse: ");
        String str;
        while (true) {
            str = sc.next().trim();
            if (str.length() < 20 && str.matches("[a-zA-Z]+") && !str.isEmpty()) {
                break;
            }
            System.out.println("Invalid Expression.. Please Enter Again");
        }
        Stack st = new Stack(str.length());
        for (int i = 0; i < str.length(); i++) {
            st.push(str.charAt(i));
        }
        StringBuilder rev = new StringBuilder();
        while (!st.isEmpty()) {
            char ch = (char)st.stk[st.top];
            rev.append(ch);
            st.pop();
        }
        System.out.println("Reversed String is : " + rev);
    }
}

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p = 1;
        System.out.println("\n ****** Stack Application *******\n");
        do {
            System.out.println("\n---------- Menu ----------\n");
            System.out.println("1. Reverse a String");
            System.out.println("2. Evaluate Postfix Expression");
            System.out.println("3. Exit");
            System.out.println("Choose Option: ");
            int choice ;
            while(true){
              choice = sc.nextInt();
              sc.nextLine();
              if(choice>=1 && choice<3) {
                break;
              }
              System.out.println("Invalid Option.. Please Enter Again");
            }
            switch (choice) {
                case 1 -> Stack.reverse();
                case 2 -> Stack.evaluate();
                default -> System.out.println("Invalid Option!");
            }
              System.out.println("Do you want to continue ? (1/0)");
              p = sc.nextInt();
              sc.nextLine(); // consume newline
        } while (p != 0);
        System.out.println("Program Successfully Executed..");
        sc.close();
    }
}