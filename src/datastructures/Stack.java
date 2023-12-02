package datastructures;

public class Stack<T> {
    private Node<T> top;
    public void push(T data) {
        Node<T> node = new Node<>(data);
        node.setNext(top);
        top = node;
    }
    public T pop() {
        if (top == null) {
            return null;
        }
        T data = top.getData();
        top = top.getNext();
        return data;
    }
    public void delete() {
        top = null;
    }
}
