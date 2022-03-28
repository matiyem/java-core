package com.example.exception.indexOutOfBounds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

class CopyListUsingAddAllMethodDemoUnitTest {

    @Test
    void whenPassValidArrayList_thenCopyListUsingAddAllMethod() {
        List<Integer> source = Arrays.asList(11, 22, 33);

        assertEquals(source, CopyListUsingAddAllMethodDemo.copyList(source));
    }
}