package com.example.exceptions.nosuchfielderror;

import com.example.exceptions.noSuchFiledError.FieldErrorExample;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FieldErrorExampleUnitTest {
    
    @Test
    public void whenDependentMessage_returnMessage() {
        
        String dependentMessage = FieldErrorExample.getDependentMessage();
        assertTrue("Hello Baeldung!!".equals(dependentMessage));
    }
    
}