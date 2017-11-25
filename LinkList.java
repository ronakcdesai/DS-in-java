package linklist;

import java.util.Scanner;

class sortedset {
    Scanner userEnter = new Scanner(System.in);
    private Node head;
    class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
        }
    }
    void printList() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }

    void add(int element) {
        Node new_node = new Node(element);

        if (head == null) {
            head = new Node(element);
            return;
        }
        new_node.next = null;
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = new_node;
    }

    private void clear() {
        head = null;// GC will take care of unreferenced objects
    }

    boolean contains(Node head, int value) {
        if (head == null) {
            System.out.println("Value NOT FOUND");
            return false;
        }
        if (head.data == value) {
            System.out.println("Value FOUND");
            return true;
        }
        return contains(head.next, value);
    }

    void remove(int element) {
        Node temp = head, prev = null;
        if (temp != null && temp.data == element) {
            head = temp.next;
            return;
        }
        while (temp != null && temp.data != element) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            return;
        }
        prev.next = temp.next;

    }

    void removeAt(int position) {
        if (head == null) {
            return;
        }
        Node temp = head;

        if (position == 0) {
            head = temp.next;
            return;
        }
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            return;
        }
        Node next = temp.next.next;
        temp.next = next;
    }

    Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }
        Node fastptr = head.next;
        Node slowptr = head;
        while (fastptr != null) {
            fastptr = fastptr.next;
            if (fastptr != null) {
                slowptr = slowptr.next;
                fastptr = fastptr.next;
            }
        }
        return slowptr;
    }

    Node sort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node middle = getMiddle(head);
        Node nextofmiddle = middle.next;
        middle.next = null;
        Node left = sort(head);
        Node right = sort(nextofmiddle);
        Node sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    Node sortedMerge(Node a, Node b) {
        Node result = null;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.data <= b.data) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;

    }

    void printOperations() {

    }

    void operations() {
        System.out.println("Enter 1 for ADD");
        System.out.println("Enter 2 for CONTAINS");
        System.out.println("Enter 3 for REMOVE");
        System.out.println("Enter 4 for REMOVE AT");
        System.out.println("Enter 5 for CLEAR LIST!!");
        int userChoice = userEnter.nextInt();
        switch (userChoice) {
            case 1:
                System.out.println("Enter Value to ADD:-");
                int value = userEnter.nextInt();
                add(value);
                sort(head);
                printList();
                operations();
                break;
            case 2:
                System.out.println("Enter Value:-");
                int key = userEnter.nextInt();
                contains(head, key);
                operations();
            case 3:
                System.out.println("Enter Value to REMOVE:-");
                int removeValue = userEnter.nextInt();
                remove(removeValue);
                printList();
                operations();
            case 4:
                System.out.println("Enter INDEX Value to REMOVE:-");
                int removeValueIndex = userEnter.nextInt();
                remove(removeValueIndex);
                printList();
                operations();
            case 5:
                System.out.println("CAUTION ALL ENTRIES WOULD BE CLEARED");
                System.out.println("Enter Y to proceed :-");
                String c = userEnter.next();
                if( c == "Y" || c == "y"){
                    clear();
                    printList();
                    operations();
                }
                else
                    operations();

        }
    }
}

public class LinkList {

    public static void main(String[] args) {
        sortedset llObj = new sortedset();
        System.out.println("Sorted Set");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Number Of Elements:-");
        int numberOfElements = in.nextInt();
        int arr[] = new int[numberOfElements];
        for (int i = 0; i < numberOfElements; i++) {
            System.out.println("Enter Element " + i + " :-");
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < numberOfElements; i++) {
            llObj.add(arr[i]);
        }
        llObj.operations();

    }

}
