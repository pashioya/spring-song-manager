package com.kdg.springprojt5.controllers.mvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedList;

import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc()
public class IndexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        // Mock the authentication
        when(authentication.getName()).thenReturn("testuser");
    }

    @Test
    void indexPageShouldLoad() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .sessionAttr("history", new LinkedList<>())
                        .principal(authentication))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    void loginPageShouldLoad() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login")
                        .sessionAttr("history", new LinkedList<>())
                        .principal(authentication))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }
}
