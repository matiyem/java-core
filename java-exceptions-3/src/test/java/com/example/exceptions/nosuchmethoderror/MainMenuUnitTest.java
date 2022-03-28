package com.example.exceptions.nosuchmethoderror;

import com.example.exceptions.noSuchmethodError.MainMenu;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

class MainMenuUnitTest {

    @Test
    void whenGetSpecials_thenNotNull() {
        assertNotNull(MainMenu.getSpecials());
    }
}
