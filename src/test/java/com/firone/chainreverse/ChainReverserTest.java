package com.firone.chainreverse;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class ChainReverserTest {

    private ChainReverser chainReverser = new ChainReverser();

    @Test
    public void can_reverse_chain_with_1_node() throws Exception {

        Node<String> firstNode = givenChainContaining("uniqueItem");

        Node<String> reversedChain = chainReverser.reverseChain(firstNode);

        assertThat(reversedChain.getValue(), is("uniqueItem"));
    }

    @Test
    public void can_reverse_chain_with_2_nodes() throws Exception {

        Node<String> firstNode = givenChainContaining("one", "two");

        Node<String> reversedChain = chainReverser.reverseChain(firstNode);

        assertThat(reversedChain, containsValues("two", "one"));
    }

    @Test
    public void can_reverse_chain_with_3_nodes() throws Exception {

        Node<String> firstNode = givenChainContaining("one", "two", "three");

        Node<String> reversedChain = chainReverser.reverseChain(firstNode);

        assertThat(reversedChain, containsValues("three", "two", "one"));
    }

    @Test
    public void can_reverse_chain_with_5_nodes() throws Exception {

        Node<String> firstNode = givenChainContaining("one", "two", "three", "four", "five");

        Node<String> reversedChain = chainReverser.reverseChain(firstNode);

        assertThat(reversedChain, containsValues("five", "four", "three", "two", "one"));
    }

    @Test
    public void when_first_node_is_null__return_null() throws Exception {

        Node<String> reversedChain = chainReverser.reverseChain(null);

        assertThat(reversedChain, is(nullValue()));
    }

    @SafeVarargs
    private final <T> TypeSafeMatcher<Node<T>> containsValues(T... wantedItems) {

        return new TypeSafeMatcher<Node<T>>() {
            @Override
            protected boolean matchesSafely(Node<T> currentGivenNode) {

                for (T item : wantedItems) {

                    T value = currentGivenNode.getValue();

                    if (!item.equals(value))
                        return false;

                    currentGivenNode = currentGivenNode.getNextNode();
                }

                return currentGivenNode == null;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("contains this items in the same order : ").appendValueList("[", ",", "]", wantedItems);
            }
        };
    }

    @SafeVarargs
    private final <T> Node<T> givenChainContaining(T... items) {

        Node<T> currentNode = null;
        Node<T> firstNode = null;

        for (T item : items) {
            if (currentNode == null)
                firstNode = currentNode = new Node<>(item);
            else
                currentNode = currentNode.setAndGetNextNode(new Node<>(item));
        }
        return firstNode;
    }
}
