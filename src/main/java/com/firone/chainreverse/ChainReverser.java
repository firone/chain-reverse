package com.firone.chainreverse;

public class ChainReverser {

    public <T> Node<T> reverseChain(Node<T> firstNodeOfTheOriginalChain) {

        if (firstNodeOfTheOriginalChain == null) {
            return null;
        }

        Node<T> firstNodeOfTheNewChain = reverseOneNode(firstNodeOfTheOriginalChain, firstNodeOfTheOriginalChain);
        firstNodeOfTheOriginalChain.setNextNode(null);
        return firstNodeOfTheNewChain;
    }

    private <T> Node<T> reverseOneNode(Node<T> firstNodeOfTheReversedChain, Node<T> currentNode) {

        if (currentNode == null)
            return firstNodeOfTheReversedChain;

        Node<T> nextNode = currentNode.getNextNode();
        currentNode.setNextNode(firstNodeOfTheReversedChain);

        return reverseOneNode(currentNode, nextNode);
    }
}
