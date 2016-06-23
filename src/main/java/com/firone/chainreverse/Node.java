package com.firone.chainreverse;

public class Node<T> {

    private T value;
    private Node<T> nextNode;

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<T> setAndGetNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
        return nextNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
