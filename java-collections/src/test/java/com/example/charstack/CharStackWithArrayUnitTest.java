package com.example.charstack;

import com.example.charStack.CharStackWithArray;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CharStackWithArrayUnitTest {

    @Test
    public void whenCharStackIsCreated_thenItHasSize0() {

        CharStackWithArray charStack = new CharStackWithArray();

        assertEquals(0, charStack.size());
    }

    @Test
    public void givenEmptyCharStack_whenElementIsPushed_thenStackSizeisIncreased() {

        CharStackWithArray charStack = new CharStackWithArray();

        charStack.push('A');

        assertEquals(1, charStack.size());
    }

    @Test
    public void givenEmptyCharStack_when5ElementIsPushed_thenStackSizeis() {

        CharStackWithArray charStack = new CharStackWithArray();

        charStack.push('A');
        charStack.push('B');
        charStack.push('C');
        charStack.push('D');
        charStack.push('E');

        assertEquals(5, charStack.size());
    }

    @Test
    public void givenCharStack_whenElementIsPoppedFromStack_thenElementIsRemovedAndSizeChanges() {

        CharStackWithArray charStack = new CharStackWithArray();
        charStack.push('A');
        //برمیگرداند و پاک میکند
        char element = charStack.pop();

        assertEquals('A', element);
        assertEquals(0, charStack.size());
    }

    @Test
    public void givenCharStack_whenElementIsPeeked_thenElementIsNotRemovedAndSizeDoesNotChange() {

        CharStackWithArray charStack = new CharStackWithArray();
        charStack.push('A');
        //برمیگرداند و پاک نمیکند
        char element = charStack.peek();

        assertEquals('A', element);
        assertEquals(1, charStack.size());
    }

}
