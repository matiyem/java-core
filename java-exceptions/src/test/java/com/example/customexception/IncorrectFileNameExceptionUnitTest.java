package com.example.customexception;

import com.example.customException.FileManager;
import com.example.customException.IncorrectFileNameException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IncorrectFileNameExceptionUnitTest {

    @Test(expected = IncorrectFileNameException.class)
    public void testWhenIncorrectFileNameExceptionThrown_ReceivesIncorrectFileNameException() throws IncorrectFileNameException {
        FileManager.getFirstLine("wrongFileName.txt");
    }

    @Test
    public void testWhenCorrectFileNameExceptionThrown_ReceivesNoException() throws IncorrectFileNameException {
        assertThat(FileManager.getFirstLine("correctFileName.txt")).isEqualTo("Default First Line");
    }

}
