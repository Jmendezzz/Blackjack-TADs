package datastructures;

import domain.model.Card;

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
    public T peek() {
        if (top == null) {
            return null;
        }
        return top.getData();
    }
    public Node<T> getTop() {
        return top;
    }
    public int getTotalValue() {
        int total = 0;
        Node<T> current = top;
        while (current != null) {
            Card card = (Card) current.getData();
            if (card.getName().equalsIgnoreCase("A")){
                if (total + 11 > 21) {
                    total += 1;
                } else {
                    total += 11;
                }
            } else {
                total += card.getValue();
            }
            total += card.getValue();
            current = current.getNext();
        }
        return total;
    }
    public int getSize(){
        int size = 0;
        Node<T> current = top;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }
    public void clear() {
        top = null;
    }
}
