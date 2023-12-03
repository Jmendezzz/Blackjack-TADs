package datastructures;

public class CircularLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = node;
            tail = node;
            node.setNext(head);
        } else {
            tail.setNext(node);
            tail = node;
            tail.setNext(head);
        }
        size++;
    }

    public void deleteAll() {
        head = null;
        tail = null;
        size = 0;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public int size() {
        return size;
    }
}
