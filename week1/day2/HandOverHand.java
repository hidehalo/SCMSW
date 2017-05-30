import java.util.concurrent.locks.ReentrantLock;

public class HandOverHand {
  public static void main (String[] args) {
    Node head = new Node();
    Node tail = new Node();
    ConcurrentSortedList csl = new ConcurrentSortedList(head, tail);
    int end = 20;
    while ((end--) > 0) {
      csl.insert(end);
    }
    csl.print();
  }
};

class Node {
  int value;
  Node prev;
  Node next;
  ReentrantLock lock = new ReentrantLock();

  Node () {}

  Node (int value, Node prev,Node next) {
    this.value = value;
    this.prev = prev;
    this.next = next;
  }
};

class ConcurrentSortedList {
  private Node head;
  private Node tail;

  ConcurrentSortedList (Node head, Node tail) {
    this.head = head;
    this.tail = tail;
    this.head.next = tail;
    this.tail.prev = head;
  }

  public void insert (int value) {
    Node current = this.head;
    Node next = current.next;
    try {
      current.lock.lock();
      next.lock.lock();
      Node node = new Node(value, current, next);
      current.next = node;
      next.prev = node;
    } finally {
      next.lock.unlock();
      current.lock.unlock();
    }
  }

  public void print() {
    Node current = this.head;
    while (current != null) {
      System.out.println(current.value);
      current = current.next;
    }
  }
};
