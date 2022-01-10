package com.example.charstack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class CharStackUsingJavaUnitTest {

    @Test
    public void whenCharStackIsCreated_thenItHasSize0() {
//جاوا یک API داخلی به نام java.util.Stack دارد. از آنجایی که char یک نوع داده اولیه است که نمی‌توان از آن در ژنریک استفاده کرد، باید از کلاس wrapper java.lang.Character برای ایجاد یک پشته استفاده کنیم:
        Stack<Character> charStack = new Stack<>();

        assertEquals(0, charStack.size());
    }

    @Test
    public void givenEmptyCharStack_whenElementIsPushed_thenStackSizeisIncreased() {

        Stack<Character> charStack = new Stack<>();

        charStack.push('A');

        assertEquals(1, charStack.size());
    }

    @Test
    public void givenCharStack_whenElementIsPoppedFromStack_thenElementIsRemovedAndSizeChanges() {

        Stack<Character> charStack = new Stack<>();
        charStack.push('A');

        char element = charStack.pop();

        assertEquals('A', element);
        assertEquals(0, charStack.size());
    }

    @Test
    public void givenCharStack_whenElementIsPeeked_thenElementIsNotRemovedAndSizeDoesNotChange() {

        Stack<Character> charStack = new Stack<>();
        charStack.push('A');
//        فقط مقدار را برمیگرداند و پاک نمیکند
        char element = charStack.peek();

        assertEquals('A', element);
        assertEquals(1, charStack.size());
    }

}
