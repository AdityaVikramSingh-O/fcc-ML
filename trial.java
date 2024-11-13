Program to convert prefix to Postfix expression

import java.util.*;
class stack {
    public int ptr=-1, size; 
    String arr[]=new String[0];
    
    stack(int s){
    arr= new String[s];
    size=s;
    }
    public void push(String d){
    if(!isFull()){
    ptr++;
    arr[ptr]=d;};
    }
    public String peek(){
    if (!isEmpty()) return arr[ptr];
    else return "EMPTY";
    }
    public String pop(){
    if(!isEmpty()){
    ptr--;return arr[ptr+1];}
    else return "EMPTY";
    }
    public boolean isEmpty( ){
       return ptr == -1;
    }
       public boolean isFull(){
       return ptr == size-1;
       }     
    } 

class trial {
 
    // function to check if character
    // is operator or not
    static boolean isOperator(char x)
    {
        switch (x) {
        case '+':
        case '-':
        case '/':
        case '*':
            return true;
        }
        return false;
    }
 
    
    static String preToPost(String pre_exp)
    {
        int length = pre_exp.length();
        stack s = new stack(length);
        // reading from right to left
        for (int i = length - 1; i >= 0; i--) 
        {
            // check if symbol is operator
            if (isOperator(pre_exp.charAt(i))) 
            {
                // pop two operands from stack
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
 
                // concat the operands and operator
                String temp = op1 + op2 + pre_exp.charAt(i);
 
                // Push String temp back to stack
                s.push(temp);
            }
 
            // if symbol is an operand
            else {
                // push the operand to the stack
                s.push(pre_exp.charAt(i) + "");
            }
        }
 
        // stack contains only the Postfix expression
        return s.peek();
    }
 
    // Driver Code
    public static void main(String args[])
    {
        System.out.println("Enter the Expression");
        Scanner sc=new Scanner(System.in);
        String pre_exp = sc.nextLine();
        System.out.println("Postfix : "
                           + preToPost(pre_exp));
    }
}

/*Program to convert infix expression to postfix expression using stack

import java.util.*;

class trial {
  static boolean isalpha(char c) {
    if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
      return true;
    }
    return false;
  }

  static boolean isdigit(char c) {
    if (c >= '0' && c <= '9') {
      return true;
    }
    return false;
  }

  static boolean isOperator(char c) {
    return (!isalpha(c) && !isdigit(c));
  }

  static int getPriority(char C) {
    if (C == '-' || C == '+')
      return 1;
    else if (C == '*' || C == '/')
      return 2;
    else if (C == '^')
      return 3;

    return 0;
  }
  static String reverse(char str[], int start, int end) {  
    char temp;
    while (start < end) {
      temp = str[start];
      str[start] = str[end];
      str[end] = temp;
      start++;
      end--;
    }
    return String.valueOf(str);
  }

  static String infixToPostfix(char[] infix1) {
    System.out.println(infix1);
    String infix = '(' + String.valueOf(infix1) + ')';
    int l = infix.length();
    Stack < Character > char_stack = new Stack < > ();
    String output = "";
    for (int i = 0; i < l; i++) {   
      if (isalpha(infix.charAt(i)) || isdigit(infix.charAt(i)))
        output += infix.charAt(i);
      else if (infix.charAt(i) == '(')
        char_stack.add('(');    
      else if (infix.charAt(i) == ')') {
        while (char_stack.peek() != '(') {
          output += char_stack.peek();
          char_stack.pop();
        }      
        char_stack.pop();
      }
      else {
        if (isOperator(char_stack.peek())) {
          while ((getPriority(infix.charAt(i)) <
 getPriority(char_stack.peek())) 
          ||
(getPriority(infix.charAt(i)) <=
 getPriority(char_stack.peek()) &&
            infix.charAt(i) == '^')) {
            output += char_stack.peek();
            char_stack.pop();
          }
          char_stack.add(infix.charAt(i));
        }
      }
    }
    while (!char_stack.empty()) {
      output += char_stack.pop();
    }
    return output;
  }

  static String infixToPrefix(char[] infix) {   
    int l = infix.length;
    String infix1 = reverse(infix, 0, l - 1);
    infix = infix1.toCharArray();
    for (int i = 0; i < l; i++) {

      if (infix[i] == '(') {
        infix[i] = ')';
        i++;
      } else if (infix[i] == ')') {
        infix[i] = '(';
        i++;
      }
    }
    String prefix = infixToPostfix(infix);
    prefix = reverse(prefix.toCharArray(), 0, l - 1);
    return prefix;
  }

  public static void main(String[] args) {
    System.out.println("Enter the Expression");
    Scanner sc=new Scanner(System.in);
    String s = sc.nextLine();
    System.out.println("Infix expression: " + s);
    System.out.print("Prefix expression: ");
    System.out.print(infixToPrefix(s.toCharArray()));
  }
}

/*Program to convert infix expression to postfix expression using stack

import java.util.Scanner;
class stack {
    public int ptr=-1, size; //int arr[]= new int[10]; char arr[]= new char[0];
    char arr[]=new char[0];
    
    stack(int s){
    arr= new char[s];
    size=s;
    }
    public void push(char d){
    if(!isFull()){
    ptr++;
    arr[ptr]=d;};
    }
    public char peek(){
    if (!isEmpty()) return arr[ptr];
    else return 0;
    }
    public char pop(){
    if(!isEmpty()){
    ptr--;return arr[ptr+1];}
    else return 0;
    }
    public boolean isEmpty( ){
       return ptr == -1;
    }
       public boolean isFull(){
       return ptr == size-1;
       }     
    } 
class trial {

  static int Prec(char ch) {
    switch (ch) {
    case '+':
    case '-':
      return 1;

    case '*':
    case '/':
      return 2;

    case '^':
      return 3;
    }
    return -1;
  }


  static String infixToPostfix(String exp) {
    
    String result = new String("");


    stack st = new stack(exp.length());

    for (int i = 0; i < exp.length(); ++i) {
      char c = exp.charAt(i);

      if (Character.isLetterOrDigit(c))
        result += c;

  
      else if (c == '(')
        st.push(c);

     
      else if (c == ')') {
        while (!st.isEmpty() &&
          st.peek() != '(')
          result += st.pop();

        st.pop();
      } else 
      {
        while (!st.isEmpty() && Prec(c) <=
          Prec(st.peek())) {

          result += st.pop();
        }
        st.push(c);
      }

    }


    while (!st.isEmpty()) {
      if (st.peek() == '(')
        return "Invalid Expression";
      result += st.pop();
    }
    return result;
  }


  public static void main(String[] args) {
    System.out.println("Enter the Expression");
    Scanner sc=new Scanner(System.in);
    String exp = sc.nextLine();
    System.out.println("Infix expression: " + exp);
    System.out.println("Postfix expression: " + infixToPostfix(exp));
  }
}

/*

Program to create A doubly linked list of number include the function score traveling the list in reverse order and delete the nth node from the end

import java.util.Scanner;

class Node {
    int data;
    Node next;
    Node prev;
    
    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class DLL {
    Node head;
    Node tail;
    
   
    public void addNode(int data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    
   
    public void traverseReverse() {
        Node current = tail;
        System.out.print("Reverse traversal: ");
        
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }
    
  
    public void deleteNthFromEnd(int n) {
        if (head == null || n <= 0) {
            System.out.println("List is empty or invalid position.");
            return;
        }
        
        // Step 1: Find the length of the list
        int length = 0;
        Node current = head;
        
        while (current != null) {
            length++;
            current = current.next;
        }
        
       
        int positionFromStart = length - n + 1;
        
        if (positionFromStart <= 0 || positionFromStart > length) {
            System.out.println("Invalid position: Cannot delete the node.");
            return;
        }
        
        // Step 3: Delete the node at the calculated position
        current = head;
        for (int i = 1; i < positionFromStart; i++) {
            current = current.next;
        }
        
      
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next; 
        }
        
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev; 
        }
        
        System.out.println("Deleted node with data: " + current.data);
    }
    
   
    public void display() {
        Node current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class trial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DLL list = new DLL();
        
        System.out.println("Enter the number of Integers to be stored in list");
        int s= scanner.nextInt();

        for (int i = 0; i < s; i++) {
            int num = scanner.nextInt();
            list.addNode(num);
        }
        
        list.display();
        
       
        list.traverseReverse();
        
     
        System.out.print("Enter the position (from the end) of the node to delete: ");
        int n = scanner.nextInt();
        list.deleteNthFromEnd(n);
        
       
        list.display();
        
        scanner.close();
    }
}



/*
Program to input a string and form a singly linked list of its chatracters and find seperate count of vowels in it

import java.util.Scanner;

class Node {
    char data;
    Node next;
    
    Node(char data) {
        this.data = data;
        this.next = null;
    }
}

class DLL {
    Node head;
    
   
    public void addNode(char data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
  
    public void countVowels() {
        int aCount = 0, eCount = 0, iCount = 0, oCount = 0, uCount = 0;
        Node current = head;
        
        while (current != null) {
            switch (Character.toLowerCase(current.data)) {
                case 'a': aCount++; break;
                case 'e': eCount++; break;
                case 'i': iCount++; break;
                case 'o': oCount++; break;
                case 'u': uCount++; break;
            }
            current = current.next;
        }
        
        System.out.println("Count of vowels:");
        System.out.println("A: " + aCount);
        System.out.println("E: " + eCount);
        System.out.println("I: " + iCount);
        System.out.println("O: " + oCount);
        System.out.println("U: " + uCount);
    }
}

public class trial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
       
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
     
        DLL list = new DLL();
        for (char c : input.toCharArray()) {
            list.addNode(c);
        }
        
      
        list.countVowels();
        
        scanner.close();
    }
}

/*
Program to Input a multidigit number and store the digit in a singly linked list then find the sum of digit by reading the list until the sum reduces to sum reduces to a single digit

import java.util.*;
import java.io.*;

class Node {
   int data;
   Node next;

   Node(int data) {
       this.data = data;
       this.next = null;
   }
}

class DLL {
   Node head;

   
   void insert(int data) {
       Node newNode = new Node(data);
       newNode.next = head;
       head = newNode;
   }

  
   void createFromNumber(int number) {
       while (number > 0) {
           int digit = number % 10;
           insert(digit);
           number /= 10;
       }
   }

 
   int getSingleDigitSum() {
       int sum = sumOfDigits();

     
       while (sum >= 10) {
           sum = getDigitSum(sum);
       }
       return sum;
   }


   int sumOfDigits() {
       int sum = 0;
       Node current = head;
       while (current != null) {
           sum += current.data;
           current = current.next;
       }
       return sum;
   }


   int getDigitSum(int number) {
       int sum = 0;
       while (number > 0) {
           sum += number % 10;
           number /= 10;
       }
       return sum;
   }

   public static void main(String[] args) {
       DLL list = new DLL();
       System.out.println("Enter the number: ");
       Scanner sc = new Scanner(System.in);
       int number = sc.nextInt();
       list.createFromNumber(number);

       int singleDigitSum = list.getSingleDigitSum();
       System.out.println("Single digit sum: " + singleDigitSum);
   }
}
/* 
 Program to check bracket validity using stack


import java.util.*;
import java.io.*;



class stack {
   public int ptr=-1, size; //int arr[]= new int[10]; char arr[]= new char[0];
   char arr[]=new char[0];
   
   stack(int s){
   arr= new char[s];
   size=s;
   }
   public void push(char d){
   if(!isFull()){
   ptr++;
   arr[ptr]=d;};
   }
   public char peek(){
   if (!isEmpty()) return arr[ptr];
   else return 0;
   }
   public char pop(){
   if(!isEmpty()){
   ptr--;return arr[ptr+1];}
   else return 0;
   }
   public boolean isEmpty( ){
      return ptr == -1;
   }
      public boolean isFull(){
      return ptr == size-1;
      }     
   } 
class hasValidBracket{
   public stack st;


   public boolean check(String s){
   st =new stack(s.length());
   for(int i=0;i<s.length(); i++)
   {
   if (s.charAt(i) == '('||s.charAt(i) == ')'||s.charAt(i) == '{'||s.charAt(i) == '}'||s.charAt(i) == '['||s.charAt(i) == ']')
   { 
   if(st.isEmpty())if (s.charAt(i) == '('|| s.charAt(i) == '{'||s.charAt(i) == '[')st.push(s.charAt(i)); else {System.out.print("x" ); return
   false; }
   else if (s.charAt(i) == ')'){if (st.pop() != '(')return false;}
   else if (s.charAt(i) == '}'){if(st.pop() != '{')return false; }
   else if (s.charAt(i) == ']'){if(st.pop() != '[') return false;}
   else{
   st.push(s.charAt(i));
   }
}
   }
 
   return st.isEmpty();
}
}




public class trial {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
System.out.print("Enter string to check bracket validity : ");
String inputString = scanner.nextLine();
hasValidBracket a=new hasValidBracket();
if(a.check(inputString))
System.out.println("\nValid bracket\n");
else
System.out.println( "\nInvalid bracket\n");
}
}













class InfixPostfix {
   int value;

   public static void convert(String s ){
      char arr[]=new char[s.length()];
      int last =-1;
   for (int i=0;i<s.length();i++){
      if((s.charAt(i)>='A' &&s.charAt(i)<='Z')||(s.charAt(i)>='a' &&s.charAt(i)<='z')){
      arr[++last]=s.charAt(i);
      }
      else if (s[i]=='+' || s[i]=='-' || s[i]=='*' || s[i]=='/'){
   }

   }
}

class trial {

  }
   // Both obj1 and obj2 are different objects in memory}}
class array{
    public int arr[] = new int[10];
 array (){
    System.out.println("Enter the size of array");
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    
    arr=new int[n];
  
 }
    public void insert(int d){} 
}
class trial {

public static void main (String args[]){
int a=10;
float b=(float)a/3;

System.out.println(b);
}


}
*/


